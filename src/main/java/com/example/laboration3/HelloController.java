package com.example.laboration3;

import Shapes.Circle;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;

public class HelloController {
    Model model;

            // ResizableCanvas canvas;
    @FXML
    Canvas canvas;
    @FXML
    public ColorPicker colorPicker;
    @FXML
    public TextField shapeSize;

    @FXML
    public CheckBox eraser;



    public void initialize() {
     draw();
    }


    public void draw(){
        GraphicsContext g = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(shapeSize.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
            if(eraser.isSelected()){
                g.clearRect(x,y,size,size);
            }else{
                g.setFill(colorPicker.getValue());
                g.fillRect(x,y,size,size);
            }
        } );
    }

    public void draw2(){
        GraphicsContext g = canvas.getGraphicsContext2D();
        canvas.setOnMouseClicked(e ->{
            double size = Double.parseDouble(shapeSize.getText());
            double x = e.getX() - size / 2;

        });
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



}

