package ConsoleUI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class SelectionFlow {
    private int _x;
    private int _y;
    
    private final String[][] _selectionMap;
    private final int _w;
    private final int _h;
    
    private final List<Consumer<String>> _callbacks = new ArrayList<Consumer<String>>();
    
    public SelectionFlow(int width, int height) {
        _selectionMap = new String[width][height];
        _w = width;
        _h = height;
    }
    
    public String GetSelected() {
        return _selectionMap[_x][_y];
    }
    public boolean IsSelected(String s) {
        return GetSelected().equals(s);
    }
    
    public void SelectRelative(int deltaX, int deltaY) {
        int x = _x;
        int y = _y;
        do {
            x = (x + _w + deltaX) % _w;
            y = (y + _h + deltaY) % _h;
        } while ((_selectionMap[x][y] == null || _selectionMap[x][y].equals(GetSelected())) && (x != _x || y != _y));
        _x = x;
        _y = y;
    }
    
    public void RegisterCallback(Consumer<String> callback) {
        _callbacks.add(callback);
    }
    
    public void SelectCurrent() {
        for (var callback : _callbacks) {
            callback.accept(GetSelected());
        }
    }
    
    public void UpdateSelectionMap(int x, int y, String id) {
        _selectionMap[x][y] = id;
    }
}
