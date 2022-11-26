package ConsoleUI;

import java.util.Dictionary;
import java.util.Hashtable;

public class Resources {
    private final static Dictionary<String, Sprite> _sprites = new Hashtable<String, Sprite>();
    private final static Dictionary<String, Animation> _animations = new Hashtable<String, Animation>();

    public static void LoadSprite(String name, String path) {
        _sprites.put(name, Sprite.LoadFromFile(path));
    }
    public static Sprite GetSprite(String name) {
        return _sprites.get(name);
    }

    public static void LoadAnimation(String name, String path) {
        _animations.put(name, Animation.LoadFromFile(path));
    }
    public static Animation GetAnimation(String name) {
        return _animations.get(name);
    }
}
