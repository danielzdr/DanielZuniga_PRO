import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

import model.Coche;
import model.Persona;

/*
Un coche tiene un atributo KM,
los cuales pueden decir la distancia que ha recorrido un coche
Para poder hacer una carrera, es necesario que se pida por consola
los datos de 6 participantes, donde se debe indicar marca,  modelo, matricula, cv
Una vez todos los participantes, es necesario indicar cuantas vueltas consta la carrera
Una vez indicado las 6 vueltas la carrera comienza. Para ello en cada vuelta cada cada
coche recorre un numero de km aleatorio entre (50 y 75)
Una vez se terminan las vueltas el sistema mostrara :
el ganador es el coche XXXX XXXX XXXX
La clasificacion final es:
1- XXXX XXXX XXXX
2- XXXX XXXX XXXX
3- XXXX XXXX XXXX
 */
public class Carrera {
    public static void main(String[] args) {new ArrayList<>();
        ArrayList<Coche> listaCoches= new ArrayList<>();
        ArrayList<Persona> listaPersona= new ArrayList<>();

        Scanner scanner=new Scanner(System.in);
        Random random= new Random();

        for (int i = 0; i <= 6; i++) {
            System.out.println("Introduce el dni que quiere ingresar");
            String dni= scanner.next();
            System.out.println("Introduce el nombre que quiere ingresar");
            String nombre= scanner.next();
            System.out.println("Introduce el apellido que quiere ingresar");
            String apellido= scanner.next();
            Persona persona=new Persona(dni,nombre,apellido);
            listaPersona.add(persona);

        }

        for (int i = 0; i <= 6; i++) {
            System.out.println("Introduce la marca ");
            String marca= scanner.next();
            System.out.println("Introduce el modelo ");
            String modelo= scanner.next();
            System.out.println("Introduce la matricula ");
            String matricula= scanner.next();
            System.out.println("Introduce el cv");
            int cv= scanner.nextInt();
            Coche coche=new Coche(marca,modelo,matricula,cv);
            listaCoches.add(coche);

        }


        System.out.println("De cuantas vueltas consta la carrera: ");
        int vueltas= scanner.nextInt();

        int totalKM=0;
        for (int i = 0; i < vueltas; i++) {
            for (Coche coche: listaCoches) {
                int kmAleatorio =(int) (Math.random()*26)+50;
                coche.setKm(kmAleatorio);
                System.out.println("Vuelta " + i + " : " + kmAleatorio + "km");
            }
        }

        listaCoches.sort(new Comparator<Coche>() {
            @Override
            public int compare(Coche o1 , Coche o2) {
                if (o1.getKm()>o2.getKm()) {
                    return 1;
                } else if (o1.getKm()<o2.getKm()) {
                    return -1;
                }
                return  0;
            }
        });

        System.out.println("El ganador de la carrera es " +listaCoches.get(0).getMatricula()+" con km " +listaCoches.get(0).getKm());


    }
}

