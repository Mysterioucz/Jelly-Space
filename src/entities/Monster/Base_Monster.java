package entities.Monster;

import entities.Monster.Abilities.Elements;
import entities.Sprite;
import gui.MapPane;
import javafx.scene.image.Image;

public abstract class Base_Monster extends Sprite {
    private String name;
    private Elements element;
    private int maxHp;
    private int hp;
    private int maxMana;
    private int mana;
    private int baseDmg;
    private int dmg;
    private int baseDef;
    private int def;
    private int manaReg;
    private boolean owned;

    public Base_Monster(String name,Elements element,int maxHp,int maxMana,int manaReg,int baseDmg,int baseDef,boolean owned, double x, double y, double width, double height, double speed, Image img){
        super(x,y,width,height,speed,img);
        this.name = name;
        this.element = element;
        this.setMaxHp(maxHp);
        this.setHp(maxHp);
        this.setMaxMana(maxMana);
        this.setMana(maxMana);
        this.setManaReg(manaReg);
        this.setBaseDmg(baseDmg);
        this.setDmg(baseDmg);
        this.setBaseDef(baseDef);
        this.setDef(baseDef);
        this.owned = owned;
    }

    public void statBuff(Base_Monster monster){
        Elements e = monster.element;
        Elements m = this.element;

        if (e == Elements.ALIEN){
            if (m == Elements.MACHINE){
                this.setDmg(this.getDmg()+10);
            } else if (m == Elements.EARTHLINGS) {
                this.setDmg(this.getDmg()-10);
            }
        } else if (e == Elements.MACHINE){
            if (m == Elements.ALIEN){
                this.setDmg(this.getDmg()-10);
            } else if (m == Elements.EARTHLINGS) {
                this.setDmg(this.getDmg()+10);
            }
        } else if (e == Elements.EARTHLINGS){
            if (m == Elements.ALIEN){
                this.setDmg(this.getDmg()+10);
            } else if (m == Elements.MACHINE) {
                this.setDmg(this.getDmg()-10);
            }
        }
    }

    public boolean isDead(){
        if (this.getHp() == 0){
            return  true;
        }else{
            return false;
        }
    }

    public static String toString(String amount,String type){
        if (type == "b"){
            if (amount == "m"){
                return "Buff --> Multiple Targets";
            }else if (amount == "s"){
                return "Buff --> Single Target";
            } else{
                return null;
            }
        }else if (type == "d"){
            if (amount == "m"){
                return "Damage --> Multiple Targets";
            }else if (amount == "s"){
                return "Damage --> Single Target";
            } else{
                return null;
            }
        }else {
            return null;
        }
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = Math.max(0,maxHp);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0,Math.min(hp,maxHp));
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = Math.max(0,maxMana);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = Math.max(0,Math.min(mana,maxMana));
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = Math.max(0,dmg);
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = Math.max(0,def);
    }

    public int getBaseDmg() {
        return baseDmg;
    }

    public void setBaseDmg(int baseDmg) {
        this.baseDmg = Math.max(0,baseDmg);
    }

    public int getBaseDef() {
        return baseDef;
    }

    public void setBaseDef(int baseDef) {
        this.baseDef = Math.max(0,baseDef);
    }

    public int getManaReg() {
        return manaReg;
    }

    public void setManaReg(int manaReg) {
        this.manaReg = manaReg;
    }

    public boolean isOwned(){
        return owned;
    }

}
