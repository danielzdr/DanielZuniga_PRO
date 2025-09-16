package DTO;

import database.DBconection;
import database.SchemaDB;
import model.Productos;


import java.sql.*;

public class ProductosDTO {
    private Connection connection;

    public ProductosDTO() {
        connection = DBconection.getConnection();
    }

    public void insertarProductos(Productos productos) {
        String query = "INSERT INTO %s ( %s, %s, %s) VALUES (?,?,?)";
        String queryFormateada = String.format(query , SchemaDB.TAB_PRODUCTOS ,
                SchemaDB.COL_NOMBRE  , SchemaDB.COL_PRECIO , SchemaDB.COL_CANTIDAD);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryFormateada);
            preparedStatement.setString(1 , productos.getNombre());
            preparedStatement.setInt(2 , productos.getPrecio());
            preparedStatement.setInt(3 , productos.getCantidad());
            preparedStatement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error en correo duplicado");
        } catch (SQLException e) {
            System.out.println("Error en la ejecucion query");
        }
    }

    public void actualizarPrecioNombre(Productos productos){
        String query = "UPDATE %s SET %s = ? WHERE %s = ?";
        String queryFormateada= String.format(query,SchemaDB.TAB_PRODUCTOS,SchemaDB.COL_PRECIO,SchemaDB.COL_NOMBRE);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryFormateada);
            preparedStatement.setInt(1, productos.getPrecio());
            preparedStatement.setString(2, productos.getNombre());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la ejecucion de la query");
        }
    }

    public void mostrarProductos(String nombre, int precio, int cantidad){
        String query= "SELECT  %s, %s, %s FROM %s WHERE %s <= ?";
        String queryFormateada= String.format(query, SchemaDB.COL_NOMBRE,SchemaDB.COL_PRECIO,SchemaDB.COL_CANTIDAD,
                SchemaDB.TAB_PRODUCTOS,SchemaDB.COL_CANTIDAD);
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(queryFormateada);
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, precio);
            preparedStatement.setInt(3, cantidad);
            preparedStatement.setInt(4,cantidad);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                nombre = resultSet.getString(SchemaDB.COL_NOMBRE);
                precio = resultSet.getInt(SchemaDB.COL_PRECIO);
                cantidad = resultSet.getInt(SchemaDB.COL_CANTIDAD);
            }

        } catch (SQLException e) {
            System.out.println("Error en la ejecucion sql");
        }
    }



    public void borrarProducto(int id) {
        int borrados = 0;
        String query = "DELETE FROM %s WHERE %s = ?";
        String queryFormateada = String.format(query, SchemaDB.TAB_PRODUCTOS, SchemaDB.COL_ID);

        try {
            PreparedStatement ps = connection.prepareStatement(queryFormateada);
            ps.setInt(1, id);
            borrados = ps.executeUpdate();
            System.out.println("Los productos borrados son: " + borrados);
        } catch (SQLException e) {
            System.out.println("Fallo de SQL: " + e.getMessage());
        }
    }


}



