package ConsoleUI;

import java.util.HashMap;
import java.util.Map;

public class UIObject {
    private final Map<String, Component> _components = new HashMap<String, Component>();
    
    public void Render(char[][] buffer, int time) {
        for (var component : _components.values()) {
            component.Tick(buffer, time);
        }
    }
    
    public Component GetComponent(String name) {
        return _components.get(name);
    }
    
    public void AddComponent(String name, Component component) {
        _components.put(name, component);
    }
}
