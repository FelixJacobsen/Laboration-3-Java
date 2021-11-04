package shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape{
    private double radius;
    public Circle(Color color, double x, double y, double value) {
        super(color, x, y, value);
    }


    public Circle(Shape shape){
        super(shape);
    }
    @Override
    public boolean isInside(double x, double y) {
        double dx = x - getX();
        double dy = y - getY();
        double distanceFromCircleCenterSquared = dx * dx + dy * dy;
        return distanceFromCircleCenterSquared < radius*radius;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getBorderColor());
        context.fillOval(getX() - 2.5,getY() - 2.5,getSize() + 5,getSize() + 5);

        context.setFill(getColor());
        context.fillOval(getX(),getY(),getSize(),getSize());
    }

    @Override
    public String shapeToSVG() {
        String convertedColor = "#" + getColor().toString().substring(2,10);

        return "<circle cx=\"" + getSize() + "\" "
                + "cy=\"" + getY() + "\" "
                + "r=\"" + getSize()  + "\" "
                + "fill=\"" + convertedColor + "\" />";
    }


}
