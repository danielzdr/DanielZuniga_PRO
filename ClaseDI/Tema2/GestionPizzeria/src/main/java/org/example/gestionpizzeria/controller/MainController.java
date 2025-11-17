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
import org.example.gestionpizzeria.model.Pizza;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class MainController implements Initializable {
    @FXML
    private Button botonDetalle;
    @FXML
    private ToggleButton botonPendientes;
    @FXML
    private Button botonRealizar;
    @FXML
    private Button botonServir;
    @FXML
    private BorderPane panelGeneral;
    @FXML
    private ListView<Pedidos> listasPed;
    @FXML
    private ComboBox<String> pizzaDesplegable;
    @FXML
    private RadioButton radioFam;
    @FXML
    private RadioButton radioMed;
    @FXML
    private RadioButton radioPeq;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textTelefono;
    @FXML
    private GridPane parteDerecha;

    private ObservableList<String> listaPizzas;
    private ObservableList<Pedidos> listaPedidos;
    private ToggleGroup tamanioPizzas;
    private PedidosDaoImp pedidosDaoImp;
    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        acciones();
        initGUI();
        instancias();
    }

    private void instancias() {
        pedidosDaoImp=new PedidosDaoImp();
        listaPizzas= FXCollections.observableArrayList("Barbacoa","Hawaiana", "Jamon y queso", "4 quesos");
        pizzaDesplegable.getItems().addAll(listaPizzas);
        tamanioPizzas=new ToggleGroup();
        tamanioPizzas.getToggles().addAll(radioPeq,radioMed,radioFam);
        listaPedidos=FXCollections.observableArrayList();
    }

    private void initGUI() {
        listasPed.setItems(listaPedidos);
        if (botonPendientes.isSelected()){
            panelGeneral.setRight(parteDerecha);

        }else {
            panelGeneral.setRight(null);
        }
    }

    private void acciones() {
        textNombre.setOnAction(new ManejoAcciones());
        textTelefono.setOnAction(new ManejoAcciones());
        botonRealizar.setOnAction(new ManejoAcciones());
        botonServir.setOnAction(new ManejoAcciones());
        botonDetalle.setOnAction(new ManejoAcciones());
        botonPendientes.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue , Boolean aBoolean , Boolean t1) {
                if (t1){
                    panelGeneral.setRight(parteDerecha);
                }else {
                    panelGeneral.setRight(null);
                }
            }
        });


    }

    private void mostrarInfoPedido(Pedidos pedido) {
        if (pedido == null) return;
        System.out.println("Pedido seleccionado: "+pedido);
    }

    private void limpiarDatos(){
        textNombre.clear();
        textTelefono.clear();
        tamanioPizzas.selectToggle(null);
        pizzaDesplegable.getSelectionModel().select(-1);
        System.out.println("Datos limpiados correctamente");
    }

    class ManejoAcciones implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource()==botonRealizar){
                if (!textNombre.getText().isEmpty() &&
                    !textTelefono.getText().isEmpty() &&
                    tamanioPizzas.getSelectedToggle()!=null &&
                    !pizzaDesplegable.getSelectionModel().isEmpty()) {

                    String nombre=textNombre.getText();
                    int telefono= Integer.parseInt(textTelefono.getText());
                    String nombrePizza=pizzaDesplegable.getValue();
                    String tamanio="";
                    if (radioPeq.isSelected()){
                        tamanio="pequeña";
                    } else if (radioMed.isSelected()) {
                        tamanio="mediana";
                    }else {
                        tamanio="familiar";
                    }
                    Pizza pizza= new Pizza(nombrePizza,tamanio);
                    Pedidos pedidos=new Pedidos(nombre,pizza,telefono,true);
                    listaPedidos.add(pedidos);
                    System.out.println("Pedidos agregados correctamente");
                    limpiarDatos();
                    try {
                        pedidosDaoImp.insertarPedidos(pedidos);
                    }catch (SQLException e){
                        Alert alert= new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error en la inserccion");
                        alert.setContentText("Telefono duplicado");
                        alert.show();
                        System.out.println(e.getMessage());
                    }


                }else {
                    Alert alert= new Alert(Alert.AlertType.ERROR,"Completa todos los datos");
                    alert.show();
                }

            } else if (actionEvent.getSource()==botonPendientes) {
                if (botonPendientes.isSelected()){
                    ObservableList<Pedidos> pendientes=
                            listaPedidos.filtered(p -> !p.isServido());
                    listasPed.setItems(pendientes);
                }else {
                    listasPed.setItems(listaPedidos);
                }
                } else if (actionEvent.getSource()==botonServir) {
                    Pedidos seleccionado=listasPed.getSelectionModel().getSelectedItem();
                    if (seleccionado==null){
                        seleccionado.setServido(true);
                        listasPed.refresh();
                    }

                } else if (actionEvent.getSource()==botonDetalle) {
                Pedidos pedidos=listasPed.getSelectionModel().getSelectedItem();
                if (pedidos==null){
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("detalle.fxml"));
                    Stage stage=new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    try {
                        stage.setScene(new Scene(loader.load()));
                        DetalleController detalleController=loader.getController();
                        detalleController.cargarDatos(pedidos);
                        stage.show();
                    } catch (IOException e) {
                        System.out.println("Error de entrada y salida");
                        System.out.println(e.getMessage());
                    }
                }

                }
        }
        }
    }

