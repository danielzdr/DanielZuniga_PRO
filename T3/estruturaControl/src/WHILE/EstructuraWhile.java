package WHILE;

import java.util.Scanner;

public class EstructuraWhile {
    static Scanner lectorTeclado = new Scanner(System.in);

    public static void main(String[] args) {

        //int i =10;
        //while (i>0){
        //  System.out.println("El valor de i es "+i);
        //i--;


        //pedir por consola numeros hasta que el usuario meta un 0


        //int i = 10;
        //int numero = 1;
        //while (numero%2!=0) {

        //  i++;

        //System.out.println("Introduce un  numeros: ");
        //int numeros = lectorTeclado.nextInt();
    //}
    //  System.out.println("Has introducido "+i);

    //int numero;
    //int i=0;
    //do{
      //  System.out.println("Introduce un numero");
        //numero= lectorTeclado.nextInt();
        //i++;
    //}while(numero!=0);
      //  System.out.println("El numero de introducidos es: "+i);


        //Nada mas arrancar aparezca el menu
        //1.opcion añadir
        //2.opcion borrar
        //3.opcion listar
        //4.opcion buscar
        //5.salir
        //¿que quieres hacer?
        //elegir menu
        //opcion 5 termina el programa

        int numeroMenu;


        do {

            System.out.println("Introduce un menu: ");
            numeroMenu= lectorTeclado.nextInt();

            System.out.println("1.Opcion añadir");
            System.out.println("1.Opcion borrar");
            System.out.println("1.Opcion listar");
            System.out.println("1.Opcion buscar");
            System.out.println("5.Opcion salir");
            System.out.println("Que opcion quieres hacer");

            switch (numeroMenu){
                case 1:
                    System.out.println("1.Opcion añadir");
                    break;
                case 2:
                    System.out.println("2.Opcion borrar");
                    break;
                case 3:
                    System.out.println("3.Opcion listar");
                    break;
                case 4:
                    System.out.println("4.Opcion buscar");
                    break;
                case 5:
                    System.out.println("4.Opcion salir");
                    break;
                default:
                    System.out.println("Opcion incorrecta, intentalo de nuevo");
                    break;
            }

        }while( numeroMenu!= 5);

        System.out.println("Has selecionado: ");


    }





    }

