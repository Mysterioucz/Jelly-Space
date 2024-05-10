package Items.Poisons;

import Items.Base.Splashable;
import entities.Monster.Base_Monster;

public class Stunt_Gadget implements Splashable {
    @Override
    public void splash(Base_Monster monster) {
        monster.setDmg(0);
        monster.setDef(0);
    }
}
