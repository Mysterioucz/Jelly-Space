package map;

import boundaries.EllipseBoundary;
import entities.Monster.Base_Monster;
import entities.Monster.Chatrin;
import entities.Player.Player;
import entities.Player.Rocket;
import entities.Sprite;
import gui.MapPane;
import javafx.scene.canvas.GraphicsContext;

public class MapEarth extends GameMap {

    public MapEarth(){
        this.boundary = new EllipseBoundary(559,375,400,125);
        // set player position to initial position
        setInitialPosition(229,375);
        resetPlayerPosition();
        // Create map entities
        setBoss(new Chatrin(897, 326));
        rocket = new Rocket(48,263,"normal");
    }
    public boolean checkBoundary(double x, double y) {
        if (!boundary.isWithinBoundary(x, y)) {
//            System.out.println("Out of Boundary");
        } else {
//            System.out.println("Within Boundary");
        }
        return boundary.isWithinBoundary(x, y);
    }
    public void drawBoundary(GraphicsContext gc) {
        boundary.drawBoundary(gc);
    }

}
