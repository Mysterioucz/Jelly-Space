package main;

import gui.RocketPane;
import gui.StartPane;
import gui.battle.ActionPane;
import gui.battle.MonsterPane;
import inputs.KeyboardInputs;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application{
    private Stage primaryStage;
    private static Main instance;
    @Override
    public void start(Stage primaryStage) throws Exception{
        instance = this;
        Font.loadFont(ClassLoader.getSystemResource("fonts/VCR_OSD.ttf").toExternalForm(), 20); // Load custom font
        Pane root = new MonsterPane();
        Scene scene = new Scene(root,1280,720);
        scene.getStylesheets().add(ClassLoader.getSystemResource("style.css").toExternalForm()); // Load custom CSS
        primaryStage.setScene(scene);
        primaryStage.setTitle("Jelly Space");

        // Set aspect ratio to 16:9
        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                primaryStage.setHeight(newSceneWidth.doubleValue() * 9 / 16);
            }
        });

        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                primaryStage.setWidth(newSceneHeight.doubleValue() * 16 / 9);
            }
        });
        this.primaryStage = primaryStage;
        primaryStage.show();
        root.setFocusTraversable(true);
        root.requestFocus();
    }
    public void changeScene(Pane newPane,Boolean transition) {
        if(transition){
            // Create a FadeTransition for the old scene
            FadeTransition fadeOut = new FadeTransition(Duration.millis(1000), primaryStage.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);

            // Set an action to be performed when the fade out transition finishes
            fadeOut.setOnFinished(e -> {
                // Create the new scene
                Scene scene = new Scene(newPane);
                scene.getStylesheets().add(ClassLoader.getSystemResource("style.css").toExternalForm());

                // Set the new scene on the primaryStage
                primaryStage.setScene(scene);

                // Create a FadeTransition for the new scene
                FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), newPane);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);

                // Start the fade in transition
                fadeIn.play();
                newPane.requestFocus();
            });
            // Start the fade out transition
            fadeOut.play();

        }else{
            Scene scene = new Scene(newPane);
            scene.getStylesheets().add(ClassLoader.getSystemResource("style.css").toExternalForm());
            primaryStage.setScene(scene);
            newPane.requestFocus();
        }
        // Request focus for the new scene
    }
    public static void changeSceneStatic(Pane newPane,Boolean transition) {
        instance.changeScene(newPane,transition);
    }
    public void addPaneToStackPane(StackPane stackPane, Pane pane) {
        // Add the new Pane to the StackPane
        stackPane.getChildren().add(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Main getInstance(){
        return instance;
    }
}
