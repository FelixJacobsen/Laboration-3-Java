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

    public abstract boolean isInside(double x, double y);

    public abstract void draw(GraphicsContext context);

    public Color getColor() {
        return color;
    }

    public Shape setColor(Color color) {
        this.color = color;
        return this;
    }

    public double getX() {
        return x;
    }

    public Shape setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public Shape setY(double y) {
        this.y = y;
        return this;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }


    public abstract String shapeToSVG();

    public Color getBorderColor() {
        return borderColor;
    }

    public Shape setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        return this;
    }
}
