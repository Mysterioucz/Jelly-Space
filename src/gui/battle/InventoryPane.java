package gui.battle;

import Items.Base.Base_Item;
import Items.Base.Base_Poison;
import Items.Base.Base_Potion;
import entities.Monster.Base_Monster;
import entities.Player.Player;
import gui.MapPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class InventoryPane extends GridPane {
    protected static InventoryPane instance;

    public InventoryPane() {
        super();
        setPrefSize(400,90);
        setMaxSize(400,90);
        // Set Background color
        Background bg = new Background(new BackgroundFill(Color.BLACK, new CornerRadii(15), null));
        setBackground(bg);
        setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(5))));
        // Load Inventory & Item image
        for(int i =0 ; i < Player.getInventory().getItems().size() ; i++){
            Base_Item item = Player.getInventory().getItems().get(i);
            ImageView itemImage = new ImageView(item.getImage());
            itemImage.setOnMouseEntered(e -> handleHover(item));
            itemImage.setOnMouseClicked(e -> handleItem(item));
            add(itemImage,i,0);
        }
        instance = this;
    }
    public void handleItem(Base_Item item){
        if(item instanceof Base_Potion){
            Base_Potion potion = (Base_Potion) item;
            potion.use(Player.getActiveMonster()); // Done: Use on active monster
        }else{
            Base_Poison poison = (Base_Poison) item;
            Base_Monster enemy = (Base_Monster) MapPane.getGameMap().getBoss();
            poison.use(enemy); // Done: Use on active monster
        }
    }
    public void handleHover(Base_Item item){

        // Done make Item detail in ActionPane Change to current item detail that mouse hovering
        // TODO Override toString method in Base_Item
        ActionPane.getInstance().setItemDetail(item.toString());

    }

    public static InventoryPane getInstance(){
        return instance;
    }

}
