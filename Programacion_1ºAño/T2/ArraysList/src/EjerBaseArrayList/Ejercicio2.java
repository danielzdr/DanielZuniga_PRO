package EjerBaseArrayList;

import java.util.ArrayList;
import java.util.Collections;

public class Ejercicio2 {
    //Crea un ArrayList de numeros enteros que guarde valores aleatorios (entre 0 y 100).
    // Una vez introducidos todos calcula la suma, la media, el máximo y el mínimo de esos números.

    private static ArrayList<Integer> listaNumeros=new ArrayList();

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            agregarNumero((int)(Math.random()*101));
        }
        imprimirArrayList(listaNumeros);
        //calculo min y max
        int max=listaNumeros.getFirst();
        int min=listaNumeros.getFirst();
        int suma=0;
        for (int item: listaNumeros) {
            item+=suma;
        }
        double media= (double) suma/listaNumeros.size();
            Collections.sort(listaNumeros);
            max = listaNumeros.getLast();
            min = listaNumeros.getFirst();

    }
    public static void agregarNumero(int numero){
        listaNumeros.add(numero);
    }

    public static void imprimirArrayList(ArrayList<Integer>lista){
        for (int item:listaNumeros){
            System.out.println(item);
        }
    }
}
