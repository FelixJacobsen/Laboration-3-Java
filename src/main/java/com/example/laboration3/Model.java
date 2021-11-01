package com.example.laboration3;

import shapes.Shape;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.List;

public class Model {
    ObservableList<Shape> shapes;
    ObservableList<Shape> selectedShape;
    private final BooleanProperty circleClicked;
    private final BooleanProperty rectangleClicked;
    private final ObjectProperty<Color> color;
    private final ObjectProperty<Color> borderColor;
    private final StringProperty shapeSize;
    private final BooleanProperty selectMode;


    public Model() {
        this.shapes = FXCollections.observableArrayList();
        this.selectedShape = FXCollections.observableArrayList();
        this.circleClicked = new SimpleBooleanProperty();
        this.rectangleClicked = new SimpleBooleanProperty();
        this.color = new SimpleObjectProperty<>();
        this.color.set(Color.BLACK);
        this.borderColor = new SimpleObjectProperty<>();
        this.borderColor.set(Color.TRANSPARENT);
        this.shapeSize = new SimpleStringProperty("18");
        this.selectMode = new SimpleBooleanProperty();
    }

    public BooleanProperty selectDeleteMode() {
        return selectMode;
    }


    public void deleteSelectedShapes() {
        for (var shape : selectedShape) {
            shapes.remove(shape);
        }
    }


    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public boolean isCircleClicked() {
        return circleClicked.get();
    }


    public BooleanProperty circleClickedProperty() {
        return circleClicked;
    }


    public boolean isRectangleClicked() {
        return rectangleClicked.get();
    }

    public BooleanProperty rectangleClickedProperty() {
        return rectangleClicked;
    }


    public String getShapeSize() {
        return shapeSize.get();
    }

    public Double getShapeSizeAsDouble() {
        return Double.parseDouble(getShapeSize());
    }

    public Color getColor() {
        return color.get();
    }

    public Property<String> shapeSizeProperty() {
        return shapeSize;
    }

}
