package Core;

public class Pet {
    public final String Type;
    private float _food;
    private float _sleep;
    private float _happiness;
    private float _health;
    protected StateMachine _brain;

    public Pet(String type) {
        Type = type;
        _food = 1f;
        _sleep = 1f;
        _happiness = 1f;
        _health = 1f;
        _brain = new StateMachine();
    }

    public float GetFood() {
        return _food;
    }

    public float GetSleep() {
        return _sleep;
    }

    public float GetHappiness() {
        return _happiness;
    }

    public float GetHealth() {
        return _health;
    }

    public void Feed() {

    }

    public void Sleep() {

    }

    public void Play() {

    }

    public void Tick() {
        _brain.Tick();
    }
}
