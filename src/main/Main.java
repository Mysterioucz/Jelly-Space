package main;

import gui.StartPane;
import inputs.KeyboardInputs;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Font.loadFont(ClassLoader.getSystemResource("fonts/VCR_OSD.ttf").toExternalForm(), 20); // Load custom font
        Pane root = new StartPane();
        Scene scene = new Scene(root);
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

        primaryStage.show();
        root.setFocusTraversable(true);
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
