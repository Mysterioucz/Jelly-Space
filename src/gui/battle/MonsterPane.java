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
        setPrefSize(400,140);
        setMaxSize(400,140);
        // Set Background color
        Background bg = new Background(new BackgroundFill(Color.BLACK, new CornerRadii(15), null));
        setBackground(bg);
        setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(5))));
        // Create Monster Image
        for(int i =0 ; i<Player.getMy_monster().getMonsters().size() ; i++){
            Base_Monster monster = Player.getMy_monster().getMonsters().get(i);
            ImageView monsterImage = new ImageView(monster.getImage());
            monsterImage.setOnMouseEntered(e -> handleHover(monster));
            monsterImage.setOnMouseClicked(e -> handleOnClick(monster));
            add(monsterImage,i,0);
        }

        instance = this;
    }
    public void handleHover(Base_Monster monster) {
        // Done make Monster detail in ActionPane Change to current monster detail that mouse hovering
        System.out.println("handleMonsterHover running"); // for debugging
        MonsterDetail monsterDetail = new MonsterDetail(monster.getName(), String.valueOf(monster.getDmg()), String.valueOf(monster.getDef()), String.valueOf(monster.getHp()), String.valueOf(monster.getMana()));
        ActionPane.getInstance().setMonsterDetail(monsterDetail);
    }
    public void handleOnClick(Base_Monster monster){
        // Done clicked monster to be Player's active monster
        // Don't forget to change the monster detail in BattleFieldPane
        // Don't forget to check if the monster is already on BattleFieldPane or not
        if(Player.getActiveMonster().equals(monster)){
            System.out.println("Monster already active");
        }else{
            Player.setActiveMonster(monster);
            BattleFieldPane.getInstance().setMyMonster(monster);
            BattleFieldPane.getInstance().setMyMonsterDetail(new MonsterDetail(monster.getName(), String.valueOf(monster.getDmg()), String.valueOf(monster.getDef()), String.valueOf(monster.getHp()), String.valueOf(monster.getMana())));
        }
    }
    public static MonsterPane getInstance(){
        return instance;
    }
}
