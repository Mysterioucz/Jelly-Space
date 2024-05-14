package Items.Base;

import entities.Monster.Base_Monster;
import entities.Player.Player;
import gui.battle.BattleFieldPane;
import javafx.application.Platform;

public abstract class Base_Potion extends Base_Item implements Consumeable{
    public Base_Potion(Type type,int power) {
        super(power);
        setType(type);
    }

    @Override
    public void use(Base_Monster monster) {
        if (!monster.isDead()) {
            if (getType() == Type.HEALTH) {
                monster.setHp(monster.getHp() + this.getPower());
            } else if (getType() == Type.MANA) {
                monster.setMana(monster.getMana() + this.getPower());
            }
        }else{
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    BattleFieldPane.getInstance().handleBattle("You can't use potion on dead monster");
                }
            });
        }
    }
}
