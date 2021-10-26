package shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape{
    public Circle(Color color, double x, double y, double value) {
        super(color, x, y, value);
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getColor());
        context.fillOval(getX(),getY(),25,25);
    }

}
