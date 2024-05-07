package Monster;

import Monster.Abilities.Attackable;
import Monster.Abilities.Guardable;
import Monster.Abilities.Unique_Ability;

public class Chatrin extends Base_Monster implements Attackable, Guardable, Unique_Ability {

    public Chatrin(){
        super("Chatrin","Coding",200,50,20,70,20);
    }

    @Override
    public void attack(Base_Monster otherMonster) {
        int dmgNet = this.getDmg()-otherMonster.getDef();
        otherMonster.setHp(otherMonster.getHp()-dmgNet);
    }

    @Override
    public void guard(Base_Monster ChosenMonster) {
        if (this.getMana() >= 20) {
            this.setDef(this.getDef() + 10);
            this.setMana(this.getMana() - 20);
        }else{
            System.out.println("You don't have enough mana");
        }
    }

    @Override
    public void unique_ability(Base_Monster monster) {
        monster.setHp(monster.getHp()-this.getHp()*3);
        this.setHp(0);
    }
}
