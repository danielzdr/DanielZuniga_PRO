package EjerciciosDel1al3;

import EjerciciosDel1al3.model.Lectura;
import EjerciciosDel1al3.model.Usuario;

import java.util.ArrayList;

public class Entrada {
    public static void main(String[] args) {

        ArrayList<Usuario> usuarios;
        Lectura lectura= new Lectura();
        lectura.leerMensaje("src/EjerciciosDel1al3/model/Ejercicio1.txt");
        //lectura.escribirMnesaje("src/EjerciciosDel1al3/model/Ejercicio1.txt");
        //lectura.lecturaUsuarios("src/ejercicio3.obj");
        //lectura.escribirUsuario("src/ejercicio3.obj", new Usuario("Daniel", "Zuñiga", "67777777",25));
        //lectura.descifrarMensaje("src/Ejercicio2.txt",3);
        //lectura.descifrarMensajeCodigo("src/Ejercicio2.txt",3);
    }
}
