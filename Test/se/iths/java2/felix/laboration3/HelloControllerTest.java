package se.iths.java2.felix.laboration3;


import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import shapes.FactoryShapes;
import shapes.Shape;
import static org.junit.jupiter.api.Assertions.*;
class HelloControllerTest {

    HelloController helloController = new HelloController(new Model());
    Model model = helloController.model;
    Shape shape = FactoryShapes.circleOf(Color.RED, 10, 20, 10);
    Shape shape2 = FactoryShapes.circleOf(Color.BLACK, 2.5, 3.5, 5.5);


    @Test
    void circleClickShouldReturnTrueAndReturnRectangleButtonFalse(){
        helloController.circleClick();
        assertTrue(model.isCircleClicked(), "Circle button should be set to True");
        assertFalse(model.isRectangleClicked(), "Rectangle button should be set to false");
    }
    @Test
    void rectangleClickShouldReturnTrueAndReturnCircleButtonFalse(){
        helloController.rectangleClick();
        assertTrue(model.isRectangleClicked(), "Rectangle button should be set to true");
        assertFalse(model.isCircleClicked(), "Circle button should be set to false");
    }

    @Test
    void deletingTwoShapesShouldReturnSizeOfZero(){
        model.addShape(shape);
        model.addShape(shape2);
        model.deleteShape(shape2);
        model.deleteShape(shape);
        var result = model.getShapes().size();
        assertEquals(0,result, "The list should be empty");
    }

    @Test
    void addingThreeShapesShouldReturnSizeOfThree(){
        model.addShape(shape);
        model.addShape(shape);
        model.addShape(shape2);
        var result = model.getShapes().size();
        assertEquals(3,result ,"The list should contain three shapes ");
    }








}