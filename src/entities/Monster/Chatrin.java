package entities.Monster;

import entities.Monster.Abilities.Attackable;
import entities.Monster.Abilities.Elements;
import entities.Monster.Abilities.Guardable;
import entities.Monster.Abilities.Unique_Ability;
import entities.Player.Player;

public class Chatrin extends Base_Monster implements Attackable, Guardable, Unique_Ability {

    public Chatrin(int x,int y){
        super("Chatrin", Elements.EARTHLINGS,250,50,10,50,20,true,x,y,100,200,200,null);
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
            this.setHp(this.getDef() + 10);
            this.setMana(this.getMana() - 30);
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
            Player.setUsed_Point(Player.getUsed_Point()+2);
        }
    }
}
