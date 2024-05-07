package Player;

import Monster.Base_Monster;
import Monster.Chatrin;

import java.util.ArrayList;

public class Player {
    private String name;
    private int maxHp;
    private int hp;
    private ArrayList<Base_Monster> myMonster;
    private final int MAX_MONSTER = 3;
    private Inventory inventory;

    public Player(String name){
        this.name = name;
        myMonster = new ArrayList<>();
        inventory = new Inventory();
        myMonster.add(new Chatrin());
    }
}
