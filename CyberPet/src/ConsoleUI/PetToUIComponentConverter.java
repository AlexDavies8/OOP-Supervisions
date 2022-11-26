package ConsoleUI;

import Core.IInteropProvider;
import Core.Pet;

public class PetToUIComponentConverter implements IInteropProvider<Pet, UIObject> {
    @Override
    public UIObject Create(Pet from) {
        UIObject uiObject = new UIObject();
        uiObject.AddComponent("animator", new Animator(uiObject));
        uiObject.AddComponent("spriteRenderer", new SpriteRenderer(uiObject));
        return uiObject;
    }
    
    @Override
    public void Update(Pet from, UIObject uiObject) {
       var animator = (Animator)uiObject.GetComponent("animator");
       animator.SetAnimation(GetHappinessAnimation(from));
    }
    
    private Animation GetHappinessAnimation(Pet pet) {
        if (pet.GetHappiness() < 0.33f) { // Sad
            return Resources.GetAnimation(GetAnimationName(pet, "idle_sad"));
        } else if (pet.GetHappiness() < 0.66f) { // Normal
            return Resources.GetAnimation(GetAnimationName(pet, "idle_normal"));
        } else { // Happy
            return Resources.GetAnimation(GetAnimationName(pet, "idle_happy"));
        }
    }
    
    private String GetAnimationName(Pet pet, String actionName) {
        return String.format("%s_%s", pet.Type, actionName);
    }
}
