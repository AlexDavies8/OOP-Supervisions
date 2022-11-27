package ConsoleUI;

import Core.Pet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShopRenderer extends ScreenRenderer {
    private final List<Pet> _pets;
    
    public ShopRenderer(List<Pet> pets) {
        _pets = pets.stream().sorted(Comparator.comparingInt(x -> x.Cost)).collect(Collectors.toList());
        
        _selectionFlow = new SelectionFlow(2, 10);
        for (int i = 0; i < 10; i++) {
            _selectionFlow.UpdateSelectionMap(0, i, "back");
        }
        
        for (var pet : pets) {
            _selectionFlow.RegisterCallback(id -> {
                if (id.equals(pet.ID)) _callbacks.get("buy").accept(pet.ID);
            });
        }
    }
    
    @Override
    public void Render(char[][] buffer, int x, int y) {
        RenderHelper.DrawButton(buffer, "Back", _selectionFlow.IsSelected("back"), 2, 1, 8, 3);
        UpdateSelectionFlow();
        int i = 0;
        for (var pet : _pets) {
            if (!pet.IsOwned()) {
                var petText = String.format("%-10s %03d Ð", pet.Name, pet.Cost);
                RenderHelper.DrawButton(buffer, petText, _selectionFlow.IsSelected(pet.ID), (120-petText.length()-2)/2, i*3+2, petText.length()+6, 3);
                i++;
            }
        }
    }
    
    void UpdateSelectionFlow() {
        int i = 0;
        for (var pet : _pets) {
            if (!pet.IsOwned()) {
                for (int j = i; j < 10; j++) {
                    _selectionFlow.UpdateSelectionMap(1, j, pet.ID);
                }
                i++;
            }
        }
    }
}
