package EjerBaseArrayList;

import java.util.ArrayList;

public class Ejercicio1 {

    //1. Crea un ArrayList de String y realiza las siguientes acciones
    //- Guarda manualmente 10 palabras
    //- Imprime por pantalla todas las palabras del arraylist

    private static ArrayList<String> listaPalabras=new ArrayList();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            agregarPalabras("Palabras"+(i+1));
        }
        imprimirPalabras(listaPalabras);

    }
    public static void imprimirPalabras(ArrayList<String>lista){
        for (String item: listaPalabras){

        }
    }

    public static void agregarPalabras(String palabras){

    }


}
