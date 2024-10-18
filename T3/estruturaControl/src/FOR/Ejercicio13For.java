package FOR;

import java.util.Scanner;

public class Ejercicio13For {

    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        //System.out.println("Introduce una nota: ");
        //int nota = lectorTeclado.nextInt();

        System.out.println("Introduce una palabra: ");
        String palabra= lectorTeclado.nextLine();

        //metodo para sacar una palabra al reves
        for (int i = palabra.length()-1; i >=0; i--) {
            System.out.println(palabra.charAt(i));

        }

        //palabra2=palabra2.replaceAll("","_");
        //"Holaquetal"

        //for (int i = 0; i < palabra1.length(); i++) {

           // char letra= palabra1.charAt(i);
          //  System.out.println(letra);
        //}
        //int len1=palabra1.length()-1;
        //int len2=palabra2.length()-1;

        //if (len1==len2){

            //System.out.println("La longitud de las palabras es igual a "+len1);
        //}else{
            //ystem.out.println("La longitud de las palabras es distinta");
        //}


    }
}
