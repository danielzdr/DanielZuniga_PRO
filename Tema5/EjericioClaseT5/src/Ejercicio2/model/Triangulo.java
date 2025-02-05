package Ejercicio2.model;

public class Triangulo {

    private int base, altura;

    public Triangulo(){}

    public Triangulo(int base, int altura){
        this.base=base;
        this.altura=altura;
    }

    public double calcularArea(){
         return (base * altura) / 2.0;
    }

    public void mostrarDatos() {
        System.out.println("Base: " + base + ", Altura: " + altura);
    }
}
