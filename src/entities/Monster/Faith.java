package entities.Monster;

import entities.Monster.Abilities.Attackable;
import entities.Monster.Abilities.Guardable;
import entities.Monster.Abilities.Unique_Ability;
import entities.Player.Player;
import javafx.scene.image.Image;

public class Faith extends Base_Monster implements Attackable, Guardable, Unique_Ability {
    private Image img = new Image(ClassLoader.getSystemResource("img/entities/monster/Faith/Idle.gif").toString());

    public Faith(int x, int y){
        super("Faith",1500,1000,300,150,60,false,x,y,192,192,0,null);
        setImage(img);
        setIdle_battle_img(getName());
    }


    @Override
    public boolean attack(Base_Monster otherMonster) {
        // True Damage
        if (getMana()>=300){
            this.setDmg(this.getDmg()+10);
            int netDmg = Math.max(0,this.getDmg()+this.getDef()/2-otherMonster.getDef());
            this.setDef(0);
            otherMonster.setHp(otherMonster.getHp()-netDmg);
            this.setMana(this.getMana() - 300);
            return true;
        }else{
            System.out.println("You don't have enough mana");
            return false;
        }
    }

    @Override
    public String getAttack() {
        return null;
    }

    @Override
    public boolean guard(Base_Monster ChosenMonster) {
        if (this.getDef() + 60 <= 60) {
            if (this.getMana() >= 400) {
                this.setDef(this.getDef()+60);
                this.setMana(this.getMana() - 400);
                this.setDmg(this.getDmg() + 10);
                return true;
            } else {
                System.out.println("You don't have enough mana");
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public boolean unique_ability(Base_Monster monster) {
        if (this.getMana()>=900){
            int dmg = (this.getDmg()+monster.getBaseDef()+this.getDef());
            this.setDef(0);
            monster.setBaseDef(0);
            monster.setDef(0);
            for (Base_Monster e: Player.getMy_monster().getMonsters()){
                int netDmg = Math.max(dmg-e.getDef(),0);
                e.setHp(e.getHp()-netDmg);
            }
            this.setMana(this.getMana() - 900);
            return true;
        }else{
            System.out.println("You don't have enough mana");
            return false;
        }
    }

    @Override
    public String getUnique() {
        return null;
    }

}
