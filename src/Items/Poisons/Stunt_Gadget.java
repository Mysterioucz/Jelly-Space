package Items.Poisons;

import Items.Base.Splashable;
import entities.Monster.Base_Monster;
import javafx.scene.image.Image;

public class Stunt_Gadget implements Splashable {
    private Image image;
    @Override
    public void splash(Base_Monster monster) {
        monster.setDmg(0);
        monster.setDef(0);
        image = new Image(ClassLoader.getSystemResource("img/potions/Stunt_Gadget.png").toString());
    }

    public Image getImage() {
        return image;
    }
}
