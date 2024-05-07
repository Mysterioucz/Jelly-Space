package Player;

import Monster.Base_Monster;

import java.util.ArrayList;

public class My_Monster {
    private ArrayList<Base_Monster> myMonster;
    private final int MAX_MONSTER = 3;

    public My_Monster(){
        myMonster = new ArrayList<>();
    }

    public void addMonster(Base_Monster monster){
        if (myMonster.size() < MAX_MONSTER){
            myMonster.add(monster);
        }else {
            System.out.println("You can only have three monsters");
        }
    }

    public void deleteDeadMonster(){
        for (Base_Monster m:myMonster){
            if (m.getHp()==0){
                myMonster.remove(m);
            }
        }
    }
}
