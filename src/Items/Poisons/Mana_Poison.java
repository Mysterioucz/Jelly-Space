package Items.Poisons;

import Items.Base.Base_Poison;
import Items.Base.Type;
import javafx.scene.image.Image;

public class Mana_Poison extends Base_Poison {
    public Mana_Poison() {

        super(Type.MANA,50);
        image = new Image(ClassLoader.getSystemResource("img/potions/M_Poison.png").toString());
    }
}
