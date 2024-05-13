package Items.Potions;

import Items.Base.Base_Potion;
import Items.Base.Type;
import javafx.scene.image.Image;

public class Mega_Health_Potion extends Base_Potion {
    public Mega_Health_Potion() {

        super(Type.HEALTH,100);
        image = new Image(ClassLoader.getSystemResource("img/potions/MH_Potion.png").toString());
    }
}
