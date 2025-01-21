package agenda_arraylist;


import agenda_arraylist.controller.Agenda;

import java.util.Scanner;

public class Entrada {

    private static Scanner scanner=new Scanner(System.in);

    public static void main(String[] args) {
        Agenda agenda=new Agenda();

        System.out.println("Introduce tu nombre");
        String nombre=scanner.next();

        System.out.println("Introduce tus apellidos");
        String apellidos= scanner.next();

        System.out.println("Introduce tu telefeno");
        int telefono= scanner.nextInt();

        System.out.println("Introduce el dni");
        String dni= scanner.next();

        if (agenda.agregarPersona(nombre,apellidos,telefono,dni)){
            System.out.println("Usuario agregado correctamente");
        }else {
            System.out.println("No se ha agregado correctamente");
        }


        System.out.println("Introduce tu nombre");
        String nombre2=scanner.next();

        System.out.println("Introduce tus apellidos");
        String apellidos2= scanner.next();

        System.out.println("Introduce tu telefeno");
        int telefono2= scanner.nextInt();

        System.out.println("Introduce el dni");
        String dni2= scanner.next();

        if (agenda.agregarPersona(nombre2,apellidos2,telefono2,dni2)){
            System.out.println("Usuario agregado correctamente");
        }else {
            System.out.println("No se ha agregado correctamente");
        }

        switch(agenda.listarDatosPersona("1234A")){
            case 1:
                System.out.println("No se pueden hacer busquedas sobre una lista vacia");
                break;
            case 2:
                System.out.println("No esta en la losta, quieres agregarlo (s/n)");
                String contestacion= scanner.next();
                if (contestacion.equalsIgnoreCase("s")){
                    // pido datos
                    // agregar persona
                    agenda.agregarPersona("Borja","Martin",1234,"1234B");
                }else {
                    System.out.println("Continuando con el programa");
                }
                break;

        }
    }
}
