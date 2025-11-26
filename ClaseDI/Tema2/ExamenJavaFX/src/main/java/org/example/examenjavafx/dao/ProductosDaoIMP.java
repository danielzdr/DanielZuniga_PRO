package org.example.examenjavafx.dao;

import org.example.examenjavafx.db.DBConnection;
import org.example.examenjavafx.db.SchemaDB;
import org.example.examenjavafx.db.SchemaDBPr;
import org.example.examenjavafx.model.Productos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductosDaoIMP implements ProductosDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public ProductosDaoIMP() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void insertarProductos(Productos productos) throws SQLException {
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?) " ,
                SchemaDBPr.TAB_NAME , SchemaDBPr.COL_ID , SchemaDBPr.COL_NAME , SchemaDBPr.COL_CATEGORIA ,
                SchemaDBPr.COL_PRECIO , SchemaDBPr.COL_DESCRIP);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1 , productos.getId());
            preparedStatement.setString(2 , productos.getNombre());
            preparedStatement.setString(3 , productos.getCategoria());
            preparedStatement.setInt(4 , productos.getPrecio());
            preparedStatement.setString(5 , productos.getDescripcion());
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Error en la insercion");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Productos> obtenerProductos() {
        List<Productos> listaProductos=new ArrayList<>();
        try {
            preparedStatement= connection.prepareStatement("SELECT * FROM " + SchemaDB.TAB_NAME);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                int id=resultSet.getInt(SchemaDB.COL_ID);
                String nombre= resultSet.getNString(SchemaDB.COL_NAME);
                String categoria= resultSet.getNString(SchemaDBPr.COL_CATEGORIA);
                int  precio= resultSet.getInt(SchemaDBPr.COL_PRECIO);
                String descripcion=resultSet.getNString(SchemaDBPr.COL_DESCRIP);
                Productos productos= new Productos(id,nombre,categoria,precio,descripcion);
                listaProductos.add(productos);


            }
        } catch (SQLException e) {
            System.out.println("Error al obtener Usuarios");
            System.out.println(e.getMessage());
        }
        return List.of();
    }
}
