import model.Usuarios;

import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args){

        OperacionesUusarios operacionesUusarios= new OperacionesUusarios();
        //operacionesUusarios.escribirUsuarioObjeto("src/recursos/agenda.obj",new Usuarios("Pablo", "Soriano", "pablo.soriano@cesjuanpablosegundo.es",66666666));
       //operacionesUusarios.lecturaUsuarios("src/recursos/agenda.txt");
        //operacionesUusarios.escribirUsuario("src/recursos/agenda.txt", new Usuarios("Daniel", "Zuñiga", "daniel.zuniga@cesjuanpablosegundo.es", 677702776));
       operacionesUusarios.leerUusarioObjeto("src/recursos/agenda.obj");

    }
}
