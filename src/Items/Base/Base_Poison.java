package Items.Base;

import entities.Monster.Base_Monster;

public abstract class Base_Poison extends Base_Item implements Splashable{
    Type type;

    public Base_Poison(Type type,int power) {
        super(power);
        setType(type);
    }

    @Override
    public void splash(Base_Monster monster) {
        if (getType() == Type.HEALTH){
            monster.setHp(monster.getHp()-getPower());
        } else if (getType() == Type.MANA) {
            monster.setMana(monster.getMana()-getPower());
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
