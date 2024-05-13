package gui.battle;

import entities.Monster.Base_Monster;
import entities.Player.Player;
import gui.MapPane;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import gui.MapSelectPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;
import main.Main;

import java.util.concurrent.atomic.AtomicBoolean;


public class BattlePane extends GridPane {
    protected static BattlePane instance;
    protected Thread battleLoop;
    protected volatile boolean gameEnd = false;
    private AtomicBoolean isBossTurnStarted = new AtomicBoolean(false);
    protected Boolean turn = true; // true for player, false for monster
    protected ActionPane actionPane;
    protected BattleFieldPane battleFieldPane;
    protected InventoryPane inventoryPane;
    protected MonsterPane monsterPane;
    private int cellWidth = 250;
    private int cellHeight = 128;

    public BattlePane() {
        super();
        init();
        startBattle();
//        setGridLinesVisible(true); // for debugging
    }
    public void init(){
        setPrefSize(1280,720);
        setPadding(new Insets(60,76,60,76));
        Background Bg = new Background(new BackgroundImage(
                new Image(ClassLoader.getSystemResource("img/background/battle/"+ MapSelectPane.mapName +".png").toString()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        ));
        setBackground(Bg);
        // Create column Constraints
        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPrefWidth(cellWidth);
            getColumnConstraints().add(column); // Add the constraint to the column
        }
        // Create row constraints
        for (int i = 0; i < 5; i++) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(cellHeight);
            getRowConstraints().add(row); // Add the constraint to the row
        }
        // Create Child Pane
        actionPane = new ActionPane();
        battleFieldPane = new BattleFieldPane();
        inventoryPane = new InventoryPane();
        monsterPane = new MonsterPane();
        // Create Inventory Container
        VBox inventoryContainer = new VBox();
        inventoryContainer.getChildren().addAll(monsterPane,inventoryPane);
        inventoryContainer.setSpacing(10);
        inventoryContainer.setMaxWidth(400);
        // Add Child Pane
        add(actionPane,0,0,3,2);
        add(battleFieldPane,0,2,5,3);
        add(inventoryContainer,3,0,2,2);
        // Set some childPane alignment
        setHalignment(inventoryContainer, HPos.RIGHT);

        instance = this;

    }

    public void startBattle(){
        battleLoop = new Thread(() -> {
            while (!gameEnd) {
                // Update the battlefield
                Platform.runLater(() -> {
                    update();
                    draw();
                });
                // For Fix FPS
                try {
                    Thread.sleep(1000 / 60); // Sleep for approximately 60 FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("BattleLoop running"); // for debugging
            }
        });
        battleLoop.start();
        checkGameState();
    }

    public void endBattle(Boolean win){
        // Done when battle end reset all stat of myMonster and show message that player got defeated for 5 second
        gameEnd = true;
        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(5));
        if(win){
            BattleFieldPane.getInstance().handleBattle("You win! returning to map in 5 seconds");
            MapPane.getGameMap().setIsCleared(true);
        }else{
            BattleFieldPane.getInstance().handleBattle("You got defeated try again next time");
        }
        pause.setOnFinished(e -> {
            // Reset Player's used point
            Player.setUsed_Point(3);
            // Reset Player's active monster
            Player.setActiveMonster(Player.getMy_monster().getMonsters().get(0));
            // Reset Player's turn
            setPlayerTurn(true);
            // Change scene to MapPane and reset GameLoop
            MapPane.getInstance().setGameLoopState(true);
            MapPane.getInstance().createGameLoop();
            // Create a FadeTransition for the old scene
            FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), this);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            // Set an action to be performed when the fade out transition finishes
            fadeOut.setOnFinished(e2 -> {
                // Start the fade in transition
                MapPane.getInstance().getChildren().remove(this);
            });
            // Start the fade out transition
            fadeOut.play();

            // Reset all monster's stat
            for (Base_Monster monster : Player.getMy_monster().getMonsters()){ // Reset stat for each monster
                monster.setHp(monster.getMaxHp());
                monster.setMana(monster.getMaxMana());
                monster.setDef(monster.getBaseDef());
                monster.setDmg(monster.getBaseDmg());
            }
        });
        pause.play();
    }

    public void draw(){
        // Draw the battlefield
        battleFieldPane.draw();
        actionPane.update();
    }
    public void update() {
        // Update the battlefield
        battleFieldPane.update();
        if (turn) {
            // Player's turn
            if(!battleFieldPane.getMyMonster().isDead()){
                actionPane.setDisable(false);
            }

        } else {
            // Boss turn
            actionPane.setDisable(true);
            startBossTurn();
        }


    }
    public void checkGameState(){
        Thread checkThread = new Thread(() -> {
            while (!gameEnd) {
                Platform.runLater(() -> {
                    if(Player.getMy_monster().getMonsters().get(0).isDead() && Player.getMy_monster().getMonsters().get(1).isDead() && Player.getMy_monster().getMonsters().get(2).isDead()){
                        endBattle(false);
                    }
                    if(BattleFieldPane.getInstance().getEnemyMonster().isDead()){
                        endBattle(true);
                        System.out.println("Boss Defeated");
                    }
                    if(Player.getUsed_Point() == 0 & turn){
                        setPlayerTurn(false);
                        System.out.println("Turn end");
                    }
                    System.out.println("CheckThread running"); // for debugging
                });

                try {
                    Thread.sleep(500); // Sleep for 0.5 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        checkThread.start();
    }
    public void startBossTurn(){
        if (isBossTurnStarted.get()) {
            return;
        }
        isBossTurnStarted.set(true);
        Thread bossThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                // Boss's turn
                // Boss use random ability (Attack, Guard, Unique Ability
                String bossLog = "Boss use " + Base_Monster.Choose_Boss_Ability(BattleFieldPane.getInstance().getEnemyMonster());
                BattleFieldPane.getInstance().handleBattle(bossLog);
                System.out.println("Boss's turn");
            });
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                // Boss's turn
                // Boss use random ability (Attack, Guard, Unique Ability
                setPlayerTurn(true);
            });
        });
        bossThread.start();
    }

    public void setPlayerTurn(Boolean turn) {
        if(turn){ // Player turn
            Player.setUsed_Point(3);
            // Restore mana and other stats
            for(Base_Monster monster: Player.getMy_monster().getMonsters()){
                monster.startTurn();
            }
            BattleFieldPane.getInstance().handleBattle("Your Turn");
            isBossTurnStarted.set(false);
        }else{ // Boss turn
            BattleFieldPane.getInstance().getEnemyMonster().startTurn();
            BattleFieldPane.getInstance().handleBattle("Boss Turn");
        }
        this.turn = turn;

    }

    public static BattlePane getInstance(){
        return instance;
    }

    public static void setInstance(BattlePane instance) {
        BattlePane.instance = instance;
    }

    public Thread getBattleLoop() {
        return battleLoop;
    }

    public void setBattleLoop(Thread battleLoop) {
        this.battleLoop = battleLoop;
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

    public Boolean getTurn() {
        return turn;
    }

    public void setTurn(Boolean turn) {
        this.turn = turn;
    }

    public ActionPane getActionPane() {
        return actionPane;
    }

    public void setActionPane(ActionPane actionPane) {
        this.actionPane = actionPane;
    }

    public BattleFieldPane getBattleFieldPane() {
        return battleFieldPane;
    }

    public void setBattleFieldPane(BattleFieldPane battleFieldPane) {
        this.battleFieldPane = battleFieldPane;
    }

    public InventoryPane getInventoryPane() {
        return inventoryPane;
    }

    public void setInventoryPane(InventoryPane inventoryPane) {
        this.inventoryPane = inventoryPane;
    }

    public MonsterPane getMonsterPane() {
        return monsterPane;
    }

    public void setMonsterPane(MonsterPane monsterPane) {
        this.monsterPane = monsterPane;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }
}
