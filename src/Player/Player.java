package Player;

import Monster.Base_Monster;
import Monster.Chatrin;

import java.util.ArrayList;

public class Player {
    private String name;
    private int maxHp;
    private int hp;
    private My_Monster my_monster;
    private Inventory inventory;

    public Player(String name){
        this.name = name;
        my_monster = new My_Monster();
        inventory = new Inventory();
        my_monster.addMonster(new Chatrin());
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
