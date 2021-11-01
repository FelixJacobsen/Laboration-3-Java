package com.example.laboration3;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import shapes.Circle;
import shapes.Rectangle;
import shapes.Shape;

import javax.imageio.ImageIO;
import java.io.File;

public class HelloController {
    Model model;


    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField shapeSize;

    @FXML
    private CheckBox eraser;



    public void initialize() {
        this.model = new Model();
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        shapeSize.textProperty().bindBidirectional(model.shapeSizeProperty());
        eraser.selectedProperty().bindBidirectional(model.selectDeleteMode());

        canvas.widthProperty().addListener(o -> drawShape());
        canvas.heightProperty().addListener(o -> drawShape());



    }



    public void clickedOnCanvas(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        if(eraser.isSelected()){
            for(var shape: model.shapes){
                if(shape.isInside(x,y)){
                    if(model.selectedShape.contains(shape)){
                        shape.setColor()
                        model.selectedShape.remove(shape);

                    }
                    else {
                        model.selectedShape.add(shape);

                    }
                }
            }
        }



        if (model.isCircleClicked())
            model.shapes.add(new Circle(model.getColor(), x, y, model.getShapeSizeAsDouble()));

        if (model.isRectangleClicked())
            model.shapes.add(new Rectangle(model.getColor(), x, y, model.getShapeSizeAsDouble()));

        drawShape();
    }


    public void drawShape() {
        var gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getHeight(), canvas.getWidth());
        for (var shape : model.shapes) {
            shape.draw(gc);
        }
    }


    public void onSave() {
        try {
            Image snapshot = canvas.snapshot(null, null);

            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("paint.png"));
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e);
        }
    }

    public void onExit() {
        Platform.exit();
    }


    public void circleClick() {
        model.circleClickedProperty().setValue(true);
        model.rectangleClickedProperty().setValue(false);
    }

    public void rectangleClick() {
        model.rectangleClickedProperty().setValue(true);
        model.circleClickedProperty().setValue(false);
    }
}


