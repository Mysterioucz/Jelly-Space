package gui;

import inputs.KeyboardInputs;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import entities.Player.Player;

public class MapPane extends Pane {
    private AnimationTimer gameLoop;
    public static KeyboardInputs keyHandler = new KeyboardInputs();
    private Boolean Battle = false;
    private Canvas canvas;
    private GraphicsContext gc;
    private Player player;

    public MapPane() {
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
        setCanvas(new Canvas(getPrefWidth(), getPrefHeight()));
        setGc(canvas.getGraphicsContext2D());
        getChildren().add(canvas);

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
}
