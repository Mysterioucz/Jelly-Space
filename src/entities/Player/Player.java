package entities.Player;

import boundaries.Boundary;
import entities.Monster.Base_Monster;
import entities.Monster.Chatrin;
import entities.Sprite;
import inputs.KeyboardInputs;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import gui.MapPane;

import java.util.ArrayList;

import static gui.MapPane.keyHandler;// import static constant

public class Player extends Sprite {
    private static String name;
    private static My_Monster my_monster;
    private static Inventory inventory;
    private static final int ACTION_POINT = 3;
    private static int Used_Point;
    private static Player player;
    private static final int width = 80;
    private static final int height = 80;
    private static final Image imgRight = new Image(ClassLoader.getSystemResource("img/entities/player/right.gif").toString());
    private static final Image imgLeft = new Image(ClassLoader.getSystemResource("img/entities/player/left.gif").toString());
    private static double newX, newY;
    private static Rectangle playerRect, bossRect, rocketRect;
    private static Base_Monster activeMonster;

    public Player(String name,double x, double y, double width, double height){
        super(x,y,width,height,2,imgRight); // set initial image to be right
        Player.name = name;
        my_monster = new My_Monster();
        inventory = new Inventory();
        my_monster.addMonster(new Chatrin(620,360));
        Used_Point = 0;
        activeMonster = my_monster.getMyMonster().getFirst();
    }
    public void update(){
        movePlayer(); //Called move player method
        // Check if the player collides with the boss
        if(playerRect.intersects(bossRect.getBoundsInLocal())){
            System.out.println("Collided with Boss");
            MapPane.getInstance().handleCollideWithBoss();
        } else if (playerRect.intersects(rocketRect.getBoundsInLocal())) {
            System.out.println("Collided with Rocket");
            MapPane.getInstance().handleCollideWithRocket();
        }
    }
    public void movePlayer() {
        newX = getX();
        newY = getY();
        if(keyHandler.up){
            newY -= getSpeed();
        } else if (keyHandler.down) {
            newY += getSpeed();
        }else if(keyHandler.left){
            newX -= getSpeed();
            setImage(imgLeft);
        } else if (keyHandler.right) {
            newX += getSpeed();
            setImage(imgRight);
        }
        // Create rectangles for the player, the boss and rocket
        createEntitiesBound();
        // Calculate the Center position of the player
        int posX = (int) (newX+getWidth()/2);
        int posY = (int) (newY+getHeight()/2);
        if((MapPane.getGameMap().checkBoundary(newX+getWidth()/2,newY+getHeight()/2)) & (posX >= 0 && posX <= (1280-getWidth())) && (posY >= 0 && posY <= 720-getHeight())){
            // If the new position is within the boundary, check if the player collides with the boss or rocket
            if(!playerRect.intersects(bossRect.getBoundsInLocal())&!playerRect.intersects(rocketRect.getBoundsInLocal())){
                // if not move the player
                move(newX, newY);
            }
        }
    }
    public void draw(GraphicsContext gc){
        gc.drawImage(getImage(),getX(),getY(),getWidth(),getHeight());
        // draw Player boundary, boss boundary and rocket boundary
        Sprite boss = MapPane.getGameMap().getBoss();
        Rocket rocket = MapPane.getGameMap().getRocket();
        gc.setStroke(Color.RED);
        gc.strokeRect(getX(), getY(), getWidth(), getHeight());
        gc.strokeRect(boss.getX(), boss.getY(), boss.getWidth(), boss.getHeight());
        gc.strokeRect(rocket.getX(), rocket.getY(), rocket.getWidth(), rocket.getHeight());
    }
    public void createEntitiesBound(){
        playerRect = new Rectangle(newX, newY, getWidth(), getHeight());
        Sprite boss = MapPane.getGameMap().getBoss();
        bossRect = new Rectangle(boss.getX(), boss.getY(), boss.getWidth(), boss.getHeight());
        Rocket rocket = MapPane.getGameMap().getRocket();
        rocketRect = new Rectangle(rocket.getX(), rocket.getY(), rocket.getWidth(), rocket.getHeight());
    }

    public static String getName() {
        return name;
    }

    public static ArrayList<Base_Monster> getMy_monster() {
        return my_monster.getMyMonster();
    }

    public static Inventory getInventory() {
        return inventory;
    }

    public static int getACTION_POINT() {
        return ACTION_POINT;
    }

    public static int getUsed_Point() {
        return Used_Point;
    }

    public static void setUsed_Point(int used_Point) {
        Used_Point = used_Point;
    }

    public static Player getPlayer() {
        if(player == null){
            player = new Player("Player",50,360,Player.width,Player.height);
        }
        return player;
    }

    public static Base_Monster getActiveMonster(){
        // Done
        return activeMonster;
    }
    public static void setActiveMonster(Base_Monster monster){
        // Done
        activeMonster = monster;
    }


}
