package EjerciciosDel1al3.model;

import java.io.*;
;

public class Lectura {

    public void leerMensaje(String path){
        File file = new File(path);
        FileReader fileReader= null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error de busqueda");
        }
        finally {
            try {
                fileReader.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error de acabado");
            }
        }
    }

    public void escribirMnesaje(String path){
        File file = new File(path);
        FileWriter fileWriter= null;

        try {
            fileWriter= new FileWriter(file);
        } catch (IOException e) {
            System.out.println("Error de entrada/ salida");
        }finally {
            try {
                fileWriter.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error de acabado");
            }
        }
    }

    public void escribirUsuario(String path, Usuario usuario){
        File file = new File(path);
        PrintWriter printWriter= null;

        try {
            printWriter= new PrintWriter(new FileWriter(file,true));
            printWriter.println(usuario.toString());
        } catch (IOException e) {
            System.out.println("Error de entrada/salida");
        }finally {
            try {
                printWriter.close();
            }catch (Exception e){
                System.out.println("Error de acabado");
            }

        }
    }

    public void lecturaUsuarios(String path){
        File file = new File(path);
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String linea;
            while ((linea = bufferedReader.readLine())!=null){

                String[] datos = linea.split(",");
                Usuario usuario = new Usuario(datos[0], datos[1],(datos[2]), Integer.parseInt(datos[3]));
                usuario.mostrarDatos();

            }

        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        } catch (IOException e) {
            System.out.println("Fallo de entrada / salida");
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Fallo en el cierre");
            }
        }
    }

    public void descifrarMensaje( String path,int fase){
        File file = new File(path);
        FileReader fileReader= null;

        try {
            fileReader=new FileReader(file);
            int codigoLectura = -1;
            while ((codigoLectura=fileReader.read())!=-1){
                int codigoDescifrado= codigoLectura/fase;
                System.out.println((char)codigoDescifrado);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error en la lectura");
        } catch (IOException e) {
            System.out.println("No tienes permisos de lectura");
        } finally {
            try {
                fileReader.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error entrada/salida");
            }
        }
    }

    public void descifrarMensajeCodigo( String path,int fase){
        File file = new File(path);
        FileReader fileReader= null;
        BufferedReader bufferedReader=null;
        try {
            fileReader= new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String lectura= null;
            while ((lectura = bufferedReader.readLine())!=null){
                String[] codigos= lectura.split(" ");
                for (String codigo:codigos){
                    int codigoDescifrado = Integer.valueOf(codigo)/fase;
                    System.out.print((char) codigoDescifrado);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe");
        } catch (IOException e) {
            System.out.println("No tienes permisos de lectura");
        }finally {
            try {
                fileReader.close();
            } catch (IOException  | NullPointerException e) {
                System.out.println("Error en la entrada/salida");
            }
        }
    }



}
