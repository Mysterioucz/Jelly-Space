package Monster;

import Monster.Abilities.Attackable;
import Monster.Abilities.Guardable;

public class Chatrin extends Base_Monster implements Attackable, Guardable {

    public Chatrin(){
        super("Chatrin","Coding",100,100,50,20);
    }
}
