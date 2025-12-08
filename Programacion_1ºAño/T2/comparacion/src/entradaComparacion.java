public class entradaComparacion {

    public static void main(String[] args) {

        int n1= 20;
        int n2= 30;
        boolean resultado= n1>n2 ;//20>30
        System.out.println("El resultado de la comparacion mayor es " +resultado);
        resultado= n1<n2 ;//20<30
        System.out.println("El resultado de la comparacion menor es " +resultado);
        resultado= n1>=n2 ;//20>=30
        System.out.println("El resultado de la comparacion mayor o igual es " +resultado);
        resultado= n1<=n2 ;//20<=30
        System.out.println("El resultado de la comparacion menor o igual es " +resultado);
        resultado= n1==n2 ;//20==30
        System.out.println("El resultado de la comparacion igual igual es " +resultado);
        resultado= n1!=n2 ;//20!=30 -> != comparacion diferente a algo
        System.out.println("El resultado de la comparacion es diferente  " +resultado);
        //negacion !
        resultado = !resultado; //true
        System.out.println("El resultado de la negacion es "+resultado);

        System.out.println("OPERADORES LOGICOS");
        //&& -> and
        n1= 30;
        n2= 40;
        boolean n3= true;
        int n4 = -20;
        boolean resultadoLogico =(n1<n2) && n3 && n4<0;//true && true && true = true
        System.out.println("El resultado logico de las && es "+resultadoLogico);
        resultadoLogico =(n1<n2) && !n3 && n4<0;//true && false && true = false
        System.out.println("El resultado logico de las && es "+resultadoLogico);
        boolean resultadoCompuesto = ((n1>n2)) && n3 && (n4>10) || n4>0 ||n2>0;
                                    //(false && false && true) || false || true
                                    // false || false || true
                                    //true
        System.out.println("El resultado compuesto de la siguente operacion es "+resultadoCompuesto);

        //OR-> || con que una condicion se cumpla es true
        //true true = true
        //true false = true
        //false false = false
        //false true = true
        resultadoLogico =(n1<n2) || (n2!=n1) || n3 || n4>0;//true && true && true = true
        System.out.println("El resultado logico de las || es "+resultadoLogico);
        //comparar palabras con un = //equals mayusculas y minusculas
        String palabra1 = "Hola";
        String palabra2 = "hola";
        boolean resultadoPalabras = palabra1.equals(palabra2);
        System.out.println("El resultado de la comparacion de las palabras es "+resultadoPalabras);
        boolean resultadoPalabras2 = palabra1.compareTo(palabra2);
        System.out.println("El resultado de la comparacion de las palabras es "+resultadoPalabras2);


        n1= 4;
        n2= 3;
        double resultadoOperacion = (double) n1/n2;// de forma temporal
        double n1Double = (double) n1;//de forma definitiva
        //cambio de string a int
        String n1String = String.valueOf(n1);
        String palabra = "12345";
        int numeroPalabra = Integer.parseInt(palabra);
        System.out.println("El resultado de la division es "+resultadoOperacion);

    }

}
