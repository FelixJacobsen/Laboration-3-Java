package shapes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape{
    public Rectangle(Color color, double x, double y, double value) {
        super(color, x, y, value);

    }

    public Rectangle(Shape shape){
        super(shape);
    }

    @Override
    public boolean isInside(double x, double y) {
    double left = getX() - getSize();
    double top = getY() - getSize();

    return x >= left &&
            x <= left + 2 * getSize() &&
            y >= top &&
            y <= top + 2 * getSize();
    }


    @Override
    public void draw(GraphicsContext context) {
        context.setFill(getBorderColor());
        context.fillRect(getX() - 2.5 ,getY() - 2.5,getSize() + 5,getSize() + 5);

        context.setFill(this.getColor());
        context.fillRect(getX(),getY(),getSize(),getSize());
    }

    @Override
    public String shapeToSVG() {
        String convertedColor = "#" + getColor().toString().substring(2,10);

        return "<rect x=\"" + getSize() + "\" "
                + "y=\"" + getY() + "\" "
                + "width=\"" + getSize() * 2 + "\" "
                + "height=\"" + getSize() * 2 + "\" "
                + "fill=\"" + convertedColor + "\" />";
    }
}
