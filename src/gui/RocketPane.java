package gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
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
    private Button confirmBtn;
    private Button cancelBtn;
    private Text Title;
    private Image BgImage  = new Image(ClassLoader.getSystemResource("img/background/rocket/RocketPaneBG.png").toString());

    public RocketPane() {
        super();
        init();
//        setGridLinesVisible(true); // for debugging
    }
    public void init(){
        createTitle(); // Create Title
        initializeButtons();// Initialize buttons with actions

        // Create a ColumnConstraints object
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPrefWidth(BgImage.getWidth()/2);
        // Set the column constraints for each column in the GridPane
        for (int i = 0; i < 2; i++) {
            this.getColumnConstraints().add(columnConstraints);
        }

        //Set Background Image
        setBackground(new Background(new BackgroundImage(
                BgImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        )));

        //Add components to the pane
        add(Title, 0, 0, 2, 1);
        add(confirmBtn, 0, 1);
        add(cancelBtn, 1, 1);
        // Set margin and alignment
        setHalignment(Title, HPos.CENTER);
        setValignment(confirmBtn, VPos.CENTER);
        setValignment(cancelBtn, VPos.CENTER);
        setHalignment(confirmBtn, HPos.CENTER);
        setHalignment(cancelBtn, HPos.CENTER);
        setVgap(40);
    }

    public void createTitle() {
        Title = new Text("Confirm to launch the rocket");
        Title.setFont(Font.font("VCR OSD Mono", 25));
    }
    public void initializeButtons() {
        confirmBtn = new Button("Yes");
        cancelBtn = new Button("No");
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
    }
    public void confirmLaunch() {
        Main.changeSceneStatic(new MapSelectPane(), true);
    }
    public void cancelLaunch() {
        // Reset player position and resume game loop
//        MapPane.getGameMap().resetPlayerPosition();
        MapPane.getInstance().setGameLoopState(true);
        MapPane.getInstance().createGameLoop();
        // Remove the rocket pane and request focus to the map pane
        MapPane.getInstance().getChildren().remove(this);
        MapPane.getInstance().requestFocus();
    }
}
