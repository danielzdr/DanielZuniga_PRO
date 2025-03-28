import java.io.IOException;

public class Entrada {
    public static void main(String[] args) throws IOException {
        OperacionesFichero operacionesFichero= new OperacionesFichero();
        /*operacionesFichero.leerInformacion(new File("C:\\1ºDAM\\Programacion"));*/

        /*operacionesFichero.recorrerDirectorio(new File("C:\\1ºDAM\\Programacion"));*/
        /*operacionesFichero.escribirFichero("src/recursos/lectura/ejemplo_escritura.txt");*/
        //operacionesFichero.datosPersonales("src/recursos/lectura/datos.txt");
        operacionesFichero.escrituraFicheroCompleta("src/recursos/lectura/ejemplo_lectura_codigo.txt");

        //Crear un agenda peristida en fichero
        //Crea un objeto en una clase llamada Usuarios (nombre,apellido,correo,telefono)
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
    }
}
