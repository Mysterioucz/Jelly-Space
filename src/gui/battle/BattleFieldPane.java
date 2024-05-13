package gui.battle;

import com.sun.webkit.graphics.WCGraphicsContext;
import entities.Monster.Base_Monster;
import entities.Player.Player;
import gui.MapPane;
import javafx.animation.PauseTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BattleFieldPane extends Pane {
    protected static BattleFieldPane instance;
    protected Base_Monster myMonster;
    protected Base_Monster enemyMonster;
    protected MonsterDetail myMonsterDetail;
    protected MonsterDetail enemyMonsterDetail;
    private Canvas battleCanvas;
    private GraphicsContext gc;
    private Text battleLog = new Text();
    private Image activeMonsterImage;
    private Image enemyMonsterImage;
    private double activeMonsterPosX = 100;
    private double activeMonsterPosY = 50;
    private double bossPosX = 850;
    private double bossPosY = 50;
    private long lastLogTime = 0;

    public BattleFieldPane() {
        // Done implement Player.getActiveMonster
        super();
        init();
        draw();
    }
    public void init(){
        setPrefSize(1250,300);
        setMaxSize(1250,300);
        // Set Background color
        Background bg = new Background(new BackgroundFill(Color.BLACK, new CornerRadii(15), null));
        setBackground(bg);
        setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(15), new BorderWidths(5))));
        // Set Player's active monster and enemy monster Image
        setMyMonster(Player.getActiveMonster());
        setEnemyMonster((Base_Monster) MapPane.getGameMap().getBoss());
        enemyMonsterImage = enemyMonster.getIdle_battle_img();
        activeMonsterImage = myMonster.getIdle_ally_img();
        // Create Monster Detail Text
        createMonsterDetail();
        // Create battleCanvas and add it to Pane
        battleCanvas = new Canvas(1128,300);
        gc = battleCanvas.getGraphicsContext2D();
        getChildren().addLast(battleCanvas);
        // Check if boss image is out of Canvas size
        if (bossPosX + enemyMonsterImage.getWidth() * 2 > 1128){
            bossPosX = bossPosX - (bossPosX + enemyMonsterImage.getWidth() * 2 - 1128);
        }

        setInstance(this);
    }
    public void createMonsterDetail(){
        // Create Monster Detail
        setMyMonsterDetail(new MonsterDetail(myMonster.getName(), String.valueOf(myMonster.getDmg()), String.valueOf(myMonster.getDef()), String.valueOf(myMonster.getHp()), String.valueOf(myMonster.getMana())));
        myMonsterDetail.setLayoutX(100 + activeMonsterImage.getWidth() * 2);
        myMonsterDetail.setLayoutY(activeMonsterImage.getHeight() - 5);
        enemyMonsterDetail = new MonsterDetail(enemyMonster.getName(), String.valueOf(enemyMonster.getDmg()), String.valueOf(enemyMonster.getDef()), String.valueOf(enemyMonster.getHp()), String.valueOf(enemyMonster.getMana()));
        enemyMonsterDetail.setLayoutX(850 - enemyMonsterImage.getWidth());
        enemyMonsterDetail.setLayoutY(enemyMonsterImage.getHeight() - 5);
        getChildren().addAll(myMonsterDetail,enemyMonsterDetail);
    }
    public void update(){
        if(myMonster.isDead()  && System.currentTimeMillis() - lastLogTime > 2000){
            handleBattle("This monster is dead!!!");
            ActionPane.getInstance().setDisable(true);
            setActiveMonsterImage(myMonster.getDead_img()); // update monster image in BattleFieldPane
            MonsterPane.getInstance().update(); // update monster image in MonsterPane
        }
        setMyMonster(Player.getActiveMonster());
        getChildren().remove(myMonsterDetail);
        getChildren().remove(enemyMonsterDetail);
        createMonsterDetail();

    }
    public void draw(){
        gc.clearRect(0,0,battleCanvas.getWidth(),battleCanvas.getHeight()); // Clear the old element before draw a new one
        gc.drawImage(activeMonsterImage,activeMonsterPosX,activeMonsterPosY,activeMonsterImage.getWidth()*2, activeMonsterImage.getHeight()*2);
        gc.drawImage(enemyMonsterImage,bossPosX,bossPosY, enemyMonsterImage.getWidth()*2, enemyMonsterImage.getHeight()*2);
    }
    public void handleBattle(String detail){
        long currentTime = System.currentTimeMillis();
        // Create & add new battle log
            // If the newBattleLog is not in the children list, remove the old one and add the new one
            getChildren().remove(battleLog);
            battleLog = new Text(detail);
            battleLog.setFont(Font.font("VCR OSD Mono", 20));
            battleLog.setFill(Color.WHITE);
            battleLog.setLayoutX(getWidth()/3);
            battleLog.setLayoutY(75);
            getChildren().addLast(battleLog);
            // set duration to show the battle log
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> getChildren().remove(battleLog));
            pause.play();
            lastLogTime = currentTime;

    }

    public void setMyMonster(Base_Monster myMonster) {
        try {
            if(!(this.myMonster.equals(myMonster))){
                System.out.println("Monster Changed");
                this.myMonster = myMonster;
                setActiveMonsterImage(myMonster.getIdle_ally_img());
            }
        }catch (NullPointerException e) {
            this.myMonster = myMonster;
            setActiveMonsterImage(myMonster.getIdle_ally_img());
        }

    }
    public void setMyMonsterDetail(MonsterDetail monsterDetail){
        myMonsterDetail = monsterDetail;
    }

    public Image getActiveMonsterImage() {
        return activeMonsterImage;
    }

    public void setActiveMonsterImage(Image activeMonsterImage) {
        this.activeMonsterImage = activeMonsterImage;
    }

    public Image getEnemyMonsterImage() {
        return enemyMonsterImage;
    }

    public void setEnemyMonsterImage(Image enemyMonsterImage) {
        this.enemyMonsterImage = enemyMonsterImage;
    }

    public static BattleFieldPane getInstance(){
        return instance;
    }

    public static void setInstance(BattleFieldPane instance) {
        BattleFieldPane.instance = instance;
    }

    public Base_Monster getMyMonster() {
        return myMonster;
    }

    public Base_Monster getEnemyMonster() {
        return enemyMonster;
    }

    public void setEnemyMonster(Base_Monster enemyMonster) {
        this.enemyMonster = enemyMonster;
    }

    public MonsterDetail getMyMonsterDetail() {
        return myMonsterDetail;
    }

    public MonsterDetail getEnemyMonsterDetail() {
        return enemyMonsterDetail;
    }

    public void setEnemyMonsterDetail(MonsterDetail enemyMonsterDetail) {
        this.enemyMonsterDetail = enemyMonsterDetail;
    }

    public Canvas getBattleCanvas() {
        return battleCanvas;
    }

    public void setBattleCanvas(Canvas battleCanvas) {
        this.battleCanvas = battleCanvas;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public Text getBattleLog() {
        return battleLog;
    }

    public void setBattleLog(Text battleLog) {
        this.battleLog = battleLog;
    }
}
