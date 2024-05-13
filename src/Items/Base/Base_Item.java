package Items.Base;

import javafx.scene.image.Image;

public abstract class Base_Item {
    private boolean isUsed;
    private int power;
    protected Image image;

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

}
