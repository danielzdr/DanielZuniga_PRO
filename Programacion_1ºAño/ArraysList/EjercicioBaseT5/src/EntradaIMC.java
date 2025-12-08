import Model.Persona;

import java.util.Scanner;

public class EntradaIMC {

    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        Persona persona=new Persona();
        System.out.println("Introduce un nombre: ");
        String nombre= scanner.next();
        System.out.println("Introduce un apellido: ");
        String apellido= scanner.next();
        System.out.println("Introduce un dni: ");
        String dni= scanner.next();
        System.out.println("Introduce una edad: ");
        int edad= scanner.nextInt();
        System.out.println("Introduce una altura ");
        double altura= scanner.nextDouble();
        System.out.println("Introduce un peso ");
        double peso= scanner.nextDouble();

        Persona p1 =new Persona(nombre,apellido,dni,edad,altura,peso);
        Persona p2= new Persona("Daniel","Zuñiga");
        Persona p3= new Persona();

        p1.mostrarDatos();
        p2.mostrarDatos();
        p3.mostrarDatos();

        persona.setEdad(20);

        persona.setIMC();

        p1.mostrarIMC();
        p1.mostrarIMCMen();
        p1.mostrarIMCMUJ();

    }
}
