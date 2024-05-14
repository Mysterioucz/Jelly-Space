package map;

import boundaries.Boundary;
import entities.Monster.Base_Monster;
import entities.Player.Player;
import entities.Player.Rocket;
import entities.Sprite;
import gui.MapPane;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameMap {
    protected Boundary boundary;
    protected Sprite boss;
    protected Rocket rocket;
    protected double initialX,initialY;
    protected static Boolean earthIsCleared = false,blackHoleIsCleared = false,planet1IsCleared = false,planet2IsCleared = false,planet3IsCleared = false;

    public abstract boolean checkBoundary(double x, double y);
    public Boundary getBoundary(){
        return boundary;
    }
    public abstract void drawBoundary(GraphicsContext gc);
    public void draw(GraphicsContext gc) {
        // Draw the boss and rocket
        try {
            if(!((Base_Monster) boss).isDead()){ // if boss is not dead draw the boss
                gc.drawImage(boss.getImage(),boss.getX(),boss.getY());
            }
        } catch (NullPointerException e) {
        }
        gc.drawImage(rocket.getImage(),rocket.getX(),rocket.getY());
    }

    public void setBoss(Sprite boss){
        if(!isCleared()){
            // if map not cleared create the boss
            this.boss = boss;
        }else{
            // if cleared set boss to null
            this.boss = null;
        }
    }
    public Sprite getBoss(){
        return boss;
    }
    public void resetBoss(){
        Base_Monster boss = (Base_Monster) this.boss;
        boss.setHp(boss.getMaxHp());
        boss.setMana(boss.getMaxMana());
        boss.setDef(boss.getBaseDef());
        boss.setDmg(boss.getBaseDmg());
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

    public abstract void setIsCleared(Boolean b);
    public abstract Boolean isCleared();
    public static Boolean gameCleared(){
        return blackHoleIsCleared&&earthIsCleared&&planet1IsCleared&&planet2IsCleared&&planet3IsCleared;
    }
}



