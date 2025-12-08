package model;

abstract public class Vehiculo {
    private String estado, marca, modelo, numeroSerie;
    private double precioVenta;


    public Vehiculo() {
    }

    public Vehiculo(String estado , String marca , String modelo , String numeroSerie , double precioVenta) {
        this.estado = estado;
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.precioVenta = precioVenta;
        this.precioVenta += calcularIncremento();
    }

    public void mostrarDatos(){
        System.out.println("Estado= " +estado+
                            "Marca= " +marca+
                            "Modelo= " +modelo+
                            "Numero de serie= " +numeroSerie+
                            "Precio de venta= " +precioVenta);
    }

    public void reparar(){}

    public void vender(){}

    public String getEstado() {
        return estado;
    }

    private double calcularIncremento(){
        double incremento=0;
        if (this.estado.equalsIgnoreCase("nuevo")) {
            incremento=this.precioVenta*0.1;
        } else if (this.estado.equalsIgnoreCase("seminuevo")) {
            incremento=this.precioVenta*0.05;
        }
        return incremento;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getnumeroSerie() {
        return numeroSerie;
    }

    public void setnumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
}
