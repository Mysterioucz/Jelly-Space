package main;

import gui.mapPane.StartPane;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
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
        Pane root = new StartPane();
        Scene scene = new Scene(root,1280,720);
        scene.getStylesheets().add(ClassLoader.getSystemResource("style.css").toExternalForm()); // Load custom CSS
        primaryStage.setScene(scene);
        primaryStage.setTitle("Jelly Space");
        primaryStage.setResizable(false); // prevent the window from being resized
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
                // Create a FadeTransition for the new scene
                FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), newPane);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                // Start the fade in transition
                fadeIn.play();
                // Set the new scene on the primaryStage
                primaryStage.setScene(scene);
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
    public static void fadeAudio(MediaPlayer mediaPlayer, int durationInSeconds) {
        // Create a new timeline
        Timeline timeline = new Timeline();

        // Add a key frame at the start with the current volume
        timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, new KeyValue(mediaPlayer.volumeProperty(), mediaPlayer.getVolume())));

        // Add a key frame at the end with the volume set to 0
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(durationInSeconds), new KeyValue(mediaPlayer.volumeProperty(), 0)));

        // Play the timeline
        timeline.play();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public Main getInstance(){
        return instance;
    }
}
