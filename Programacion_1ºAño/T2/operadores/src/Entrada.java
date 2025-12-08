public class Entrada {


    public static void main(String[] args){

        //operadores aritmetcos BINARIOS
        int operador1= 10;
        int operador2= 5;
        float resultado= operador1 + operador2; //15
        System.out.println("El resultado de la suma es " +resultado);

        //si se pone el nombre de nuevo del nombre de la variable se
        // reasigna el resultado en todo el main
        resultado = operador1 - operador2;//5
        System.out.println("El resultado de la resta es " +resultado);
        resultado = operador1*operador2;//50
        System.out.println("El resultado de la multiplicacion es " +resultado);
        operador1= 7;
        operador2= 4;

        resultado = operador1/operador2;//2
        System.out.println("El resultado de la division es " +resultado);

        resultado = operador1%operador2; //3.0

        System.out.println("El resultado del resto es "+resultado);

        //ARITMETICOS MONARIOS
        resultado= -resultado; //-3
        System.out.println("El resultado del opuesto es "+resultado);//-3
        //MONARIO DE INCREMENTO resultado = resultado +1
        resultado++;
        System.out.println("El resultado del incremento es "+resultado);// 2
        //MONARIO DECREMENTO
        resultado --;
        System.out.println("El resultado del decremento es "+resultado);// 1

        //OPERACIONES DE ASIGNACION

        System.out.println("Operadores de asignacion");
        // resultado =1
        operador1 =10;
        resultado+=operador1; //11
        System.out.println("El resultado de la suma asginada es " +resultado);

        resultado-=6; //5
        System.out.println("El resultado de la resta asignada es " +resultado);
        resultado*=3; //15
        System.out.println("El resultado de la multiplicacion asiganada es "+resultado);
        resultado/=2; // 7-> 15/2.0 =7
        System.out.println("El resultado de la division asignada es "+resultado);
        resultado%=2;// 1
        System.out.println("El resultado del modulo asignado es "+resultado);




    }
}
