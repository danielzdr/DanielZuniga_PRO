import java.io.*;
import java.util.Scanner;


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

    public void escribirFichero(String path) throws IOException {
     File file= new File(path);
     FileWriter fileWriter=null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write("Hola esto es un ejemplo de String");
            fileWriter.write("\tContinuo con el ejemplo");
            fileWriter.write(195);
        } catch (IOException e) {
            System.out.println("Error en escritua");
        } finally {
            try{
                fileWriter.close();
            }catch (IOException | NullPointerException e){
                System.out.println("Error en el cerrado");
            }
        }
    }

    public void datosPersonales(String path){
        File file = new File(path);
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Introduce el nombre");
            String nombre= scanner.next();
            System.out.println("Introduce el apellido");
            String apellidos= scanner.next();
            System.out.println("Introduce la edad");
            int edad = scanner.nextInt();
            System.out.println("Introduce el mensaje");
            String mensaje= bufferedReader.readLine();
            fileWriter.write("nombre" +nombre);
            fileWriter.write("Apellidos " +apellidos);
            fileWriter.write("Edad " +edad);
            fileWriter.write("Mensaje " +mensaje);
        } catch (IOException e) {
            System.out.println("Error en al escritura");
        }finally {
            try {
                fileWriter.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }

    }

    public void recorrerDirectorio(File fichero){
        System.out.println(fichero.getName());
        if (fichero.isDirectory()){
            for (File file: fichero.listFiles()){
                recorrerDirectorio(file);
            }
        }
    }

    public void crearDirectorio(String Path){
        //src/recursos/lectura
        File file = new File(Path);
        if (!file.exists()){
            file.mkdirs();
        }
    }

    public void crearFichero(String Path){
        File file = new File(Path);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Fallo en la escritura del fichero");
            }
        }
    }

    public void  lecturaUnitaria(String Path){
        File file = new File(Path);
            //hago lectura
            FileReader fileReader=null;
            try {
                fileReader= new FileReader(file);
                int lectura= -1;
                while ((lectura = fileReader.read())!=-1){
                    System.out.println("lectura = " + lectura + " asociado a la letra " +(char)lectura);
                }
                //devuelve numero de codigo ASCI
               /* int lectura=fileReader.read();
                System.out.println("lectura " +lectura+ "asociado a la letra "+(char)lectura);
                lectura= fileReader.read();
                System.out.println("lectura " +lectura+ "asociado la letra "+(char)lectura);*/
            } catch (FileNotFoundException e) {
                System.out.println("El fichero no existe");
            } catch (IOException e) {
                System.out.println("Error en la lectura");
            }

    }

    public void lecturaCodigoCompleta(String Path){
        File file = new File(Path);
        FileReader fileReader= null;
        //lectura linea a linea de forma recurrente
        BufferedReader bufferedReader=null;

        try {
            fileReader= new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String linea = bufferedReader.readLine();
            System.out.println("La linea leida es " +linea);
            linea = bufferedReader.readLine();
            System.out.println("La linea leida es " +linea);
        }catch (FileNotFoundException e){
            System.out.println("El fichero no existe");
        }catch (IOException e ){
            System.out.println("Error de la lectura");
        }finally {
            System.out.print("terminando lectura");
        }
    }

    //metodo para escribit lo que te dice y lo cambia el fichero
    public void escrituraFicheroCompleta(String path){
        File file = new File(path);
        FileWriter fileWriter = null;
        PrintWriter printWriter= null;

        try {
            fileWriter= new FileWriter(file);
            printWriter= new PrintWriter(fileWriter);
            //realiza la escritura
            printWriter.println("Esta primera linea es la escritura completa");
            printWriter.println("Esta segunda linea se ha incorporado de forma automatica");
            printWriter.print(69);
        } catch (IOException e) {
            System.out.println("Error de entrada/salida");
        }finally {
            try {
                printWriter.close();
            } catch (Exception e){
                System.out.println("Error en el cerrado");
            }
        }
    }

}
