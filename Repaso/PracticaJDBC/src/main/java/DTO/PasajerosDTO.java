package DTO;
import database.DBConection;
import database.SchemaDB;
import model.Pasajeros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class PasajerosDTO {

 private Connection connection;

public  PasajerosDTO(){
    connection=DBConection.getConnection();
}

    public void insertarPasajeros(Pasajeros pasajeros) throws SQLException {
        String query = "INSERT INTO " + SchemaDB.TAB_PASAJEROS + " (" +
                SchemaDB.COL_NOMBRE + ", " +
                SchemaDB.COL_EDAD + ", " +
                SchemaDB.COL_PESO + ") VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1 , pasajeros.getNombre());
            stmt.setInt(2 , pasajeros.getEdad());
            stmt.setInt(3 , pasajeros.getPeso() );

            stmt.executeUpdate();
        }
    }

    public void borrarPasajeros(String nombre){
        int borrados= 0;
        String query = String.format("DELETE FROM %s WHERE %s<?",SchemaDB.TAB_PASAJEROS,SchemaDB.COL_NOMBRE);
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nombre);
            borrados= ps.executeUpdate();
            System.out.println("Los pasajeros borrados son: " +borrados);
        } catch (SQLException e) {
            System.out.println("Fallo de SQL");
        }
    }

    public void listarPasajeros(){
        //SELECT * FROM PASAJEROS;
        String query= String.format("SELECT * FROM "+SchemaDB.TAB_PASAJEROS);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //SELECT-> executeQuery
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){//devuelve un boolean y mueve el puntero
                String nombre= resultSet.getString("nombre");
                int edad= resultSet.getInt("edad");
                int peso= resultSet.getInt("peso");


            }
        } catch (SQLException e) {
            System.out.println("Error en la base de datos");
        }
    }
    }




