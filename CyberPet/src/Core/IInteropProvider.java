package Core;

public interface IInteropProvider<From, To> {
    To Create(From from);
    void Update(From from, To to);
}
