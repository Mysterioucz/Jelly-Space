package entities.Monster;

import entities.Monster.Abilities.Attackable;
import entities.Monster.Abilities.Elements;
import entities.Monster.Abilities.Unique_Ability;
import entities.Player.Player;
import gui.battle.BattleFieldPane;
import javafx.scene.image.Image;

public class Fai extends Base_Monster implements Attackable, Unique_Ability {
    private Image img = new Image(ClassLoader.getSystemResource("img/entities/monster/Fai/Idle.gif").toString());

    public Fai(int x,int y,boolean owned){
        super("Fai", Elements.MACHINE,300,300,250,70,50,owned,x,y,96,96,200,null);
        setImage(img);
        setDead_img(getName());
        setIdle_ally_img(getName());
        setIdle_battle_img(getName());
        setSpecial_img(getName());
        setSpecial_ally_img(getName());
    }


    @Override
    public boolean attack(Base_Monster otherMonster) {
        if (this.getMana() >= 100) {
            otherMonster.setMana(otherMonster.getMana() - this.getDmg());
            int netDmg = Math.max(0,(this.getDmg() - otherMonster.getDef()));
            otherMonster.setHp(otherMonster.getHp() - netDmg);
            this.setMana(this.getMana() - 100);
            if (this.isOwned()){
                Player.setUsed_Point(Player.getUsed_Point()-1);
            }
            return true;
        }else{
            System.out.println("You don't have enough mana");
            BattleFieldPane.getInstance().handleBattle("You don't have enough mana");
            return false;
        }
    }

    @Override
    public String getAttack() {
        return Base_Monster.toString("s","d",100,"(Debuff)");
    }

    @Override
    public boolean unique_ability(Base_Monster monster) {
        if (this.getMana()>=0){
            monster.setHp(monster.getHp()+50);
            monster.setMana(monster.getMana()+50);
            this.setMana(this.getMana());
            if (this.isOwned()){
                Player.setUsed_Point(Player.getUsed_Point()-1);
            }
            return true;
        }else{
            System.out.println("You don't have enough mana");
            BattleFieldPane.getInstance().handleBattle("You don't have enough mana");
            return false;
        }
    }

    @Override
    public String getUnique() {
        return Base_Monster.toString("s","b",0,"");
    }
}
