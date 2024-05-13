package gui.battle;

import entities.Monster.Base_Monster;
import entities.Player.Player;
import gui.MapPane;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import gui.MapSelectPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import main.Main;


public class BattlePane extends GridPane {
    protected static BattlePane instance;
    protected Thread battleLoop;
    protected volatile boolean gameEnd = false;
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
                System.out.println("BattleLoop running");
            }
        });
        battleLoop.start();
    }

    public void endBattle(Boolean win){
        // TODO when battle end reset all stat of myMonster and show message that player got defeated for 5 second
        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(5));
        if(win){
            BattleFieldPane.getInstance().handleBattle("You win! returning to map in 5 seconds");
            MapPane.getGameMap().setIsCleared(true);
        }else{
            BattleFieldPane.getInstance().handleBattle("You got defeated try again next time");
        }
        gameEnd = true;
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
            MapPane.getInstance().getChildren().remove(this);
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
        if(Player.getMy_monster().getMonsters().get(0).isDead() && Player.getMy_monster().getMonsters().get(1).isDead() && Player.getMy_monster().getMonsters().get(2).isDead()){
            endBattle(false);
        }
        if(BattleFieldPane.getInstance().getEnemyMonster().isDead()){
            endBattle(true);
        }
        if(Player.getUsed_Point() <= 0){
            setPlayerTurn(false);
        }
        if (turn) {
            // Player's turn
            actionPane.setDisable(false);
        } else {
            // Boss turn
            actionPane.setDisable(true);
            System.out.println("Monster's turn");
            Thread bossThread = new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String bossLog = "Boss use " + Base_Monster.Choose_Boss_Ability(BattleFieldPane.getInstance().getEnemyMonster());
                BattleFieldPane.getInstance().getBattleLog().setText(bossLog);
                setPlayerTurn(true);
            });
        }
        // Update the battlefield
        battleFieldPane.update();

    }
    public void setPlayerTurn(Boolean turn) {
        if(turn){ // Player turn
            Player.setUsed_Point(3);
            // Restore mana and other stats
            for(Base_Monster monster: Player.getMy_monster().getMonsters()){
                monster.startTurn();
            }
            BattleFieldPane.getInstance().handleBattle("Your Turn");
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
