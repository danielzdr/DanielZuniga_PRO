package dto;

import database.DBConection;
import database.SchemaDB;
import model.Profesor;

import java.sql.*;

public class ProfesorDTO {
private Connection connection;

    public ProfesorDTO() {
        connection = DBConection.getConnection();
    }

    public void insertarProfesores(Profesor profesor) {
        String query = "INSERT INTO %s ( %s, %s, %s) VALUES (?,?,?)";
        String queryFormateada = String.format(query , SchemaDB.TAB_PROFESOR ,
                SchemaDB.COL_NOMBRE  , SchemaDB.COL_DNI , SchemaDB.COL_SALARIOBASE);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryFormateada);
            preparedStatement.setString(1 , profesor.getNombre());
            preparedStatement.setString(2 , profesor.getDNI());
            preparedStatement.setInt(3 , profesor.getSalarioAnualBase());
            preparedStatement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error en correo duplicado");
        } catch (SQLException e) {
            System.out.println("Error en la ejecucion query");
        }
    }



    public void mostrarProfesores(){
        String query= "Select * FROM %s";
        String queryFormateada= String.format(query, SchemaDB.TAB_PROFESOR);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryFormateada);
            ResultSet resultSet= preparedStatement.executeQuery();
            System.out.println("Ejecutado con exito");
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error en la base de datos");
            System.out.println(e.getMessage());
        }
    }



}
