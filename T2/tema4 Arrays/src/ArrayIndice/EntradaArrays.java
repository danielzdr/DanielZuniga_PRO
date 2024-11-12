package ArrayIndice;

public class EntradaArrays {
    public static void main(String[] args) {


        int[] numeros = new int[4];
        //guarda el 0,0,0,0
        //cuantas posiciones tienes
        //System.out.println(numeros.length);
        // 0 0 0 7
        //numeros[3]=7;
        //System.out.println("El numero de la ultima posicion es "+numeros[numeros.length-1]);

        //String[] palabras={"Hola","Que","Tal","Estas"};
        //System.out.println("La palabra en la ultima posicion es "+palabras[palabras.length-1]);
        //sacar todas las palabras que estan en el array
        //imaginar que tengo 4000000000

        /*for (int i = 0; i <= palabras.length-1; i++) {
            System.out.println("La palabra es "+palabras[i]);
        }*/

        for (int i = 0; i < numeros.length; i++) {
            numeros[i]=(int)Math.random()*101;
        }
        for (int i = 0; i < numeros.length ; i++) {
            //numeros posicion par
            if (i%2==0){
                System.out.println(numeros[i]);
            }
            //numeros que son pares
            if (numeros[i]%2==0){
                System.out.println(numeros[i]);
            }
        }
        //foreach para recorrer datos para verlo
        for(int intem:numeros){
            if (intem %2==0) {
                System.out.println(intem);
            }
            if (intem==50){
                break;
            }
            }

    }
}
