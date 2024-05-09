package gui;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class MapSelectPane extends GridPane {
    public MapSelectPane() {
        super();
        // Set background image
        Image img = new Image(ClassLoader.getSystemResource("img/background/mapSelect.png").toString());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        this.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT, backgroundSize)));

        setGridLinesVisible(true);// for debugging

        // Set GridPane to grow with window size
        setPrefSize(1280, 720);
        HBox.setHgrow(this, Priority.ALWAYS);
        VBox.setVgrow(this, Priority.ALWAYS);
        // Set the percentWidth for each column
        for (int i = 0; i < 6; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100.0 / 6);
            getColumnConstraints().add(column);
        }
// Set the percentHeight for each row
        for (int i = 0; i < 3; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / 3);
            getRowConstraints().add(row);
        }

        initializePlanet();//initialize the planet

    }
    public void initializePlanet(){
        // Create planet image
        Image planet1 = new Image(ClassLoader.getSystemResource("img/planets/planet1.png").toString());
        ImageView planetView1 = new ImageView(planet1);
        planetView1.setFitWidth(200);
        planetView1.setFitHeight(200);
        setValignment(planetView1, VPos.TOP);
        Image planet2 = new Image(ClassLoader.getSystemResource("img/planets/planet2.png").toString());
        ImageView planetView2 = new ImageView(planet2);
        planetView2.setFitWidth(200);
        planetView2.setFitHeight(200);
        Image planet3 = new Image(ClassLoader.getSystemResource("img/planets/planet3.png").toString());
        ImageView planetView3 = new ImageView(planet3);
        planetView3.setFitWidth(200);
        planetView3.setFitHeight(200);
        setValignment(planetView3, VPos.BOTTOM);
        Image earth = new Image(ClassLoader.getSystemResource("img/planets/earth.png").toString());
        ImageView earthView = new ImageView(earth);
        earthView.setFitWidth(200);
        earthView.setFitHeight(200);
        setHalignment(earthView, HPos.CENTER);
        setValignment(earthView, VPos.TOP);
        Image blackhole = new Image(ClassLoader.getSystemResource("img/planets/Blackhole.png").toString());
        ImageView blackholeView = new ImageView(blackhole);
        blackholeView.setFitWidth(250);
        blackholeView.setFitHeight(250);
        setHalignment(blackholeView, HPos.CENTER);
        // Set planet position
        setHgap(20);
        setVgap(20);
        // Add planet to grid
        add(planetView1, 4, 2,1,1);
        add(planetView2, 1, 0,1,2);
        add(planetView3, 4, 0,1,1);
        add(earthView, 1, 2,2,1);
        add(blackholeView, 2, 1,2,1);

    }
}
