package CyberPet;

import Core.Game;
import Core.IUI;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        new Game(GetConsoleUI()).Run(); // Run the game
    }

    private static IUI GetConsoleUI() {
        var ui = new ConsoleUI.ConsoleUI(120, 30);
        return ui;
    }

    private static IUI GetGraphicsUI() {
        throw new RuntimeException("Not Implemented");
    }
}