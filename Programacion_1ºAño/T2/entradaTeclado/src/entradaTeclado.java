import java.util.Scanner;

public class entradaTeclado {
    //System.in para importar una clase
    static Scanner lectorTeclado= new Scanner(System.in);
    public static void main (String[]args){

        String nombre; // valor null

        System.out.println("Introduce tu nombre");
       nombre = lectorTeclado.next();

        System.out.println("Por favor introduce tu edad ");
        int edad = lectorTeclado.nextInt();

        System.out.println("Por favor introduce tu altura ");
        float altura= lectorTeclado.nextFloat();

        System.out.printf("Mi nombre es: %s tengo %d años y mido %.1fm\n", nombre, edad, altura);
        //el usuario introduzca el nombre por el teclado


        //Mi nombre es XXX(Daniel) tengo XXX(24) mido XXX(1.76)
        //y tengo experiencia trabajando XXX(True)








        //variables para flujo de datos de teclado definir dentro de la clase

    }


}
