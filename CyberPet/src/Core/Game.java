package Core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final IUI _ui;
    private GameState _state = GameState.Menu;
    private final List<Pet> _loadedPets = new ArrayList<Pet>();
    private int _money = 0;
    
    public List<Pet> GetLoadedPets() {
        return _loadedPets;
    }
    
    public GameState GetState() {
        return _state;
    }
    public void SetState(GameState state) {
        _state = state;
    }
    public int GetMoney() {
        return _money;
    }
    public void AddMoney(int amount) {
        _money += amount;
    }
    
    public Game(IUI ui) {
        _ui = ui;
        
        File folder = new File("resources/pets");
        File[] petFiles = folder.listFiles();
        for (var file : petFiles) {
            if (file.isFile()) _loadedPets.add(Pet.LoadFromFile(file.getAbsolutePath()));
        }
        
        AddMoney(1705);
        
        _ui.Setup(this);
    }
    
    public void Run() {
        _ui.RegisterTick(this::Tick);
    }
    
    private void Tick(int time) {
        for (var pet : _loadedPets) {
            if (pet.IsOwned()) {
                pet.Tick();
                float ticksPerEarning =  _ui.GetTicksPerSecond() * 2f / pet.Earn();
                if (RepeatInterval(time, Math.max(1, (int)ticksPerEarning))) {
                    AddMoney(Math.max(1, (int)(1f / ticksPerEarning)));
                }
            }
        }
    }
    
    private boolean RepeatInterval(int time, int interval) {
        if (interval == 1) return true;
        return (time % interval) / (interval-1) == 1;
    }
    
    public enum GameState {
        Menu,
        Gallery,
        Info,
        Shop
    }
    
    public Pet GetPetByID(String id) {
        return _loadedPets.stream().filter(p -> p.ID.equals(id)).findAny().orElse(null);
    }
    
    public void BuyPet(Pet pet) {
        if (GetMoney() >= pet.Cost) {
            pet.Own();
            AddMoney(-pet.Cost);
        }
    }
}
