package gui.battle;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MonsterDetail extends VBox {
    protected Text monsterName;
    protected Text monsterDamage;
    protected Text monsterDef;
    protected Text monsterHp;
    protected Text monsterMana;

    public MonsterDetail(String monsterName, String monsterDamage, String monsterDef, String monsterHp, String monsterMana) {
        super();
        setMonsterName(monsterName);
        setMonsterDamage(monsterDamage);
        setMonsterDef(monsterDef);
        setMonsterHp(monsterHp);
        setMonsterMana(monsterMana);
        getChildren().addAll(this.monsterName, this.monsterHp, this.monsterDamage, this.monsterDef, this.monsterMana);
        setSpacing(5);

    }


    public void setMonsterName(String monsterName) {
        Text monsterNameText = new Text(monsterName);
        monsterNameText.setFont(Font.font("VCR OSD Mono", 20));
        monsterNameText.setFill(Color.WHITE);
        this.monsterName = monsterNameText;
    }

    public void setMonsterDamage(String monsterDamage) {
        Text monsterDamageText = new Text("DMG: " + monsterDamage);
        monsterDamageText.setFont(Font.font("VCR OSD Mono", 20));
        monsterDamageText.setFill(Color.WHITE);
        this.monsterDamage = monsterDamageText;
    }

    public void setMonsterDef(String monsterDef) {
        Text monsterDefText = new Text("DEF: " + monsterDef);
        monsterDefText.setFont(Font.font("VCR OSD Mono", 20));
        monsterDefText.setFill(Color.WHITE);
        this.monsterDef = monsterDefText;
    }

    public void setMonsterHp(String monsterHp) {
        Text monsterHpText = new Text("HP: " + monsterHp);
        monsterHpText.setFont(Font.font("VCR OSD Mono", 20));
        monsterHpText.setFill(Color.WHITE);
        this.monsterHp = monsterHpText;
    }

    public void setMonsterMana(String monsterMana) {
        Text monsterManaText = new Text("MANA: " + monsterMana);
        monsterManaText.setFont(Font.font("VCR OSD Mono", 20));
        monsterManaText.setFill(Color.WHITE);
        this.monsterMana = monsterManaText;
    }
}
