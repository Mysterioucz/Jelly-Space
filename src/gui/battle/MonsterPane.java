package gui.battle;

import entities.Monster.Base_Monster;
import entities.Player.Player;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MonsterPane extends GridPane {
    protected static MonsterPane instance;

    public MonsterPane() {
        super();
        // Set Background color
        Background bg = new Background(new BackgroundFill(Color.BLACK, new CornerRadii(15), null));
        setBackground(bg);
        setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(5))));
        // Create Monster Image
        for(int i =0 ; i<Player.getMy_monster().size() ; i++){
            Base_Monster monster = Player.getMy_monster().get(i);
            ImageView monsterImage = new ImageView(monster.getImage());
            monsterImage.setOnMouseEntered(e -> handleHover(monster));
            monsterImage.setOnMouseClicked(e -> handleOnClick());
            add(monsterImage,i,0);
        }

        instance = this;
    }
    public void handleHover(Base_Monster monster) {
        // TODO make Monster detail in ActionPane Change to current monster detail that mouse hovering
    }
    public void handleOnClick(){
        // TODO clicked monster to be Player's active monster
        // Don't forget to change the monster detail in BattleFieldPane
        // Don't forget to check if the monster is already on BattleFieldPane or not
    }
    public static MonsterPane getInstance(){
        return instance;
    }
}
