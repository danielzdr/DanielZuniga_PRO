import java.util.Scanner;

public class ejercicio14 {

        static Scanner lectorTeclado=new Scanner(System.in);
        public static void main(String[]args){


            int edad,estudios,ingresos;

            System.out.println("Edad: ");
            edad= lectorTeclado.nextInt();

            System.out.println("Estudios: ");
            estudios= lectorTeclado.nextInt();

            System.out.println("Ingresos: ");
            ingresos= lectorTeclado.nextInt();

            boolean condicionLogica=(edad>40)&&((estudios >= 5) && (estudios <= 8))&&(ingresos<15000);

            System.out.println("Tiene mas de 40 años y estudios entre 5 y 8 y gana menos de 15000: "+condicionLogica);

        };
}
