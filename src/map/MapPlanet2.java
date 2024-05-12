package map;

import boundaries.Boundary;
import boundaries.LinearBoundary;
import entities.Monster.Fei;
import entities.Player.Player;
import entities.Player.Rocket;
import gui.MapPane;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MapPlanet2 extends GameMap{
    private Image tree = new Image(ClassLoader.getSystemResource("img/mapComponent/tree.png").toString());
    private Image tree_Rock= new Image(ClassLoader.getSystemResource("img/mapComponent/tree_rock.png").toString());
    private Boundary boundary2;

    public MapPlanet2(){
        this.boundary = new LinearBoundary(1,100);
        this.boundary2 = new LinearBoundary(1,-600);
        // set player position to initial position
        setInitialPosition(750,460);
        resetPlayerPosition();
        // Create map entities
        setBoss(new Fei(250,70,false));
        rocket = new Rocket(833,542,"red");
    }
    public boolean checkBoundary(double x, double y) {
        return boundary.isWithinBoundary(x, y) & !boundary2.isWithinBoundary(x, y); // ! at boundary 2 because player cant be above boundary 2
    }
    public void drawBoundary(GraphicsContext gc) {
        boundary.drawBoundary(gc);
        boundary2.drawBoundary(gc);
    }

    @Override
    public void draw(GraphicsContext gc) {
        // Draw map components
        try {
            gc.drawImage(boss.getImage(),boss.getX(),boss.getY());
        } catch (Exception e) {
        }
        gc.drawImage(rocket.getImage(),rocket.getX(),rocket.getY());
        gc.drawImage(tree,325,287);
        gc.drawImage(tree,569,560);
        gc.drawImage(tree_Rock,0,108);
    }
}
