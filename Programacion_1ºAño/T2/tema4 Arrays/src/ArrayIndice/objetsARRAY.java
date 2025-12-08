package ArrayIndice;


import java.util.Random;

public class objetsARRAY {

    public static void main(String[] args) {
           // Object[]cosas={5,"dam",true,5.9,'a',7};
            //for (int i = 0; i < cosas.length; i++) {
            //if (cosas[i] instanceof Integer){
              //  System.out.println(cosas[i]);
               // System.out.println("La longitud de la palabra es");

                //busquedas->{1,2,3,4,5,6,6,7,8,8,764,53,34}
                //7-> recorro y termino cuando encuentro. Lo he encontrado cuando == elemento.




        //al array hay que quitarle uno a casa posicion y volver a imprimir
        /*
        0,2,3,5
        1,2,3,6
        5,6,5,6
        3,0,1,2
         */
        Random random=new Random();
        int filas= (int)Math.random()*5;
        int [][]numeros=new int[filas][5];

        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < numeros[i].length; j++) {
                numeros[i][j] -= 1;

            }
        }
        System.out.println("Imprimiendo modificado");
        imprimirArray(numeros);
    }

    public static void imprimirArray(int[][]array) {
        for (int [] row : array) {
            for (int element : row) {
                System.out.print(element +"\t");
            }
            System.out.println();
        }
    }
}
