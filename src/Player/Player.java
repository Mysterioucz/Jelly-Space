package Player;

import Monster.Base_Monster;
import Monster.Chatrin;

import java.util.ArrayList;

public class Player {
    private static String name;
    private static My_Monster my_monster;
    private static Inventory inventory;
    private static final int ACTION_POINT = 3;
    private static int Used_Point;

    public Player(String name){
        Player.name = name;
        my_monster = new My_Monster();
        inventory = new Inventory();
        my_monster.addMonster(new Chatrin());
        Used_Point = 0;
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
}
