package AplicacionCoches.model;

public class Coche {
    private String marca, modelo, matricula;
    private int cv, cc;
    private double precio;

    public Coche() {
    }

    public Coche(String marca , String modelo , String matricula , int cv , int cc , double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.cv = cv;
        this.cc = cc;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", matricula='" + matricula + '\'' +
                ", cv=" + cv +
                ", cc=" + cc +
                ", precio=" + precio +
                '}';
    }

    public void mostrarDatos(){
        System.out.println("marca=" + marca + '\'' +
                ", modelo=" + modelo + '\'' +
                ", matricula='" + matricula + '\'' +
                ", cv=" + cv +
                ", cc=" + cc +
                ", precio=" + precio
                );
    }

    public void reprogramarMotor(int nuevosCaballos){
        if (nuevosCaballos>0){
            setCv(nuevosCaballos);
            System.out.println("El motor ha sido reprogramado correctamente. Ahora tiene " +nuevosCaballos);
        }else {
            System.out.println("No se puede reprogramar el motor del coche");
        }
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
