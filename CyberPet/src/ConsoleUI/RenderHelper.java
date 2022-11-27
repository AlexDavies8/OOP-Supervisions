package ConsoleUI;

public class RenderHelper {
    public static void ClearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void MoveCursorTo(int x, int y) {
        System.out.printf("%c[%d;%df",0x1B,y,x);
    }

    public static void WriteStringToBuffer(char[][] buffer, String s, int x, int y) {
        int w = buffer.length;
        for (int i = x; i < Math.min(w, x + s.length()); i++) {
            buffer[i][y] = s.charAt(i - x);
        }
    }
    
    public static void ProgressBar(char[][] buffer, int v, int min, int max, int x, int y) {
        int width = max - min;
        for (int i = 0; i < width; i++) {
            if (i+min > v) buffer[x+i][y] = '_';
            else buffer[x+i][y] = '#';
        }
    }
    
    public static void FillArea(char[][] buffer, char c, int x, int y, int w, int h) {
        for (int fx = 0; fx < w; fx++) {
            for (int fy = 0; fy < h; fy++) {
                buffer[x+fx][y+fy] = c;
            }
        }
    }
    
    public static void DrawButton(char[][] buffer, String label, boolean selected, int x, int y, int width, int height) {
        char cornerChar = selected ? '.' : '+';
        if (selected) {
            for (int i = 0; i < width; i++) {
                buffer[x+i][y] = '-';
                buffer[x+i][y+height-1] = '-';
            }
            for (int i = 0; i < height; i++) {
                buffer[x][y+i] = '|';
                buffer[x+width-1][y+i] = '|';
            }
        }
        buffer[x][y] = cornerChar;
        buffer[x+width-1][y] = cornerChar;
        buffer[x+width-1][y+height-1] = cornerChar;
        buffer[x][y+height-1] = cornerChar;
        int lx = x + width / 2 - label.length() / 2;
        int ly = y + (height - 1) / 2;
        WriteStringToBuffer(buffer, label, lx, ly);
    }
    
    public static boolean InBounds(char[][] buffer, int x, int y) {
        int w = buffer.length;
        int h = buffer[0].length;
        return x >= 0 && y >= 0 && x < w && y < h;
    }
}
