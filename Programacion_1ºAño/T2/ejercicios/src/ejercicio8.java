import java.util.Scanner;

public class ejercicio8 {

    public static void main(String[]args){
        System.out.println("Escribe los grados centigrados: ");
        Scanner lectorTeclado = new Scanner(System.in);
        float C= lectorTeclado.nextFloat();
        float F= ((9*C)/5)+32;
        float K= C+273.15F;
        System.out.printf("Farenheit: %.2f Kelvin: %.2f\n",F,K);
        System.out.println("Escribe los grados Farenheit: ");
             F= lectorTeclado.nextFloat();
             C=(5*(F-32))/9;
             K=C+273.15F;
        System.out.printf("Centigrados: %.2f Kelvin: %.2f\n",C,K);
        System.out.println("Escribe los grados Kelvin: ");
            K= lectorTeclado.nextFloat();
            C=K-273.15F;
            F=((9*C)/5)+32;
        System.out.printf("Centigrados: %.2f Farenheit: %.2f\n",C,F);


    };

}
