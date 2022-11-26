package ConsoleUI;

import Core.StateMachine;

public class Animator extends Component {
    private Animation _currentAnimation;
    
    public Animator(UIObject owner) {
        super(owner);
    }
    
    @Override
    public void Tick(char[][] buffer, int time) {
        var spriteRenderer = (SpriteRenderer)_uiObject.GetComponent("spriteRenderer");
        spriteRenderer.SetSprite(_currentAnimation.GetFrame(time));
    }

    public void SetAnimation(Animation animation) {
        _currentAnimation = animation;
    }
}
