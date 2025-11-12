package EjercicioFOR;

public class Ejercicio3 {
    public static void main(String[] args) {
        for (int num = 0; num <=10 ; num++) {
            System.out.println("Tabla de " +num);
            for (int i = 0; i <=10 ; i++) {
                System.out.println(num+" x "+i+" = "+(num*i));
            }
        }
    }
}
