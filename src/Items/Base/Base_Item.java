package Items.Base;

public abstract class Base_Item {
    private boolean isUsed;
    private int power;

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
}
