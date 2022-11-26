package ConsoleUI;

import Core.IUI;
import Core.Pet;

import java.util.*;

public class ConsoleUI implements IUI {

    private final int _screenWidth;
    private final int _screenHeight;
    private static final int FPS = 20;
    private final List<Runnable> _injected = new ArrayList<Runnable>();
    private Boolean _running;
    private Hashtable<Pet, UIObject> _petMap = new Hashtable<Pet, UIObject>();
    private final List<UIObject> _toDraw = new ArrayList<UIObject>();

    public ConsoleUI(int screenWidth, int screenHeight) {
        _screenWidth = screenWidth;
        _screenHeight = screenHeight;
    }

    @Override
    public void Setup() {
        _running = true;
        new Thread(this::UpdateLoop).start();
    }

    private void UpdateLoop() {
        RenderHelper.ClearScreen();

        int time = 0;
        long frameTimeMillis = 1000 / FPS;
        while (_running) {
            long startTime = System.currentTimeMillis();
            
            for(var injected : _injected) {
                injected.run();
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
    public void InjectToEventLoop(Runnable action) {
        _injected.add(action);
    }

    @Override
    public void Quit() {
        _running = false;
    }

    @Override
    public void DrawPet(Pet pet) {
        var converter = new PetToUIComponentConverter();
        if (!_petMap.containsKey(pet)) {
            var obj = converter.Create(pet);
            _petMap.put(pet, obj);
            _toDraw.add(obj);
        }
        converter.Update(pet, _petMap.get(pet));
    }

    private void Blit(int time) {;
        var buffer = InitializeBuffer();
        
        for (var obj : _toDraw) {
            obj.Render(buffer, time);
        }
        
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
}
