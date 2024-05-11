package map;

import boundaries.RectangleBoundary;
import javafx.scene.canvas.GraphicsContext;

public class MapBlackHole extends GameMap{
    public MapBlackHole(){
        this.boundary = new RectangleBoundary(0,267,1280,400);
    }
    public boolean checkBoundary(double x, double y) {
        return boundary.isWithinBoundary(x, y);
    }
    public void drawBoundary(GraphicsContext gc) {
        boundary.drawBoundary(gc);
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO
    }
}
