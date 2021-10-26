package com.example.laboration3;

import shapes.Shape;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

public class Model {
    ObservableList<Shape> shapes;
    private final BooleanProperty circleClicked;
    private final BooleanProperty rectangleClicked;
    private final ObjectProperty<Color> color;
    private final ObjectProperty<Color> borderColor;
    private final StringProperty shapeSize;


    public Model() {
        this.shapes = FXCollections.observableArrayList();
        this.circleClicked = new SimpleBooleanProperty();
        this.rectangleClicked = new SimpleBooleanProperty();
        this.color = new SimpleObjectProperty<>();
        this.color.set(Color.BLACK);
        this.borderColor = new SimpleObjectProperty<>();
        this.borderColor.set(Color.TRANSPARENT);
        this.shapeSize = new SimpleStringProperty();
        this.shapeSize.set("18");

    }

    public ObservableList<Shape> getShapes() {
        return shapes;
    }

    public Model setShapes(ObservableList<Shape> shapes) {
        this.shapes = shapes;
        return this;
    }

    public boolean isCircleClicked() {
        return circleClicked.get();
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor.set(borderColor);
    }

    public BooleanProperty circleClickedProperty() {
        return circleClicked;
    }

    public void setCircleClicked(boolean circleClicked) {
        this.circleClicked.set(circleClicked);
    }

    public boolean isRectangleClicked() {
        return rectangleClicked.get();
    }

    public BooleanProperty rectangleClickedProperty() {
        return rectangleClicked;
    }

    public void setRectangleClicked(boolean rectangleClicked) {
        this.rectangleClicked.set(rectangleClicked);
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
}
