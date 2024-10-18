package FOR;

import java.util.Scanner;

public class Ejercicio14For {

    static Scanner lectorTeclado = new Scanner(System.in);

    public static void main(String[] args) {

        //palabra palindroma que se lea igual que al reves
      //  System.out.println("Introduce una palabra palindroma: ");
        //String palabra = lectorTeclado.nextLine();
        String palabra = "ejecucion";

        //No se tiene en cuenta ni mayusculas ni acentos
        palabra = palabra.toLowerCase().replaceAll("ó", "o")
                .replaceAll("u", "ú")
                .replaceAll("í", "i")
                .replaceAll("é", "e")
                .replaceAll("á", "a");
        // String palabraInversa="";

        ;
        //for (int i = palabra.length()-1; i >=0 ; i--) {

        //  palabraInversa+=palabra.charAt(i);

        // }
        //Comprobar con el metodo equals SI una palabra es igual a la otra e imprimirlo

        //if(palabra.equals(palabraInversa)) {
        //  System.out.println("Es palindromo");
        //}else{
        //  System.out.println("No es palindromo");
        //}

        //palabra.charAt(length-1-i);

        boolean palindromo = true;

        for (int i = 0; i < palabra.length() / 2; i++) {

            if (palabra.charAt(i) == palabra.charAt(palabra.length() - 1 - i)) {
                palindromo = false;
                break;

            }
            System.out.println("La palabra es palindromo: "+palindromo);
            if(palindromo){
                System.out.println("tu palabra es palindromo ");
            }else {
                System.out.println("Tu palabra no es palindromo ");
            }

        }
    }
}
