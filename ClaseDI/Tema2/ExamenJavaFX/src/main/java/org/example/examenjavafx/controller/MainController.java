package org.example.examenjavafx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.examenjavafx.dao.UsuarioDaoIMP;
import org.example.examenjavafx.model.Usuarios;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button botonLogin;

    @FXML
    private TextField textCorreo;

    @FXML
    private PasswordField textPass;
    private ObservableList<Usuarios> listaUsuarios;
    private UsuarioDaoIMP usuarioDaoIMP;
    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        
        instancias();
        initGUI();
        acciones();

    }

    private void acciones() {
        textCorreo.setOnAction(new ManejoActions());
        textPass.setOnAction(new ManejoActions());
        botonLogin.setOnAction(new ManejoActions());
    }

    private void initGUI() {
    }

    private void instancias() {
        usuarioDaoIMP=new UsuarioDaoIMP();

    }

    private Usuarios estaUsuario(String nombre){
        for (Usuarios usuario:listaUsuarios){
            if(usuario.getCorreo().equalsIgnoreCase(nombre)){
                return usuario;
            }
        }

        return null;
    }

    class ManejoActions implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource()==botonLogin){
                if (!textCorreo.getText().isEmpty()
                    && !textPass.getText().isEmpty()){
                    String name=textCorreo.getText();
                    String pass= textPass.getText();
                    if (estaUsuario(name)!=null){
                        usuarioDaoIMP.obtenerUsuarios();
                    }else {
                        Alert alert= new Alert(Alert.AlertType.ERROR,"Los usuarios no estan en la lista");
                        alert.setTitle("Usuarios no registrados");
                        alert.showAndWait();
                    }



                } else {
                    Alert alert= new Alert(Alert.AlertType.WARNING,"Faltan campos por rellenar");
                    alert.setTitle("Problemas con los campos");
                    alert.showAndWait();
                }
            }
        }
    }
}

