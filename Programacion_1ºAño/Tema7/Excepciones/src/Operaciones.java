import java.io.File;
import java.io.IOException;

public class Operaciones {


    //Como el metodo puede prpvocar fallo se pone throws al final para tratar en error
    public void divisionEntre0(int numero) throws ArithmeticException{
        System.out.println("Procedemos a dividir entre 0");
        System.out.println("La division del numero es "+numero/0);
    }

    public void crearFichero(String path) throws IOException{
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Errorr en la creacion del fichero");;
        }
    }

}
