package Ejerciciosif;

import java.util.Scanner;

public class Ejercicio7 {

        static int diasMes(int mes){
            return (mes==2)?28 : (mes==4||mes==6||mes==9||mes==11)?30:31;
            }

        public static void main(String[] args){
            Scanner scanner=new Scanner(System.in);
            System.out.println("Introduce un dia del mes");
            int dia=scanner.nextInt();
            System.out.println("Introoduce un mes del año");
            int mes=scanner.nextInt();
            System.out.println("Introduce un año");
            int ano=scanner.nextInt();
            dia++;
            if(dia>diasMes(mes)){
                dia=1; mes++;
                if(mes>12){
                    mes=1; ano++;
                }
            }
            System.out.printf("%02d/%02d/%04d\n", dia, mes, ano);
        }
    }

