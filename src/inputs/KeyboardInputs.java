package inputs;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import entities.Player.Player;

public class KeyboardInputs implements EventHandler<KeyEvent> {
    public boolean up, down, left, right;


    @Override
    public void handle(KeyEvent event) {
        System.out.println("Key Pressed");
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            switch (event.getCode()) {
                case W:
                case UP:
                    System.out.println("Up");
                    up = true;
                    break;
                case S:
                case DOWN:
                    System.out.println("Down");
                    down = true;
                    break;
                case A:
                case LEFT:
                    System.out.println("Left");
                    left = true;
                    break;
                case D:
                case RIGHT:
                    System.out.println("Right");
                    right = true;
                    break;
                default:
                    break;
            }
        }
        if(event.getEventType() == KeyEvent.KEY_RELEASED){
            switch (event.getCode()) {
                case W:
                case UP:
                    System.out.println("Up Released");
                    up = false;
                    break;
                case S:
                case DOWN:
                    System.out.println("Down Released");
                    down = false;
                    break;
                case A:
                case LEFT:
                    System.out.println("Left Released");
                    left = false;
                    break;
                case D:
                case RIGHT:
                    System.out.println("Right Released");
                    right = false;
                    break;
                default:
                    break;
            }
        }
    }
}