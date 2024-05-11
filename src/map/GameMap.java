package map;

import boundaries.Boundary;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameMap {
    protected Boundary boundary;

    public abstract boolean checkBoundary(double x, double y);
    public Boundary getBoundary(){
        return boundary;
    }
    public abstract void drawBoundary(GraphicsContext gc);
    public abstract void draw(GraphicsContext gc);
}



