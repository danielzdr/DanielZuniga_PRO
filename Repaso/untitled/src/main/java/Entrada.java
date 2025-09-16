import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Entrada {
    public static void main(String[] args) {
        System.out.println();
        File ficheroAlumnos = new File("src/main/java/resources/alumnos.txt");
        FileWriter fileWriter= null;
        try {
            //printWriter = new PrintWriter (new fileWriter(ficheroAlumnos ))
            fileWriter = new FileWriter(ficheroAlumnos);
            fileWriter.write("Me llamo Pablo Soriano");
            fileWriter.write("\n");
            fileWriter.write("tengo 20 años");
            fileWriter.write("\n");
            fileWriter.write("Y soy del madrid");
        } catch (IOException e) {
            System.out.println("Error al encontrar fichero");
        }finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("No se puede cerrar");
            }
        }
    }
}
