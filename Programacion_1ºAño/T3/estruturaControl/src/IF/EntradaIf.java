package IF;

public class EntradaIf {

    public static void main (String[]args){

        int nota=6;
        //if-> 0-10-> nota correcta

        String mensaje =null;

        if (nota>=0 && nota<=10){

            System.out.println("La nota es correcta ");

            if (nota<5){
                System.out.println("Suspenso");
            }

            else if (nota<7){
                System.out.println("Aprobado ");
            }

            else if (nota>7 && nota<9){
                System.out.println("Notable ");
            }

            else if (nota<10){
                System.out.println("Sobresaliente ");

            }

            else {
                System.out.println("Matricula de honor ");
            }


        }

        System.out.println("Terminando la ejecucion");

    };

}





