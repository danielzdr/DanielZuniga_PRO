package model;

public class VideoJuegoRPG extends Videojuego implements Descargable{
    private boolean mundoAierto;
    private int horasHistoriaPrincipal;
    private double tamanioGB;

    public VideoJuegoRPG() {
    }

    public VideoJuegoRPG(String titulo , String desarrollador , String clasificacionEdad , int anoLanzamiento , double precio , boolean mundoAierto , int horasHistoriaPrincipal , double tamanioGB) {
        super(titulo , desarrollador , clasificacionEdad , anoLanzamiento , precio);
        this.mundoAierto = mundoAierto;
        this.horasHistoriaPrincipal = horasHistoriaPrincipal;
        this.tamanioGB = tamanioGB;
    }

    public boolean isMundoAierto() {
        return mundoAierto;
    }

    public void setMundoAierto(boolean mundoAierto) {
        this.mundoAierto = mundoAierto;
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



    @Override
    public int calcularPrecioFinal(int precio) {
        boolean hayMundoAbierto=true;
        if (hayMundoAbierto== mundoAierto){
            precio+=0.15;
        } else if (horasHistoriaPrincipal==10) {
            precio+=0.2;
        }else {
            System.out.println("No ha sido posible realizar el cambio");
        }

        return precio;
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
        return super.toString()+"VideoJuegoRPG{" +
                "mundoAierto=" + mundoAierto +
                ", horasHistoriaPrincipal=" + horasHistoriaPrincipal +
                ",TamañoGb= "+ tamanioGB +
                '}';
    }
}
