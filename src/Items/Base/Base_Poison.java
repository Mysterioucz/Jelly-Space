package Items.Base;

import entities.Monster.Base_Monster;
import entities.Player.Player;

public abstract class Base_Poison extends Base_Item implements Splashable{

    public Base_Poison(Type type,int power) {
        super(power);
        setType(type);
    }

    @Override
    public void use(Base_Monster monster) {
        if (getType() == Type.HEALTH){
            monster.setHp(monster.getHp()-getPower());
        } else if (getType() == Type.MANA) {
            monster.setMana(monster.getMana()-getPower());
        }
    }
}
