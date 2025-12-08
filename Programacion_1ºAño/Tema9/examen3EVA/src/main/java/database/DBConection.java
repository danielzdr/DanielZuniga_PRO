package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {
    private static Connection connection;

    public static java.sql.Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    public static void createConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/examen" , "root" , "RISA");
        } catch (SQLException e) {
            System.out.println("Error en la base de datos");
            System.out.println(e.getMessage());
        }
    }


}
