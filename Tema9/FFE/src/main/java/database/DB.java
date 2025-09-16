package database;
import org.json.JSONObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class DB {

    public static void guardarProducto(JSONObject producto) {
        try (Connection conn = ConexionDB.obtenerConexion()) {
            String sql = """
                INSERT INTO productos 
                (numero, fecha_lanzada, cliente, cantidad, trabajo, familia, fecha_entrega, notas)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, producto.getString("numero"));
            ps.setDate(2, Date.valueOf(producto.getString("fecha_lanzada")));
            ps.setString(3, producto.getString("cliente"));
            ps.setInt(4, producto.getInt("cantidad"));
            ps.setString(5, producto.getString("trabajo"));
            ps.setString(6, producto.getString("familia"));
            ps.setDate(7, Date.valueOf(producto.getString("fecha_entrega")));
            ps.setString(8, producto.optString("notas", ""));
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(" Error al guardar producto: " + e.getMessage());
            e.printStackTrace();
        }
    }
}





