package Monster;

import Monster.Abilities.Elements;

import java.util.ArrayList;

public abstract class Base_Monster {
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

    public Base_Monster(String name,Elements element,int maxHp,int maxMana,int manaReg,int baseDmg,int baseDef){
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
    }

    public void statBuff(Base_Monster monster){
        Elements e = monster.element;
        Elements m = this.element;

        if (e == Elements.CODING){
            this.setDmg(this.getDmg()+10);
        } else if (m == Elements.CODING) {
            this.setDmg(this.getDmg()+10);
        }

        if (e == Elements.CALCULUS){
            if (m == Elements.CHEMISTRY){
                this.setDmg(this.getDmg()+10);
            } else if (m == Elements.PHYSICS) {
                this.setDmg(this.getDmg()-10);
            }
        } else if (e == Elements.PHYSICS) {
            if (m == Elements.CHEMISTRY){
                this.setDmg(this.getDmg()-10);
            } else if (m == Elements.CALCULUS) {
                this.setDmg(this.getDmg()+10);
            }
        } else if (e == Elements.CHEMISTRY) {
            if (m == Elements.CALCULUS){
                this.setDmg(this.getDmg()-10);
            } else if (m == Elements.PHYSICS) {
                this.setDmg(this.getDmg()+10);
            }
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
}
