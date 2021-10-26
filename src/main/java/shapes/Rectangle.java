package shapes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape{
    public Rectangle(Color color, double x, double y, double value) {
        super(color, x, y, value);
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(this.getColor());
        context.fillRect(getX(),getY(),25,25);
    }
}
