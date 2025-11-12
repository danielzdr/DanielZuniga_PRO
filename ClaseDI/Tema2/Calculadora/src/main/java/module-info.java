module org.example.calculadora {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.calculadora to javafx.fxml;
    exports org.example.calculadora;
    exports org.example.calculadora.controller;
    opens org.example.calculadora.controller to javafx.fxml;
}