package map;

import boundaries.Boundary;
import boundaries.LinearBoundary;
import entities.Monster.Fei;
import entities.Player.Player;
import entities.Player.Rocket;
import gui.MapPane;
import javafx.scene.canvas.GraphicsContext;

public class MapPlanet2 extends GameMap{
    private Boundary boundary2;
    public MapPlanet2(){
        this.boundary = new LinearBoundary(1,100);
        this.boundary2 = new LinearBoundary(1,-600);
        Player.getPlayer().setX(750);
        Player.getPlayer().setY(460);
        boss = new Fei(250,70,false);
        rocket = new Rocket(833,542,"red");
    }
    public boolean checkBoundary(double x, double y) {
        return boundary.isWithinBoundary(x, y) & !boundary2.isWithinBoundary(x, y); // ! at boundary 2 because player cant be above boundary 2
    }
    public void drawBoundary(GraphicsContext gc) {
        boundary.drawBoundary(gc);
        boundary2.drawBoundary(gc);
    }
    public void draw(GraphicsContext gc) {
        // TODO
        gc.drawImage(boss.getImage(),boss.getX(),boss.getY());
        gc.drawImage(rocket.getImage(),rocket.getX(),rocket.getY());
    }
}
