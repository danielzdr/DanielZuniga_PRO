package database;


import java.sql.*;

public class DBConector {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null){
            createConnection();
        }
        return connection;
    }

    public static void createConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/concesionario_ces","root","RISA");
        } catch (SQLException e) {
            System.out.println("Error en la base de datos");
            System.out.println(e.getMessage());
        }
    }



}
