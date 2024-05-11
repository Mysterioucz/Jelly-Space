package map;

import boundaries.InverseParabolicBoundary;
import gui.MapPane;
import javafx.scene.canvas.GraphicsContext;

public class MapPlanet3 extends GameMap{
    public MapPlanet3(){
        this.boundary = new InverseParabolicBoundary(390,450,0.0005);
        MapPane.getPlayer().setX(950);
        MapPane.getPlayer().setY(335);
    }
    public boolean checkBoundary(double x, double y) {
        return this.boundary.isWithinBoundary(x, y);
    }
    public void drawBoundary(GraphicsContext gc) {
        boundary.drawBoundary(gc);
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO
    }
}
