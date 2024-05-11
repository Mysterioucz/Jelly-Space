package entities.Monster;

import entities.Monster.Abilities.Attackable;
import entities.Monster.Abilities.Guardable;
import entities.Monster.Abilities.Unique_Ability;
import entities.Player.Player;

public class Faith extends Base_Monster implements Attackable, Guardable, Unique_Ability {
    public Faith(int x, int y){
        super("Faith", null,1000,1000,300,100,60,false,x,y,100,200,200,null);
    }


    @Override
    public void attack(Base_Monster otherMonster) {
        // True Damage
        if (getMana()>=300){
            otherMonster.setHp(otherMonster.getHp()-this.getDmg());
            this.setMana(this.getMana() - 300);
        }else{
            System.out.println("You don't have enough mana");
        }
    }

    @Override
    public void guard(Base_Monster ChosenMonster) {
        if (this.getMana()>=400){
            ChosenMonster.setDef(0);
            this.setMana(this.getMana() - 400);
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
            this.setMana(this.getMana() - 900);
        }else{
            System.out.println("You don't have enough mana");
        }
    }

}
