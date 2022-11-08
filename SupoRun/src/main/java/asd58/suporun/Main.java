package asd58.suporun;

public class Main {
    public static void main(String[] args) {
        Runner.Run(() -> {});
        Runner.LogValue("Integer", 0);
        Runner.LogArray("Array", new int[0]);
    }
}
