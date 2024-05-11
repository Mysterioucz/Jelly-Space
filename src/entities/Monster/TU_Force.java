package entities.Monster;

import entities.Monster.Abilities.Attackable;
import entities.Monster.Abilities.Elements;
import entities.Monster.Abilities.Unique_Ability;
import entities.Player.Player;

public class TU_Force extends Base_Monster implements Attackable, Unique_Ability {
    TU_Force(int x,int y,boolean owned){
        super("TU Force", Elements.ALIEN,500,400,50,60,60,owned,x,y,100,200,200,null);
    }


    @Override
    public void attack(Base_Monster otherMonster) {
        if (this.getMana()>=100){
            this.setMana(this.getMana() - 100);
            if (this.isOwned()){
                int netDmg = this.getDmg()-otherMonster.getDef();
                Player.setUsed_Point(Player.getUsed_Point()+1);
                for (Base_Monster e: Player.getMy_monster()){
                    netDmg = netDmg + e.getDmg()/2;
                }
                otherMonster.setHp(otherMonster.getHp()-netDmg);
            }else{
                for (Base_Monster e: Player.getMy_monster()){
                    int netDmg = this.getDmg()-e.getDef();
                    e.setHp(e.getHp()-netDmg);
                }
            }
        }else{
            System.out.println("You don't have enough mana");
        }
    }

    @Override
    public void unique_ability(Base_Monster monster) {
        if (this.getMana()>=300){
            monster.setDmg(monster.getDmg()+50);
            monster.setDef(monster.getDef()+50);
            this.setMana(this.getMana() - 300);
            if (this.isOwned()){
                Player.setUsed_Point(Player.getUsed_Point()+2);
            }
        }else{
            System.out.println("You don't have enough mana");
        }
    }
}
