package entities.Monster;

import entities.Monster.Abilities.Attackable;
import entities.Monster.Abilities.Elements;
import entities.Monster.Abilities.Unique_Ability;
import entities.Player.Player;
import javafx.scene.image.Image;

public class TU_Force extends Base_Monster implements Attackable, Unique_Ability {
    private Image Idle_battle = new Image(ClassLoader.getSystemResource("img/entities/monster/tu_force/Idle_battle.gif").toString());
    private Image Idle_right = new Image(ClassLoader.getSystemResource("img/entities/monster/tu_force/Idle.gif").toString());
    private Image special = new Image(ClassLoader.getSystemResource("img/entities/monster/tu_force/special.gif").toString());


    public TU_Force(int x, int y, boolean owned){
        super("TU Force", Elements.ALIEN,500,400,50,60,60,owned,x,y,188,100,200,null);
        setImage(Idle_right);
    }


    @Override
    public boolean attack(Base_Monster otherMonster) {
        if (this.getMana()>=100){
            this.setMana(this.getMana() - 100);
            if (this.isOwned()){
                int netDmg = this.getDmg()-otherMonster.getDef();
                Player.setUsed_Point(Player.getUsed_Point()+1);
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
            return false;
        }
    }

    @Override
    public String getAttack() {
        return Base_Monster.toString("s","d");
    }

    @Override
    public boolean unique_ability(Base_Monster monster) {
        if (this.getMana()>=300){
            monster.setDmg(monster.getDmg()+100);
            this.setMana(this.getMana() - 300);
            if (this.isOwned()){
                Player.setUsed_Point(Player.getUsed_Point()+2);
            }
            return true;
        }else{
            System.out.println("You don't have enough mana");
            return false;
        }
    }

    @Override
    public String getUnique() {
        return Base_Monster.toString("s","b");
    }
}
