package map;

import boundaries.RectangleBoundary;
import entities.Monster.Faith;
import entities.Player.Player;
import entities.Player.Rocket;
import javafx.scene.canvas.GraphicsContext;

public class MapBlackHole extends GameMap{

    public MapBlackHole(){
        this.boundary = new RectangleBoundary(0,267,1280,425);
        // set player position to initial position
        setInitialPosition(200,267);
        resetPlayerPosition();

        // Create map entities
        setBoss(new Faith(1180, 300));
        rocket = new Rocket(0,275,"normal");
    }
    public boolean checkBoundary(double x, double y) {
        return boundary.isWithinBoundary(x, y);
    }
    public void drawBoundary(GraphicsContext gc) {
        boundary.drawBoundary(gc);
    }


    @Override
    public Boolean isCleared() {
        return GameMap.blackHoleIsCleared;
    }
    public void setIsCleared(Boolean isCleared) {
        GameMap.blackHoleIsCleared = isCleared;
    }
}
