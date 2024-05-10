package inputs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseInputs implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            System.out.println("Mouse Clicked");
        } else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            System.out.println("Mouse Pressed");
        } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            System.out.println("Mouse Released");
        } else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
            System.out.println("Mouse Entered");
        } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
            System.out.println("Mouse Exited");
        }
    }
}

