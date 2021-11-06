package se.iths.java2.felix.laboration3;

import javafx.collections.ListChangeListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import shapes.Shape;

import javax.imageio.ImageIO;
import java.io.File;

public class HelloController {
    Model model;

    @FXML
    private CheckBox modifyBox;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField shapeSize;

    public HelloController(Model model) {
        this.model = model;
    }

    public HelloController() {

    }

    public void initialize() {
        this.model = new Model();
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        shapeSize.textProperty().bindBidirectional(model.shapeSizeProperty());
        canvas.widthProperty().addListener(o -> drawShape());
        canvas.heightProperty().addListener(o -> drawShape());
        modifyBox.selectedProperty().bindBidirectional(model.modifyModeProperty());
        model.getShapes().addListener((ListChangeListener<Shape>) change -> drawShape());
    }

    public void clickedOnCanvas(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        if (model.isModifyMode()) {
            model.checkIfInside(x, y);
        } else {
            model.checkShapeAndAddToShapes(x, y);
        }
    }

    public void drawShape() {
        var gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getHeight(), canvas.getWidth());
        for (var shape : model.getShapes()) {
            shape.draw(gc);
        }
    }

    public void onSave() {
        SVGWriter.saveToFile(model);
        try {
            Image snapshot = canvas.snapshot(null, null);

            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("paint.png"));
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e);
        }
    }

    public void onExit() {
        System.exit(0);
    }

    public void circleClick() {
        model.circleClick();
    }

    public void rectangleClick() {
        model.rectangleClick();
    }

    public void deleteShape() {
        model.deleteSelectedShapes();
    }

    public void modifyColor() {
        model.changeColor();
    }

    public void modifyFont() {
        model.modifySize();
    }

    public void undoAction() {
        model.undoFromModel();
    }

    public void redoAction() {
        model.redoFromModel();
    }
}


