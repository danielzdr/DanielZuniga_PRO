package org.example.appinicial;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button botonPulsar, botonVaciar;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private Label labelSaludo;
    @FXML
    private CheckBox checkBox;
    @FXML
    private ProgressBar progressBarra;
    @FXML
    private DatePicker dateFecha;

    private DropShadow sombra;


    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        //metodo ejecutado directamnete en la asociacion de la stage

        System.out.println("Ejecucion de la parte logica");
        instancias();
        acciones();


        // Establecer una fecha por defecto
        dateFecha.setValue(LocalDate.now());

        // Obtener la fecha seleccionada
        dateFecha.valueProperty().addListener((obs , oldDate , newDate) -> {
            System.out.println("Nueva fecha seleccionada: " + newDate);
        });
        LocalDate fecha = dateFecha.getValue();
        System.out.println(fecha);

        // Escuchar cambios en el estado
        checkBox.selectedProperty().addListener((obs , wasSelected , isSelected) -> {
            if (isSelected) {
                System.out.println("Checkbox marcado ");
            } else {
                System.out.println("Checkbox desmarcado ");
            }
        });
        boolean marcado = checkBox.isSelected();

    }

    private void initGUI() {
        //personaliza las partes de la UI
        //botonPulsar.setEffect(sombra);
    }

    private void instancias() {
        sombra = new DropShadow();
    }

    private void acciones() {
        botonPulsar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //el metodo a ejecutar cuando el boton sea pulsado
                //System.out.println("Boton pulsado correctamente");
                String nombre = textFieldNombre.getText();
                if (nombre.isBlank()) {
                    System.out.println("Por favor, introduce tu nombre: ");
                } else {
                    labelSaludo.setText(String.format("Enorabuena %s has completado el primer ejercicio " , nombre));
                }
            }

        });

        botonPulsar.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Raton en foco");
                botonPulsar.setEffect(sombra);
            }
        });
        botonPulsar.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Raton sin foco");
                botonPulsar.setEffect(null);
            }
        });

        botonVaciar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                labelSaludo.setText("");
                textFieldNombre.clear();
            }
        });

        progressBarra.setProgress(0.5);
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    updateProgress(i , 100);
                    Thread.sleep(50);
                }
                return null;
            }
        };

        botonPulsar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                botonPulsar.setCursor(Cursor.OPEN_HAND);
            }
        });

        botonPulsar.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                botonPulsar.setCursor(Cursor.CLOSED_HAND);
            }
        });

        botonPulsar.setOnMouseClicked(new ManejoRaton());
        botonPulsar.setOnMouseReleased(new ManejoRaton());
        botonPulsar.setOnMouseEntered(new ManejoRaton());
        botonPulsar.setOnMouseExited(new ManejoRaton());


    }

    class ManejoAccion implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            System.out.println("Pulsando boton ");
            //que boton se ha pulsado de los dos?
            if (actionEvent.getSource() == botonPulsar) {//para buscar en la fuente

            } else if (actionEvent.getSource() == botonVaciar) {

            }
        }


    }

    class ManejoRaton implements EventHandler<MouseEvent> {
        public void handle(MouseEvent mouseEvent) {
            Button boton= (Button) mouseEvent.getSource();
            System.out.println("Foco raton");
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
                boton.setEffect(null);
                if(boton==botonPulsar){
                    botonPulsar.setCursor(Cursor.OPEN_HAND);
                }
                if (mouseEvent.getSource()==botonPulsar) {
                    botonPulsar.setEffect(sombra);
                } else if (mouseEvent.getSource()==botonVaciar) {
                    botonVaciar.setEffect(sombra);

                }
                System.out.println("Foco raton dentro");

            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED) {
                botonPulsar.setEffect(null);
            } else if (mouseEvent.getEventType()==MouseEvent.MOUSE_CLICKED){
                System.out.println("Raton click");
                botonPulsar.setCursor(Cursor.OPEN_HAND);
            } else if (mouseEvent.getEventType()==MouseEvent.MOUSE_RELEASED) {
                botonPulsar.setCursor(Cursor.CLOSED_HAND);
                System.out.println("Boton Released");
            }

            }


        }

    }

