package gui.battle;

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


    public ActionPane() {
        super();
        init();
        instance = this;
    }

    public void init() {
        // Set font for the text
        actionText = new Text("Attack: Single Target");
        itemDetail = new Text("Item Detail: Heal 100 Hp");
        actionText.setFont(Font.font("VCR OSD Mono", 20));
        itemDetail.setFont(Font.font("VCR OSD Mono", 20));
        actionText.setFill(Color.WHITE);
        itemDetail.setFill(Color.WHITE);
        VBox textContainer = new VBox(actionText, itemDetail);
        add(textContainer, 1, 0);
        // Set Background color
        Background bg = new Background(new BackgroundFill(Color.BLACK, new CornerRadii(15), null));
        setBackground(bg);
        setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(5))));
        // Create Button
        createButton();
        // Create Monster Detail
        monsterDetail = new MonsterDetail("XXXX", "XX", "XX", "XX", "XX");
        add(monsterDetail, 1, 1,1,2);
        // Create ActionPoint
        createActionPoint();
        setVgap(10);
        setHgap(10);
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
    }
    public void createActionPoint(){
        Text actionPoint = new Text("Action Point: 3");
        actionPoint.setFont(Font.font("VCR OSD Mono", 20));
        actionPoint.setFill(Color.WHITE);
        add(actionPoint, 2, 2);
    }
    // TODO implement handleAttack, handleGuard, handleUnique
    public void handleAttack(){
        System.out.println("Attack");
        BattleFieldPane.getInstance().handleBattle("Attack");
    }
    public void handleGuard(){
        System.out.println("Guard");
        BattleFieldPane.getInstance().handleBattle("Guard");
    }
    public void handleUnique(){
        System.out.println("Unique");
        BattleFieldPane.getInstance().handleBattle("Unique");
    }
    public void setItemDetail(String detail){
        itemDetail.setText("Item Detail: " + detail);
    }
    public void setActionText(String action){
        actionText.setText(action);
    }

    public static ActionPane getInstance(){
        return instance;
    }
}
