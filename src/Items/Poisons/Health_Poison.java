package Items.Poisons;

import Items.Base.Base_Poison;
import Items.Base.Base_Potion;
import Items.Base.Type;
import javafx.scene.image.Image;

public class Health_Poison extends Base_Poison {

    public Health_Poison() {

        super(Type.HEALTH,50);
        image = new Image(ClassLoader.getSystemResource("img/potions/H_Poison.png").toString());
        usedImage = new Image(ClassLoader.getSystemResource("img/potions/H_Poison_isUsed.png").toString());
    }
}
