package Items.Potions;

import Items.Base.Base_Potion;
import Items.Base.Type;
import javafx.scene.image.Image;

public class Mega_Mana_Potion extends Base_Potion {
    public Mega_Mana_Potion() {
        super(Type.MANA,100);
        image = new Image(ClassLoader.getSystemResource("img/potions/MM_Potion.png").toString());
    }
}
