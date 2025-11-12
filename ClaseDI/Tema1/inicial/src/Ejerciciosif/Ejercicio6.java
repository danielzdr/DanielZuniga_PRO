package Ejerciciosif;

import java.util.Scanner;

public class Ejercicio6 {
    static int diasMes(int mes) {
        switch (mes) {
            case 2:
                return 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce un dia del mes");
        int dia = scanner.nextInt();
        System.out.println("Introduce un mes del año");
        int mes = scanner.nextInt();
        System.out.println("Introduce un año");
        int ano = scanner.nextInt();
        boolean ok = mes >= 1 && mes <= 12 && dia >= 1 && dia <= diasMes(mes);
        System.out.println(ok ? "Fecha correcta" : "Fecha incorrecta");
    }
}
