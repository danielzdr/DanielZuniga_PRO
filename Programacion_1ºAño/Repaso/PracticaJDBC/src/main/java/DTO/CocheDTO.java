package DTO;
import database.DBConection;
import database.SchemaDB;
import model.Coche;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CocheDTO {
    private Connection connection;

    public CocheDTO() {
        connection= DBConection.getConnection();
    }




    public void insertarCoche(Coche coche) throws SQLException {
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                SchemaDB.TAB_COCHES,SchemaDB.COL_MATRICULA,SchemaDB.COL_MARCA,SchemaDB.COL_MODELO,SchemaDB.COL_COLOR);
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,coche.getMatricula());
        preparedStatement.setString(2,coche.getMarca());
        preparedStatement.setString(3,coche.getModelo());
        preparedStatement.setString(4,coche.getColor());
        preparedStatement.execute();
    }

    public void borrarCoche(Coche coche){
        int borrados= 0;
        String query = String.format("DELETE FROM %s WHERE %s<?",SchemaDB.TAB_COCHES,SchemaDB.COL_MATRICULA);
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, coche.getMatricula());
            borrados= ps.executeUpdate();
            System.out.println("Los coche borrados son: " +borrados);
        } catch (SQLException e) {
            System.out.println("Fallo de SQL");
        }

    }

    public void listarCoches(){
        //SELECT * FROM COCHES
        String query= String.format("SELECT * FROM "+SchemaDB.TAB_COCHES);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){//devuelve un boolean y mueve el puntero2
                String matricula= resultSet.getString("matricula");
                String marca= resultSet.getString("marca");
                String modelo= resultSet.getString("modelo");
                String color= resultSet.getString("color");

            }
        } catch (SQLException e) {
            System.out.println("Error en la base de datos");
        }
    }







}


