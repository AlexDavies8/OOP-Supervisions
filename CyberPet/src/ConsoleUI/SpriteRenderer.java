package ConsoleUI;

public class SpriteRenderer extends Component {
    private int _x = 0;
    private int _y = 0;
    private Sprite _sprite;

    public SpriteRenderer(UIObject owner) {
        super(owner);
    }

    @Override
    public void Tick(char[][] buffer, int time) {
        if (_sprite == null) return;
        _sprite.RenderTo(buffer, _x, _y);
    }
    
    public void SetSprite(Sprite sprite) {
        _sprite = sprite;
    }
    
    public void SetPosition(int x, int y) {
        _x = x;
        _y = y;
    }
}
