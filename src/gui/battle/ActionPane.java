package gui.battle;

import entities.Monster.Abilities.Attackable;
import entities.Monster.Abilities.Guardable;
import entities.Monster.Abilities.Unique_Ability;
import entities.Monster.Base_Monster;
import entities.Monster.Fai;
import entities.Monster.TU_Force;
import entities.Player.Player;
import gui.MapPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ActionPane extends GridPane {
    protected static ActionPane instance;
    protected Text actionText;
    protected Text itemDetail;
    protected ImageView attackButton;
    protected ImageView GuardButton;
    protected ImageView UniqueButton;
    protected  MonsterDetail monsterDetail;
    private Base_Monster boss;
    private Base_Monster myMonster;
    private int cellHeight = 78;


    public ActionPane() {
        super();
        init();
        boss = (Base_Monster) MapPane.getGameMap().getBoss();
        myMonster = Player.getActiveMonster();
        setGridLinesVisible(true); // for debugging
        instance = this;
    }

    public void init() {
        setPrefSize(678,256);
        setMaxSize(678,256);
        // Create row constraints
        for (int i = 0; i < 3; i++) {
            RowConstraints row = new RowConstraints();
            row.setPrefHeight(cellHeight);
            getRowConstraints().add(row); // Add the constraint to the row
        }
        // Set Background color
        Background bg = new Background(new BackgroundFill(Color.BLACK, new CornerRadii(15), null)); // Color.WHITE for debugging
        setBackground(bg);
        setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(5))));
        // Create Button & Text
        createButton();
        createText();
        // Create ActionPoint
        createActionPoint();
        setVgap(10);
        setHgap(10);


    }
    public void createText(){
        // Set font for the text
        actionText = new Text("Attack: Single Target");
        itemDetail = new Text("Item Detail: Heal 100 Hp");
        actionText.setFont(Font.font("VCR OSD Mono", 20));
        itemDetail.setFont(Font.font("VCR OSD Mono", 20));
        actionText.setFill(Color.WHITE);
        itemDetail.setFill(Color.WHITE);
        VBox textContainer = new VBox(actionText, itemDetail);
        textContainer.setPrefSize(300,50);
        textContainer.setSpacing(10);
        textContainer.setMaxSize(300,50);
        add(textContainer, 1, 0);
        // Create Monster Detail
        monsterDetail = new MonsterDetail("XXXX", "XX", "XX", "XX", "XX");
        add(monsterDetail, 1, 1,1,2);
        // Set alignment for the text
        setValignment(textContainer, VPos.CENTER);
        setValignment(monsterDetail, VPos.CENTER);
    }
    public void createButton(){
        attackButton = new ImageView(new Image(ClassLoader.getSystemResource("img/Components/battleComponent/ActionPane/attackBtn.png").toString()));
        GuardButton = new ImageView(new Image(ClassLoader.getSystemResource("img/Components/battleComponent/ActionPane/guardBtn.png").toString()));
        UniqueButton = new ImageView(new Image(ClassLoader.getSystemResource("img/Components/battleComponent/ActionPane/uniqueBtn.png").toString()));
        attackButton.setOnMouseClicked(e -> handleAttack());
        GuardButton.setOnMouseClicked(e -> handleGuard());
        UniqueButton.setOnMouseClicked(e -> handleUnique());
        add(attackButton,0,0);
        add(GuardButton,0,1);
        add(UniqueButton,0,2);
        setHalignment(attackButton, HPos.CENTER);
        setHalignment(GuardButton, HPos.CENTER);
        setHalignment(UniqueButton, HPos.CENTER);
        setValignment(attackButton, VPos.CENTER);
        setValignment(GuardButton, VPos.CENTER);
        setValignment(UniqueButton, VPos.CENTER);

    }
    public void createActionPoint(){
        Text actionPoint = new Text("Action Point: " + String.valueOf(Player.getACTION_POINT())+"/3");
        actionPoint.setFont(Font.font("VCR OSD Mono", 20));
        actionPoint.setFill(Color.WHITE);
        actionPoint.setTranslateX(-20);
        add(actionPoint, 2, 2);
    }
    // Done implement handleAttack, handleGuard, handleUnique
    public void handleAttack(){
        if(myMonster instanceof Attackable){
            System.out.println("Attack");
            // Create Action text in BattlefieldPane
            BattleFieldPane.getInstance().handleBattle("Attack");
            // Attack the boss
            ((Attackable) myMonster).attack(boss);
        }else{
            System.out.println("This monster can't attack");
        }
    }
    public void handleGuard(){
        if(myMonster instanceof Guardable){
            System.out.println("Guard");
            // Create Action text in BattlefieldPane
            BattleFieldPane.getInstance().handleBattle("Guard");
            // Guard the boss
            ((Guardable) myMonster).guard(myMonster);
        }

    }
    public void handleUnique(){
        System.out.println("Unique");
        // Create Action text in BattlefieldPane
        BattleFieldPane.getInstance().handleBattle("Unique");
        // use Unique ability on the boss
        if(myMonster instanceof Fai || myMonster instanceof TU_Force){
            // Buff themselves
            ((Unique_Ability) myMonster).unique_ability(myMonster);
        }else{
            // Debuff/Attack the boss
            ((Unique_Ability) myMonster).unique_ability(boss);
        }


    }
    public void setItemDetail(String detail){
        itemDetail.setText("Item Detail: " + detail);
    }
    public void setMonsterDetail(MonsterDetail monsterDetail){
        // remove old monsterDetail
        getChildren().remove(this.monsterDetail);
        // Set new monsterDetail and add it to the ActionPane
        this.monsterDetail = monsterDetail;
        add(monsterDetail, 1, 1,1,2);
        System.out.println("Monster Detail set");
    }
    public void setActionText(String action){
        actionText.setText(action);
    }

    public static ActionPane getInstance(){
        return instance;
    }
}
