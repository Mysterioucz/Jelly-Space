package gui;

import inputs.KeyboardInputs;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class StartPane extends BorderPane {
    public StartPane() {
        setPrefWidth(1280);
        setPrefHeight(720);

        //  create buttons
        Button startButton = new Button("Start");
        Button exitButton = new Button("Exit");
        // set button size
        startButton.setPrefSize(250, 75);
        exitButton.setPrefSize(125, 75);
        //set position of Each button  & add to pane
        setCenter(startButton);
        setBottom(exitButton);

        //  Set Background Image
        Image img = new Image(ClassLoader.getSystemResource("img/Background.png").toString());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        this.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, backgroundSize)));
        // add keyEvent
        this.setOnKeyPressed(new KeyboardInputs());

    }

}