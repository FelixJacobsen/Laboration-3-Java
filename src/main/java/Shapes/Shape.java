package Shapes;

import javafx.scene.paint.Color;

public class Shape {
    Color color;
    private double x;
    private double y;
    private double value;

    public Shape(Color color, double x, double y, double value) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.value = value;
    }

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

    public double getValue() {
        return value;
    }

    public Shape setValue(double value) {
        this.value = value;
        return this;
    }




}
