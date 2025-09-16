package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConector {
    private static final String URL = "jdbc:mysql://localhost:3306/practica";
    private static final String USER = "root";
    private static final String PASSWORD = "Tonny@sey19";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null || isClosed()) {
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión con la base de datos:");
            System.out.println(e.getMessage());
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión:");
                System.out.println(e.getMessage());
            }
        }
    }

    private static boolean isClosed() {
        try {
            return connection == null || connection.isClosed();
        } catch (SQLException e) {
            return true;
        }
    }
}
