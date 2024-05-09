package inputs;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyboardInputs implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent event) {
        System.out.println("Key Pressed");
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            switch (event.getCode()) {
                case W:
                case UP:
                    System.out.println("Up");
                    break;
                case S:
                case DOWN:
                    System.out.println("Down");
                    break;
                case A:
                case LEFT:
                    System.out.println("Left");
                    break;
                case D:
                case RIGHT:
                    System.out.println("Right");
                    break;
                default:
                    break;
            }
        }
    }
}