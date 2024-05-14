package entities.Player;

import entities.Sprite;
import javafx.scene.image.Image;

public class Rocket extends Sprite {
    private Image rocket = new Image(ClassLoader.getSystemResource("img/entities/rocket/rocket.png").toString());
    private Image purple_rocket = new Image(ClassLoader.getSystemResource("img/entities/rocket/purple_rocket.png").toString());
    private Image red_rocket = new Image(ClassLoader.getSystemResource("img/entities/rocket/red_rocket.png").toString());



    public Rocket(double x,double y,String rocketColor){
        super(x,y,175,170,0,null);
        setRocket(rocketColor);
    }

    public void setRocket(String color){
        if(color.equals("purple")){
            this.setImage(purple_rocket);
        }else if(color.equals("red")){
            this.setImage(red_rocket);
        }else{
            this.setImage(rocket);
        }
    }
}
