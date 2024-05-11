package entities.Monster;

import entities.Monster.Abilities.Attackable;
import entities.Monster.Abilities.Elements;
import entities.Monster.Abilities.Unique_Ability;
import entities.Player.Player;
import javafx.scene.image.Image;

public class Fai extends Base_Monster implements Attackable, Unique_Ability {
    private Image Idle = new Image(ClassLoader.getSystemResource("img/entities/monster/fai/Idle.gif").toString());
    private Image special = new Image(ClassLoader.getSystemResource("img/entities/monster/fai/special.gif").toString());

    public Fai(int x,int y,boolean owned){
        super("Fai", Elements.MACHINE,300,300,100,30,50,owned,x,y,96,96,200,null);
        setImage(Idle);
    }


    @Override
    public void attack(Base_Monster otherMonster) {
        statBuff(otherMonster);
        if (this.getMana() >= 200) {
            otherMonster.setMana(otherMonster.getMana() - this.getDmg());
            int netDmg = this.getDmg() - otherMonster.getDef();
            otherMonster.setHp(otherMonster.getHp() - netDmg);
            this.setMana(this.getMana() - 200);
            if (this.isOwned()){
                Player.setUsed_Point(Player.getUsed_Point()+2);
            }
        }else{
            System.out.println("You don't have enough mana");
        }
    }

    @Override
    public void unique_ability(Base_Monster monster) {
        if (this.getMana()>=100){
            monster.setHp(monster.getHp()+50);
            monster.setMana(monster.getMana()+50);
            this.setMana(this.getMana()-100);
            if (this.isOwned()){
                Player.setUsed_Point(Player.getUsed_Point()+2);
            }
        }else{
            System.out.println("You don't have enough mana");
        }
    }
}
