package org.example.gestionpizzeria.daoPedido;

import org.example.gestionpizzeria.db.DBConnection;
import org.example.gestionpizzeria.model.Pedidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PedidosDaoImp implements PedidosDao{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public PedidosDaoImp(){
        connection= DBConnection.getConnection();
    }
    @Override
    public void insertarPedidos(Pedidos pedidos)throws SQLException {

    }

    @Override
    public List<Pedidos> obtenerPedidos() {
        return List.of();
    }

    @Override
    public int borrarPedidos(int telefono) {
        return 0;
    }
}
