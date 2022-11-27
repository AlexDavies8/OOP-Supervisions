package ConsoleUI;

import Core.FileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Animation {
    private final List<Frame> _frames;
    private int _length;
    
    public int GetLength() {
        return _length;
    }

    public Animation() {
        _frames = new ArrayList<Frame>();
        _length = 0;
    }

    public void AddSprite(Sprite sprite, int length) {
        _frames.add(new Frame(sprite, length));
        _length += length;
    }

    public Sprite GetFrame(int time) {
        int i = 0;
        Frame curr = _frames.get(i);
        if (curr == null) throw new RuntimeException("Can't have an animation with no frames!");
        int framesLeft = (time % _length) - curr.length;
        while (framesLeft >= 0) {
            curr = _frames.get(++i);
            framesLeft -= curr.length;
        }
        return curr.sprite;
    }

    private static class Frame {
        public final int length;
        public final Sprite sprite;

        private Frame(Sprite sprite, int length) {
            this.length = length;
            this.sprite = sprite;
        }
    }

    public static Animation LoadFromFile(String path) {
        try {
            var lines = FileReader.ReadLines(path);
            return LoadFromFile(lines);
        } catch (FileNotFoundException fnf) {
            System.out.printf("File could not be found at path: \"%s\"%n", path);
        } catch (IOException e) {
            System.out.printf("File at path: \"%s\" was found, but couldn't be read%n", path);
        }
        System.out.printf("Animation will be replaced by empty animation%n"); // Only reached on error
        return new Animation();
    }
    public static Animation LoadFromFile(List<String> lines) {
        try {
            Animation anim = new Animation();
            String[] args = lines.get(0).split("\\s+");
            int frameCount = Integer.parseInt(args[0]);
            int h = Integer.parseInt(args[1])+1;//+1 to account for frame-time line
            lines = lines.subList(1, lines.size());
            for (int i = 0; i < frameCount; i++) {
                int length = Integer.parseInt(lines.get(i*h));
                anim.AddSprite(Sprite.LoadFromFile(lines.subList(i*h+1, (i+1)*h)), length);
            }
            return anim;
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Couldn't load sprite data: invalid format%n");
        }
        return new Animation();
    }
}
