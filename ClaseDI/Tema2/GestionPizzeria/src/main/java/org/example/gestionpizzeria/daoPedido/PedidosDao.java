package org.example.gestionpizzeria.daoPedido;

import org.example.gestionpizzeria.model.Pedidos;

import java.sql.SQLException;
import java.util.List;

public interface PedidosDao {
    void insertarPedidos(Pedidos pedidos)throws SQLException;
    List<Pedidos>obtenerPedidos();
    int borrarPedidos(int telefono);
}
