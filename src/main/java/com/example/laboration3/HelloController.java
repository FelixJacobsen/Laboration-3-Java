package com.example.laboration3;

import javafx.application.Platform;
import javafx.collections.ObservableList;
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
    CheckBox modifyBox;
    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField shapeSize;




    public void initialize() {
        this.model = new Model();
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        shapeSize.textProperty().bindBidirectional(model.shapeSizeProperty());

        canvas.widthProperty().addListener(o -> drawShape());
        canvas.heightProperty().addListener(o -> drawShape());


        modifyBox.selectedProperty().bindBidirectional(model.modifyModeProperty());

    }



    public void clickedOnCanvas(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        if(model.isModifyMode()){
            for(var shape: model.shapes){
                if(shape.isInside(x,y)){
                    if(model.selectedShape.contains(shape)){
                        shape.setBorderColor(Color.TRANSPARENT);
                        model.selectedShape.remove(shape);
                    }else {
                        shape.setBorderColor(Color.RED);
                        model.selectedShape.add(shape);
                    }
                }
                drawShape();
            }
        }

        ObservableList<Shape> temp = model.getTemp();

        if (model.isCircleClicked()){
            model.shapes.add(new Circle(model.getColor(), x, y, model.getShapeSizeAsDouble()));
            model.undo.addLast(temp);
        }

        if (model.isRectangleClicked()){
            model.shapes.add(new Rectangle(model.getColor(), x, y, model.getShapeSizeAsDouble()));
            model.undo.addLast(temp);
        }

    }


    public void drawShape() {
      GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getHeight(), canvas.getWidth());
        for (var shape : model.shapes) {
            shape.draw(gc);
        }
    }


    public void onSave() {
        SVGWriter.saveToFile(model);
        try {
            Image snapshot = canvas.snapshot(null, null);

            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("paint.png"));
        } catch (Exception e) {
            System.out.println("Something went wrong while trying to save to file " + e);
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


    public void deleteShape() {
        model.deleteSelectedShapes();
        drawShape();
    }

    public void modifyColor() {
        model.changeColor();
        drawShape();
    }

    public void modifyFont() {
        model.modifySize();
    }
}


