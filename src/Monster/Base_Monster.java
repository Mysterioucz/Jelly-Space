package Monster;

import java.util.ArrayList;

public abstract class Base_Monster {
    private String name;
    private int maxHp;
    private int hp;
    private int maxMana;
    private int mana;
    private int MaxDmg;
    private int dmg;
    private int MaxDef;
    private int def;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getMaxDmg() {
        return MaxDmg;
    }

    public void setMaxDmg(int maxDmg) {
        MaxDmg = Math.max(0,maxDmg);
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = Math.max(0,Math.min(dmg,MaxDmg));
    }

    public int getMaxDef() {
        return MaxDef;
    }

    public void setMaxDef(int maxDef) {
        MaxDef = Math.max(0,maxDef);
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = Math.max(0,Math.min(def,MaxDef));
    }
}
