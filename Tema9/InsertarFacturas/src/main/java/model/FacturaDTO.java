package model;

import database.SchemaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturaDTO {
    private String numeroFactura;
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String productos;
    private double precioFinal;

    public FacturaDTO() {
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public void insert(Connection conn) throws SQLException {
        String sql = "INSERT INTO " + SchemaDB.TAB_FACTURAS + " (" +
                SchemaDB.COL_NUMERO + ", " +
                SchemaDB.COL_NOMBRE + ", " +
                SchemaDB.COL_DIRECCION + ", " +
                SchemaDB.COL_MAIL + ", " +
                SchemaDB.COL_PRODUCTOS + ", " +
                SchemaDB.COL_PRECIO + ") VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1 , numeroFactura);
            stmt.setString(2 , nombre + " " + apellido); // Combina nombre y apellido
            stmt.setString(3 , direccion);
            stmt.setString(4 , correo);
            stmt.setString(5 , productos);
            stmt.setDouble(6 , precioFinal);

            stmt.executeUpdate();
        }
    }
}
