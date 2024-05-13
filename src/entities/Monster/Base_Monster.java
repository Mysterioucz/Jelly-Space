package entities.Monster;

import entities.Monster.Abilities.Attackable;
import entities.Monster.Abilities.Elements;
import entities.Monster.Abilities.Guardable;
import entities.Monster.Abilities.Unique_Ability;
import entities.Player.Player;
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
    private Image special_img;
    private Image dead_img;
    private Image idle_ally_img;
    private Image idle_battle_img;
    private Image special_ally_img;

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

    public boolean isDead(){
        if (this.getHp() == 0){
            return  true;
        }else{
            return false;
        }
    }

    public static String toString(String amount,String type,int point){
        if (type == "b"){
            if (amount == "m"){
                return "Buff --> Multiple Targets"+" (Mana: "+point+")";
            }else if (amount == "s"){
                return "Buff --> Single Target"+" (Mana: "+point+")";
            } else{
                return null;
            }
        }else if (type == "d"){
            if (amount == "m"){
                return "Damage --> Multiple Targets"+" (Mana: "+point+")";
            }else if (amount == "s"){
                return "Damage --> Single Target"+" (Mana: "+point+")";
            } else{
                return null;
            }
        }else {
            return null;
        }
    }

    public static String Choose_Boss_Ability(Base_Monster monster) {
        Base_Monster active_monster = Player.getActiveMonster();
        if (monster instanceof Unique_Ability) {
            if (monster instanceof Fai || monster instanceof TU_Force) {
                if (((Unique_Ability) monster).unique_ability(monster)) {
                    ((Unique_Ability) monster).unique_ability(monster);
                    return "Unique Ability";
                }
            } else {
                if (((Unique_Ability) monster).unique_ability(active_monster)) {
                    ((Unique_Ability) monster).unique_ability(active_monster);
                    return "Unique Ability";
                }
            }
        }
        if (monster instanceof Guardable) {
            if (((Guardable) monster).guard(active_monster)) {
                ((Guardable) monster).guard(active_monster);
                return "Guard";
            }
        }
        if (monster instanceof Attackable){
            if (((Attackable) monster).attack(active_monster)){
                ((Attackable) monster).attack(active_monster);
                return "Attack";
            }
        }
        return "Nothing";
    }

    public String getName() {
        return name;
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

    public Image getSpecial_img() {
        return special_img;
    }

    public void setSpecial_img(String name) {
        this.special_img = new Image(ClassLoader.getSystemResource("img/entities/monster/"+name+"/special.gif").toString());;
    }

    public Image getDead_img() {
        return dead_img;
    }

    public void setDead_img(String name) {
        this.dead_img = new Image(ClassLoader.getSystemResource("img/entities/monster/"+name+"/Dead.gif").toString());
    }

    public Image getIdle_ally_img() {
        return idle_ally_img;
    }

    public void setIdle_ally_img(String name) {
        this.idle_ally_img = new Image(ClassLoader.getSystemResource("img/entities/monster/"+name+"/Idle_ally.gif").toString());;
    }

    public Image getIdle_battle_img() {
        return idle_battle_img;
    }

    public void setIdle_battle_img(String name) {
        this.idle_battle_img = new Image(ClassLoader.getSystemResource("img/entities/monster/"+name+"/Idle_battle.gif").toString());;
    }

    public Image getSpecial_ally_img() {
        return special_ally_img;
    }

    public void setSpecial_ally_img(String name) {
        this.special_ally_img = new Image(ClassLoader.getSystemResource("img/entities/monster/"+name+"/special_ally.gif").toString());;
    }
    public void startTurn(){
        this.setMana(this.getMana()+this.getManaReg());
        this.setBaseDef(getBaseDef());
        this.setDmg(getBaseDmg());
    }
}
