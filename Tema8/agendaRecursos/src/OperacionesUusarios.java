import model.Usuarios;

import java.io.*;

public class OperacionesUusarios {

    public void escribirUsuario(String path, Usuarios usuario){
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
                // Borja,Martin,borja@gmail.com,1234
                String[] datos = linea.split(",");
                Usuarios usuario = new Usuarios(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]));
                usuario.mostrarDatos();

            }

        } catch (FileNotFoundException e) {
            System.out.println("Ficheo no encontrado");
        } catch (IOException e) {
            System.out.println("Fallo de I/O");
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Fallo en el cierre");
            }
        }
    }

    public void escribirUsuarioObjeto(String path, Usuarios usuarios){
        File file = new File(path);
        FileOutputStream fileOutputStream= null;
        ObjectOutputStream objectOutputStream= null;
        try {

            fileOutputStream = new FileOutputStream(file);
            objectOutputStream= new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(usuarios);
        } catch (FileNotFoundException e) {
            System.out.println("Error en el fichero");
        }catch (IOException e) {
            System.out.println("Error entrada/salida");
        }finally {
            try {
                objectOutputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error de acabado");
            }
        }
    }

    public void leerUusarioObjeto(String path){
        File file = new File(path);
        FileInputStream fileInputStream= null;
        ObjectInputStream objectInputStream= null;
        try {

            fileInputStream = new FileInputStream(file);
            objectInputStream=new ObjectInputStream(fileInputStream);
            Usuarios usuarios= (Usuarios) objectInputStream.readObject();
            usuarios.mostrarDatos();
        } catch (FileNotFoundException e) {
            System.out.println("Error en el fichero");
        }catch (IOException e) {
            System.out.println("error entrada/ salida");
        }catch (ClassNotFoundException e) {
            System.out.println("clase no encontrada");
        }catch (ClassCastException e){
            System.out.println("Error en el casteo de las clases");
        }finally {
            try {
                objectInputStream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("error al acabado");
            }
        }
    }
}
