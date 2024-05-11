package boundaries;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LinearBoundary extends Boundary{
    private double m;
    private double c;

    public LinearBoundary(double m, double c){
        this.m = m;
        this.c = c;
    }
    public boolean isWithinBoundary(double x, double y){
        return y <= m*x + c;
    }
    public void drawBoundary(GraphicsContext gc){
//        System.out.println("LinearBoundary: y = " + m + "x + " + c);
        gc.setStroke(Color.CYAN);
        gc.strokeLine(0, c, 1280, m*1280 + c);
    }
}
