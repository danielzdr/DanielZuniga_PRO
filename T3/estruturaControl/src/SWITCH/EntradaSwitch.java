package SWITCH;

public class EntradaSwitch {

    public static void main(String[]args){
//Switch->depende de un valor (String, char, int)
//int numero=7
//switch (numero){
// case 1:
//      cuerpo
//break
// case 3:
//      cuerpo
//break
//case 5:
//      cuerpo
//break
//default
// }
        int numero=6;
        switch (numero){
            case 0:
                System.out.println("El valor es 0");
                break;
            case 5:
                System.out.println("El valor es 5");
                break;
            case 10:
                System.out.println("El valor es 10");
                break;
            default:
                System.out.println("El valor no esta contemplado");



        }

        String mes= "Enero";
        switch (mes){
            case "Enero":
                break;
            case "Febrero":
                break;
            case "Marzo":
                break;

        }

        char letra ='Z';
        switch (letra){
            case 'A':
                break;
            case 'B':
                break;
            case 'C':
                break;

        }

    };
}
