package ListarMultimedia.model;

public class Audio extends Elemento {
    private String soporte;
    private double duracion;

    public Audio() {
    }

    public Audio(String id , String titulo , String formato , Persona autor , int identificador , int tamanio) {
        super(id , titulo , formato , autor , identificador , tamanio);
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Soporte = " +soporte);
        System.out.println("Duracion = " +duracion);
        super.mostrarDatos();
    }

    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
}
