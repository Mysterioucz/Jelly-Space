package entities.Monster;

import entities.Monster.Abilities.Elements;
import entities.Monster.Abilities.Unique_Ability;
import entities.Player.Player;
import javafx.scene.image.Image;

public class Fei extends Base_Monster implements Unique_Ability {
    private Image img = new Image(ClassLoader.getSystemResource("img/entities/monster/Fei/Idle.gif").toString());

    public Fei(int x,int y,boolean owned){
        super("Fei", Elements.EARTHLINGS,300,100,50,50,50,owned,x,y,96,96,200,null);
        setImage(img);
        setDead_img(getName());
        setIdle_ally_img(getName());
        setIdle_battle_img(getName());
        setSpecial_img(getName());
        setSpecial_ally_img(getName());
    }

    @Override
    public boolean unique_ability(Base_Monster monster) {
        if (this.getMana() >= 200){
            int netDmg = this.getDmg()+100-monster.getDef();
            this.setHp(getHp()-50);
            monster.setHp(monster.getHp()-netDmg);
            this.setMana(this.getMana()-200);
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
    public String getUnique() {
        return Base_Monster.toString("s","d");
    }
}
