package org.example.gestionpizzeria.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.gestionpizzeria.daoPedido.PedidosDaoImp;
import org.example.gestionpizzeria.model.Pedidos;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private Button botonDetalle;
    @FXML private ToggleButton botonPendientes;
    @FXML private Button botonRealizar;
    @FXML private Button botonServir;
    @FXML private BorderPane panelGeneral;
    @FXML private ListView<Pedidos> listasPed;
    @FXML private ComboBox<String> pizzaDesplegable;
    @FXML private RadioButton radioFam;
    @FXML private RadioButton radioMed;
    @FXML private RadioButton radioPeq;
    @FXML private TextField textNombre;
    @FXML private TextField textTelefono;
    @FXML private GridPane parteDerecha;

    private ObservableList<String> listaPizzas;
    private ObservableList<Pedidos> listaPedidos;
    private ToggleGroup tamanioPizzas;

    private PedidosDaoImp pedidosDaoImp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        instancias();
        initGUI();
        acciones();
    }


    private void instancias() {

        pedidosDaoImp = new PedidosDaoImp();

        listaPizzas = FXCollections.observableArrayList(
                "Barbacoa", "Hawaiana", "Jamon y queso", "4 quesos"
        );
        pizzaDesplegable.getItems().addAll(listaPizzas);

        tamanioPizzas = new ToggleGroup();
        tamanioPizzas.getToggles().addAll(radioPeq, radioMed, radioFam);

        listaPedidos = FXCollections.observableArrayList();
    }

    private void initGUI() {

        listasPed.setItems(listaPedidos);

        if (botonPendientes.isSelected()) {
            panelGeneral.setRight(parteDerecha);
        } else {
            panelGeneral.setRight(null);
        }
    }

    private void acciones() {

        botonRealizar.setOnAction(new ManejoAcciones());
        botonServir.setOnAction(new ManejoAcciones());
        botonDetalle.setOnAction(new ManejoAcciones());

        botonPendientes.selectedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        panelGeneral.setRight(parteDerecha);
                        listasPed.setItems(listaPedidos.filtered(p -> !p.isServido()));
                    } else {
                        panelGeneral.setRight(null);
                        listasPed.setItems(listaPedidos);
                    }
                }
        );
    }

    private void limpiarDatos() {
        textNombre.clear();
        textTelefono.clear();
        tamanioPizzas.selectToggle(null);
        pizzaDesplegable.getSelectionModel().clearSelection();
    }

    class ManejoAcciones implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {

            if (actionEvent.getSource() == botonRealizar) {

                if (!textNombre.getText().isEmpty() &&
                        !textTelefono.getText().isEmpty() &&
                        tamanioPizzas.getSelectedToggle() != null &&
                        !pizzaDesplegable.getSelectionModel().isEmpty()) {

                    String nombre = textNombre.getText();
                    int telefono = Integer.parseInt(textTelefono.getText());
                    String nombrePizza = pizzaDesplegable.getValue();

                    String tamanio;
                    if (radioPeq.isSelected()) tamanio = "pequeña";
                    else if (radioMed.isSelected()) tamanio = "mediana";
                    else tamanio = "familiar";

                    Pedidos pedido = new Pedidos(nombre, telefono, nombrePizza, tamanio);

                    listaPedidos.add(pedido);
                    System.out.println("Pedido agregado correctamente");
                    limpiarDatos();

                    try {
                        pedidosDaoImp.insertarPedidos(pedido);
                    } catch (SQLException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Teléfono duplicado");
                        alert.show();
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Completa todos los datos");
                    alert.show();
                }
            }

            else if (actionEvent.getSource() == botonServir) {

                Pedidos seleccionado = listasPed.getSelectionModel().getSelectedItem();

                if (seleccionado != null) {
                    seleccionado.setServido(true);
                    listasPed.refresh();

                    if (botonPendientes.isSelected()) {
                        listasPed.setItems(listaPedidos.filtered(p -> !p.isServido()));
                    }
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Pedido servido correctamente").show();
                    System.out.println("pedido servido correctamente");
                }
            }

            else if (actionEvent.getSource() == botonDetalle) {

                Pedidos pedido = listasPed.getSelectionModel().getSelectedItem();



                if (pedido != null) {

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/gestionpizzeria/detalle_view2.fxml"));
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(new Scene(loader.load()));

                        DetalleController detalleController = loader.getController();
                        detalleController.setPedidos(pedido);

                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    new Alert(Alert.AlertType.WARNING,
                            "Selecciona un pedido para ver los detalles").show();
                }
            }
        }
    }
}


