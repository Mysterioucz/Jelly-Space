package gui.battle;

import entities.Monster.Base_Monster;
import entities.Player.Player;
import gui.MapPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BattleFieldPane extends StackPane {
    protected static BattleFieldPane instance;
    protected Base_Monster myMonster;
    protected Base_Monster enemyMonster;
    protected MonsterDetail myMonsterDetail;
    protected MonsterDetail enemyMonsterDetail;

    public BattleFieldPane() {
        // Done implement Player.getActiveMonster
        super();
        setPrefSize(1250,300);
        setMaxSize(1250,300);

        // Set Background color
        Background bg = new Background(new BackgroundFill(Color.BLACK, new CornerRadii(15), null));
        setBackground(bg);
        setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(5))));
        // Create Monster Detail
        setMyMonster(Player.getActiveMonster());
        setMyMonsterDetail(new MonsterDetail(myMonster.getName(), String.valueOf(myMonster.getDmg()), String.valueOf(myMonster.getDef()), String.valueOf(myMonster.getHp()), String.valueOf(myMonster.getMana()))); // TODO Fix this shit
        enemyMonster = (Base_Monster) MapPane.getGameMap().getBoss();
        enemyMonsterDetail = new MonsterDetail(enemyMonster.getName(), String.valueOf(enemyMonster.getDmg()), String.valueOf(enemyMonster.getDef()), String.valueOf(enemyMonster.getHp()), String.valueOf(enemyMonster.getMana()));
        instance = this;

    }
    public void handleBattle(String detail){
        Text battleLog = new Text(detail);
        battleLog.setFont(Font.font("VCR OSD Mono", 20));
        battleLog.setFill(Color.WHITE);
        getChildren().addLast(battleLog);
    }

    public void setMyMonster(Base_Monster myMonster) {
        this.myMonster = myMonster;
    }
    public void setMyMonsterDetail(MonsterDetail monsterDetail){
        myMonsterDetail = monsterDetail;
    }

    public static BattleFieldPane getInstance(){
        return instance;
    }

}
