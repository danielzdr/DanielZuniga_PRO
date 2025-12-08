package model;

import database.DBConector;
import database.SchemaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDTO {

    public void insertarProducto(Product product) {
        System.out.println("Intentando insertar orden: " + product.getCode());

        String query = "INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?)";
        String queryFormateada = String.format(query,
                SchemaDB.TAB_PRODUCTOS,
                SchemaDB.COL_CODE,
                SchemaDB.COL_CLIENT,
                SchemaDB.COL_DESCRIPTION,
                SchemaDB.COL_UNITS,
                SchemaDB.COL_TOTAL,
                SchemaDB.COL_COMMENT
        );


        try (Connection conn = DBConector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(queryFormateada)) {

            stmt.setString(1, product.getCode());
            stmt.setString(2, product.getClient());
            stmt.setString(3, product.getDescription());
            stmt.setInt(4, product.getUnits());
            stmt.setDouble(5, product.getTotal());
            stmt.setString(6, product.getComment());


            stmt.executeUpdate();
            System.out.println("Orden insertada correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al insertar orden: " + e.getMessage());
        }
    }
}
