import java.util.Scanner;

public class ejercicio9 {
    public static void main(String[]args){
        Scanner lectorTeclado = new Scanner(System.in);


        System.out.println("Numero de bebidas: ");
        int bebidas=lectorTeclado.nextInt();

        System.out.println("Numero de bocadillos: ");
        int bocadillos= lectorTeclado.nextInt();

        System.out.println("Dime cuantos sois: ");
        int numeroComensales= lectorTeclado.nextInt();

        double precioBocadillo=2.05;

        double precioBebida=1.20;



        double costeBebida=(double) 1.20*bebidas;
        System.out.printf("%.2f",costeBebida);
        double costeBocadillo=(double) 2.05*bocadillos;
        System.out.printf("%.2f",costeBocadillo);

        double total=costeBebida+costeBocadillo;
        System.out.printf("TOTAL: %.2f",total);
        System.out.println("ARTICULO\tCANTIDAD\tPRECIO\tCOSTE");
        System.out.println("==========\t========\t=======\t======");
        System.out.printf("Bocadillos\t\t\t\t\t%d\t\t\t\t\t%.2f\t\t\t\t\t%.2f\n",costeBebida,costeBocadillo,precioBebida,precioBocadillo,numeroComensales);
        System.out.printf("Bebidass\t\t\t\t\t%d\t\t\t\t\t%.2f\t\t\t\t\t%.2f\n",costeBebida,costeBocadillo,precioBebida,precioBocadillo,numeroComensales);
        System.out.printf("TOTAL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%.2f\n",total);











    };


}
