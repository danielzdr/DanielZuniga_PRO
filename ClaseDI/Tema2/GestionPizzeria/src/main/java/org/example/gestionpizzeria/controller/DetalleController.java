package org.example.gestionpizzeria.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.gestionpizzeria.model.Pedidos;

public class DetalleController {
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

        public void cargarDatos(Pedidos pedidos) {
            labelNombre.setText(pedidos.getNombre());
            labelTelefono.setText(String.valueOf(pedidos.getTelefono()));
            labelPizza.setText(pedidos.getPizza().getNombre());
            labelTamano.setText(pedidos.getPizza().getTamanio());
            labelPrecio.setText(pedidos.getPizza().getPrecio() + " €");
        }
    }

