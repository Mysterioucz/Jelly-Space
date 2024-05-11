package entities.Monster;

import entities.Monster.Abilities.Elements;
import entities.Monster.Abilities.Unique_Ability;
import entities.Player.Player;
import javafx.scene.image.Image;

public class Fei extends Base_Monster implements Unique_Ability {
    private Image Idle = new Image(ClassLoader.getSystemResource("img/entities/monster/fei/Idle.gif").toString());
    private Image special = new Image(ClassLoader.getSystemResource("img/entities/monster/fei/special.gif").toString());

    public Fei(int x,int y,boolean owned){
        super("Fei", Elements.EARTHLINGS,300,100,50,50,50,owned,x,y,96,96,200,null);
        setImage(Idle);
    }

    @Override
    public void unique_ability(Base_Monster monster) {
        if (this.getMana() >= 200){
            int netDmg = this.getDmg()+100-monster.getDef();
            this.setHp(getHp()-50);
            monster.setHp(monster.getHp()-netDmg);
            if (this.isOwned()){
                Player.setUsed_Point(Player.getUsed_Point()+1);
            }else{
                System.out.println("You don't have enough mana");
            }
        }
    }
}
