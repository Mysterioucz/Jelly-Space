package map;

import boundaries.InverseParabolicBoundary;
import entities.Monster.Fai;
import entities.Player.Rocket;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MapPlanet3 extends GameMap{
    private Image component = new Image(ClassLoader.getSystemResource("img/Components/mapComponent/planet3.png").toString());

    public MapPlanet3(){
        this.boundary = new InverseParabolicBoundary(390,450,0.0005);
        // set player position to initial position
        setInitialPosition(930,375);
        resetPlayerPosition();
        // Create map entities
        setBoss(new Fai(235,510,false));
        rocket = new Rocket(1048,275,"purple");
    }
    public boolean checkBoundary(double x, double y) {
        return this.boundary.isWithinBoundary(x, y);
    }
    public void drawBoundary(GraphicsContext gc) {
        boundary.drawBoundary(gc);
    }

    @Override
    public void draw(GraphicsContext gc) {
        // Draw map components
        try {
            gc.drawImage(boss.getImage(),boss.getX(),boss.getY());
        } catch (Exception e) {
        }
        gc.drawImage(rocket.getImage(),rocket.getX(),rocket.getY());
        gc.drawImage(component,-250,360,1673,377);
    }
}
