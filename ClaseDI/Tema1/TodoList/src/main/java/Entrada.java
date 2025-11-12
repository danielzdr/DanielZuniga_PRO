import model.Persona;
import model.Tarea;
import model.TareaPersonal;
import model.TareaProfesional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Entrada {
    public static void main(String[] args) {
        //Tarea tarea = new Tarea("Esta practica DI", "Esta practica DI es hacer un programa todoList"
        //, false, 6);
        Persona persona= new Persona();
        //Tarea tarea= new Tarea();
        //tarea.asignarResponsableDNI(new Persona("Pablo", "Soriano","12345A", 18));
        //tarea.mostrarDatos("Daniel", "Zuñiga", "12334A");
        //tarea.mostrarDatos("Ruben", "Parra", "12334B");
        //TareaProfesional tareaProfesional= new TareaProfesional();
        //TareaProfesional tareaProfesional= new TareaProfesional("Tarea1", "descripcion", 3,300000,new Date());
        //System.out.println(tareaProfesional);

        //TareaPersonal tareaPersonal= new TareaPersonal("Tarea2", "Pablo no hace nada en clase", 2, "ALlcorcon");
        //System.out.println(tareaPersonal);


        List<String> responsablesProfesional = Arrays.asList("Borja", "Luis");
        //Es un método de la clase Arrays que convierte un array en una lista fija (List) de elementos.
        //En este caso, convierte el array {"Borja", "Luis"} en una List<String>.
        Tarea tareaProfesional = new TareaProfesional(responsablesProfesional);
        tareaProfesional.enviarRecordatorio();

        List<String> responsablesPersonal = Arrays.asList("Luismi", "Jesus");
        Tarea tareaPersonal = new TareaPersonal(responsablesPersonal);
        tareaPersonal.enviarRecordatorio();

    }

}
