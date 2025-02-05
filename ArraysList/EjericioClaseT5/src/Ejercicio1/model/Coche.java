package Ejercicio1.model;

import java.util.Random;

public class Coche {
    private Motor motor;
    private String marca, modelo, matricula;
    private double precioAveria;
    private Random random;

    public Coche(String marca, String modelo, String matricula){
        this.marca=marca;
        this.modelo=modelo;
        this.matricula=matricula;
    }

    public Motor getMotor() {
        return motor;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public double getPrecioAveria() {
        return precioAveria;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setPrecioAveria(double precioAveria) {
        this.precioAveria = precioAveria;
    }

    public void agregarAveria(String aceite){
        int incremento= (int)random.nextInt(10);
        precioAveria += incremento;
        System.out.println("Se ha acumulado una avería de: " + incremento + "€. Importe total: " + precioAveria + "€");
    }
}
