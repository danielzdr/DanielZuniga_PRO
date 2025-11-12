package dao;

import org.example.formulario.db.DBConnection;
import org.example.formulario.db.SchemaDB;
import org.example.formulario.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoImp implements UsuarioDAO{
    private Connection connection;
    private PreparedStatement preparedStatement;

    //STATEMENT PREPAREDSTATEMENT
    //execute-> boolean->Insert
    //executeUpdate-> int -> Update o delete
    //resultSet->Select


    public UsuarioDaoImp(){
        connection=DBConnection.getConnection();
    }
    @Override
    public void insertarUsuario(Usuario usuario) throws SQLException{
        String query=String.format("INSERT INTO %s (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?) ",
                SchemaDB.TAB_NAME,SchemaDB.COL_NAME,SchemaDB.COL_SURNAME,SchemaDB.COL_MAIL,
                SchemaDB.COL_EDAD,SchemaDB.COL_GENERO);
        try {
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellidos());
            preparedStatement.setString(3, usuario.getCorreo());
            preparedStatement.setInt(4, usuario.getEdad());
            preparedStatement.setString(5, usuario.getGenero());
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println("Error en la insercion");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> listaUsuario=new ArrayList<>();
        try {
            preparedStatement= connection.prepareStatement("SELECT * FROM " +SchemaDB.TAB_NAME);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                String nombre= resultSet.getNString(SchemaDB.COL_NAME);
                String apellidos= resultSet.getNString(SchemaDB.COL_SURNAME);
                String correo= resultSet.getNString(SchemaDB.COL_MAIL);
                int edad= resultSet.getInt(SchemaDB.COL_EDAD);
                String genero=resultSet.getNString(SchemaDB.COL_GENERO);
                Usuario usuario= new Usuario(nombre,correo,apellidos,genero,edad,true);
                listaUsuario.add(usuario);


            }
        } catch (SQLException e) {
            System.out.println("Error en la query");
            System.out.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public List<Usuario> actualizarUsuarios(String correo) {
        String query = String.format("UPDATE %s SET %s=?",
                SchemaDB.TAB_NAME,SchemaDB.COL_MAIL);
        try {
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1,correo);
            preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println("Error al obtener Usuarios");
            System.out.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public int borrarUsuarios(String nombre) {
        String query=String.format("DELETE FROM %s WHERE %s=?",SchemaDB.TAB_NAME,SchemaDB.COL_NAME);
        try {
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1,nombre);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario");
        }

        return 0;
    }
}

