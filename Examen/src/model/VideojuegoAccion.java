package model;

public class VideojuegoAccion extends Videojuego implements Descargable {
    private int nivelViolencia;
    private boolean modoMultijugador;
    private double tamanioGB;

    public VideojuegoAccion() {

    }

    public VideojuegoAccion(String titulo, String desarrollador, String clasificacionEdad, int anoLanzamiento, double precioBase, int nivelViolencia, boolean modoMultijugador, double tamanioGB) {
        super(titulo, desarrollador, clasificacionEdad, anoLanzamiento, precioBase);
        this.nivelViolencia = nivelViolencia;
        this.modoMultijugador = modoMultijugador;
        this.tamanioGB = tamanioGB;
    }

    public int getNivelViolencia() {
        return nivelViolencia;
    }

    public void setNivelViolencia(int nivelViolencia) {
        this.nivelViolencia = nivelViolencia;
    }

    public boolean isModoMultijugador() {
        return modoMultijugador;
    }

    public void setModoMultijugador(boolean modoMultijugador) {
        this.modoMultijugador = modoMultijugador;
    }

    @Override
    public int calcularTiempoDescarga(double vel) {
        if (vel <= 0) {
            return -1; // velocidad inválida
        }
        return (int) (tamanioGB * 1024 / vel); // tiempo en segundos
    }

    @Override
    public double obtenerTamanioGB() {
        return tamanioGB;
    }

    @Override
    public double calcularPrecioFinal() {
        double precio = getPrecioBase();
        boolean hayMultijugador = true;

        if (nivelViolencia > 3) {
            precio += 0.5;
        } else if (hayMultijugador == modoMultijugador) {
            precio += 0.1;
        } else {
            System.out.println("No ha sido posible hacer el cambio");
        }

        return precio;
    }

    @Override
    public String toString() {
        return super.toString() + " VideojuegoAccion{" +
                "nivelViolencia=" + nivelViolencia +
                ", modoMultijugador=" + modoMultijugador +
                ", tamanioGB=" + tamanioGB +
                '}';
    }
}
