package gui.battle;

import Items.Base.Base_Item;
import Items.Base.Base_Poison;
import Items.Base.Base_Potion;
import entities.Monster.Base_Monster;
import entities.Player.Player;
import gui.MapPane;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class InventoryPane extends GridPane {
    protected static InventoryPane instance;

    public InventoryPane() {
        super();
        init();

        // Set instance to this
        instance = this;
//        setGridLinesVisible(true); // for debugging
    }
    public void init(){
        setPrefSize(400,90);
        setMaxSize(400,90);
        // Create column Constraints
        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(20); // set the column width to 20% of the total width
            getColumnConstraints().add(column); // Add the constraint to the column
        }
        // Create Row Constraints
        RowConstraints row = new RowConstraints();
        row.setPrefHeight(90);
        getRowConstraints().add(row); // Add the constraint to the row
        // Set Background color
        Background bg = new Background(new BackgroundFill(Color.BLACK, new CornerRadii(15), null)); // Color.WHITE dor debug
        setBackground(bg);
        setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(5))));
        // Load Inventory & Item image
        for(int i =0 ; i < Player.getInventory().getItems().size() ; i++){
            Base_Item item = Player.getInventory().getItems().get(i);
            ImageView itemImage = new ImageView(item.getImage());
            itemImage.setFitHeight(64); // Set the image height
            itemImage.setFitWidth(64);// Set the image width
            itemImage.setOnMouseEntered(e -> handleHover(item));
            itemImage.setOnMouseClicked(e -> handleItem(item));
            add(itemImage,i,0);
            setValignment(itemImage, VPos.CENTER);
            setHalignment(itemImage, HPos.CENTER);
        }
    }

    public void handleItem(Base_Item item){
        Thread handleItemThread = new Thread(() -> {
            if(item instanceof Base_Potion){
                Base_Potion potion = (Base_Potion) item;
                potion.use(Player.getActiveMonster()); // Done: Use on active monster
            }else{
                Base_Poison poison = (Base_Poison) item;
                Base_Monster enemy = (Base_Monster) MapPane.getGameMap().getBoss();
                poison.use(enemy); // Done: Use on active monster
            }
        });
        handleItemThread.start();
    }
    public void handleHover(Base_Item item){

        // Done make Item detail in ActionPane Change to current item detail that mouse hovering
        // Done Override toString method in Base_Item
        ActionPane.getInstance().setItemDetail(item.toString());

    }

    public static InventoryPane getInstance(){
        return instance;
    }

}
