package boundaries;

import javafx.scene.canvas.GraphicsContext;

public class EllipseBoundary extends Boundary{
    private double centerX,centerY,radiusX,radiusY;

    public EllipseBoundary(double centerX, double centerY, double radiusX, double radiusY){
        this.centerX = centerX;
        this.centerY = centerY;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }
    public boolean isWithinBoundary(double x, double y){
        return Math.pow((x-centerX)/radiusX,2) + Math.pow((y-centerY)/radiusY,2) <= 1;
    }
    public void drawBoundary(GraphicsContext gc){
//        System.out.println("EllipseBoundary: (" + centerX + ", " + centerY + ") " + radiusX + " " + radiusY);
        gc.strokeOval(centerX-radiusX, centerY-radiusY, 2*radiusX, 2*radiusY);
    }
}
