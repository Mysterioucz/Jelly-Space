package gui;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class CongratulationPane extends Pane {
    public CongratulationPane() {
        super();
        // Set GridPane to grow with window size
        setPrefSize(1280, 720);
        // Set the background image
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        Background bg = new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("img/background/mapSelect.png").toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize));
        setBackground(bg);
        // Create a text
        Text text = new Text("Congratulations! You have cleared the game!");
        text.setFont(text.getFont().font("VCR OSD Mono", 30));
        text.setFill(Color.WHITE);
        // Center the text
        text.layoutXProperty().bind(widthProperty().subtract(text.prefWidth(-1)).divide(2));
        text.layoutYProperty().bind(heightProperty().subtract(text.prefHeight(-1)).divide(2));
        // Add the text to the pane
        getChildren().add(text);
    }
}
