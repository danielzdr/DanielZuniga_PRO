package model;

import java.io.Serializable;

public class Usuarios implements Serializable {
    //Crear un agenda peristida en fichero
    //Crea un objeto en una clase llamada model.Usuarios (nombre,apellido,correo,telefono)
    //en la clase entrada pide los datos de 4 usuarios
    //Pide confirmacion al sistema si todos los datos son correctos, y en e caso de deci que si, se prodece a guardar los datos en el sistema
    //Guardar los datos quiere decir que un fichero llamado agenda.txt se guardan los datos con el siguiente formato
    //nombre, apellido, correo, telefono
    //nombre, apellido, correo, telefono
    //nombre, apellido, correo, telefono
    //nombre, apellido, correo, telefono
    //Una vez tenga el fichero escrito, crear un metodo llamado lecturaAgenda.Este metodo mostrara por consola el nombre, apellido, correo, telefono
    //de cada uno de los usuarios previa a la creacion del mismo
    //--leo fichero
    //--paso la lectura de la linea a usuario
    //--muestro los datos del usuario

    //elemento serial de como se reconstruye el objeto-> clave reconstruccion
    private final static long serialVersionUID=123456789L;
    private String nombre, apellido, correo;
    private int telefono;

    public Usuarios() {
    }

    public Usuarios(String nombre , String apellido , String correo , int telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return getNombre()+","+getApellido()+","+getCorreo()+","+getTelefono();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void mostrarDatos(){
        System.out.println("Nombre " +nombre);
        System.out.println("Apellido " +apellido);
        System.out.println("Correo " +correo);
        System.out.println("Telefono " +telefono);
    }
}
