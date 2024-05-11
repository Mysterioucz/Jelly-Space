package gui;

import javafx.animation.FadeTransition;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class MapSelectPane extends GridPane {

    public static String mapName;

    public MapSelectPane() {
        super();
        // Set background image
        Image img = new Image(ClassLoader.getSystemResource("img/background/mapSelect.png").toString());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        this.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize)));

//        setGridLinesVisible(true);// for debugging

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
        // Create and configure ImageView for each planet
        ImageView planetView1 = createPlanetView("img/planets/planet1.png", 200, 200, VPos.TOP, "Planet 1");
        ImageView planetView2 = createPlanetView("img/planets/planet2.png", 200, 200, null, "Planet 2");
        ImageView planetView3 = createPlanetView("img/planets/planet3.png", 200, 200, VPos.BOTTOM, "Planet 3");
        ImageView earthView = createPlanetView("img/planets/earth.png", 200, 200, VPos.TOP, HPos.CENTER, "Earth");
        ImageView blackholeView = createPlanetView("img/planets/Blackhole.png", 250, 250, null, HPos.CENTER, "Black Hole");

        // Set planet position
        setHgap(20);
        setVgap(20);

        // Add event handlers to show planet name
        addPlanetClickHandler(planetView1, "planet1");
        addPlanetClickHandler(planetView2, "planet2");
        addPlanetClickHandler(planetView3, "planet3");
        addPlanetClickHandler(earthView, "earth");
        addPlanetClickHandler(blackholeView, "blackhole");

        // Add planet to grid
        add(planetView1, 4, 2,1,1);
        add(planetView2, 1, 0,1,2);
        add(planetView3, 4, 0,1,1);
        add(earthView, 1, 2,2,1);
        add(blackholeView, 2, 1,2,1);
    }

    private ImageView createPlanetView(String imagePath, int width, int height, VPos vPos, String planetName) {
        return createPlanetView(imagePath, width, height, vPos, null, planetName);
    }

    private ImageView createPlanetView(String imagePath, int width, int height, VPos vPos, HPos hPos, String planetName) {
        Image planet = new Image(ClassLoader.getSystemResource(imagePath).toString());
        ImageView planetView = new ImageView(planet);
        planetView.setFitWidth(width);
        planetView.setFitHeight(height);
        if (vPos != null) setValignment(planetView, vPos);
        if (hPos != null) setHalignment(planetView, hPos);
        return planetView;
    }

    private void addPlanetClickHandler(ImageView imageView, String planetName) {
        imageView.setOnMouseClicked(event -> {
            System.out.println("Clicked on " + planetName);
            mapName = planetName;
            FadeTransition ft = new FadeTransition(Duration.millis(1000), this); // set FadeTransition
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setOnFinished(e2 -> {
                        Scene scene = new Scene(new MapTransitionPane());
                        scene.getStylesheets().add(ClassLoader.getSystemResource("style.css").toExternalForm());
                        this.getScene().setRoot(new StackPane(new Pane(), scene.getRoot()));
                        scene.getRoot().requestFocus();
                    }); // Change the root of the scene to the new MapPane after the fade out
            ft.play();

        });
    }
}