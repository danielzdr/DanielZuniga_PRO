package model;

public class Factura {
    private String numero;
    private String clienteNombre;
    private String clienteApellido;
    private String direccion;
    private String correo;
    private String productos;
    private double precioFinal;


    public Factura() {
    }

    public Factura(String numero , String clienteNombre , String clienteApellido , String direccion , String correo , String productos , double precioFinal) {
        this.numero = numero;
        this.clienteNombre = clienteNombre;
        this.clienteApellido = clienteApellido;
        this.direccion = direccion;
        this.correo = correo;
        this.productos = productos;
        this.precioFinal = precioFinal;
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }

    public String getClienteApellido() { return clienteApellido; }
    public void setClienteApellido(String clienteApellido) { this.clienteApellido = clienteApellido; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getProductos() { return productos; }
    public void setProductos(String productos) { this.productos = productos; }

    public double getPrecioFinal() { return precioFinal; }
    public void setPrecioFinal(double precioFinal) { this.precioFinal = precioFinal; }
}

