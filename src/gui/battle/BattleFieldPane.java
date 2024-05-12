package gui.battle;

import entities.Monster.Base_Monster;
import gui.MapPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
        // TODO implement Player.getActiveMonster
        super();
//        myMonster = Player.getActiveMonster;
        myMonsterDetail = new MonsterDetail(myMonster.getName(), String.valueOf(myMonster.getDmg()), String.valueOf(myMonster.getDef()), String.valueOf(myMonster.getHp()), String.valueOf(myMonster.getMana())); // TODO Fix this shit
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

    public static BattleFieldPane getInstance(){
        return instance;
    }

}
