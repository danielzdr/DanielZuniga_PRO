import java.io.IOException;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        String [] cosas= new String[]{"Dato1, Dato2, Dato3"};
        int numero1=2;
        int numero2=0;
        String nombre;

        System.out.println("A que posicion quieres acceder");
        int posicion= scanner.nextInt();


        //System.out.println("El numero de letras de tu nombre es " +nombre.length());

        //corregir las excepciones
        try{
            System.out.println(cosas[posicion]);
            int resultado=  numero1/numero2;
        }catch (ArrayIndexOutOfBoundsException e){
            //System.out.println("El size es mas pequeño que la posicion indicada");
            System.out.println("Error en el programa");
        } catch (ArithmeticException e) {
            System.out.println("Error en la division entre 0");
        }finally {
            System.out.println("Terminando un proceso");
        }

        System.out.println("Termiando el programa");

        Operaciones operaciones= new Operaciones();

        operaciones.divisionEntre0(8);
    }
}
