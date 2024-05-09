package inputs;

import java.awt.event.MouseListener;

public class MouseInputs implements MouseListener {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        System.out.println("Mouse Clicked");
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        System.out.println("Mouse Pressed");
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        System.out.println("Mouse Released");
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        System.out.println("Mouse Entered");
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        System.out.println("Mouse Exited");
    }
}
