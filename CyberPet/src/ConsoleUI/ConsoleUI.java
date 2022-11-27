package ConsoleUI;

import Core.Game;
import Core.IUI;
import Core.Pet;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ConsoleUI implements IUI {
    
    
    private Game _game;
    private final int _screenWidth;
    private final int _screenHeight;
    private static final int FPS = 20;
    private final List<Consumer<Integer>> _injected = new ArrayList<Consumer<Integer>>();
    private Boolean _running;

    public ConsoleUI(int screenWidth, int screenHeight) {
        _screenWidth = screenWidth;
        _screenHeight = screenHeight;
    }

    @Override
    public void Setup(Game game) {
        _game = game;
        
        ResourceLoader.LoadAllResources(_game);
        
        var menuRenderer = new MenuRenderer();
        menuRenderer.RegisterCallback("quit", id -> Quit());
        menuRenderer.RegisterCallback("play", id -> SetGameState(Game.GameState.Gallery));
        
        var petRenderers = new ArrayList<PetRenderer>();
        for (var pet : _game.GetLoadedPets()) {
            var renderer = new PetRenderer(pet);
            petRenderers.add(renderer);
            RegisterTick(time -> renderer.Tick());
        }
        
        var galleryRenderer = new GalleryRenderer(petRenderers);
        galleryRenderer.RegisterCallback("menu", id -> SetGameState(Game.GameState.Menu));
        galleryRenderer.RegisterCallback("shop", id -> SetGameState(Game.GameState.Shop));
        
        var infoRenderer = new InfoRenderer();
        infoRenderer.RegisterCallback("back", id -> SetGameState(Game.GameState.Gallery));
        
        galleryRenderer.RegisterCallback("info", id -> {
            SetGameState(Game.GameState.Info);
            petRenderers
                    .stream()
                    .filter(x -> x.Pet.ID.equals(id))
                    .findAny()
                    .ifPresent(infoRenderer::SetPetRenderer);
        });
        
        var shopRenderer = new ShopRenderer(_game.GetLoadedPets());
        shopRenderer.RegisterCallback("back", id -> SetGameState(Game.GameState.Gallery));
        shopRenderer.RegisterCallback("buy", id -> {
            _game.BuyPet(_game.GetPetByID(id));
        });
        
        _screens.put(Game.GameState.Menu, menuRenderer);
        _screens.put(Game.GameState.Gallery, galleryRenderer);
        _screens.put(Game.GameState.Info, infoRenderer);
        _screens.put(Game.GameState.Shop, shopRenderer);

        SetGameState(_game.GetState()); // Update starting state
        
        _running = true;
        new Thread(this::UpdateLoop).start();
        new Thread(this::InputLoop).start();
    }
    
    private void UpdateLoop() {
        RenderHelper.ClearScreen();

        int time = 0;
        long frameTimeMillis = 1000 / FPS;
        while (_running) {
            long startTime = System.currentTimeMillis();
            
            for(var injected : _injected) {
                injected.accept(time);
            }
            Blit(time++);

            long endTime = System.currentTimeMillis();
            try {
                Thread.sleep(startTime - endTime + frameTimeMillis);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    @Override
    public int GetTicksPerSecond() {
        return FPS;
    }
    
    enum Keys {
        LeftArrow(57419),
        RightArrow(57421),
        UpArrow(57416),
        DownArrow(57424),
        Enter(13);

        private final int _code;

        Keys(int code) {
            _code = code;
        }

        public int GetKeycode() {
            return _code;
        }
        public static Keys GetKey(int code) {
            Keys[] keys = Keys.values();
            for (Keys key : keys) {
                if (key.GetKeycode() == code)
                    return key;
            }
            return null;
        }
    }
    private void InputLoop() {
        while (_running) {
            try {
                var key = Keys.GetKey(RawConsoleInput.read(false));
                if (key == null) continue;
                if (key.equals(Keys.LeftArrow)) {
                    _currentSelectionFlow.SelectRelative(-1, 0);
                }
                if (key.equals(Keys.RightArrow)) {
                    _currentSelectionFlow.SelectRelative(1, 0);
                }
                if (key.equals(Keys.UpArrow)) {
                    _currentSelectionFlow.SelectRelative(0, -1);
                }
                if (key.equals(Keys.DownArrow)) {
                    _currentSelectionFlow.SelectRelative(0, 1);
                }
                if (key.equals(Keys.Enter)) {
                    _currentSelectionFlow.SelectCurrent();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void RegisterTick(Consumer<Integer> action) {
        _injected.add(action);
    }

    @Override
    public void Quit() {
        _running = false;
    }

    private final Dictionary<Game.GameState, ScreenRenderer> _screens = new Hashtable<Game.GameState, ScreenRenderer>();
    private SelectionFlow _currentSelectionFlow;
    
    private void Blit(int time) {;
        var buffer = InitializeBuffer();
        
        // Render current screen
        _screens.get(_game.GetState()).Render(buffer, 0, 0);
        if (!_game.GetState().equals(Game.GameState.Menu)) RenderHelper.WriteStringToBuffer(buffer, String.format("Money: %03d Ð", _game.GetMoney()), 107, 1);
        
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < _screenHeight; y++) {
            for (int x = 0; x < _screenWidth; x++) {
                sb.append(buffer[x][y]);
            }
            sb.append('\n');
        }
        RenderHelper.MoveCursorTo(0, 0);
        System.out.print(sb);
        System.out.flush();
    }
    
    private char[][] InitializeBuffer() {
        char[][] buffer = new char[_screenWidth][_screenHeight];
        for (int y = 0; y < _screenHeight; y++) {
            for (int x = 0; x < _screenWidth; x++) {
                buffer[x][y] = ' ';
            }
        }
        return buffer;
    }
    
    public void SetGameState(Game.GameState state) {
        _game.SetState(state);
        _currentSelectionFlow = _screens.get(state).GetSelectionFlow();
    }
}
