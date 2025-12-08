package agenda_arraylist.controller;

import java.util.ArrayList;

public class Agenda {

    //siempre definir el arraylist object[] dentro de la clase
    private ArrayList<Object[]> listaContactos;

    //Constructor
    public Agenda() {
        listaContactos = new ArrayList();
    }

    public boolean agregarPersona(String nombre , String apellido , int telefono , String dni) {
        //comprobacion dni
        Object[] contacto = {nombre , apellido , telefono , dni};
        if (buscarUsuario(dni) == null) {
            //agregar
            listaContactos.add(contacto);
            return false;
        }
        //agregar persona

        return false;
    }


    private Object[] buscarUsuario(String dni) {

        for (Object[] item : listaContactos) {
            //parseo para pasar el object a string para que salga el ignore case -> .valueOf
            if (String.valueOf(item[3]).equalsIgnoreCase(dni)) {
                //esta en la lista
                return item;
            }
        }
        //no esta en la lista -> retorno por defecto
        return null;
    }


    public int listarDatosPersona(String dni) {

            //si la lista esta vacia
            if (listaContactos.isEmpty()) {
                return 1;
                //si la lista esta vacia pero no esta el usuario
            } else if (buscarUsuario(dni) == null) {
                // la lista no esta vacia pero el usuario no esta
                return 2;

            }
            //si la lista esta vacia pero si esta el usuario

        Object[]usuario=buscarUsuario(dni);
            for ( Object item : usuario) {
                System.out.println(item);
            }
            return -1;


    }
}

