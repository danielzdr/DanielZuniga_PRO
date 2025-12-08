package Garages_arraylist.controller;

import java.util.ArrayList;
import java.util.Scanner;

public class Garaje {
    Scanner scanner=new Scanner(System.in);
    //para sacar el foreach de -> listacoches.for
    //Variables siempre en privada
    private ArrayList<Object[]> listacoches = new ArrayList<>(5);

    public void anadirCoche(String marca , String modelo , String matricula , int cv) {
        Object[] coche = new Object[]{marca , modelo , matricula , cv};
        listacoches.add(coche);
        if (listacoches.size()<5){
            if (!estaCoche(matricula)){
                listacoches.add(coche);
            }else {
                System.out.println("La matricula esta en la lista, imposible entrar");
            }
            System.out.println("Todo en orden");
        }else {
            System.out.println("Error hay mas de 5 coches");
        }

    }

    private boolean estaCoche(String matricula){
        for (Object[] coche : listacoches) {
            if (coche[2].equals(matricula)){
                System.out.println("Ya existe ese numero de matricula");
                return true;
            }
        }
        return false;
    }

    public void mostrarCoche() {
        if (listacoches.isEmpty()){
            System.out.println("No hay coches que listar");
        }else{
            for (Object[] coche : listacoches) {
                System.out.println(coche[3]+"-"+coche[0]+"-"+coche[2]);
            }
        }
    }

    public void listarMarca(String marca) {
        for (Object[] coche : listacoches) {
            if (coche[0].toString().equalsIgnoreCase(marca)){
                System.out.println(coche[3]+"-"+coche[0]+"-"+coche[2]);
            }
            System.out.println("Error, la lista esta vacia");

        }
    }

    public void gestionCoche(){
        if (listacoches.size()<5){
            System.out.println("Todo en orden");
        }else {
            System.out.println("Error hay mas de 5 coches");
        }


    }

}
