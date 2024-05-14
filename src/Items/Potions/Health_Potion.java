package Items.Potions;

import Items.Base.Base_Poison;
import Items.Base.Base_Potion;
import Items.Base.Type;
import javafx.scene.image.Image;

public class Health_Potion extends Base_Potion {
    public Health_Potion() {

        super(Type.HEALTH,50);
        image = new Image(ClassLoader.getSystemResource("img/potions/H_Potion.png").toString());
        usedImage = new Image(ClassLoader.getSystemResource("img/potions/H_Potion_isUsed.png").toString());
    }
}
