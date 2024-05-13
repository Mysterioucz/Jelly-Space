package gui;

import inputs.KeyboardInputs;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import entities.Player.Player;
import javafx.scene.paint.Color;
import gui.battle.BattlePane;
import javafx.util.Duration;
import main.Main;
import map.*;

public class MapPane extends StackPane {
    private static MapPane instance;
    private Thread gameLoop;
    private volatile boolean running = true;
    public static KeyboardInputs keyHandler = new KeyboardInputs();
    private Boolean Battle = false;
    private Canvas canvas;
    private GraphicsContext gc;
    private Player player;
    private static GameMap gameMap;

    public MapPane() {
        init();
        createGameLoop();
        instance = this;

    }
    private void update() {
        // update player postion and game state
        player.update();
        //TODO implement battle update

    }
    private void draw() {
        // render player and game state
        gc.clearRect(0, 0, getPrefWidth(), getPrefHeight());
        player.draw(gc);
        gameMap.draw(gc);
        gameMap.drawBoundary(gc); // for debugging map boundaries

    }
    public void handleCollideWithRocket(){
        // When the player collides with the rocket, fade out the current scene and change the root of the scene to the new MapSelectPane
        setGameLoopState(false); // Stop the game loop
        RocketPane rocketPane = new RocketPane();
        getChildren().addLast(rocketPane);
        rocketPane.setAlignment(Pos.CENTER);

    }
    public void handleCollideWithBoss(){
        // Stop the game loop
        setGameLoopState(false);
        // Create a new scene with a white background
        BattlePane battlePane = new BattlePane();
        // Create a FadeTransition for the old scene
        FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), battlePane);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        // Set an action to be performed when the fade out transition finishes
        fadeOut.setOnFinished(e -> {
            // Create a FadeTransition for the new scene
            FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), battlePane);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            // Start the fade in transition
            fadeIn.play();
            battlePane.requestFocus();
            getChildren().addLast(battlePane);
        });
        // Start the fade out transition
        fadeOut.play();
    }

    private void init(){
        // initialize game state
        setPrefWidth(1280);
        setPrefHeight(720);
        // Set Background Image
        Image img = new Image(ClassLoader.getSystemResource("img/background/map/" + MapSelectPane.mapName + ".png").toString());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        this.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize)));

        // Set Keyboard Event
        keyHandler = new KeyboardInputs();
        setOnKeyPressed(keyHandler);
        setOnKeyReleased(keyHandler);

        // Set Player
        setPlayer(Player.getPlayer());
        //Create Canvas & GraphicContext
        canvas = new Canvas();
        canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());
        setGc(canvas.getGraphicsContext2D());
        getChildren().add(canvas);
        generateGameMap();
    }
    public static void generateGameMap(){
        switch (MapSelectPane.mapName){
            case "earth":
                gameMap = new MapEarth();
                break;
            case "planet1":
                gameMap = new MapPlanet1();
                break;
            case "planet2":
                gameMap = new MapPlanet2();
                break;
            case "planet3":
                gameMap = new MapPlanet3();
                break;
            case "blackhole":
                gameMap = new MapBlackHole();
                break;
        }
    }
    public void createGameLoop(){
        gameLoop = new Thread(() -> {
            while (running) {
                Platform.runLater(() -> {
                    System.out.println("GameLoop Running");
                    update();
                    draw();
                });
//                 For Fix FPS
                    try {
                        Thread.sleep(1000 / 60); // Sleep for approximately 60 FPS
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        });
        gameLoop.start();
    }


    public Boolean getBattle() {
        return Battle;
    }
    public void setBattle(Boolean battle) {
        Battle = battle;
    }
    public Canvas getCanvas() {
        return canvas;
    }
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
    public GraphicsContext getGc() {
        return gc;
    }
    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public static GameMap getGameMap() {
        return gameMap;
    }
    public static MapPane getInstance() {
        return instance;
    }

    public void setGameLoopState(boolean b ) {
        running = b;
    }
}
