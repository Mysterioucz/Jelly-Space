package Items.Poisons;

import Items.Base.Base_Poison;
import Items.Base.Type;
import javafx.scene.image.Image;

public class Mega_Health_Poison extends Base_Poison {
    public Mega_Health_Poison() {

        super(Type.HEALTH,100);
        image = new Image(ClassLoader.getSystemResource("img/potions/MH_Poison.png").toString());
    }
}
