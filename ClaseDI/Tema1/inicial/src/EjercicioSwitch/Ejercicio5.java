package EjercicioSwitch;

import java.util.Scanner;

public class Ejercicio5 {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Introduce un codigo de producto");
        String codigo=scanner.next();
        switch(codigo){
            case "P001": System.out.println("Producto: Lápiz, Precio: 0,50€"); break;
            case "P002": System.out.println("Producto: Cuaderno, Precio: 2,00€"); break;
            case "P003": System.out.println("Producto: Bolígrafo, Precio: 1,20€"); break;
            default: System.out.println("Código no reconocido");
        }
    }

}
