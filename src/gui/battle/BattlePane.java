package gui.battle;

import javafx.application.Platform;
import gui.MapSelectPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


public class BattlePane extends GridPane {
    protected static BattlePane instance;
    protected Thread battleLoop;
    protected volatile boolean running = true;
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
            while (running) {
                // Update the battlefield
                Platform.runLater(() -> {
                    update();
                });
                draw();
                if (turn) {
                    // Player's turn
                    actionPane.setDisable(false);
                } else {
                    // Monster's turn
                    actionPane.setDisable(true);
                    System.out.println("Monster's turn");
                    setPlayerTurn(true);
                }
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

    public void endBattle(){
        running = false;
    }

    public void draw(){
        // Draw the battlefield
        battleFieldPane.draw();
    }
    public void update(){
        // Update the battlefield
        battleFieldPane.update();

    }
    public void setPlayerTurn(Boolean turn) {
        this.turn = turn;
    }

    public static BattlePane getInstance(){
        return instance;
    }



}
