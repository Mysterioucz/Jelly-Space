package gui.mapPane;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import main.Main;

public class MapSelectPane extends GridPane {

    public static String mapName;

    public MapSelectPane() {
        super();
        mapName = null;
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
        ImageView planetView1 = createPlanetView("img/planets/planet1.png", 200, 200, VPos.TOP);
        ImageView planetView2 = createPlanetView("img/planets/planet2.png", 200, 200, null);
        ImageView planetView3 = createPlanetView("img/planets/planet3.png", 200, 200, VPos.BOTTOM);
        ImageView earthView = createPlanetView("img/planets/earth.png", 200, 200, VPos.TOP, HPos.CENTER);
        ImageView blackholeView = createPlanetView("img/planets/Blackhole.png", 250, 250, null, HPos.CENTER);

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

    private ImageView createPlanetView(String imagePath, int width, int height, VPos vPos) {
        return createPlanetView(imagePath, width, height, vPos, null);
    }

    private ImageView createPlanetView(String imagePath, int width, int height, VPos vPos, HPos hPos) {
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
            if(mapName != null) return;
            System.out.println("Clicked on " + planetName);
            mapName = planetName;
            Main.changeSceneStatic(new MapTransitionPane(), true);
        });
    }
}