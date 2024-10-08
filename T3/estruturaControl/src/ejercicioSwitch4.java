import java.util.Scanner;

public class ejercicioSwitch4 {
    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Ingresa un mes de año: ");
        String mes= lectorTeclado.next();

        switch (mes.toLowerCase()){
            case "febrero":
                System.out.println("tienes 28 dias");
                break;
            case "enero":
            case "marzo":
            case "abril":
            case "mayo":
            case "junio":
            case "julio":
            case "agosto":
            case "octubre":
            case "diciembre":
                System.out.println("tienes 31 dias");

            default:
                System.out.println("tienes 30 dias");

        }

    }
}
