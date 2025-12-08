import java.util.ArrayList;
import java.util.Scanner;

public class PracticarArraysList {

    //Pedir al usuario un numero a añadir, añadirlo a la lista con una particularidad,
// si el numero ya esta en la lista, pedir confirmacion
    //si no esta en la losta agregarlo
    private static ArrayList<Integer> listaNumeros=new ArrayList();
    private static Scanner scanner=new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Utilizando listas");
        System.out.println("El tamaño inicial es:"+listaNumeros.size());
        anadirNumeros(13);
        anadirNumeros(23);
        anadirNumeros(13);
        anadirNumeros(17);
    }

    public static void anadirNumeros(int numero){
        if (listaNumeros.contains(numero)){
            //estas seguro
            System.out.println("El numero dentro de la lista,¿estas seguro que quieres meterlo?");
            String respuesta= scanner.next();
            if (respuesta.equalsIgnoreCase("y")){
                listaNumeros.add(numero);
                System.out.println("Numero añadido correctamente");
                System.out.println("Lista actualizada con un size de "+listaNumeros.size());
            }else{
                System.out.println("Saliendo de añadir");
            }
        }else {
            listaNumeros.add(numero);
            System.out.println("Numero añadido correctamente");
            System.out.println("Lista actualizada con un size de "+listaNumeros.size());
        }

    }

    //TODO cambiar el retorno al indice donde esta repetido
    public static int encontrarNumero(int numero){
        for (int i = 0; i < listaNumeros.size(); i++) {
            if (listaNumeros.get(i)==numero){
                return i;
            }
        }
        return -1;
    }
}
