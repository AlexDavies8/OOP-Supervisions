package ConsoleUI;

import Core.IState;
import Core.Pet;
import Core.StateMachine;

public class PetRenderer {
    public final Pet Pet;
    private StateMachine _animator;
    private Animation _currentAnimation;
    private int _animationTime;

    public PetRenderer(Core.Pet pet) {
        Pet = pet;
        CreateAnimator();
    }
    
    private IState _playState;
    private IState _feedState;
    private IState _sleepState;
    
    private void CreateAnimator() {
        _animator = new StateMachine();
        
        var happyState = new AnimatorState(null, () -> SwitchAnimation("happy", false),null);
        var sadState = new AnimatorState(null, () -> SwitchAnimation("sad", false),null);
        _sleepState = new AnimatorState(() -> {
            Pet.IncreaseHappiness(0.4f);
            Pet.IncreaseFood(-0.2f);
            Pet.IncreaseSleep(1.6f);
        }, () -> SwitchAnimation("sleep", true),null);
        _feedState = new AnimatorState(() -> {
            Pet.IncreaseHappiness(0.5f);
            Pet.IncreaseFood(2f);
            Pet.IncreaseSleep(-0.1f);
        }, () -> SwitchAnimation("feed", true),null);
        _playState = new AnimatorState(() -> {
            Pet.IncreaseHappiness(1.6f);
            Pet.IncreaseFood(-0.2f);
            Pet.IncreaseSleep(-0.3f);
        }, () -> SwitchAnimation("play", true),null);
        
        _animator.AddTransition(happyState, sadState, () -> Pet.GetHappiness() < 30);
        _animator.AddTransition(sadState, happyState, () -> Pet.GetHappiness() >= 30);
        _animator.AddTransition(_sleepState, happyState, () -> _animationTime > _currentAnimation.GetLength());
        _animator.AddTransition(_feedState, happyState, () -> _animationTime > _currentAnimation.GetLength());
        _animator.AddTransition(_playState, happyState, () -> _animationTime > _currentAnimation.GetLength());
        
        _animator.SetState(happyState);
    }
    
    public void StartPlaying() {
        _animator.SetState(_playState);
    }
    public void StartFeeding() {
        _animator.SetState(_feedState);
    }
    public void StartSleeping() {
        _animator.SetState(_sleepState);
    }
    
    private String GetAnimationName(Pet pet, String action) {
        return String.format("%s_%s", pet.ID, action);
    }
    
    private void SwitchAnimation(String action, boolean resetTime) {
        if (resetTime) _animationTime = 0;
        _currentAnimation = Resources.GetAnimation(GetAnimationName(Pet, action));
    }
    
    public void Tick() {
        _animator.Tick();
        _animationTime++;
    }
    
    public void Render(char[][] buffer, int x, int y) {
        if (_currentAnimation == null) return;
        if (_currentAnimation.GetLength() == 0) return;
        _currentAnimation.GetFrame(_animationTime).RenderTo(buffer, x, y);
    }
}
