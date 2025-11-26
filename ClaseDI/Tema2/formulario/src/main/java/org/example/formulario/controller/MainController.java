package org.example.formulario.controller;

import dao.UsuarioDaoImp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.formulario.HelloApplication;
import org.example.formulario.model.Usuario;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TextField apellidosText;

    @FXML
    private Menu menuPrincipal;
    @FXML
    private Button botonAgregar;

    @FXML
    private Button botonDetalle;

    @FXML
    private Button botonEliminar;

    @FXML
    private ToggleButton botonListar;

    @FXML
    private CheckBox checkBox;

    @FXML
    private ComboBox<Integer> comboEdad;

    @FXML
    private TextField correoText;

    @FXML
    private RadioButton femenino;

    @FXML
    private Label label1;

    @FXML
    private ListView<Usuario> listaVisual;

    @FXML
    private RadioButton masculino;

    @FXML
    private MenuItem menuDetalle;

    @FXML
    private MenuItem menuEliminar;

    @FXML
    private MenuItem menuLista;

    @FXML
    private TextField nombreText;

    @FXML
    private BorderPane panelGeneral;

    @FXML
    private GridPane parteDerecha;

    private ToggleGroup grupoGenero;
    private ObservableList<Integer> listaEdades;
    private ObservableList<Usuario> listaUsuarios;
    private UsuarioDaoImp usuarioDaoImp;

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {

        instancias();
        initGui();
        acciones();


    }

    private void instancias() {
        usuarioDaoImp=new UsuarioDaoImp();
        grupoGenero= new ToggleGroup();
        grupoGenero.getToggles().addAll(masculino,femenino);
        listaEdades= FXCollections.observableArrayList();
        listaUsuarios=FXCollections.observableArrayList(usuarioDaoImp.obtenerUsuarios());
        for (int i = 18; i <91 ; i++) {
            listaEdades.add(i);
        }
    }

    private void initGui() {
        comboEdad.setItems(listaEdades);
        listaVisual.setItems(listaUsuarios);
        botonAgregar.setDisable(!checkBox.isSelected());//pone el checkbox en true cuando lo niegas
        if (botonListar.isSelected()){
            panelGeneral.setRight(parteDerecha);

        }else {
            panelGeneral.setRight(null);
        }
        if (menuLista.isDisable()){
            panelGeneral.setRight(parteDerecha);
        }
    }

    private void acciones() {
        menuPrincipal.setOnAction(new ManejoActions());
        menuDetalle.setOnAction(new ManejoActions());
        menuEliminar.setOnAction(new ManejoActions());
        menuLista.setOnAction(new ManejoActions());
        botonAgregar.setOnAction(new ManejoActions());
        botonEliminar.setOnAction(new ManejoActions());
        botonDetalle.setOnAction(new ManejoActions());
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue , Boolean oldValue , Boolean newValue) {
                botonAgregar.setDisable(!newValue);//devuelve true o false
            }
        });
        botonListar.selectedProperty().addListener(new ChangeListener<Boolean>() {
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



    private void limpiarDatos(){
        nombreText.clear();
        apellidosText.clear();
        correoText.clear();
        checkBox.setSelected(false);
        grupoGenero.selectToggle(null);
        comboEdad.getSelectionModel().select(-1);
        System.out.println("Datos limpiados correctamente");


    }

    private Usuario estaUsuario(String correo){
        for (Usuario usuario:listaUsuarios){
            if(usuario.getCorreo().equalsIgnoreCase(correo)){
                return usuario;
            }
        }

        return null;
    }

    public void actualizarUsuario(Usuario u){
        System.out.println("Contestacion realizada con exito");
    }

    class ManejoActions implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {

            if(actionEvent.getSource()==botonAgregar){
                if (!nombreText.getText().isEmpty()
                    && !correoText.getText().isEmpty()
                    && !apellidosText.getText().isEmpty()
                    && grupoGenero.getSelectedToggle()!=null
                    && comboEdad.getSelectionModel().getSelectedItem()>=0) {
                    String nombre = nombreText.getText();
                    String correo = correoText.getText();
                    String apellidos = apellidosText.getText();
                    Boolean disponible = checkBox.isSelected();
                    String genero = ((RadioButton) grupoGenero.getSelectedToggle()).getText();
                    int edad = comboEdad.getSelectionModel().getSelectedItem();
                    /*if(estaUsuario(correo)!=null){
                        System.out.println("El usuario esta en la lista");
                    }else {

                    }*/
                    Usuario usuario = new Usuario(nombre , correo , apellidos , genero , edad , disponible);
                    listaUsuarios.add(usuario);
                    System.out.println("Usuario agregado correctamente");
                    try {
                        usuarioDaoImp.insertarUsuario(usuario);
                    } catch (SQLException e) {
                        Alert alert= new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error en insercion");
                        alert.setContentText("Mail duplicado, por favor introduce uno nuevo");
                        alert.show();
                    }
                    limpiarDatos();
                }else {
                    System.out.println("Error, al agregar usuario");
                }

            } else if (actionEvent.getSource()==botonEliminar || actionEvent.getSource()==menuEliminar) {
                if (listaVisual.getSelectionModel().getSelectedIndex()!=-1){
                    listaUsuarios.remove(listaVisual.getSelectionModel().getSelectedIndex());
                    System.out.println("Usuario eliminado correctamente");
                    usuarioDaoImp.borrarUsuarios(String.valueOf(nombreText));
                }else {
                    System.out.println("No hay nada seleccionado");
                    Stage ventanaDialogo= new Stage();
                    try {
                        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("dialogo-view.fxml"));
                        Scene scene = new Scene(loader.load());
                        ventanaDialogo.setScene(scene);
                        ventanaDialogo.setTitle("Confirmacion");
                        ventanaDialogo.setResizable(false);
                        ventanaDialogo.initModality(Modality.APPLICATION_MODAL);//Mientras el cuadro de dialogo no se puede utilizar lo de atras
                        ventanaDialogo.showAndWait();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if (actionEvent.getSource()==botonDetalle || actionEvent.getSource()==menuDetalle) {
                int posicionSelecionada=listaVisual.getSelectionModel().getSelectedIndex();
                if (posicionSelecionada!=-1){
                    Usuario usuario= listaVisual.getSelectionModel().getSelectedItem();
                    Stage ventanaDetalle= new Stage();
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("detalle-view.fxml"));
                    Parent root= null;
                    try {
                        root = loader.load();
                        DialogoController dialogoController = loader.getController();
                        dialogoController.setUsuario(usuario);
                        dialogoController.setFormController(MainController.this);
                        Scene scene = new Scene(root);
                        ventanaDetalle.setScene(scene);
                        ventanaDetalle.initModality(Modality.APPLICATION_MODAL);
                        ventanaDetalle.setTitle("Ventana detalle");
                        ventanaDetalle.show();

                    } catch (IOException e) {
                        System.err.println("Error no carga el detalle.fxml " + e.getMessage());

                    }
                }
            } else if (actionEvent.getSource()==menuPrincipal) {
                botonListar.setSelected(!botonListar.isSelected());
            }
        }
        }
    }

