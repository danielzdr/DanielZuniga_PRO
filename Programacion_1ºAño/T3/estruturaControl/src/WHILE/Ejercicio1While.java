package WHILE;

import java.util.Scanner;

public class Ejercicio1While {
        static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        //Leer números enteros hasta introducir un 0. El programa obtendrá la suma de todos
        //los números positivos. (SumarPositivos)


        int numero;
        int sumaPositivos=0;

        while (true){
            System.out.println("Introduce numeros: ");
            numero= lectorTeclado.nextInt();

            if (numero==0){
                break;

            }else if (numero >0){
                sumaPositivos+=numero;


            }
        }
        System.out.println("La suma de los numeros positivos es "+sumaPositivos);

        }

    }

