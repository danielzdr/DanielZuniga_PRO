package dto;

import database.DBConector;
import database.SchemaDB;
import model.Usuario;
import java.sql.*;
import java.util.Scanner;

public class UsuarioDTO {
    //representa el servicio contra la base de datos

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UsuarioDTO() {
        connection= DBConector.getConnection();
    }

    //CREATE ->INSERT

    public void insertarUsuarios(Usuario usuario){
        try {
            statement= connection.createStatement();
            String query="INSERT INTO %s (%s, %s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s', %d)";
            String queryFormateado = String.format(query,SchemaDB.TABLA_NAME,
                    SchemaDB.COL_NAME, SchemaDB.COL_SURNAME, SchemaDB.COL_MAIL, SchemaDB.COL_PASS, SchemaDB.COL_PERFIL, SchemaDB.COL_VENTAS,
                    usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(), usuario.getPass(), usuario.getPerfil(), usuario.getVentas());
            statement.execute(queryFormateado);

        } catch (SQLException e) {
            System.out.println("Error en la query");
            System.out.println(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void insertarUsariosPS(Usuario usuario){
        String query = "INSERT INTO %s (%s, %s, %s, %s) VALUES (?,?,?,?)";
        String queryFormateada= String.format(query, SchemaDB.TABLA_NAME,
                SchemaDB.COL_NAME, SchemaDB.COL_SURNAME, SchemaDB.COL_MAIL, SchemaDB.COL_PERFIL,SchemaDB.COL_PASS);
        try {
            preparedStatement = connection.prepareStatement(queryFormateada);
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellido());
            preparedStatement.setString(3, usuario.getCorreo());
            preparedStatement.setString(4, usuario.getPass());
            preparedStatement.setInt(5, Integer.parseInt(usuario.getPass()));
            preparedStatement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error en correo duplicado");
        }catch (SQLException e ){
            System.out.println("Error en la ejecucion query");
        }
    }

    public void borrarUsuario(int ventas){
        int despidos= 0;
        String query = String.format("DELETE FROM %s WHERE %s<?",SchemaDB.TABLA_NAME,SchemaDB.COL_VENTAS);
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,ventas);
             despidos= ps.executeUpdate();
            System.out.println("Usuarios despedidos son: " +despidos);
        } catch (SQLException e) {
            System.out.println("Fallo de SQL");
        }

    }

    public void listarUusarios(){
        //SELECT * FROM USUARIOS
        String query= String.format("SELECT * FROM "+SchemaDB.TABLA_NAME);
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){//devuelve un bolean y mueve el puntero
                String nombre= resultSet.getString(SchemaDB.COL_NAME);
                String correo= resultSet.getString(SchemaDB.COL_MAIL);
                String pass= resultSet.getString(SchemaDB.COL_PASS);
            }
        } catch (SQLException e) {
            System.out.println("Error en la base de datos");
        }
    }


    public boolean loginUsuario(String correo, String pass){
        String query= String.format("SELECT ");
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
           return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error en la base de datos");

        }
        return false;
    }
}
