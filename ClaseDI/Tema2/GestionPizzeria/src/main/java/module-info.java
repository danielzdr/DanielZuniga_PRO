module org.example.gestionpizzeria {
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
    requires  lombok;
    requires java.sql;

    opens org.example.gestionpizzeria to javafx.fxml, java.sql;
    exports org.example.gestionpizzeria;
    exports org.example.gestionpizzeria.controller;
    opens org.example.gestionpizzeria.controller to javafx.fxml, java.sql;



    opens org.example.gestionpizzeria.model to lombok, java.sql;
    exports org.example.gestionpizzeria.model;
}