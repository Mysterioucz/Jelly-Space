package Monster;

import Monster.Abilities.Attackable;
import Monster.Abilities.Elements;
import Monster.Abilities.Guardable;
import Monster.Abilities.Unique_Ability;
import Player.Player;

public class Chatrin extends Base_Monster implements Attackable, Guardable, Unique_Ability {

    public Chatrin(){
        super("Chatrin", Elements.EARTHLINGS,250,50,10,80,20,true);
    }

    @Override
    public void attack(Base_Monster otherMonster) {
        statBuff(otherMonster);
        int dmgNet = this.getDmg()-otherMonster.getDef();
        otherMonster.setHp(otherMonster.getHp()-dmgNet);
        if (this.isOwned()){
            Player.setUsed_Point(Player.getUsed_Point()+1);
        }
    }

    @Override
    public void guard(Base_Monster ChosenMonster) {
        if (this.getMana() >= 30) {
            this.setDef(this.getDef() + 10);
            this.setMana(this.getMana() - 20);
            if (this.isOwned()){
                Player.setUsed_Point(Player.getUsed_Point()+1);
            }
        }else{
            System.out.println("You don't have enough mana");
        }
    }

    @Override
    public void unique_ability(Base_Monster monster) {
        monster.setHp(monster.getHp()-this.getHp());
        this.setHp(0);
        if (this.isOwned()){
            Player.setUsed_Point(Player.getUsed_Point()+1);
        }
    }
}
