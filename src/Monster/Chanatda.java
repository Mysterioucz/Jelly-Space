package Monster;

import Monster.Abilities.Attackable;
import Monster.Abilities.Guardable;
import Monster.Abilities.Unique_Ability;

public class Chanatda extends Base_Monster implements Attackable, Unique_Ability {
    public Chanatda(){
        super("Chanatda","Calculus",300,300,30,10,30);
    }


    @Override
    public void attack(Base_Monster otherMonster) {
        if (this.getMana() >= 50) {
            int netDmg = this.getDmg() - otherMonster.getDef();
            otherMonster.setHp(otherMonster.getHp() - netDmg);
            otherMonster.setDef(otherMonster.getDef() - netDmg/2);
            this.setMana(this.getMana() - 50);
        }else{
            System.out.println("You don't have enough mana");
        }
    }

    @Override
    public void unique_ability(Base_Monster monster) {
        if (this.getMana()>=100){
            monster.setHp(monster.getHp()+50);
            monster.setDmg(monster.getDmg()+20);
        }else{
            System.out.println("You don't have enough mana");
        }
    }
}
