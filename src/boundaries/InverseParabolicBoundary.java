package boundaries;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class InverseParabolicBoundary extends Boundary {
    private double h, k, c;

    public InverseParabolicBoundary(double h, double k, double c) {
        this.h = h;
        this.k = k;
        this.c = c;
    }

    public boolean isWithinBoundary(double x, double y) {
//        System.out.println("ParabolicBoundary: " + (y - k) + " >= " + c * Math.pow((x - h), 2));
//        System.out.println("Current Position: " + x + ", " + y);
        return (y - k) >= -c * Math.pow((x - h), 2);
    }

    public void drawBoundary(GraphicsContext gc) {
        gc.setStroke(Color.RED); // Set the color of the boundary
        gc.setLineWidth(2); // Set the width of the boundary

        // Start at the leftmost point of the boundary
        double x = 0;
        double y = -c * Math.pow((x - h), 2) + k;
        double prevX = x;
        double prevY = y;

        // Generate points along the boundary and draw lines between them
        for (x = 1; x <= 1280; x++) { // Adjust the range as needed
            y = -c * Math.pow((x - h), 2) + k;
            gc.strokeLine(prevX, prevY, x, y);
            prevX = x;
            prevY = y;
        }
    }
}
