package ConsoleUI;

import Core.Pet;

import java.util.ArrayList;
import java.util.List;

public class InfoRenderer extends ScreenRenderer {
    private PetRenderer _currentRenderer;

    public InfoRenderer() {
        _selectionFlow = new SelectionFlow(1, 4);
        RegisterCallback("play", id -> _currentRenderer.StartPlaying());
        RegisterCallback("feed", id -> _currentRenderer.StartFeeding());
        RegisterCallback("sleep", id -> _currentRenderer.StartSleeping());
        _selectionFlow.UpdateSelectionMap(0, 0, "back");
        _selectionFlow.UpdateSelectionMap(0, 1, "play");
        _selectionFlow.UpdateSelectionMap(0, 2, "feed");
        _selectionFlow.UpdateSelectionMap(0, 3, "sleep");
    }
    
    public void SetPetRenderer(PetRenderer renderer) {
        _currentRenderer = renderer;
    }
    
    @Override
    public void Render(char[][] buffer, int x, int y) {
        if (_currentRenderer == null) return;
        
        RenderHelper.DrawButton(buffer,"Back", _selectionFlow.IsSelected("back"), 56, 2, 8, 3);
        
        RenderHelper.DrawButton(buffer,"Play", _selectionFlow.IsSelected("play"), 40, 11, 10, 3);
        RenderHelper.DrawButton(buffer,"Feed", _selectionFlow.IsSelected("feed"), 40, 14, 10, 3);
        RenderHelper.DrawButton(buffer,"Sleep", _selectionFlow.IsSelected("sleep"), 40, 17, 10, 3);

        _currentRenderer.Render(buffer, 52, 10);
        
        var pet = _currentRenderer.Pet;
        RenderHelper.WriteStringToBuffer(buffer, pet.Name, 69, 10);
        
        RenderHelper.WriteStringToBuffer(buffer, "Happiness", 69, 12);
        RenderHelper.ProgressBar(buffer, pet.GetHappiness()/10, 0, 10, 69, 13);
        
        RenderHelper.WriteStringToBuffer(buffer, "Food", 69, 15);
        RenderHelper.ProgressBar(buffer, pet.GetFood()/10, 0, 10, 69, 16);
        
        RenderHelper.WriteStringToBuffer(buffer, "Sleep", 69, 18);
        RenderHelper.ProgressBar(buffer, pet.GetSleep()/10, 0, 10, 69, 19);
    }
}
