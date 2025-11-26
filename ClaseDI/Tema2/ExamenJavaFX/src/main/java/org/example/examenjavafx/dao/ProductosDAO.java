package org.example.examenjavafx.dao;

import org.example.examenjavafx.model.Productos;
import org.example.examenjavafx.model.Usuarios;

import java.sql.SQLException;
import java.util.List;

public interface ProductosDAO {
    void insertarProductos(Productos productos)throws SQLException;
    List<Productos> obtenerProductos();

}
