package entities.Player;

import entities.Monster.Base_Monster;

import java.util.ArrayList;

public class My_Monster {
    private ArrayList<Base_Monster> monsters;
    private final int MAX_MONSTER = 3;

    public My_Monster(){
        monsters = new ArrayList<>();
    }

    public void addMonster(Base_Monster monster){
        if (monsters.size() < MAX_MONSTER){
            monsters.add(monster);
        }else {
            System.out.println("You can only have three monsters");
        }
    }

    public ArrayList<Base_Monster> getMonsters() {
        return monsters;
    }
}
