package map;

import boundaries.EllipseBoundary;
import entities.Monster.Base_Monster;
import entities.Monster.Chatrin;
import gui.MapPane;
import javafx.scene.canvas.GraphicsContext;

public class MapEarth extends GameMap {
private Base_Monster boss;

    public MapEarth(){
        this.boundary = new EllipseBoundary(559,325,375,150);
        MapPane.getPlayer().setX(229);
        MapPane.getPlayer().setY(375);
        boss = new Chatrin(897, 326);
    }
    public boolean checkBoundary(double x, double y) {
        if (!boundary.isWithinBoundary(x, y)) {
            System.out.println("Out of Boundary");
        } else {
            System.out.println("Within Boundary");
        }
        return boundary.isWithinBoundary(x, y);
    }
    public void drawBoundary(GraphicsContext gc) {
        boundary.drawBoundary(gc);
    }
    public void draw(GraphicsContext gc) {
        gc.drawImage(boss.getImage(),boss.getX(),boss.getY());
    }
}
