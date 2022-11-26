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
}
