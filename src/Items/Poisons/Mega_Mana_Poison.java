package Items.Poisons;

import Items.Base.Base_Poison;
import Items.Base.Type;
import javafx.scene.image.Image;

public class Mega_Mana_Poison extends Base_Poison {
    public Mega_Mana_Poison() {

        super(Type.MANA,100);
        image = new Image(ClassLoader.getSystemResource("img/potions/MM_Poison.png").toString());
        usedImage = new Image(ClassLoader.getSystemResource("img/potions/MM_Poison_isUsed.png").toString());
    }
}
