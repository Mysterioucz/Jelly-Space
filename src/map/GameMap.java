package map;

import boundaries.Boundary;
import entities.Player.Rocket;
import entities.Sprite;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameMap {
    protected Boundary boundary;
    protected Sprite boss;
    protected Rocket rocket;

    public abstract boolean checkBoundary(double x, double y);
    public Boundary getBoundary(){
        return boundary;
    }
    public abstract void drawBoundary(GraphicsContext gc);
    public abstract void draw(GraphicsContext gc);

    public Sprite getBoss(){
        return boss;
    }
    public Rocket getRocket(){
        return rocket;
    }
}



