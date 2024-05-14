package entities.Player;

import Items.Poisons.Mega_Health_Poison;
import Items.Potions.Health_Potion;
import Items.Potions.Mana_Potion;
import Items.Potions.Mega_Health_Potion;
import Items.Potions.Mega_Mana_Potion;
import entities.Monster.*;
import entities.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import gui.MapPane;

import static gui.MapPane.keyHandler;// import static constant

public class Player extends Sprite {
    private static String name;
    private static My_Monster my_monster;
    private static Inventory inventory;
    private static final int ACTION_POINT = 3;
    private static int Used_Point;
    private static Player player;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 80;
    private static final Image IMG_RIGHT = new Image(ClassLoader.getSystemResource("img/entities/player/right.gif").toString());
    private static final Image IMG_LEFT = new Image(ClassLoader.getSystemResource("img/entities/player/left.gif").toString());
    private static double newX, newY;
    private static Rectangle playerRect, bossRect, rocketRect;
    private static Base_Monster activeMonster;

    public Player(String name,double x, double y, double width, double height){
        super(x,y,width,height,2, IMG_RIGHT); // set initial image to be right
        Player.name = name;
        my_monster = new My_Monster();
        inventory = new Inventory();
        my_monster.addMonster(new Chatrin(620,360));
        Used_Point = 3;
        activeMonster = my_monster.getMonsters().getFirst();

        //********************************** initialize player monster ******************************************
        my_monster.addMonster(new Fai(0,0,true));
        my_monster.addMonster(new Fei(0,0,true));

        inventory.addItem(new Health_Potion());
        inventory.addItem(new Mega_Health_Potion());
        inventory.addItem(new Mana_Potion());
        inventory.addItem(new Mega_Mana_Potion());
        inventory.addItem(new Mega_Health_Poison());
        //***************************************************************************************************
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
            setImage(IMG_LEFT);
        } else if (keyHandler.right) {
            newX += getSpeed();
            setImage(IMG_RIGHT);
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
//        drawBoundary(gc); // for debugging
    }
    public void drawBoundary(GraphicsContext gc){
        Sprite boss = MapPane.getGameMap().getBoss();
        Rocket rocket = MapPane.getGameMap().getRocket();
        gc.setStroke(Color.RED);
        gc.strokeRect(getX(), getY(), getWidth(), getHeight());
        try {
            if(!((Base_Monster) boss).isDead()){ // if the boss is not dead, draw a rectangle for the boss
                gc.strokeRect(boss.getX(), boss.getY(), boss.getWidth(), boss.getHeight());
            }
        } catch (NullPointerException e) {
        }
        gc.strokeRect(rocket.getX(), rocket.getY(), rocket.getWidth(), rocket.getHeight());
    }
    public void createEntitiesBound(){
        playerRect = new Rectangle(newX, newY, getWidth(), getHeight());
        Sprite boss = MapPane.getGameMap().getBoss();
        try{
            if(!((Base_Monster) boss).isDead()){ // if the boss is not dead, create a rectangle for the boss
                bossRect = new Rectangle(boss.getX(), boss.getY(), boss.getWidth(), boss.getHeight());
            }else{
                bossRect = new Rectangle(0,0,0,0);
            }
        }catch (NullPointerException e){
            bossRect = new Rectangle(0,0,0,0);
        }
        Rocket rocket = MapPane.getGameMap().getRocket();
        rocketRect = new Rectangle(rocket.getX(), rocket.getY(), rocket.getWidth(), rocket.getHeight());
    }

    public static String getName() {
        return name;
    }

    public static My_Monster getMy_monster() {
        return my_monster;
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
        Used_Point = Math.max(0,Math.min(getACTION_POINT(),used_Point));
    }

    public static Player getPlayer() {
        if(player == null){
            player = new Player("Player",50,360,Player.WIDTH,Player.HEIGHT);
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
