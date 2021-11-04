package com.example.laboration3;

import shapes.Circle;
import shapes.Rectangle;
import shapes.Shape;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class Model {
    ObservableList<Shape> shapes;
    ObservableList<Shape> selectedShape;
    private final BooleanProperty circleClicked;
    private final BooleanProperty rectangleClicked;
    private final ObjectProperty<Color> color;
    private final ObjectProperty<Color> borderColor;
    private final StringProperty shapeSize;
    private final BooleanProperty modifyMode;
    Deque<ObservableList<Shape>> undo;




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

        this.modifyMode = new SimpleBooleanProperty();
        undo = new ArrayDeque<>();
    }


    public boolean isModifyMode() {
        return modifyMode.get();
    }

    public BooleanProperty modifyModeProperty() {
        return modifyMode;
    }

    public void setModifyMode(boolean modifyMode) {
        this.modifyMode.set(modifyMode);
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

    public ObservableList<Shape> getTemp() {
        ObservableList<Shape> tempList = FXCollections.observableArrayList();
        for(Shape shape : shapes){
            if(shape.getClass() == Rectangle.class){
                tempList.add(new Rectangle(shape));
            }
            if(shape.getClass() == Circle.class){
                tempList.add(new Circle(shape));
            }
        }
        return tempList;
    }

    public void changeColor() {
        for(var shape : selectedShape)
            shape.setColor(getColor());
    }

    public void modifySize() {
        for(var shape : selectedShape)
            shape.setSize(getShapeSizeAsDouble());
    }
}
