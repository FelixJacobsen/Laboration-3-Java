module com.example.laboration3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    requires org.kordamp.bootstrapfx.core;

    opens se.iths.java2.felix.laboration3 to javafx.fxml;
    exports se.iths.java2.felix.laboration3;
}