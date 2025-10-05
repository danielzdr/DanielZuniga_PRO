package model;

public class VideojuegoEstrategia extends Videojuego implements Descargable{
    private int complejidad, duracioPartida;
    private double tamanioGB;

    public VideojuegoEstrategia() {
    }

    public VideojuegoEstrategia(String titulo , String desarrollador , String clasificacionEdad , int anoLanzamiento , double precio , int complejidad , int duracioPartida , double tamanioGB) {
        super(titulo , desarrollador , clasificacionEdad , anoLanzamiento , precio);
        this.complejidad = complejidad;
        this.duracioPartida = duracioPartida;
        this.tamanioGB = tamanioGB;
    }

    public int getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(int complejidad) {
        this.complejidad = complejidad;
    }

    public int getDuracioPartida() {
        return duracioPartida;
    }

    public void setDuracioPartida(int duracioPartida) {
        this.duracioPartida = duracioPartida;
    }

    @Override
    public int calcularPrecioFinal(int precio) {


        return precio;
    }

     public double getTamanioGB() {
         return tamanioGB;
     }

     public void setTamanioGB(double tamanioGB) {
         this.tamanioGB = tamanioGB;
     }

     @Override
     public int calcularTiempoDescarga(double vel) {
         return 0;
     }

     @Override
     public double obtenerTamanioGB() {
         return 0;
     }

     @Override
    public String toString() {
        return super.toString()+"VideojuegoEstrategia{" +
                "complejidad=" + complejidad +
                ", duracioPartida=" + duracioPartida +
                ", TamañoGB =" +tamanioGB +
                '}';
    }
}
