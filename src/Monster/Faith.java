package Monster;

import Monster.Abilities.Attackable;
import Monster.Abilities.Elements;
import Monster.Abilities.Guardable;
import Monster.Abilities.Unique_Ability;
import Player.Player;

import static Player.Player.getMy_monster;

public class Faith extends Base_Monster implements Attackable, Guardable, Unique_Ability {
    public Faith(){
        super("Faith", null,1000,1000,300,100,60,false);
    }


    @Override
    public void attack(Base_Monster otherMonster) {
        // True Damage
        if (getMana()>=300){
            otherMonster.setHp(otherMonster.getHp()-this.getDmg());
        }else{
            System.out.println("You don't have enough mana");
        }
    }

    @Override
    public void guard(Base_Monster ChosenMonster) {
        if (this.getMana()>=400){
            ChosenMonster.setDef(0);
        }else{
            System.out.println("You don't have enough mana");
        }
    }

    @Override
    public void unique_ability(Base_Monster monster) {
        if (this.getMana()>=900){
            this.setDmg(this.getDmg()+monster.getBaseDef()+this.getDef());
            this.setDef(0);
            monster.setBaseDef(0);
            monster.setDef(0);
            for (Base_Monster e: Player.getMy_monster()){
                int netDmg = this.getDmg()-e.getDef();
                e.setHp(e.getHp()-netDmg);
            }
        }else{
            System.out.println("You don't have enough mana");
        }
    }

}
