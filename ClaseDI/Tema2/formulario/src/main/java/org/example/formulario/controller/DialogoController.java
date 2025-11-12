package org.example.formulario.controller;
import dao.UsuarioDaoImp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.formulario.model.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogoController implements Initializable {

    @FXML
    private TextField textoNombre, textoApellidos, textoGenero, textoEdad, textoMail;

    @FXML
    private Button botonContestar;
    private Usuario usuario;
    private MainController formController;
    private UsuarioDaoImp usuarioDaoImp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        instancias();

        botonContestar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Pulsado el contestar");
                formController.actualizarUsuario(null);
                ((Stage)botonContestar.getScene().getWindow()).close();
                usuarioDaoImp.actualizarUsuarios(String.valueOf(textoMail));
            }
        });
    }

    private void instancias() {
        usuarioDaoImp=new UsuarioDaoImp();
    }

    public void setFormController(MainController controller){
        this.formController = controller;
    }

    public void setUsuario(Usuario usuario){
        textoNombre.setText(usuario.getNombre());
        textoMail.setText(usuario.getCorreo());
        textoGenero.setText(usuario.getGenero());
        textoEdad.setText(String.valueOf(usuario.getEdad()));
        textoApellidos.setText(usuario.getApellidos());
    }
}

