package Ejercicio2;

import Ejercicio2.model.Circulo;
import Ejercicio2.model.Cuadrado;
import Ejercicio2.model.Triangulo;

import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        int opcion;

        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Trabajar con triángulos");
            System.out.println("2. Trabajar con círculos");
            System.out.println("3. Trabajar con cuadrados");
            System.out.println("4. Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce base: ");
                    int baseTri = scanner.nextInt();
                    System.out.print("Introduce altura: ");
                    int alturaTri = scanner.nextInt();
                    Triangulo triangulo = new Triangulo(baseTri, alturaTri);
                    System.out.println("¿Qué operación quieres hacer?");
                    System.out.println("1. Calcular área");
                    System.out.println("2. Mostrar datos");
                    int operacionTri = scanner.nextInt();
                    if (operacionTri == 1) {
                        System.out.println("Área: " + triangulo.calcularArea());
                    } else {
                        triangulo.mostrarDatos();
                    }
                    break;
                case 2:
                    System.out.print("Introduce radio: ");
                    double radio = scanner.nextDouble();
                    Circulo circulo = new Circulo(radio);
                    System.out.println("¿Qué operación quieres hacer?");
                    System.out.println("1. Calcular área");
                    System.out.println("2. Calcular diámetro");
                    System.out.println("3. Mostrar datos");
                    int operacionCir = scanner.nextInt();
                    if (operacionCir == 1) {
                        System.out.println("Área: " + circulo.calcularArea());
                    } else if (operacionCir == 2) {
                        System.out.println("Diámetro: " + circulo.calcularDiametro());
                    } else {
                        circulo.mostrarDatos();
                    }
                    break;
                case 3:
                    System.out.print("Introduce base: ");
                    int baseCuad = scanner.nextInt();
                    System.out.print("Introduce altura: ");
                    int alturaCuad = scanner.nextInt();
                    Cuadrado cuadrado = new Cuadrado(baseCuad, alturaCuad);
                    System.out.println("¿Qué operación quieres hacer?");
                    System.out.println("1. Calcular área");
                    System.out.println("2. Calcular perímetro");
                    System.out.println("3. Mostrar datos");
                    int operacionCuad = scanner.nextInt();
                    if (operacionCuad == 1) {
                        System.out.println("Área: " + cuadrado.calcularArea());
                    } else if (operacionCuad == 2) {
                        System.out.println("Perímetro: " + cuadrado.calcularPerimetro());
                    } else {
                        cuadrado.mostrarDatos();
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
            System.out.println("Pulsa enter para continuar...");

        } while (opcion != 4);

    }
}
