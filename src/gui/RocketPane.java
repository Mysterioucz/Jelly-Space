package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Main;

public class RocketPane extends GridPane {
    private int BTN_WIDTH = 173;
    private int BTN_HEIGHT = 42;
    public RocketPane() {
        super();

        //Create Pane Title
        Text Title = new Text("Confirm to launch the rocket");
        Title.setFont(Font.font("VCR OSD Mono", 25));

        //Set Background Image
        setBackground(new Background(new BackgroundImage(
                new Image(ClassLoader.getSystemResource("img/background/rocket/RocketPaneBG.png").toString()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        )));
        //initialize buttons with actions
        Button confirmBtn = new Button("Yes");
        Button cancelBtn = new Button("No");
        Background btnIdle = new Background(new BackgroundImage(
                new Image(ClassLoader.getSystemResource("img/background/rocket/button.png").toString()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        ));
        Background btnHover = new Background(new BackgroundImage(
                new Image(ClassLoader.getSystemResource("img/background/rocket/button_onHover.png").toString()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        ));
        confirmBtn.setPrefWidth(BTN_WIDTH);
        confirmBtn.setPrefHeight(BTN_HEIGHT);
        cancelBtn.setPrefWidth(BTN_WIDTH);
        cancelBtn.setPrefHeight(BTN_HEIGHT);
        confirmBtn.setBackground(btnIdle);
        cancelBtn.setBackground(btnIdle);
        confirmBtn.setOnMouseEntered(e -> confirmBtn.setBackground(btnHover));
        confirmBtn.setOnMouseExited(e -> confirmBtn.setBackground(btnIdle));
        cancelBtn.setOnMouseEntered(e -> cancelBtn.setBackground(btnHover));
        cancelBtn.setOnMouseExited(e -> cancelBtn.setBackground(btnIdle));
        confirmBtn.setOnAction(e -> confirmLaunch());
        cancelBtn.setOnAction(e -> cancelLaunch());
        add(Title, 0, 0, 2, 1);
        add(confirmBtn, 0, 1);
        add(cancelBtn, 1, 1);
        setGridLinesVisible(true);
        setHalignment(Title, javafx.geometry.HPos.CENTER);
        setValignment(confirmBtn, javafx.geometry.VPos.CENTER);
        setValignment(cancelBtn, javafx.geometry.VPos.CENTER);
        setMargin(Title, new Insets(60, 0, 0, 40));
        setMargin(confirmBtn, new Insets(0, 0, 0, 40));
        setMargin(cancelBtn, new Insets(0, 0, 0, 40));
        setVgap(40);
    }
    public void confirmLaunch() {
        //TODO implement launch rocket
        Main.changeSceneStatic(new MapSelectPane(), true);
    }
    public void cancelLaunch() {
        //TODO implement cancel rocket
        MapPane.getInstance().setGameLoopState(true);
        MapPane.getInstance().getChildren().remove(this);
    }
}
