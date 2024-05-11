package map;

import boundaries.*;
import gui.MapPane;
import javafx.scene.canvas.GraphicsContext;

public class MapPlanet1 extends GameMap{
    private Boundary boundary2,boundary3;

    public MapPlanet1(){
        this.boundary = new InverseParabolicBoundary(976,400,0.0015); // tuned
        this.boundary2 = new ParabolicBoundary(976,750,0.0005); // tuned
        this.boundary3 = new LinearBoundary(0,100); // tuned
        MapPane.getPlayer().setX(760);
        MapPane.getPlayer().setY(560);
    }
    public boolean checkBoundary(double x, double y) {
        System.out.println(String.valueOf(boundary.isWithinBoundary(x, y)) + boundary2.isWithinBoundary(x, y));
        return boundary.isWithinBoundary(x, y) & boundary2.isWithinBoundary(x, y)& !boundary3.isWithinBoundary(x, y);
    }
    public void drawBoundary(GraphicsContext gc) {
        boundary.drawBoundary(gc);
        boundary2.drawBoundary(gc);
        boundary3.drawBoundary(gc);
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO
    }
}
