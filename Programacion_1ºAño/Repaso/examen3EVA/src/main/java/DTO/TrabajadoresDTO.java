package DTO;

import database.DBconection;
import database.SchemaDB;
import model.Trabajadores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrabajadoresDTO {
    private Connection connection;
    ArrayList<Trabajadores> trabajadores= new ArrayList<>();

    public TrabajadoresDTO(){
        connection= DBconection.getConnection();
    }

    public void crearUsuarios(Trabajadores trabajadores) {
        try {
            String query = "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)";
            String queryFormateada=String.format(query, SchemaDB.TAB_TRABAJADORES,SchemaDB.COL_NOMBRE,SchemaDB.COL_CARGO,
                    SchemaDB.COL_SALARIO,SchemaDB.COL_MAIL,SchemaDB.COL_PASS);
            PreparedStatement ps = connection.prepareStatement(queryFormateada);

                ps.setString(1, trabajadores.getNombre());
                ps.setString(2, trabajadores.getCargo());
                ps.setInt(3, trabajadores.getSalario());
                ps.setString(4, trabajadores.getCorreo());
                ps.setString(5, trabajadores.getPassword());
                ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean loginUsuario(String correo, String password) {
        String query = "SELECT * FROM %s WHERE %s = ? AND %s = ?";
        String queryFormateada= String.format(query,SchemaDB.TAB_TRABAJADORES,SchemaDB.COL_MAIL,SchemaDB.COL_PASS);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryFormateada);
            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error en la base de datos: " + e.getMessage());
        }
        return false;
    }
}
