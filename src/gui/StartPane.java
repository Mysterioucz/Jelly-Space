package gui;

import inputs.KeyboardInputs;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class StartPane extends BorderPane {
    private int BTN_WIDTH = 250;
    private int BTN_HEIGHT = 75;


    public StartPane() {
        setPrefWidth(1280);
        setPrefHeight(720);
        // initialize title text
        initializeTitle();
        //  initialize buttons
        initilizeStartBtn();
        initilizeExitBtn();
        //  Set Background Image
        Image img = new Image(ClassLoader.getSystemResource("img/background/StartPaneBG.png").toString());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        this.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, backgroundSize)));
        // add keyEvent
        this.setOnKeyPressed(new KeyboardInputs());

    }
    public void initializeTitle(){
        //create Text & set Position
        Text title = new Text("Jelly Space");
        title.setFont(Font.font("VCR OSD Mono", 100));
        title.setFill(Color.WHITE);
        title.setStrokeWidth(2);
        title.setStroke(Color.CYAN);
        title.setTranslateY(100);
        title.setTextAlignment(TextAlignment.CENTER);
        //create Text Container
        HBox textContainer = new HBox(title);
        textContainer.setAlignment(Pos.CENTER);
        // set Position of Text Container
        setTop(textContainer);
    }
    public void initilizeStartBtn(){
        Button button = new Button("Start");
        button.getStyleClass().add("start-pane-button");// add class to Button
        button.setPrefSize(BTN_WIDTH, BTN_HEIGHT);// set button size
        button.setOnMouseClicked(e -> {
            Scene scene = new Scene(new MapSelectPane());
            scene.getStylesheets().add(ClassLoader.getSystemResource("style.css").toExternalForm());
            this.getScene().setRoot(new StackPane(new Pane(), scene.getRoot()));
            scene.getRoot().requestFocus();
        });
        setCenter(button);  // set position of button
    }
    public void initilizeExitBtn(){
        Button button = new Button("Exit");
        button.getStyleClass().add("start-pane-button");// add class to Button
        button.setPrefSize(BTN_WIDTH, BTN_HEIGHT);// set button size
        button.setTranslateY(-20);
        button.setTranslateX(20);
        button.setOnMouseClicked(e -> {
            System.exit(0);
        });
        setBottom(button);  // set position of button
    }


}