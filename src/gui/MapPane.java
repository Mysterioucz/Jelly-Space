package gui;

import inputs.KeyboardInputs;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import entities.Player.Player;
import map.*;

public class MapPane extends Pane {
    private AnimationTimer gameLoop;
    public static KeyboardInputs keyHandler = new KeyboardInputs();
    private Boolean Battle = false;
    private Canvas canvas;
    private GraphicsContext gc;
    private static Player player;
    private static GameMap gameMap;

    public MapPane() {
        init();
        // Create GameLoop
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // TODO Auto-generated method stub
                update();
                draw();
            }
        };
        gameLoop.start();

    }
    private void update() {
        // TODO Auto-generated method stub
        // update player postion and game state
        player.update();

    }
    private void draw() {
        // TODO Auto-generated method stub
        // render player and game state
        gc.clearRect(0, 0, getPrefWidth(), getPrefHeight());
        player.draw(gc);
        gameMap.draw(gc);
        gameMap.drawBoundary(gc); // for debugging map boundaries

    }
    private void init(){
        // TODO Auto-generated method stub
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
    public static Player getPlayer() {
        return player;
    }
    public static GameMap getGameMap() {
        return gameMap;
    }
}
