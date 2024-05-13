package Items.Base;

import javafx.scene.image.Image;

public abstract class Base_Item {
    private Type type;
    private boolean isUsed;
    private int power;
    protected Image image;

    public String toString(){
        if (type == Type.HEALTH){
            if (this instanceof Consumeable){
                return "Heal "+getPower()+" HP";
            }else if (this instanceof Splashable){
                return "Damage "+getPower()+" HP";
            }else {
                return null;
            }
        } else if (type == Type.MANA) {
            if (this instanceof Consumeable){
                return "Increase "+getPower()+" MP";
            }else if (this instanceof Splashable){
                return "Decrease "+getPower()+" MP";
            }else {
                return null;
            }
        } else {
            return "Stunt";
        }
    }

    public Base_Item(int power){
        this.setPower(power);
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = Math.max(0,power);
    }

    public Image getImage() {
        return image;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
