package ConsoleUI;

import Core.Game;

import java.io.File;

public class ResourceLoader {
    public static void LoadAllResources(Game game) {
        String[] petAnimations = {"happy", "sad", "sleep", "play", "feed"};
        var basePath = new File("resources\\consoleui\\").getAbsolutePath();
        for (var pet : game.GetLoadedPets()) {
            for (var anim : petAnimations) {
                Resources.LoadAnimation(
                        String.format("%s_%s", pet.ID, anim),
                        String.format("%s\\%s\\%s.txt", basePath, pet.ID, anim)
                );
            }
        }
        Resources.LoadSprite("title", String.format("%s\\title.txt", basePath));
    }
}
