package gui.battle;

import entities.Monster.Base_Monster;
import entities.Player.Player;
import javafx.geometry.HPos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MonsterPane extends GridPane {
    protected static MonsterPane instance;;

    public MonsterPane() {
        super();
        init();
        // Set instance to this
        instance = this;
//        setGridLinesVisible(true); // for debugging

    }
    public void init(){
        setPrefSize(400,140);
        setMaxSize(400,140);

        // Create column Constraints
        for (int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(33); // set the column width to 30% of the total width
            getColumnConstraints().add(column); // Add the constraint to the column
        }
        // Set Background color
        Background bg = new Background(new BackgroundFill(Color.BLACK, new CornerRadii(15), null));
        setBackground(bg);
        setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(5))));
        // Create Monster Image
        for(int i =0 ; i<Player.getMy_monster().getMonsters().size() ; i++){
            Base_Monster monster = Player.getMy_monster().getMonsters().get(i);
            // Done Change Image to dead image if monster is dead
            // Done Load AllyImage instead of EnemyImage
            ImageView monsterImage;
            if(monster.isDead()){
                monsterImage = new ImageView(monster.getDead_img());
            }else{
                monsterImage = new ImageView(monster.getIdle_ally_img());
            }
            monsterImage.setOnMouseEntered(e -> handleHover(monster));
            monsterImage.setOnMouseClicked(e -> handleOnClick(monster));
            add(monsterImage,i,0);
            setHalignment(monsterImage, HPos.CENTER);
        }
    }
    public void update(){
        for(int i =0 ; i<Player.getMy_monster().getMonsters().size() ; i++){
            Base_Monster monster = Player.getMy_monster().getMonsters().get(i);
            ImageView monsterImage;
            if(monster.isDead()){
                monsterImage = new ImageView(monster.getDead_img());
            }else{
                monsterImage = new ImageView(monster.getIdle_ally_img());
            }
            monsterImage.setOnMouseEntered(e -> handleHover(monster));
            monsterImage.setOnMouseClicked(e -> handleOnClick(monster));
            if((((ImageView) getChildren().get(i)).getImage()).getUrl().equals(monsterImage.getImage().getUrl())){
                // if equal do nothing
            }else{
                // if not equal update the image
                add(monsterImage,i,0);
                setHalignment(monsterImage, HPos.CENTER);
                System.out.println("Monster Image Updated" + monster.getName());
            }


        }
    }

    public void handleHover(Base_Monster monster) {
        // Done make Monster detail in ActionPane Change to current monster detail that mouse hovering
//        System.out.println("handleMonsterHover running"); // for debugging
        MonsterDetail monsterDetail = new MonsterDetail(monster.getName(), String.valueOf(monster.getDmg()), String.valueOf(monster.getDef()), String.valueOf(monster.getHp()), String.valueOf(monster.getMana()));
        ActionPane.getInstance().setMonsterDetail(monsterDetail);
    }
    public void handleOnClick(Base_Monster monster){
        // Done clicked monster to be Player's active monster
        // Don't forget to change the monster detail in BattleFieldPane
        // Don't forget to check if the monster is already on BattleFieldPane or not
        System.out.println("Monster Clicked");
        if(Player.getActiveMonster().equals(monster)){
            System.out.println("Monster already active");
        }else{
            Player.setActiveMonster(monster);
        }
    }
    public static MonsterPane getInstance(){
        return instance;
    }
}
