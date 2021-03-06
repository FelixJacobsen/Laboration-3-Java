package se.iths.java2.felix.laboration3;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import shapes.Shape;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SVGWriter {
    private static final FileChooser fileChooser = new FileChooser();

    public static void saveToFile(Model model) {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG format", "*.svg"));
        Path path = getPath();
        if(path == null)
            return;

        List<String> stringList = new ArrayList<>();
        convertToStrings(model, stringList);

        try {
            Files.write(path, stringList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Path getPath() {
        Path path;
        try{
            path = Path.of(fileChooser.showSaveDialog(new Stage()).getPath());
        }catch (NullPointerException e){
            return null;
        }
        return path;
    }

    private static void convertToStrings(Model model, List<String> stringList) {
        stringList.add("<svg xlmns=\"http://www.w3.org/2000/svg\" version=\"1.1\" " +
                "width=\"800.0\" height=\"800.0\"> ");

        model.getShapes().forEach(shape -> shapeSVGtoString(shape, stringList));
        stringList.add("</svg>");
    }

    private static void shapeSVGtoString(Shape shape, List<String> stringList) {
        stringList.add(shape.shapeToSVG());
    }
}
