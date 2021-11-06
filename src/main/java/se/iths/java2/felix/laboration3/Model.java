package se.iths.java2.felix.laboration3;

import javafx.beans.Observable;
import shapes.Circle;
import shapes.FactoryShapes;
import shapes.Rectangle;
import shapes.Shape;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.ArrayDeque;
import java.util.Deque;

public class Model {
    private final BooleanProperty circleClicked;
    private final BooleanProperty rectangleClicked;
    private final ObjectProperty<Color> color;
    private final StringProperty shapeSize;
    private final BooleanProperty modifyMode;
    private ObservableList<Shape> shapes;
    private ObservableList<Shape> selectedShapes;
    private Deque<ObservableList<Shape>> undo;
    private Deque<ObservableList<Shape>> redo;

    public Model() {
        this.shapes = FXCollections.observableArrayList(
                shape -> new Observable[]{
                        shape.colorProperty(),
                        shape.borderColorProperty(),
                        shape.xProperty(),
                        shape.yProperty(),
                        shape.sizeProperty(),
                }
        );
        selectedShapes = FXCollections.observableArrayList();
        circleClicked = new SimpleBooleanProperty();
        rectangleClicked = new SimpleBooleanProperty();
        color = new SimpleObjectProperty<>();
        color.set(Color.BLACK);
        ObjectProperty<Color> borderColor = new SimpleObjectProperty<>();
        borderColor.set(Color.TRANSPARENT);
        shapeSize = new SimpleStringProperty("30");
        modifyMode = new SimpleBooleanProperty();
        undo = new ArrayDeque<>();
        redo = new ArrayDeque<>();
    }

    public void setCircleClicked(boolean circleClicked) {
        this.circleClicked.set(circleClicked);
    }

    public void setRectangleClicked(boolean rectangleClicked) {
        this.rectangleClicked.set(rectangleClicked);
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public void setShapeSize(String shapeSize) {
        this.shapeSize.set(shapeSize);
    }

    public void setModifyMode(boolean modifyMode) {
        this.modifyMode.set(modifyMode);
    }

    public void setSelectedShapes(ObservableList<Shape> selectedShapes) {
        this.selectedShapes = selectedShapes;
    }

    public void setUndo(Deque<ObservableList<Shape>> undo) {
        this.undo = undo;
    }

    public void setRedo(Deque<ObservableList<Shape>> redo) {
        this.redo = redo;
    }

    public void setShapes(ObservableList<Shape> shapes) {
        this.shapes = shapes;
    }

    public ObservableList<Shape> getShapes() {
        return shapes;
    }

    public ObservableList<Shape> getSelectedShapes() {
        return selectedShapes;
    }

    public void deleteShape(Shape shape) {
        shapes.remove(shape);
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public boolean isModifyMode() {
        return modifyMode.get();
    }

    public BooleanProperty modifyModeProperty() {
        return modifyMode;
    }

    public void deleteSelectedShapes() {
        for (var shape : selectedShapes) {
            shapes.remove(shape);
        }
    }

    public Deque<ObservableList<Shape>> getUndo() {
        return undo;
    }

    public Deque<ObservableList<Shape>> getRedo() {
        return redo;
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
        for (Shape shape : shapes) {
            if (shape.getClass() == Rectangle.class) {
                tempList.add(new Rectangle(shape));
            }
            if (shape.getClass() == Circle.class) {
                tempList.add(new Circle(shape));
            }
        }
        return tempList;
    }

    public void changeColor() {
        ObservableList<Shape> temp = getTemp();
        undo.addLast(temp);

        for (var shape : selectedShapes)
            shape.setColor(getColor());
    }

    public void modifySize() {
        ObservableList<Shape> temp = getTemp();
        undo.addLast(temp);

        for (var shape : selectedShapes)
            shape.setSize(getShapeSizeAsDouble());
    }

    public void updateShapes() {
        shapes.clear();
        shapes.addAll(undo.removeLast());
    }

    public void updateAfterRedo() {
        shapes.clear();
        shapes.addAll(redo.removeLast());
    }

    public void undoFromModel() {
        if (getUndo().isEmpty())
            return;
        ObservableList<Shape> temp = getTemp();
        redo.addLast(temp);
        updateShapes();
    }

    public void redoFromModel() {
        if (getRedo().isEmpty())
            return;
        ObservableList<Shape> tempList = getTemp();
        undo.addLast(tempList);
        updateAfterRedo();
    }

    public void circleClick() {
        circleClickedProperty().setValue(true);
        rectangleClickedProperty().setValue(false);
    }

    public void rectangleClick() {
        rectangleClickedProperty().setValue(true);
        circleClickedProperty().setValue(false);
    }

    public void checkIfInside(double x, double y) {
        for(var shape : getShapes()){
            if (shape.isInside(x, y)) {
                checkIfContains(shape);
            }
        }
    }

    private void checkIfContains(Shape shape) {
        if (getSelectedShapes().contains(shape)) {
            shape.setBorderColor(Color.TRANSPARENT);
            getSelectedShapes().remove(shape);
        } else {
            shape.setBorderColor(Color.RED);
            getSelectedShapes().add(shape);
        }
    }

    public void checkShapeAndAddToShapes(double x, double y) {
        ObservableList<Shape> temp = getTemp();

        if (isCircleClicked()) {
            Shape shape = FactoryShapes.circleOf(getColor(), x, y, getShapeSizeAsDouble());
            getUndo().addLast(temp);
            getShapes().add(shape);
        }

        if (isRectangleClicked()) {
            Shape shape = FactoryShapes.rectangleOf(getColor(), x, y, getShapeSizeAsDouble());
            getUndo().addLast(temp);
            getShapes().add(shape);
        }
    }
}