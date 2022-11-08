package asd58.suporun;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

public class Runner {

    private static int actionCount = 0;

    public static void Run(Action action) {
        Run(null, action);
    }

    public static void Run(String name, Action action) {
        actionCount++;
        String nameText = name == null ? String.format("Code Block %d", actionCount) : String.format("\"%s\"", name);
        String runText = String.format("%n▶️%d Running %s", actionCount, nameText);
        System.out.println(runText);
        System.out.print("- Begin ");
        System.out.println("-".repeat(runText.length() - 8));
        try {
            action.Invoke();
        } catch (Throwable e) {
            System.out.printf("⚠️%s failed with the following error:%n", nameText);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.out.println(sw);
        }
        System.out.print("- End ");
        System.out.println("-".repeat(runText.length() - 6));
    }

    public static void LogValue(String name, Object value) {
        System.out.printf("%s: %s%n", name, value.toString());
    }

    public static void LogArray(String name, int[] arr) {
        System.out.printf("%s: %s%n", name, Arrays.toString(arr));
    }
    public static void LogArray(String name, float[] arr) {
        System.out.printf("%s: %s%n", name, Arrays.toString(arr));
    }
    public static void LogArray(String name, double[] arr) {
        System.out.printf("%s: %s%n", name, Arrays.toString(arr));
    }
    public static void LogArray(String name, char[] arr) {
        System.out.printf("%s: %s%n", name, Arrays.toString(arr));
    }
    public static void LogArray(String name, String[] arr) {
        System.out.printf("%s: %s%n", name, Arrays.toString(arr));
    }
    public static void LogArray(String name, byte[] arr) {
        System.out.printf("%s: %s%n", name, Arrays.toString(arr));
    }
    public static void LogArray(String name, short[] arr) {
        System.out.printf("%s: %s%n", name, Arrays.toString(arr));
    }
    public static void LogArray(String name, long[] arr) {
        System.out.printf("%s: %s%n", name, Arrays.toString(arr));
    }
    public static <T> void LogArray(String name, T[] arr) {
        System.out.printf("%s: %s%n", name, Arrays.toString(arr));
    }
}
