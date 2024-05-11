package entities.Player;

import entities.Monster.Base_Monster;
import entities.Monster.Chatrin;
import entities.Sprite;
import inputs.KeyboardInputs;
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
    private static final int width = 75;
    private static final int height = 75;
    private static final Image imgRight = new Image(ClassLoader.getSystemResource("img/entities/player/right.gif").toString());
    private static final Image imgLeft = new Image(ClassLoader.getSystemResource("img/entities/player/left.gif").toString());

    public Player(String name,double x, double y, double width, double height){
        super(x,y,width,height,2,imgRight); // set initial image to be right
        Player.name = name;
        my_monster = new My_Monster();
        inventory = new Inventory();
        my_monster.addMonster(new Chatrin(620,360));
        Used_Point = 0;
    }
    public void update(){

        if(keyHandler.up){
            move(0,-getSpeed());
        } else if (keyHandler.down) {
            move(0,getSpeed());
        }else if(keyHandler.left){
            move(-getSpeed(),0);
            setImage(imgLeft);
        } else if (keyHandler.right) {
            move(getSpeed(),0);
            setImage(imgRight);
        }
    }
    public void draw(GraphicsContext gc){
        gc.drawImage(getImage(),getX(),getY(),getWidth(),getHeight());
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

}
