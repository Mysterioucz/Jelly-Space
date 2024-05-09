package Monster.Abilities;

import Monster.Base_Monster;

public class TU_Force extends Base_Monster implements Attackable,Guardable,Unique_Ability {
    TU_Force(boolean owned){
        super("TU Force",Elements.ALIEN,500,200,50,60,60,owned);
    }


    @Override
    public void attack(Base_Monster otherMonster) {

    }

    @Override
    public void guard(Base_Monster ChosenMonster) {

    }

    @Override
    public void unique_ability(Base_Monster monster) {

    }
}
