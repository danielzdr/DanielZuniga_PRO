import java.io.*;

public class Ejercicio1 {
    public static void main(String[] args) {
        File file = new File("src/recursos/ejercicio1.txt");

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String linea= null;
            while ((linea= bufferedReader.readLine())!=null){
                String [] palabra= linea.split(" ");
                for (String palabras: palabra){
                    System.out.print((char)Integer.parseInt(palabras));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentrea el fichero");
        }catch (IOException e){
            System.out.println("No hay permisos");
        }

    }
}
