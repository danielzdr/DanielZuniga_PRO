package org.example.calculadora.controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button botonBorrar;
    @FXML
    private Button botonMas;
    @FXML
    private Button botonCero;
    @FXML
    private ToggleButton botonCientifica;
    @FXML
    private Button botonCinco;
    @FXML
    private Button botonComa;
    @FXML
    private Button botonCuatro;
    @FXML
    private Button botonDivision;
    @FXML
    private Button botonDos;
    @FXML
    private Button botonElevado;
    @FXML
    private Button botonExponencial;
    @FXML
    private Button botonIgual;
    @FXML
    private Button botonMenos;
    @FXML
    private Button botonMulti;
    @FXML
    private Button botonNegativo;
    @FXML
    private Button botonNueve;
    @FXML
    private Button botonOcho;
    @FXML
    private Button botonPi;
    @FXML
    private Button botonPorcentaje;
    @FXML
    private Button botonRaiz;
    @FXML
    private ToggleButton botonRegistro;
    @FXML
    private Button botonReset;
    @FXML
    private Button botonSeis;
    @FXML
    private Button botonSiete;
    @FXML
    private Button botonTres;
    @FXML
    private Button botonUno;
    @FXML
    private Label labelNumero;
    @FXML
    private BorderPane panelGeneral;
    @FXML
    private GridPane parteDerecha;
    @FXML
    private ListView<String> parteIzquierda;

    private String operacionActual = " ";
    private double numeroAnterior = 0;
    private String operador = "";
    private boolean numeroNuevo = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelNumero.setText("0");
        initGui();
        acciones();
    }

    private void initGui() {
        if (botonCientifica.isSelected()) {
            panelGeneral.setRight(parteDerecha);
        } else {
            panelGeneral.setRight(null);
        }
        if (botonRegistro.isSelected()) {
            panelGeneral.setLeft(parteIzquierda);
        } else {
            panelGeneral.setLeft(null);
        }
    }

    private void acciones() {
        // Agregar todos los botones numéricos y de operación al manejador
        botonComa.setOnAction(new ManejoActions());
        botonPorcentaje.setOnAction(new ManejoActions());
        botonReset.setOnAction(new ManejoActions());
        botonUno.setOnAction(new ManejoActions());
        botonDos.setOnAction(new ManejoActions());
        botonTres.setOnAction(new ManejoActions());
        botonCuatro.setOnAction(new ManejoActions());
        botonCinco.setOnAction(new ManejoActions());
        botonSeis.setOnAction(new ManejoActions());
        botonSiete.setOnAction(new ManejoActions());
        botonOcho.setOnAction(new ManejoActions());
        botonNueve.setOnAction(new ManejoActions());
        botonBorrar.setOnAction(new ManejoActions());
        botonCero.setOnAction(new ManejoActions());
        botonMas.setOnAction(new ManejoActions());
        botonMenos.setOnAction(new ManejoActions());
        botonIgual.setOnAction(new ManejoActions());
        botonDivision.setOnAction(new ManejoActions());
        botonMulti.setOnAction(new ManejoActions());
        botonElevado.setOnAction(new ManejoActions());
        botonRaiz.setOnAction(new ManejoActions());
        botonPi.setOnAction(new ManejoActions());
        botonExponencial.setOnAction(new ManejoActions());
        botonNegativo.setOnAction(new ManejoActions());

        botonCientifica.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (t1) {
                    panelGeneral.setRight(parteDerecha);
                } else {
                    panelGeneral.setRight(null);
                }
            }
        });

        botonRegistro.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (t1) {
                    panelGeneral.setLeft(parteIzquierda);
                } else {
                    panelGeneral.setLeft(null);
                }
            }
        });
    }

    class ManejoActions implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() == botonCero || actionEvent.getSource() == botonUno
                    || actionEvent.getSource() == botonDos || actionEvent.getSource() == botonTres
                    || actionEvent.getSource() == botonCuatro || actionEvent.getSource() == botonCinco
                    || actionEvent.getSource() == botonSeis || actionEvent.getSource() == botonSiete
                    || actionEvent.getSource() == botonOcho || actionEvent.getSource() == botonNueve) {

                Button boton = (Button) actionEvent.getSource();
                String valor = boton.getText();

                if (numeroNuevo || labelNumero.getText().equals("0")) {
                    labelNumero.setText(valor);
                    numeroNuevo = false;
                } else {
                    labelNumero.setText(labelNumero.getText() + valor);
                }

            } else if (actionEvent.getSource() == botonComa) {
                if (!labelNumero.getText().contains(".")) {
                    if (numeroNuevo) {
                        labelNumero.setText("0.");
                        numeroNuevo = false;
                    } else {
                        labelNumero.setText(labelNumero.getText() + ".");
                    }
                }

            } else if (actionEvent.getSource() == botonMas || actionEvent.getSource() == botonMenos
                    || actionEvent.getSource() == botonDivision || actionEvent.getSource() == botonMulti
                    || actionEvent.getSource() == botonElevado) {

                Button botonOperador = (Button) actionEvent.getSource();
                operador = botonOperador.getText();
                numeroAnterior = Double.parseDouble(labelNumero.getText().replace(",", "."));
                numeroNuevo = true;

            } else if (actionEvent.getSource() == botonIgual) {
                if (!operador.isEmpty()) {
                    double actual = Double.parseDouble(labelNumero.getText().replace(",", "."));
                    double resultado = calcular(numeroAnterior, actual, operador);
                    labelNumero.setText(formatear(resultado));

                    // Añadir la operación en el registro de la parteIzquierda
                    parteIzquierda.getItems().add(numeroAnterior + " " + operador + " " + actual + " = " + formatear(resultado));
                    numeroNuevo = true;
                    operador = "";
                }

            } else if (actionEvent.getSource() == botonPorcentaje) {
                double valor = Double.parseDouble(labelNumero.getText().replace(",", "."));
                labelNumero.setText(formatear(valor / 100));
                numeroNuevo = true;

            } else if (actionEvent.getSource() == botonRaiz) {
                double valor = Double.parseDouble(labelNumero.getText().replace(",", "."));
                labelNumero.setText(formatear(Math.sqrt(valor)));
                numeroNuevo = true;

            } else if (actionEvent.getSource() == botonPi) {
                labelNumero.setText(formatear(Math.PI));
                numeroNuevo = true;

            } else if (actionEvent.getSource() == botonExponencial) {
                int n = (int) Double.parseDouble(labelNumero.getText().replace(",", "."));
                long factorial = 1;
                for (int i = 1; i <= n; i++) factorial *= i;
                labelNumero.setText(String.valueOf(factorial));
                numeroNuevo = true;

            } else if (actionEvent.getSource() == botonNegativo) {
                double valor = Double.parseDouble(labelNumero.getText().replace(",", "."));
                labelNumero.setText(formatear(valor * -1));

            } else if (actionEvent.getSource() == botonReset) {
                labelNumero.setText("0");
                numeroAnterior = 0;
                operador = " ";
                numeroNuevo = true;

            } else if (actionEvent.getSource() == botonBorrar) {
                String textoActual = labelNumero.getText();
                if (textoActual.length() > 1) {
                    labelNumero.setText(textoActual.substring(0, textoActual.length() - 1));
                } else {
                    labelNumero.setText("0");
                    numeroNuevo = true;
                }
            }
        }
    }
    
    private double calcular(double a, double b, String operador) {
        switch (operador) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "×":
                return a * b;
            case "/":
                return (b != 0) ? a / b : 0;
            case "^":
                return Math.pow(a, b);
            default:
                return b;
        }
    }


    private String formatear(double valor) {
        if (valor == (long) valor) {
            return String.format("%d", (long) valor);
        } else {
            // Limitar a 2 decimales y usar punto como separador decimal
            String formatted = String.format("%.2f", valor);
            // Eliminar ceros decimales innecesarios
            formatted = formatted.replaceAll("0*$", "").replaceAll("\\.$", "");
            return formatted;
        }
    }
}