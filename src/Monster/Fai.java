package Monster;

import Monster.Abilities.Attackable;
import Monster.Abilities.Elements;
import Monster.Abilities.Guardable;
import Monster.Abilities.Unique_Ability;
import Player.Player;

public class Fai extends Base_Monster implements Attackable, Unique_Ability {
    public Fai(boolean owned){
        super("Fai", Elements.MACHINE,300,300,30,30,50,owned);
    }


    @Override
    public void attack(Base_Monster otherMonster) {
        statBuff(otherMonster);
        if (this.getMana() >= 50) {
            otherMonster.setDef(otherMonster.getDef() - this.getDmg()/2);
            int netDmg = this.getDmg() - otherMonster.getDef();
            otherMonster.setHp(otherMonster.getHp() - netDmg);
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
            this.setMana(this.getMana()-100);
            if (this.isOwned()){
                Player.setHp(Player.getHp()+10);
                Player.setUsed_Point(Player.getUsed_Point()+1);
            }
        }else{
            System.out.println("You don't have enough mana");
        }
    }
}
