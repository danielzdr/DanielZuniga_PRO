module org.example.examenjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires lombok;

    opens org.example.examenjavafx to javafx.fxml;
    exports org.example.examenjavafx;
    exports org.example.examenjavafx.controller;
    opens org.example.examenjavafx.controller to javafx.fxml;

    opens org.example.examenjavafx.model to lombok,java.sql;
    exports org.example.examenjavafx.model;
}