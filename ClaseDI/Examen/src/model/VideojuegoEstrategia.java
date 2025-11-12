package model;

public class VideojuegoEstrategia extends Videojuego implements Descargable{
    private int complejidad, duracioPartida;
    private double tamanioGB;

    public VideojuegoEstrategia() {
    }

    public VideojuegoEstrategia(String titulo , String desarrollador , String clasificacionEdad , int anoLanzamiento , double precioBase , int complejidad , int duracioPartida , double tamanioGB) {
        super(titulo , desarrollador , clasificacionEdad , anoLanzamiento , precioBase);
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


     public double getTamanioGB() {
         return tamanioGB;
     }

     public void setTamanioGB(double tamanioGB) {
         this.tamanioGB = tamanioGB;
     }

    @Override
    public double calcularPrecioFinal() {
        double precioFinal = getPrecioBase();

        if (complejidad > 5) {
            precioFinal += 0.5;
        }
        if (duracioPartida > 60) {
            precioFinal += 0.3;
        }

        return precioFinal;
    }

    @Override
    public int calcularTiempoDescarga(double velocidadMBps) {
        if (velocidadMBps <= 0) {
            return -1;
        }

        return (int) (tamanioGB * 1024 / velocidadMBps);
    }

    @Override
    public double obtenerTamanioGB() {
        return tamanioGB;
    }

    @Override
    public String toString() {
        return super.toString() + " VideojuegoEstrategia{" +
                "complejidad=" + complejidad +
                ", duracionPartida=" + duracioPartida +
                ", tamanioGB=" + tamanioGB +
                '}';
    }
}
