package entities;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class Sprite {
    private double x; // X position
    private double y; // Y position
    private double width; // Width of the sprite
    private double height; // Height of the sprite
    private double speed;
    private Image image;
    private Image[] frames;
    private ImageView imageView;
    private AnimationTimer timer;

    public Sprite(double x, double y, double width, double height,double speed,Image img) {
        // Constructor for the sprite with Gif image
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setSpeed(speed);
        setImage(img);
    }

    public void move(double newX, double newY) {
        // Move the sprite by dx and dy
        x = newX;
        y = newY;
    }
    public boolean isColliding(Sprite other) {
        // Check if this sprite is colliding with another sprite
        return x < other.x + other.width && x + width > other.x && y < other.y + other.height && y + height > other.y;
    }

    // Getter & Setter for each field
    public double getSpeed(){
        return speed;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public void setImage(Image img){
        this.image = img;
    }
    public Image getImage(){
        return image;
    }
}
