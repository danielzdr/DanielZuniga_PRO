package RecursosEntrega;

import java.io.File;

public class OperacionesFichero {

    /*
    public void leerInformacion(){
        //Crear puntero siempre con File
        File file = new File("C:\\Users\\Usuario\\Documents\\GitHub\\DanielZuniga_PRO");
        System.out.println(file.isFile());
        file.list();//nombre de los ficheros dentro de los directorios
        for (File fichero: file.listFiles()){
            System.out.println(fichero.getAbsolutePath());//saca la ruta absoluta
        }
        file.listFiles();//los ficheros que estan dentro
    }
    */
    //sobre la ruta que sea listar el contenido de esta
    public void leerInformacion(File directorios) {
        File[] archivos = directorios.listFiles();
        for (File fichero : archivos) {
            System.out.println(fichero.getName());
            if (fichero.isDirectory()) {
                leerInformacion(fichero);
            }else {
                System.out.println("No es directorio");
            }
        }


    }
}
