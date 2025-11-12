package org.example.formulario.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;
    public static Connection getConnection(){
        
        if (connection==null){
            createConnection();
        }
        return connection;
    }

    private static void createConnection(){
        String user = "root";
        String pass= "RISA";
        try {
            connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usuarios",user, pass);
            //connection= DriverManager.getConnection(String.format("jdbc:mysql://%s %s %s",SchemaDB.URL,SchemaDB.PORT,SchemaDB.DB_NAME),user,pass);
        } catch (SQLException e) {
            System.out.println("Error en la conexion de base de datos");
            System.out.println(e.getMessage());
        }
    }
}