package map;

import boundaries.Boundary;
import entities.Player.Player;
import entities.Player.Rocket;
import entities.Sprite;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameMap {
    protected Boundary boundary;
    protected Sprite boss;
    protected Rocket rocket;
    protected double initialX,initialY;
    protected Boolean isCleared = false;

    public abstract boolean checkBoundary(double x, double y);
    public Boundary getBoundary(){
        return boundary;
    }
    public abstract void drawBoundary(GraphicsContext gc);
    public void draw(GraphicsContext gc) {
        // Draw the boss and rocket
        try {
            gc.drawImage(boss.getImage(),boss.getX(),boss.getY());
        } catch (NullPointerException e) {
        }
        gc.drawImage(rocket.getImage(),rocket.getX(),rocket.getY());
    }

    public void setBoss(Sprite boss){
        if(!isCleared){
            this.boss = boss;
        }else{
            this.boss = null;
        }
    }
    public Sprite getBoss(){
        return boss;
    }
    public Rocket getRocket(){
        return rocket;
    }
    public void setPlayerPosition(double posX, double posY){
        // Reset player position to the starting position of the map
        Player.getPlayer().setX(posX);
        Player.getPlayer().setY(posY);
    }
    public void resetPlayerPosition(){
        setPlayerPosition(initialX, initialY);
    }
    public void setInitialPosition(double x, double y){
        initialX = x;
        initialY = y;
    }

    public Boolean isCleared() {
        return isCleared;
    }
    public void setIsCleared(Boolean b) {
        this.isCleared = b;
    }
}



