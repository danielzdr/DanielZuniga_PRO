package FOR;

import java.util.Scanner;

public class Ejercicio15For {
    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Introduce una frase: ");
        String frase= lectorTeclado.next();

        int letras= frase.length();
        int letrasSinNada= frase.replaceAll(" ", "").replaceAll(".","").length();
        int numeroOraciones=0,numeroPalabras=0;
        for (int i = 0; i < frase.length(); i++) {
            if(frase.charAt(i)=='.'){
                numeroOraciones++;

            } else if (frase.charAt(i) == ' ') {
                numeroPalabras++;
            }


        }
        System.out.println("El numero de letras"+numeroOraciones);
        System.out.println("El numero de letras"+numeroPalabras);
        System.out.println("El numero de letras"+letras);
        System.out.println("El numero de letras"+letrasSinNada);
    }
}
