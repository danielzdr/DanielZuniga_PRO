import java.util.ArrayList;

public class Entrada {
//dinamicos
    //añadir -> add()
    //obtener-> get()
    //borrar -> remove()
    //cambiar->set()
    //borrado condicional->removeif(predicado)
    //vaciar o limpiar->clear()
    //tamaño -> size()
    // Collections.sort -> ordenar en arraylist

    private static ArrayList<Integer> listaNumeros = new ArrayList();

    public static void main(String[] args) {
        System.out.println("Utilizando listas");
        System.out.println("El tamaño inical de la lista es: " + listaNumeros.size());
        anadirNumero(50);
        anadirNumero(20);
        obtenerDatos();
    }


    public static void anadirNumero(int numero) {
        listaNumeros.add(numero);
        System.out.println("Numero añadido corrctamente");
        System.out.println("Lista actualizada con un size de" + listaNumeros.size());
    }


    public static void obtenerDatos() {
        System.out.println("El ultimo elemento del array es "+listaNumeros.get(listaNumeros.size()-1));
        System.out.println("Todos los elementos del array con");
        for (int item: listaNumeros){
            System.out.println(item);
        }
    }


}