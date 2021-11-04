package shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {

    private Color color;
    private double x;
    private double y;
    private double size;
    private Color borderColor;

    public Shape(Color color, double x, double y, double size) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.size = size;
        this.borderColor = Color.TRANSPARENT;
    }

    //Deep copy
    public Shape(Shape shape){
        this.color = shape.color;
        this.x = shape.getX();
        this.y = shape.getY();
        this.size = shape.getSize();
        this.borderColor = Color.TRANSPARENT;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public abstract boolean isInside(double x, double y);

    public abstract String shapeToSVG();

    public abstract void draw(GraphicsContext context);
}
