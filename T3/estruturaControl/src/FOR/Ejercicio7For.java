package FOR;

import java.util.Scanner;

public class Ejercicio7For {

    static Scanner lectorTeclado=new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Introduce un dado: ");
        int dadoVeces = lectorTeclado.nextInt();
        if (dadoVeces <= 0){
            dadoVeces = 100;
        }

        System.out.printf("Lanzado el dado %d veces",dadoVeces);

        for (int i = 0; i <= dadoVeces+1; i++) {
            int resultado = (int)( Math.random()*6)+1;
            System.out.printf("Lanzamiento es: %d\n ",i,resultado);
        }
    }
}
