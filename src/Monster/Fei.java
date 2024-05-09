package Monster;

import Monster.Abilities.Attackable;
import Monster.Abilities.Elements;
import Monster.Abilities.Guardable;
import Monster.Abilities.Unique_Ability;
import Player.Player;

public class Fei extends Base_Monster implements Unique_Ability {
    public Fei(boolean owned){
        super("Fei", Elements.EARTHLINGS,300,100,50,50,50,owned);
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
