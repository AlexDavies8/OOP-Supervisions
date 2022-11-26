package ConsoleUI;

import Core.FileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Sprite {
    private final char[][] _characters;

    public Sprite(char[][] characters) {
        _characters = characters;
    }

    public void RenderTo(char[][] buffer, int x, int y) {
        int bw = buffer.length;
        int bh = buffer[0].length;
        int cw = _characters.length;
        int ch = _characters[0].length;
        for (int cx = 0; cx < cw; cx++) {
            for (int cy = 0; cy < ch; cy++) {
                int bx = cx+x;
                int by = cy+y;
                if (0 <= bx && 0 <= by && bx < bw && by < bh) {
                    buffer[bx][by] = _characters[cx][cy];
                }
            }
        }
    }

    public static Sprite LoadFromFile(String path) {
        try {
            var lines = FileReader.ReadLines(path);
            return LoadFromFile(lines);
        } catch (FileNotFoundException fnf) {
            System.out.printf("File could not be found at path: \"%s\"%n", path);
        } catch (IOException e) {
            System.out.printf("File at path: \"%s\" was found, but couldn't be read%n", path);
        }
        System.out.printf("Sprite will be replaced by empty sprite%n"); // Only reached on error
        return new Sprite(new char[0][0]);
    }
    public static Sprite LoadFromFile(List<String> lines) {
        try {
            int w = lines.get(0).length();
            int h = lines.size();
            char[][] chars = new char[w][h];
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    chars[x][y] = lines.get(y).charAt(x);
                }
            }
            return new Sprite(chars);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Couldn't load sprite data: invalid format%n");
        }
        return new Sprite(new char[0][0]);
    }
}
