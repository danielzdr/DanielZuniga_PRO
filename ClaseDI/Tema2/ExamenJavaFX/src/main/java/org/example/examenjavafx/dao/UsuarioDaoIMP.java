package org.example.examenjavafx.dao;

import org.example.examenjavafx.db.DBConnection;
import org.example.examenjavafx.db.SchemaDB;
import org.example.examenjavafx.model.Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoIMP implements UsuarioDAO{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public UsuarioDaoIMP(){
        connection= DBConnection.getConnection();
    }


    @Override
    public List<Usuarios> obtenerUsuarios() {
        List<Usuarios> listaUsuario=new ArrayList<>();
        try {
            preparedStatement= connection.prepareStatement("SELECT * FROM " + SchemaDB.TAB_NAME);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                int id=resultSet.getInt(SchemaDB.COL_ID);
                String nombre= resultSet.getNString(SchemaDB.COL_NAME);
                String correo= resultSet.getNString(SchemaDB.COL_MAIL);
                String pass= resultSet.getNString(SchemaDB.COL_PASS);
                Usuarios usuario= new Usuarios(id,nombre,correo,pass);
                listaUsuario.add(usuario);


            }
        } catch (SQLException e) {
            System.out.println("Error al obtener Usuarios");
            System.out.println(e.getMessage());
        }
        return List.of();
    }
}
