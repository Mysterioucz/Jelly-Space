package Items.Potions;

import Items.Base.Base_Potion;
import Items.Base.Type;
import javafx.scene.image.Image;

public class Mana_Potion extends Base_Potion {
    public Mana_Potion() {
        super(Type.MANA,50);
        image = new Image(ClassLoader.getSystemResource("img/potions/M" +
                "_Potion.png").toString());
    }
}
