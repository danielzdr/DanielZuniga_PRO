package Ejercicio2.model;

public class Circulo {
    private double radio, diametro, area;

    public Circulo(){}

    public Circulo(double radio){
        this.radio=radio;
    }

    public double calcularArea(){
         return area=(double) (Math.PI*(radio*radio));
    }

    public double calcularDiametro(){
         return diametro=(double) (radio*radio);
    }

    public void mostrarDatos() {
        System.out.println("Area: " + area + ", Diametro: " + diametro);
    }
}
