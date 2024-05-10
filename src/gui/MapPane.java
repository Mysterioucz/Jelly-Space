package gui;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class MapPane extends Pane {
    private AnimationTimer gameLoop;
    private String BGPath = "img/background/map/";


    public MapPane() {
        setPrefWidth(1280);
        setPrefHeight(720);
        // Set Background Image
        Image img = new Image(ClassLoader.getSystemResource(BGPath+MapSelectPane.mapName+".png").toString());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        this.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize)));

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // TODO Auto-generated method stub
                update(); // update game state
                draw(); // render game state
            }
        };
        gameLoop.start();

    }
    private void update() {
        // TODO Auto-generated method stub

    }
    private void draw() {
        // TODO Auto-generated method stub

    }
}
