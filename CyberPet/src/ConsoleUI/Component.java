package ConsoleUI;

public abstract class Component {
    protected final UIObject _uiObject;
    
    public Component(UIObject owner) {
        _uiObject = owner;
    }
    
    public void Tick(char[][] buffer, int time) {}
}
