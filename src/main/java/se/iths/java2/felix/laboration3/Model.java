package se.iths.java2.felix.laboration3;
import javafx.beans.Observable;
import shapes.Circle;
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

    ObservableList<Shape> shapes;
    ObservableList<Shape> selectedShapes;
    Deque<ObservableList<Shape>> undo;
    Deque<ObservableList<Shape>> redo;


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

        this.selectedShapes = FXCollections.observableArrayList();
        this.circleClicked = new SimpleBooleanProperty();
        this.rectangleClicked = new SimpleBooleanProperty();
        this.color = new SimpleObjectProperty<>();
        this.color.set(Color.BLACK);
        ObjectProperty<Color> borderColor = new SimpleObjectProperty<>();
        borderColor.set(Color.TRANSPARENT);
        this.shapeSize = new SimpleStringProperty("30");
        this.modifyMode = new SimpleBooleanProperty();
        undo = new ArrayDeque<>();
        redo = new ArrayDeque<>();
    }
    public void deleteShape(Shape shape){
        shapes.remove(shape);
    }

    public void addShape(Shape shape){
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

    public void undoFromModel(){
        if(getUndo().isEmpty())
            return;
        ObservableList<Shape> temp = getTemp();
        redo.addLast(temp);
        updateShapes();
    }

    public void redoFromModel() {
        if(getRedo().isEmpty())
            return;
        ObservableList<Shape> tempList = getTemp();
        undo.addLast(tempList);
        updateAfterRedo();
    }

    public void circleClick(){
        circleClickedProperty().setValue(true);
        rectangleClickedProperty().setValue(false);
    }

    public void rectangleClick(){
        rectangleClickedProperty().setValue(true);
        circleClickedProperty().setValue(false);
    }

}