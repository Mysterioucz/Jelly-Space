package Monster;

import Monster.Abilities.Attackable;
import Monster.Abilities.Guardable;
import Monster.Abilities.Unique_Ability;

public class Chatrin extends Base_Monster implements Attackable, Guardable, Unique_Ability {

    public Chatrin(){
        super("Chatrin","Coding",100,100,50,20);
    }

    @Override
    public void attack(Base_Monster otherMonster) {
        int dmgNet = this.getDmg()-otherMonster.getDef();
        otherMonster.setHp(otherMonster.getHp()-dmgNet);
    }

    @Override
    public void guard(Base_Monster ChosenMonster) {
        ChosenMonster.setDef(ChosenMonster.getDef()+10);
    }

    @Override
    public void unique_ability(Base_Monster monster) {
        monster.setHp(monster.getHp()-this.getHp());
        this.setHp(0);
    }
}
