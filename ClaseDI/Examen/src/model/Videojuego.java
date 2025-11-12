package model;

public abstract class Videojuego {
    private String titulo, desarrollador, clasificacionEdad;
    private int anoLanzamiento;
    private double precioBase;

    public Videojuego() {
    }

    public Videojuego(String titulo , String desarrollador , String clasificacionEdad , int anoLanzamiento , double precioBase) {
        this.titulo = titulo;
        this.desarrollador = desarrollador;
        this.clasificacionEdad = clasificacionEdad;
        this.anoLanzamiento = anoLanzamiento;
        this.precioBase = precioBase;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesarrolldor() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrolldor) {
        this.desarrollador = desarrolldor;
    }

    public String getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(String clasificacionEdad) {
        this.clasificacionEdad = clasificacionEdad;
    }

    public int getAnoLanzamiento() {
        return anoLanzamiento;
    }

    public void setAnoLanzamiento(int anoLanzamiento) {
        this.anoLanzamiento = anoLanzamiento;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase() {
        this.precioBase = precioBase;
    }

    public static int calcularPrecioFinal(int precio){

        return precio;
    }

   

    @Override
    public String toString() {
        return "Videojuego{" +
                "titulo='" + titulo + '\'' +
                ", desarrolldor='" + desarrollador + '\'' +
                ", clasificacionEdad='" + clasificacionEdad + '\'' +
                ", anoLanzamiento=" + anoLanzamiento +
                ", precio=" + precioBase +
                '}';
    }


    public double calcularPrecioFinal() {
        return precioBase;
    }
}

