package ConsoleUI;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.Consumer;

public abstract class ScreenRenderer extends Renderer {
    protected SelectionFlow _selectionFlow;
    protected final Dictionary<String, Consumer<String>> _callbacks = new Hashtable<String, Consumer<String>>();
    
    public SelectionFlow GetSelectionFlow() {
        return _selectionFlow;
    }
    
    public void RegisterCallback(String id, Consumer<String> callback) {
        _callbacks.put(id, callback);
        _selectionFlow.RegisterCallback(x -> {
            if (id.equals(x)) callback.accept(x);
        });
    }
}
