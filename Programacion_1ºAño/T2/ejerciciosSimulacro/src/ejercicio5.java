import java.util.Scanner;

public class ejercicio5 {
    static Scanner lectorTeclado=new Scanner(System.in);
    public static void main(String[]args) {


        System.out.println("Dame un valor: ");
        int ladoA= lectorTeclado.nextInt();
        System.out.println("Dame otro valor: ");
        int ladoB= lectorTeclado.nextInt();
        int areaTrianculo=(ladoA*ladoB)/2;
        System.out.println("El area del triangulo: "+areaTrianculo);


        System.out.println("Introduce el radio: ");
        int radio= lectorTeclado.nextInt();
        int areaCirculo=(int ) Math.PI*(radio^2);
        System.out.println("El area del circulo es: "+areaCirculo);

        System.out.print("Introduce la altura del triángulo: ");
        double altura = lectorTeclado.nextDouble();
        System.out.print("Introduce el lado 1 del triángulo: ");
        double lado1 = lectorTeclado.nextDouble();
        System.out.print("Introduce el lado 2 del triángulo: ");
        double lado2 = lectorTeclado.nextDouble();
        System.out.print("Introduce el lado 3 del triángulo: ");
        double lado3 = lectorTeclado.nextDouble();


        double areaTriangulo = (lado1 * altura) / 2;
        System.out.println("El área del triángulo con base "+lado1+" y altura "+altura+" es "+areaTriangulo);


        boolean isIsosceles = (lado1 == lado2) || (lado1 == lado3) || (lado2 == lado3);
        System.out.println("El triángulo es isósceles: " + isIsosceles);


        boolean trianguloEquilatero = (lado1 == lado2) && (lado2 == lado3);
        System.out.println("El triángulo es equilátero: "+trianguloEquilatero);


        System.out.print("Introduce el radio del círculo: ");
        double radio2 = lectorTeclado.nextDouble();

        // Calcular y mostrar el área del círculo
        double areaCirculo2 = Math.PI * Math.pow(radio2, 2);
        System.out.printf("El área del círculo es %.3f\n",areaCirculo2);





    };
}
