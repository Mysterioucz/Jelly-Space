package gui;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

public class MapTransitionPane extends Pane {

    private ArrayList<Text> dots = new ArrayList<>();
    private int dotSize;
    private Text loading;

    public MapTransitionPane() {
        super();
        setWidth(1280);
        setHeight(720);
        // Set Background Image
        Image BG = new Image(ClassLoader.getSystemResource("img/loading/"+ MapSelectPane.mapName + ".png").toString());
        this.setBackground(new Background(new BackgroundImage(BG, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(100, 100, true, true, false, true))));

        // Set Entering Text
        loading = new Text("Entering Map");
        loading.setFont(Font.font("VCR OSD Mono", 50));
        loading.translateXProperty().bind(widthProperty().subtract(loading.getLayoutBounds().getWidth() + 3 * dotSize + 100));
        loading.translateYProperty().bind(heightProperty().subtract(loading.getLayoutBounds().getHeight()));
        loading.setFill(Color.WHITE);
        getChildren().add(loading);

        initializeDot(); // initialize Dot
        setDotAnimation(); // set Dot Animation

        // After 5 seconds, change to MapPane
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        // Change the root of the scene to the new MapPane after the fade out
        pause.setOnFinished(e -> {
            FadeTransition ft = new FadeTransition(Duration.millis(1000), this);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            // Change the root of the scene to the new MapPane after the fade out
            ft.setOnFinished(e2 -> {
                MapPane mapPane = new MapPane();
                getScene().setRoot(mapPane);
                mapPane.requestFocus();
            });
            ft.play();
        });
        pause.play();
    }
    public void initializeDot() {
        for(int i=0;i<3;i++){
            Text dot = new Text(".");
            dot.setFont(Font.font("VCR OSD Mono", 50));
            dot.setFill(Color.WHITE);
            dotSize = (int) dot.getLayoutBounds().getWidth();
            dots.add(dot);
            getChildren().add(dot);
        }
        updateDots();
    }
    public void updateDots() {
        for (int i = 0; i < dots.size(); i++) {
            Text dot = dots.get(i);
            dot.translateXProperty().bind(loading.translateXProperty().add(loading.getLayoutBounds().getWidth() + (i + 1) * 20));
            dot.translateYProperty().bind(loading.translateYProperty());
        }
    }
    public void setDotAnimation(){
        for(int i = 0; i < dots.size(); i++ ){
            Timeline timeline = new Timeline();
            Text dot = dots.get(i);
            double initialY = dot.getTranslateY();
            dot.translateYProperty().unbind(); // Unbind the translateY property
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(dot.translateYProperty(), initialY)),
                    new KeyFrame(Duration.seconds(0.5), new KeyValue(dot.translateYProperty(), initialY - 10)),
                    new KeyFrame(Duration.seconds(1), new KeyValue(dot.translateYProperty(), initialY))
            );
            timeline.setCycleCount(5);
            timeline.setDelay(Duration.seconds(i*0.5));
            int finalI = i;
            timeline.setOnFinished(e -> {
                dot.translateXProperty().bind(loading.translateXProperty().add(loading.getLayoutBounds().getWidth() + (finalI + 1) * 20)); // Rebind the translateX property
                dot.translateYProperty().bind(loading.translateYProperty()); // Rebind the translateY property
            });
            timeline.play();
        }
    }
}
