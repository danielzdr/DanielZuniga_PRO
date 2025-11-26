package org.example.gestionpizzeria.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.gestionpizzeria.daoPedido.PedidosDaoImp;
import org.example.gestionpizzeria.model.Pedidos;

import java.net.URL;
import java.util.ResourceBundle;

public class DetalleController implements Initializable {
@FXML
private Label labelId;
@FXML
private Label labelNombre;
@FXML
private Label labelTelefono;
@FXML
private Label labelPizza;
@FXML
private Label labelTamano;
@FXML
private Label labelPrecio;
@FXML
private Button botonActualizar;
private Pedidos pedidos;
private PedidosDaoImp pedidosDaoImp;
private MainController mainController;




    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        instancias();
        botonActualizar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ((Stage)botonActualizar.getScene().getWindow()).close();
                pedidosDaoImp.obtenerPedidos();
            }
        });


    }

    private void instancias() {
        pedidosDaoImp=new PedidosDaoImp();
    }

    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    public void setPedidos(Pedidos pedidos) {
        labelNombre.setText(pedidos.getNombre());
        labelTelefono.setText(String.valueOf(pedidos.getTelefono()));
        labelPizza.setText(pedidos.getTipoPizza());
        labelTamano.setText(pedidos.getTamanio());
        labelPrecio.setText(pedidos.getPrecio() + " €");
    }
}

