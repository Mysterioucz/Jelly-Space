package map;

import boundaries.*;
import entities.Monster.TU_Force;
import entities.Player.Player;
import entities.Player.Rocket;
import entities.Sprite;
import gui.MapPane;
import javafx.scene.canvas.GraphicsContext;

public class MapPlanet1 extends GameMap{
    private Boundary boundary2,boundary3;

    public MapPlanet1(){
        this.boundary = new InverseParabolicBoundary(976,400,0.0015); // tuned
        this.boundary2 = new ParabolicBoundary(990,670,0.00049); // tuned
        this.boundary3 = new LinearBoundary(0,100); // tuned
        Player.getPlayer().setX(720);
        Player.getPlayer().setY(475);
        boss = new TU_Force(165,195,false);
        rocket = new Rocket(833,542,"normal");
    }
    public boolean checkBoundary(double x, double y) {
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
        gc.drawImage(boss.getImage(),boss.getX(),boss.getY());
        gc.drawImage(rocket.getImage(),rocket.getX(),rocket.getY());
    }
}
