import java.util.Scanner;

public class ejercicio1 {


    public static void main(String[] args) {

        Scanner lectorTeclado = new Scanner(System.in);
        System.out.println("Introduce tu nombre: ");
        String nombre = lectorTeclado.next();
        System.out.println("Introduce el apellido: ");
        String apellido = lectorTeclado.next();
        System.out.println("Introduce la edad: ");
        int edad = lectorTeclado.nextInt();
        System.out.println("Introduce el peso: ");
        float peso = lectorTeclado.nextFloat();
        System.out.println("Introduce la altura: ");
        float altura = lectorTeclado.nextFloat();
        System.out.println("Introduce el sexo: ");
        String sexo = lectorTeclado.next();
        float imc = calculoIMC(peso, altura);
        System.out.printf("Hola %s tu IMC teniendo en cuenta tu altura es %.2f cm y tu peso de %.2f kg tiene un valor de %.2f\n ", nombre, altura, peso, imc);


    }

    ;

    public static float calculoIMC(float peso, float altura) {
        return (float) (peso / Math.pow(altura , 2));


    }
}