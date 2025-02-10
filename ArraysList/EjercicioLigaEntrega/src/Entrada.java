import model.*;

import java.util.Arrays;
import java.util.Comparator;

public class Entrada {
    public static void main(String[] args) {

        Jugador jugador1 = new Jugador("Lamine Yamal");
        Jugador jugador2 = new Jugador("Kylian Mbappe");
        Jugador jugador3 = new Jugador("Vinicius Junior");
        Jugador jugador4 = new Jugador("Pedri");
        Jugador jugador5 = new Jugador("Griezman");
        Jugador jugador6 = new Jugador("Nico Williams");
        Jugador jugador7 = new Jugador("Takefusa Kubo");
        Jugador jugador8 = new Jugador("Yeremi Pino");
        Jugador jugador9 = new Jugador("Brayan Zaragoza");
        Jugador jugador10 = new Jugador("Navarro");


        Equipo equipo1 = new Equipo("Barcelona", 1);
        Equipo equipo2 = new Equipo("Real Madrid",2);
        Equipo equipo3= new Equipo("Atletico de Madrid",3);
        Equipo equipo4= new Equipo("Atletic Bilbao",4);
        Equipo equipo5= new Equipo("Real Sociedad",5);
        Equipo equipo6= new Equipo("Villareal",6);
        Equipo equipo7= new Equipo("Osasuna",7);
        Equipo equipo8= new Equipo("Alcorcon",8);

        equipo1.agregarJugador(jugador1);
        equipo1.agregarJugador(jugador2);
        equipo2.agregarJugador(jugador3);
        equipo2.agregarJugador(jugador4);
        equipo3.agregarJugador(jugador5);
        equipo4.agregarJugador(jugador6);
        equipo5.agregarJugador(jugador7);
        equipo6.agregarJugador(jugador8);
        equipo7.agregarJugador(jugador9);
        equipo8.agregarJugador(jugador10);



        Clasificacion clasificacion = new Clasificacion();
        clasificacion.agregarEquipo(equipo1);
        clasificacion.agregarEquipo(equipo2);
        clasificacion.agregarEquipo(equipo3);
        clasificacion.agregarEquipo(equipo4);
        clasificacion.agregarEquipo(equipo5);
        clasificacion.agregarEquipo(equipo6);
        clasificacion.agregarEquipo(equipo7);
        clasificacion.agregarEquipo(equipo8);

        Partido partido1 = new Partido(equipo1, equipo2, 10, 0);
        partido1.jugar();
        clasificacion.mostrarClasificacion();
        Partido partido2 = new Partido(equipo1, equipo3, 3, 2);
        partido2.jugar();
        clasificacion.mostrarClasificacion();
        Partido partido3 = new Partido(equipo3, equipo2, 3, 0);
        partido3.jugar();
        clasificacion.mostrarClasificacion();
        Partido partido4 = new Partido(equipo4, equipo8, 0, 1);
        partido4.jugar();
        clasificacion.mostrarClasificacion();
        Partido partido5 = new Partido(equipo8, equipo7, 1, 1);
        partido5.jugar();
        clasificacion.mostrarClasificacion();
        Partido partido6 = new Partido(equipo5, equipo6, 3, 3);
        partido6.jugar();
        clasificacion.mostrarClasificacion();
        Partido partido7 = new Partido(equipo5, equipo2, 0, 2);
        partido7.jugar();
        clasificacion.mostrarClasificacion();
        Partido partido8 = new Partido(equipo3, equipo4, 2, 2);
        partido8.jugar();
        clasificacion.mostrarClasificacion();
        Partido partido9 = new Partido(equipo7, equipo1, 0, 5);
        partido9.jugar();
        clasificacion.mostrarClasificacion();
        Partido partido10 = new Partido(equipo4, equipo6, 3, 5);
        partido10.jugar();
        clasificacion.mostrarClasificacion();



        System.out.println("\nClasificacion:");
        System.out.println("Posición de Barcelona: " + clasificacion.obtenerPosicion("Barcelona"));
        System.out.println("Posición de Real Madrid: " + clasificacion.obtenerPosicion("Real Madrid"));
        System.out.println("Posición de Atletico de Madrid: " + clasificacion.obtenerPosicion("Atletico de Madrid"));
        System.out.println("Posición de Atletic Bilbao: " + clasificacion.obtenerPosicion("Atletic Bilbao"));
        System.out.println("Posición de Real Sociedad: " + clasificacion.obtenerPosicion("Real Sociedad"));
        System.out.println("Posición de Villareal: " + clasificacion.obtenerPosicion("Villareal"));
        System.out.println("Posición de Osasuna: " + clasificacion.obtenerPosicion("Osasuna"));
        System.out.println("Posición de Alcorcon: " + clasificacion.obtenerPosicion("Alcorcon"));
    }
}
