package EjerciciosClase;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio10 {
    public static void main(String[] args) {
        //10. (MenuArray)Crear un programa que mediante un menú permita:
        //- Crear un array de números enteros con n posiciones pedidas.
        //- Rellenar el array creado con números aleatorios.
        //- Rellenar el array creado con números pedidos por consola.
        //- Ordenar el array de mayor a menor
        //- Clonar el array con una una mayor longitud. Para ello pedir al usuario la nueva longitud (si esta es inferior a la que la existe continuar pidiendo)
        //- Mover todas las posiciones (pedir al usuario que seleccione la orientación)
        // - Mostrar por pantalla el array invertido
        Scanner scanner=new Scanner(System.in);
        int opcion;
        int [] numeros =null;
        do {
            System.out.println("1. Crear array");
            System.out.println("2. Rellenar aleatorios");
            System.out.println("3. Rellenar consola");
            System.out.println("4. Ordenar array");
            System.out.println("5. Clonar array");
            System.out.println("6. Rotacion izquierda");
            System.out.println("7. Rotacion derecha");
            System.out.println("8. Mover por pares");
            System.out.println("9. Invertir");
            System.out.println("10. ");
            System.out.println("11. Salir");
            System.out.println("Dime que accion quieres realizar");
            opcion= scanner.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("vas a generar un array");
                    break;
                case 2:
                    if (numeros!=null){
                        System.out.println("Vas a rellenar con aleatorios");
                        for (int i = 0; i < numeros.length; i++) {
                            numeros[i]=(int) (Math.random()*21);
                        }
                    }else
                        System.out.println("Inicia el array de antes ");
                    break;
                case 3:
                    if (numeros!=null){
                        System.out.println("Vas a rellenar con aleatorios");
                        for (int i = 0; i < numeros.length; i++) {
                            System.out.println("Introduce el numero de la posicion "+i);
                        }
                    }else
                        System.out.println("Inicia el array de antes ");
                    break;
                case 4:
                    //Para ordenar los arrays aleatorios que pedimos arriba
                    Arrays.sort(numeros);
                    break;
                case 5:
                    System.out.println("Cual es la nueva longitud del array");
                    int nuevaLongitud= scanner.nextInt();
                    //Para clonar un nuevo array pero con una nueva longitud
                    Arrays.copyOf(numeros,nuevaLongitud);
                    break;
                case 6:
                    //caso para rotar los arrays a la izquierda
                    if (numeros!=null){
                        System.out.println("Vas a rotar a la izquierda");
                        int temporal=numeros[0];
                        for (int i = 0; i < numeros.length-1; i++) {
                            numeros[i]=numeros[i+1];
                        }
                        numeros[numeros.length-1]=temporal;
                    }else {
                        System.out.println("No vas a rotar la izquierda");
                    }
                    break;
                case 7:
                    if (numeros!=null){
                        System.out.println("Vas a rotar a la derecha");
                        int temporal=numeros[numeros.length-1];
                        for (int i = numeros.length-1; i > 0 ; i--) {
                           numeros[i]=numeros[i-1];
                        }
                    }else {
                        System.out.println("No vas a rotar a la derecha");
                    }
                    break;
                case 8:
                    if (numeros!=null){
                        System.out.println("Vas a mover los pares");
                        for (int i = 0; i < numeros.length; i+=2) {
                            int temporal=numeros[i];
                            numeros[i]=numeros[i+1];
                            numeros[i+1]=temporal;

                        }
                    }else {
                        System.out.println("El elemento es nulo");
                    }
                    break;
                case 9:
                    if (numeros!=null){
                        System.out.println("Vas a invertir los arrays");
                        for (int i = 0; i < numeros.length/2; i+=2) {
                            int temporal=numeros[i];
                            numeros[i]= numeros[numeros.length-1-i];
                            numeros[numeros.length-1-i]=temporal;
                        }
                    }else {
                        System.out.println("No se puede realizar");
                    }
                    break;
                case 10:
                    if (numeros!=null){
                        for(int item:numeros){
                            System.out.println(item+"");
                        }
                        System.out.println();
                    }else {
                        System.out.println("No se ha inicializado el array");
                    }
                    break;
                case 11:
                    break;
            }
        }while(opcion!=10);
    }
}
