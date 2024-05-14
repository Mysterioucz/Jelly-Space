package entities.Monster;

import entities.Monster.Abilities.Attackable;
import entities.Monster.Abilities.Unique_Ability;
import entities.Player.Player;
import gui.battle.BattleFieldPane;
import javafx.scene.image.Image;

public class TU_Force extends Base_Monster implements Attackable, Unique_Ability {
    private Image img = new Image(ClassLoader.getSystemResource("img/entities/monster/TU_Force/Idle.gif").toString());

    public TU_Force(int x, int y, boolean owned){
        super("TU_Force",1000,400,100,100,60,owned,x,y,188,100,200,null);
        setImage(img);
        setDead_img(getName());
        setIdle_ally_img(getName());
        setIdle_battle_img(getName());
        setSpecial_ally_img(getName());
    }


    @Override
    public boolean attack(Base_Monster otherMonster) {
        if (this.getMana()>=100){
            this.setMana(this.getMana() - 100);
            if (this.isOwned()){
                int netDmg = this.getDmg()-otherMonster.getDef();
                Player.setUsed_Point(Player.getUsed_Point()-1);
                for (Base_Monster e: Player.getMy_monster().getMonsters()){
                    netDmg = netDmg + e.getDmg()/2;
                }
                otherMonster.setHp(otherMonster.getHp()-netDmg);
            }else{
                for (Base_Monster e: Player.getMy_monster().getMonsters()){
                    int netDmg = this.getDmg()-e.getDef();
                    e.setHp(e.getHp()-netDmg);
                }
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
        return Base_Monster.toString("s","d",1,"");
    }

    @Override
    public boolean unique_ability(Base_Monster monster) {
        if (this.getMana()>=300){
            monster.setDmg(monster.getDmg()+100);
            Player.getActiveMonster().setHp(Player.getActiveMonster().getHp()-this.getDmg());
            this.setMana(this.getMana() - 300);
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
        return Base_Monster.toString("s","b",1,"");
    }

}
