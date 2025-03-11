package model;




public final class Bici extends Vehiculo {

    private String tipo;


    public Bici() {
    }

    public Bici(String estado , String marca , String modelo , String numeroSerie , double precioVenta , String tipo) {
        super(estado , marca , modelo , String.valueOf(numeroSerie) , precioVenta);
        this.tipo = tipo;
    }

    public void mostrarDatos(){
        System.out.println("Tipo= " +tipo);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
