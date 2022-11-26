package Core;

import java.util.function.BooleanSupplier;

public interface IUI {
    public void Setup();
    public void InjectToEventLoop(Runnable action);
    public void Quit();
    
    public void DrawPet(Pet pet);
}
