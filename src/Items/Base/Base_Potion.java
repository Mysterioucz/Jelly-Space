package Items.Base;

import entities.Monster.Base_Monster;
import entities.Player.Player;

public abstract class Base_Potion extends Base_Item implements Consumeable{
    public Base_Potion(Type type,int power) {
        super(power);
        setType(type);
    }

    @Override
    public void use(Base_Monster monster) {
        if (getType() == Type.HEALTH){
            monster.setHp(monster.getHp()+this.getPower());
        } else if (getType() == Type.MANA) {
            monster.setMana(monster.getMana()+this.getPower());
        }
    }
}
