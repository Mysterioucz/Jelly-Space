package Items.Base;

import entities.Monster.Base_Monster;

public abstract class Base_Potion extends Base_Item implements Consumeable{
    Type type;

    public Base_Potion(Type type,int power) {
        super(power);
        setType(type);
    }

    @Override
    public void drink(Base_Monster monster) {
        if (getType() == Type.HEALTH){
            monster.setHp(monster.getHp()+this.getPower());
        } else if (getType() == Type.MANA) {
            monster.setMana(monster.getMana()+this.getPower());
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
