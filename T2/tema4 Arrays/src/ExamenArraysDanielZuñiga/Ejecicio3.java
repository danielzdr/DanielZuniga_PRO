package ExamenArraysDanielZuñiga;

import java.util.Arrays;
import java.util.Scanner;

public class Ejecicio3 {
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        int[] numeros = new int[20];
        int [] num=null;
        int opcion=0;
        do {
            System.out.println("1. Registrar posiciones");
            System.out.println("2. Obtener elementos");
            System.out.println("3. Mostrar el array completo");
            System.out.println("4. Mostrar array ordenado");
            System.out.println("5. Rotar el array");
            System.out.println("6. Salir");
            System.out.println("Introduce la opcion que quieres realizar");
            opcion=scanner.nextInt();
            switch (opcion){
                case 1:
                    for (int i = 0; i < numeros.length; i++) {
                        numeros[i]= scanner.nextInt();
                        System.out.println(numeros[i]);
                    }

                    break;
                case 2:
                    for (int i = 0; i < numeros.length; i++) {
                        numeros[i]=numeros[i+1];
                        for (int item: numeros){
                            numeros[i]+=item;
                            System.out.println(item);
                        }
                        System.out.println();
                    }

                    break;
                case 3:
                    for (int i = 0; i < numeros.length; i++) {
                        numeros[i]+=numeros.length;
                    }
                    break;
                case 4:
                    System.out.println("Array ordenado");

                    for (int i = 0; i < numeros.length; i++) {
                        Arrays.sort(numeros);
                        if (numeros[i]<numeros.length){
                            System.out.println("Array ordenado de menor a mayor");
                        }else {
                            System.out.println("Error");
                        }
                    }
                    break;
                case 5:
                    if (numeros!=num){
                        System.out.println("Vas a rotar a la derecha");
                        int temporal=numeros[numeros.length-1];
                        for (int i = numeros.length-1; i > 0 ; i--) {
                            numeros[i]=numeros[i-1];
                        }
                    }else {
                        System.out.println("No vas a rotar a la derecha");
                    }
                    break;
                case 6:
                    System.out.println("Saliendo del programa.....");
                    break;
                default:
                    System.out.println("Opcion invalida, intentalo de nuevo");
                    break;
            }
        }while(opcion!=7);
    }
}
