package boundaries;

import javafx.scene.canvas.GraphicsContext;

public abstract class Boundary {
    // this interface is used to define the boundary of the game
    public abstract boolean isWithinBoundary(double x, double y);
    public abstract void drawBoundary(GraphicsContext gc);
}
