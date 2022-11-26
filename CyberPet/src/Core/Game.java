package Core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Pet> _pets;
    private IUI _ui;
    private GameState _gameState = GameState.PetInfo;
    private StateMachine _gameStateManager;
    private Boolean _running = true;

    public Game(IUI ui) {
        _ui = ui;
        _pets = new ArrayList<Pet>();

        Setup();
    }

    private void Setup() {
        // UI Setup
        _ui.Setup();

        // Game State Setup
        _gameStateManager = new StateMachine();
        var menu = new CommandState(() -> {

        });
        var petGallery = new CommandState(() -> {

        });
        var petInfo = new CommandState(() -> {

        });
        var shop = new CommandState(() -> {

        });
        _gameStateManager.AddAnyTransition(menu, () -> _gameState.equals(GameState.Menu));
        _gameStateManager.AddAnyTransition(petGallery, () -> _gameState.equals(GameState.PetGallery));
        _gameStateManager.AddAnyTransition(petInfo, () -> _gameState.equals(GameState.PetInfo));
        _gameStateManager.AddAnyTransition(shop, () -> _gameState.equals(GameState.Shop));
        
        // Pets Setup
        _pets.add(new Pet("ninja"));
    }

    public void Run() {
        _ui.InjectToEventLoop(this::Tick); // UI Provides the event loop
    }

    private Boolean Tick() {
        _gameStateManager.Tick();
        
        if (!_gameState.equals(GameState.Menu)) { // Simulate as long as not on main menu
            for (var pet : _pets) {
                _ui.DrawPet(pet);
            }
        }
        
        return _running;
    }

    private enum GameState {
        Menu,
        PetGallery,
        PetInfo,
        Shop
    }
}
