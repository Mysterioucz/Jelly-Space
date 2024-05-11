package boundaries;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleBoundary extends Boundary{
    private double x1,y1,x2,y2;
    public RectangleBoundary(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public boolean isWithinBoundary(double x, double y){
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }

    @Override
    public void drawBoundary(GraphicsContext gc) {
        System.out.println("Drawing Rectangle Boundary");
        gc.setStroke(Color.CYAN);
        gc.strokeRect(x1, y1, x2-x1, y2-y1);
    }
}
