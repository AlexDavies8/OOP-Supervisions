package Core;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public interface IUI {
    public void Setup(Game game);
    public void RegisterTick(Consumer<Integer> action);
    public void Quit();
    public int GetTicksPerSecond();
}
