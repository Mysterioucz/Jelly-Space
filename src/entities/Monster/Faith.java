package entities.Monster;

import entities.Monster.Abilities.Attackable;
import entities.Monster.Abilities.Guardable;
import entities.Monster.Abilities.Unique_Ability;
import entities.Player.Player;
import javafx.scene.image.Image;

public class Faith extends Base_Monster implements Attackable, Guardable, Unique_Ability {
    private Image img = new Image(ClassLoader.getSystemResource("img/entities/monster/Faith/Idle.gif").toString());

    public Faith(int x, int y){
        super("Faith", null,1000,1000,300,100,60,false,x,y,192,192,0,null);
        setImage(img);
        setIdle_battle_img(getName());
        setSpecial_img(getName());
    }


    @Override
    public boolean attack(Base_Monster otherMonster) {
        // True Damage
        if (getMana()>=300){
            otherMonster.setHp(otherMonster.getHp()-this.getDmg());
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
        if (this.getMana()>=400){
            ChosenMonster.setDmg(0);
            this.setMana(this.getMana() - 400);
            return true;
        }else{
            System.out.println("You don't have enough mana");
            return false;
        }
    }

    @Override
    public String getGuard() {
        return null;
    }

    @Override
    public boolean unique_ability(Base_Monster monster) {
        if (this.getMana()>=900){
            this.setDmg(this.getDmg()+monster.getBaseDef()+this.getDef());
            this.setDef(0);
            monster.setBaseDef(0);
            monster.setDef(0);
            for (Base_Monster e: Player.getMy_monster().getMonsters()){
                int netDmg = this.getDmg()-e.getDef();
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
