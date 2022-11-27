package ConsoleUI;

public class MenuRenderer extends ScreenRenderer {
    
    public MenuRenderer() {
        _selectionFlow = new SelectionFlow(1, 2);
        _selectionFlow.UpdateSelectionMap(0, 0, "play");
        _selectionFlow.UpdateSelectionMap(0, 1, "quit");
    }
    
    @Override
    public void Render(char[][] buffer, int x, int y) {
        
        Resources.GetSprite("title").RenderTo(buffer, 37, 3);
        
        RenderHelper.DrawButton(buffer,"Play", _selectionFlow.IsSelected("play"), 52, 10, 14, 5);
        RenderHelper.DrawButton(buffer,"Quit", _selectionFlow.IsSelected("quit"), 52, 16, 14, 5);
    }
}
