package Core;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Pet {
    public final String ID;
    public final String Name;
    
    public final int EarnRate;
    public final int Cost;
    public final int HappinessDrain;
    public final int FoodDrain;
    public final int SleepDrain;
    
    private float _happiness = 100;
    private float _food = 100;
    private float _sleep = 100;
    
    private boolean _owned = false;
    
    public int GetHappiness() {
        return (int)_happiness;
    }
    public int GetFood() {
        return (int)_food;
    }
    public int GetSleep() {
        return (int)_sleep;
    }

    public Pet(String id, String name, int earnRate, int cost, int happinessDrain, int foodDrain, int sleepDrain) {
        ID = id;
        Name = name;
        EarnRate = earnRate;
        Cost = cost;
        HappinessDrain = happinessDrain;
        FoodDrain = foodDrain;
        SleepDrain = sleepDrain;
    }

    public static Pet LoadFromFile(String path) {
        try {
            var lines = FileReader.ReadLines(path);
            return new Pet(
                    lines.get(0),
                    lines.get(1),
                    Integer.parseInt(lines.get(2)),
                    Integer.parseInt(lines.get(3)),
                    Integer.parseInt(lines.get(4)),
                    Integer.parseInt(lines.get(5)),
                    Integer.parseInt(lines.get(6))
            );
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s%n", path);
        } catch (IOException e) {
            System.out.printf("File couldn't be read: %s%n", path);
        }
        System.out.println("Pet couldn't be loaded");
        return new Pet("", "", 0, 0, 0, 0, 0);
    }

    public void Tick() {
        double drainIters = 6*Math.log(Math.pow((double)((_food + 50)/100)*(double)((_sleep+50)/100)+0.35, -0.5));
        for (int i = 0; i < 1 + Math.max(drainIters, 0); i++) {
            IncreaseHappiness(HappinessDrain * -0.01f);
        }
        IncreaseFood(FoodDrain * -0.01f);
        IncreaseSleep(SleepDrain * -0.01f);
    }
    
    public void Own() {
        _owned = true;
    }
    public boolean IsOwned() {
        return _owned;
    }

    public void IncreaseHappiness(float amount) {
        _happiness = Math.max(0f, Math.min(100f, _happiness + amount));
    }
    public void IncreaseFood(float amount) {
        _food = Math.max(0f, Math.min(100f, _food + amount));
    }
    public void IncreaseSleep(float amount) {
        _sleep = Math.max(0f, Math.min(100f, _sleep + amount));
    }
    
    public int Earn() {
        return (int)Math.ceil(EarnRate * ((double)GetHappiness()/100));
    }
}
