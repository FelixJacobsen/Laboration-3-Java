package com.example.laboration3;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.File;
public class HelloController {

    Model model;
    @FXML
    Button rectangleButton;
    @FXML
    public Button circleButton;

    @FXML
    private ResizableCanvas canvas;

    @FXML
    public ColorPicker colorPicker;

    @FXML
    public TextField shapeSize;

    @FXML
    public CheckBox eraser;


    public void initialize() {
    this.model = new Model();

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

