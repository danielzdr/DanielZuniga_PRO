package model;

public class VideoJuegoRPG extends Videojuego implements Descargable {
    private boolean mundoAbierto;
    private int horasHistoriaPrincipal;
    private double tamanioGB;
    private boolean modoMultijugador; // agregado para que compile

    public VideoJuegoRPG() {
    }

    public VideoJuegoRPG(String titulo , String desarrollador , String clasificacionEdad , int anoLanzamiento , double precioBase ,
                         boolean mundoAbierto , int horasHistoriaPrincipal , double tamanioGB , boolean modoMultijugador) {
        super(titulo , desarrollador , clasificacionEdad , anoLanzamiento , precioBase);
        this.mundoAbierto = mundoAbierto;
        this.horasHistoriaPrincipal = horasHistoriaPrincipal;
        this.tamanioGB = tamanioGB;
        this.modoMultijugador = modoMultijugador;
    }

    public boolean isMundoAbierto() {
        return mundoAbierto;
    }

    public void setMundoAbierto(boolean mundoAbierto) {
        this.mundoAbierto = mundoAbierto;
    }

    public int getHorasHistoriaPrincipal() {
        return horasHistoriaPrincipal;
    }

    public void setHorasHistoriaPrincipal(int horasHistoriaPrincipal) {
        this.horasHistoriaPrincipal = horasHistoriaPrincipal;
    }

    public double getTamanioGB() {
        return tamanioGB;
    }

    public void setTamanioGB(double tamanioGB) {
        this.tamanioGB = tamanioGB;
    }

    public boolean isModoMultijugador() {
        return modoMultijugador;
    }

    public void setModoMultijugador(boolean modoMultijugador) {
        this.modoMultijugador = modoMultijugador;
    }

    @Override
    public double calcularPrecioFinal() {
        double precioFinal = getPrecioBase();

        if (mundoAbierto) {
            precioFinal += 1.0;
        }
        if (horasHistoriaPrincipal > 50) {
            precioFinal += 0.5;
        }
        if (modoMultijugador) {
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
        return super.toString() +
                " VideoJuegoRPG{" +
                "mundoAbierto=" + mundoAbierto +
                ", horasHistoriaPrincipal=" + horasHistoriaPrincipal +
                ", tamanioGB=" + tamanioGB +
                ", modoMultijugador=" + modoMultijugador +
                '}';
    }
}
