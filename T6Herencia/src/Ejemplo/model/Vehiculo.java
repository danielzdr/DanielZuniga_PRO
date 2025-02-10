package Ejemplo.model;

public class Vehiculo {
    private Motor motor;
    private String bastidor;

    public Vehiculo(Motor motor , String bastidor) {
        this.motor = motor;
        this.bastidor = bastidor;
    }

    public Vehiculo() {
    }

    public void mostrarDatos(){
        System.out.println("Ejemplo.ListarMultimedia.Trabajadores.Llamadas.model.Ejemplo.ListarMultimedia.Trabajadores.Llamadas.model.Motor "+motor.getCv());
        System.out.println("Bastidor "+bastidor);
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public String getBastidor() {
        return bastidor;
    }

    public void setBastidor(String bastidor) {
        this.bastidor = bastidor;
    }
}
