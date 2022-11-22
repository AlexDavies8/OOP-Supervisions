import java.io.Console;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Question9 {
    public static class GameManager {
        private GameSettings[] _difficultySettings = {
                new GameSettings(6, 1, 0, 20),
                new GameSettings(6, 2, 0, 100),
                new GameSettings(5, 3, 0, 100),
                new GameSettings(7, 4, 0, 1000)
        };
        private Scoreboard _scoreboard = new Scoreboard();

        public void MainMenu() {
            Console console = System.console();
            Boolean finished = false;
            while (!finished) {
                System.out.println("Select a difficulty (1-4), show (S)coreboard or (Q)uit:");
                String choiceStr = console.readLine();
                if (choiceStr.equalsIgnoreCase("Q")) {
                    finished = true;
                    continue;
                }
                if (choiceStr.equalsIgnoreCase("S")) {
                    _scoreboard.PrintScoreboard();
                    continue;
                }
                int choice = Integer.parseInt(choiceStr);
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid Difficulty!");
                    continue;
                }
                GameSettings settings = _difficultySettings[choice-1];
                System.out.printf("Guess a number between %d and %d\n", settings.Min, settings.Max);
                Game game = new Game(settings);
                game.RunGame();
                System.out.println("Please enter a name:");
                String name = console.readLine();
                _scoreboard.AddScore(name, game.GetScore(), settings);
                _scoreboard.PrintScoreboard();
            }
        }
    }

    public static class GameSettings {
        public final int MaxGuesses;
        public final int Difficulty;
        public final int Min;
        public final int Max;

        public GameSettings(int maxGuesses, int difficulty, int min, int max) {
            MaxGuesses = maxGuesses;
            Difficulty = difficulty;
            Min = min;
            Max = max;
        }
    }

    public static class Game {
        private final GameSettings _settings;
        private int _score;

        public Game(GameSettings settings) {
            _settings = settings;
        }

        public void RunGame() {
            Console console = System.console();
            Random rand = new Random();

            int target = rand.nextInt(_settings.Max - _settings.Min + 1) + _settings.Min;

            int guesses = -1;
            while (++guesses < _settings.MaxGuesses) {
                System.out.printf("%d guesses left:\n", _settings.MaxGuesses - guesses);
                int guess = Integer.parseInt(console.readLine());
                if (guess == target) {
                    _score = (_settings.MaxGuesses - guesses) * _settings.Difficulty;
                    System.out.printf("Correct! Your score is %d\n", _score);
                    return;
                } else if (guess < target) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
            }
            _score = 0;
            System.out.printf("You are out of guesses! The number was %d\n", target);
        }

        public int GetScore() {
            return _score;
        }
    }

    public static class Scoreboard {
        private final ArrayList<ScoreboardEntry> _scores = new ArrayList<ScoreboardEntry>();

        private ArrayList<ScoreboardEntry> GetTopScores(int count) {
            return (ArrayList<ScoreboardEntry>)_scores.stream().sorted((a, b) -> Integer.compare(b.Score, a.Score)).limit(count).collect(Collectors.toList());
        }

        public void AddScore(String name, int score, GameSettings settings) {
            _scores.add(new ScoreboardEntry(name, score, settings));
        }

        public void PrintScoreboard() {
            ArrayList<ScoreboardEntry> scoresToShow = GetTopScores(10);
            if (scoresToShow.isEmpty()) {
                System.out.println("Scoreboard is empty!");
            } else {
                scoresToShow.forEach(System.out::println);
            }
        }
    }

    private static class ScoreboardEntry {
        public final String Name;
        public final int Score;
        public final GameSettings Settings;

        public ScoreboardEntry(String name, int score, GameSettings settings) {
            Name = name;
            Score = score;
            Settings = settings;
        }

        @Override
        public String toString() {
            return String.format("%s | Score: %d, Difficulty: %d", Name, Score, Settings.Difficulty);
        }
    }
}
