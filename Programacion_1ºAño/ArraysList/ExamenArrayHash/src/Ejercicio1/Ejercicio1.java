package Ejercicio1;

import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controladora1 controladora1 = new Controladora1();
        controladora1.agregarProducto("Cebolla", 3);
        controladora1.agregarProducto("Pan", 1);
        controladora1.agregarProducto("Carne", 5);
        controladora1.agregarProducto("Arroz", 4);
        controladora1.listarProductos();
        System.out.println("Por favor introduce el precio minimo ");
        int precioMinimo= scanner.nextInt();
        controladora1.listarPrecioMinimo(precioMinimo);
        controladora1.listarOrdenado();
    }
}
