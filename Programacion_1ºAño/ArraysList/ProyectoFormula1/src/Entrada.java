import Controller.Coche;
import java.util.ArrayList;


public class Entrada {

    public static void main(String[] args) {

        Carrera carrera1 = new Carrera(5, "Cheste");
        Carrera carrera2 = new Carrera( 6, "Jarama");
        Carrera carrera3 = new Carrera(3, "Aragon");
        Carrera carrera4 = new Carrera(4, "Navarra");
        carrera1.inscribirParticipante(new Coche());



        carrera1.iniciarCarrera();
        carrera1.descalificar("12345A");
    }
}
