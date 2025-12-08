package WHILE;


import java.util.Random;

public class Ejercicio3While {
         public static void main(String[] args) {
        //Generar números enteros aleatorios entre 0 y 100 hasta obtener el 0.
        //El programa mostrará cada uno de los datos generados y obtendrá el mayor de los números generados. (MayorNumero)

             Random random = new Random();
             int numero;
             int mayorNumero = 0;

             // Ciclo para generar números aleatorios hasta que salga 0
             while (true) {
                 numero = random.nextInt(); // Genera un número aleatorio entre 0 y 100
                 System.out.println("Número generado: " + numero);

                 // Verificamos si el número generado es mayor que el mayor encontrado hasta ahora
                 if (numero > mayorNumero) {
                     mayorNumero = numero;
                 }

                 // Si el número generado es 0, se termina el ciclo
                 if (numero == 0) {
                     break;
                 }
             }

             System.out.println("El mayor número generado fue: " + mayorNumero);
         }

    }

