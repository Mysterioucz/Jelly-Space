package gui.battle;

import gui.MapSelectPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class BattlePane extends GridPane {
    protected static BattlePane instance;
    protected Thread battleLoop;
    protected volatile boolean running = true;
    protected Boolean turn = true; // true for player, false for monster
    protected ActionPane actionPane;

    public BattlePane() {
        super();
        init();


    }
    public void init(){
        Background Bg = new Background(new BackgroundImage(
                new Image(ClassLoader.getSystemResource("img/background/battle/"+ MapSelectPane.mapName +".png").toString()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        ));
        setBackground(Bg);

    }

    public void startBattle(){
        battleLoop = new Thread(() -> {
            while (running) {
                if (turn) {
                    // Player's turn
                    actionPane.setDisable(false);
                } else {
                    // Monster's turn
                    actionPane.setDisable(true);
                }
            }
        });
        battleLoop.start();
    }

    public void endBattle(){
        running = false;
    }

    public static BattlePane getInstance(){
        return instance;
    }



}
