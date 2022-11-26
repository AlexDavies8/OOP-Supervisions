package CyberPet;

import Core.Game;
import Core.IUI;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        new Game(GetConsoleUI()).Run(); // Run the game
    }

    private static IUI GetConsoleUI() {
        var ui = new ConsoleUI.ConsoleUI(80, 20);
        var basePath = new File("").getAbsolutePath()+"\\resources\\";

        ConsoleUI.Resources.LoadAnimation("ninja_idle_happy", basePath + "ninja_idle_happy.txt");

        return ui;
    }

    private static IUI GetGraphicsUI() {
        throw new RuntimeException("Not Implemented");
    }
}