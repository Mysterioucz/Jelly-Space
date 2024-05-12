package entities.Monster;

import entities.Monster.Abilities.Attackable;
import entities.Monster.Abilities.Elements;
import entities.Monster.Abilities.Guardable;
import entities.Monster.Abilities.Unique_Ability;
import entities.Player.Player;
import javafx.scene.image.Image;

public class Chatrin extends Base_Monster implements Attackable, Guardable, Unique_Ability {
    private Image img = new Image(ClassLoader.getSystemResource("img/entities/monster/chatrin/Idle.gif").toString()) ;
    private Image special = new Image(ClassLoader.getSystemResource("img/entities/monster/chatrin/special.gif").toString());

    public Chatrin(int x,int y){
        super("Chatrin", Elements.EARTHLINGS,250,50,10,50,20,true,x,y,96,96,0,null);
        setImage(img);
    }

    @Override
    public boolean attack(Base_Monster otherMonster) {
        statBuff(otherMonster);
        int dmgNet = this.getDmg()-otherMonster.getDef();
        otherMonster.setHp(otherMonster.getHp()-dmgNet);
        if (this.isOwned()){
            Player.setUsed_Point(Player.getUsed_Point()+1);
        }
        return true;
    }

    @Override
    public String getAttack() {
        return Base_Monster.toString("s","d");
    }

    @Override
    public boolean guard(Base_Monster ChosenMonster) {
        if (this.getMana() >= 30) {
            this.setHp(this.getDef() + 10);
            this.setMana(this.getMana() - 30);
            if (this.isOwned()){
                Player.setUsed_Point(Player.getUsed_Point()+1);
            }
            return true;
        }else{
            System.out.println("You don't have enough mana");
            return false;
        }
    }

    @Override
    public String getGuard() {
        return Base_Monster.toString("s","b");
    }

    @Override
    public boolean unique_ability(Base_Monster monster) {
        monster.setHp(monster.getHp()-this.getHp());
        this.setHp(0);
        if (this.isOwned()){
            Player.setUsed_Point(Player.getUsed_Point()+2);
        }
        return true;
    }

    @Override
    public String getUnique() {
        return Base_Monster.toString("s","d");
    }
}
