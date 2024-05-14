package map;

import boundaries.EllipseBoundary;
import entities.Monster.Chatrin;
import entities.Player.Rocket;
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
        return boundary.isWithinBoundary(x, y);
    }
    public void drawBoundary(GraphicsContext gc) {
        boundary.drawBoundary(gc);
    }

    @Override
    public Boolean isCleared() {
        return GameMap.earthIsCleared;
    }
public void setIsCleared(Boolean isCleared) {
        GameMap.earthIsCleared = isCleared;
    }
}
