package org.example.gestionpizzeria.daoPedido;

import org.example.gestionpizzeria.db.DBConnection;
import org.example.gestionpizzeria.db.SchemaDB;
import org.example.gestionpizzeria.model.Pedidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidosDaoImp implements PedidosDao{
    private Connection connection;
    private PreparedStatement preparedStatement;

    public PedidosDaoImp(){
        connection= DBConnection.getConnection();
    }
    @Override
    public void insertarPedidos(Pedidos pedidos)throws SQLException {
        String query=String.format("INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)",
                SchemaDB.TAB_NAME, SchemaDB.COL_NAME,SchemaDB.COL_TELF,SchemaDB.COL_PIZZA,
                SchemaDB.COL_TAMANIO,SchemaDB.COL_PRECIO);
        try {
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1,pedidos.getNombre());
            preparedStatement.setInt(2,pedidos.getTelefono());
            preparedStatement.setString(3, pedidos.getTipoPizza());
            preparedStatement.setString(4,pedidos.getTamanio());
            preparedStatement.setInt(5,pedidos.getPrecio());
            preparedStatement.execute();

        }catch (SQLException e){
            System.out.println("error de inyeccion de query");
            System.out.println(e.getMessage());
        }



    }

    @Override
    public List<Pedidos> obtenerPedidos() {
        List<Pedidos> listaPedidos=new ArrayList<>();
        try {
            preparedStatement= connection.prepareStatement("SELECT * FROM ");
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                String nombre= resultSet.getNString(SchemaDB.COL_NAME);
                int telefono= resultSet.getInt(SchemaDB.COL_TELF);
                String tipo=resultSet.getNString(SchemaDB.COL_PIZZA);
                String tamanio=resultSet.getNString(SchemaDB.COL_TAMANIO);
                int precio=resultSet.getInt(SchemaDB.COL_PRECIO);
                Pedidos pedidos=new Pedidos(nombre,telefono,tipo,tamanio);
                listaPedidos.add(pedidos);
            }
        } catch (SQLException e) {
            System.out.println("Error en el select");
            System.out.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public int borrarPedidos(int telefono) {
        return 0;
    }
}
