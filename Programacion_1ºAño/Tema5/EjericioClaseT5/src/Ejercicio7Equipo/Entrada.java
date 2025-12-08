package Ejercicio7Equipo;

import Ejercicio7Equipo.model.Equipo;
import Ejercicio7Equipo.model.Jugador;
import Ejercicio7Equipo.model.Partido;

public class Entrada {
    public static void main(String[] args) {
        Equipo equipo1= new Equipo("Madrid",90,30,10);
        Equipo equipo2=new Equipo("Atletico de madrid", 70, 90, 92);
        Jugador jugador1=new Jugador("Vinicius", "Delantero",91);
        equipo1.listarDelantero();
        Partido partido = new Partido();
        partido.iniciarPartido();
        partido.mostrarResultado();
        //jugador1.mostrarDatos();
        System.out.println(jugador1);

        for (int i = 0; i < 3; i++) {
            equipo1.atacar();
            equipo2.atacar();

        }
        // para hacer que retorne el nombre y el numero de goles para el resultado final con los get que retornan nombre y Nº goles
        System.out.printf("El resultado final es de %s : %d-%d : %s ", equipo1.getNombre(),equipo1.getGoles(),equipo2.getGoles(),equipo2.getNombre());
    }
}
