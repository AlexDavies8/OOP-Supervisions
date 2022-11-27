package ConsoleUI;

import java.util.ArrayList;
import java.util.List;

public class GalleryRenderer extends ScreenRenderer {
    private final List<PetRenderer> _petRenderers;
    private final List<PetRenderer> _owned;

    public GalleryRenderer(List<PetRenderer> petRenderers) {
        _petRenderers = petRenderers;
        _owned = new ArrayList<PetRenderer>();
        
        _selectionFlow = new SelectionFlow(4, 2);
        _selectionFlow.UpdateSelectionMap(0, 0, "menu");
        _selectionFlow.UpdateSelectionMap(1, 0, "menu");
        _selectionFlow.UpdateSelectionMap(2, 0, "shop");
        _selectionFlow.UpdateSelectionMap(3, 0, "shop");
        
        for (var petRenderer : petRenderers) {
            _selectionFlow.RegisterCallback(id -> {
                if (id.equals(petRenderer.Pet.ID)) _callbacks.get("info").accept(petRenderer.Pet.ID);
            });
        }
    }

    @Override
    public void Render(char[][] buffer, int x, int y) {
        RenderHelper.DrawButton(buffer,"Menu", _selectionFlow.IsSelected("menu"), 51, 1, 8, 3);
        RenderHelper.DrawButton(buffer,"Shop", _selectionFlow.IsSelected("shop"), 61, 1, 8, 3);
        
        var owned = GetOwned();
        var left = (120 - (17 * owned.size() + 3 * (owned.size()-1))) / 2;
        for (int i = 0; i < owned.size(); i++) {
            var petX = left + i * 20;
            var petRenderer = owned.get(i);
            RenderHelper.DrawButton(buffer, "", _selectionFlow.IsSelected(petRenderer.Pet.ID), petX, 9, 17, 12);
            petRenderer.Render(buffer, petX+1, 10);
        }
    }
    
    private List<PetRenderer> GetOwned() {
        var owned = new ArrayList<PetRenderer>();
        int i = 0;
        for (PetRenderer petRenderer : _petRenderers) {
            if (petRenderer.Pet.IsOwned()) {
                owned.add(petRenderer);
                for (int j = i; j < 4; j++) {
                    _selectionFlow.UpdateSelectionMap(j, 1, petRenderer.Pet.ID);
                }
                i++;
            }
        }
        return owned;
    }
}
