package shapes;

import javafx.scene.paint.Color;

public class Shapes {

    public static Shape circleOf(double x, double y, double value, Color color){
        return new Circle(color,x,y,value);
    }
}
