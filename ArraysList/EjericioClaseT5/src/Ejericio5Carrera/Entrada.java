package Ejericio5Carrera;

import Ejericio5Carrera.model.Coche;


public class Entrada {
    public static void main(String[] args) {
        int kmEtapa= 10000;
        int iteracion=0;
        int maxIteracion=100;
        Coche cocheA = new Coche();
        Coche cocheB = new Coche("Ibiza" , "2335GHJ" , 400);
        do {

            cocheA.acelerarCoche(50); // Aceleramos coche1
            cocheB.acelerarCoche(50); // Aceleramos coche2
            iteracion++; // Incrementamos el contador de iteraciones

            // Si se alcanza el número máximo de iteraciones, terminamos el bucle
            if (iteracion >= maxIteracion) {
                System.out.println("Se alcanzó el número máximo de iteraciones. Terminando la competencia.");
                break;
            }
        } while (cocheA.getKmRecorridos() < kmEtapa && cocheB.getKmRecorridos() < kmEtapa);

        // Determinamos el ganador
        if (cocheA.getKmRecorridos() > cocheB.getKmRecorridos()) {
            System.out.println("El ganador es el coche " + cocheA.getMatricula() + " con " + cocheA.getKmRecorridos() + " km recorridos.");
        } else if (cocheB.getKmRecorridos() > cocheA.getKmRecorridos()) {
            System.out.println("El ganador es el coche " + cocheB.getMatricula() + " con " + cocheB.getKmRecorridos() + " km recorridos.");
        } else {
            System.out.println("Ambos coches han recorrido la misma cantidad de kilómetros.");
        }



        cocheA.setModelo("Astra");
        cocheA.setMatricula("1234GGG");
        cocheA.setCv(200);
        cocheA.setCv((int)(Math.random()*10)+21);
        cocheB.setCv((int)(Math.random()*10)+21);
        cocheA.mostrarCoche();
        cocheB.mostrarCoche();

        System.out.printf("\nEl ganador es %s, %s, %.2f - %s, %s, %.2f",cocheA.getModelo(), cocheA.getMatricula(),
                cocheA.getKmRecorridos(), cocheB.getModelo(), cocheB.getMatricula(), cocheB.getKmRecorridos());

    }
}
