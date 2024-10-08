public class Entrada {

    static int edadClase= 50;

    // modificador acceso, modificador adicional, modificador retorno nombre (argumentos) {cuerpo}
    public static void main (String[] args){
        System.out.println("Imprimiendo los valores maximos y minimos de los tipos");
        valoresMaximos();
        //tipo de la variable seguido de nombre de la variable y opcionalmente le pongo el valor
        //variable compleja o clase de jaba siempre la primera en mayuscula
        String nombre = "Borja";
        char letra ='A';
        Character letraCompleta= 'a';
        short numeroPequenio= 23; //rango numero pequeño (-255, 255)
        Short numeroComplejo= 30;
        //pequenioCompleto
        long telefono= 123456789;
        Long telefonoComplejo = 123456789L;
        float altura= 3.14f;
        Float alturaCompleja= 4.656546f;
        double decimalesDetalle= 3.3456789;
        Double decimalesComplejo= 3.345546;
        boolean carnet= true; //false
        Boolean carnetComplejo= false;
        //clasificacion de variables segun el tipo de datos
        //variables simples empiezan en minuscula ej: int
        // variables complejas empiezan en mayuscula ej: String
        //segun su vida variables que cambian o variables que no cambian
        int edad= 40; // rango numeros sin decimal desde (-65.435, 65.435)
        Integer edadCompleja = 40;
        System.out.println("Mi edad es "+edad);
        edad=41;
        System.out.println("Cambiando edad");
        System.out.println("Mi edad es "+edad);

        //variable constante se le pone final (nombre siempre en mayuscula)
        // delante de la variable(cualquier variable)
        String Apellido="Daniel";
        final String CIF ="D65334567";
        //variables segun su ambito-> de clase y de bloque
        //bloque: solo existe donde esta definida
        //clase: accesible en toda la clase
    }

    public static void miMetodo(){
        String nombre="Julia";
        int edad= 40;
        edadClase= 80;

    }

    public static void otroMetodo(){
        int edadClase=90;
        //this.edadClase=90; se puede poner el this si no esta el static
    }

    public static void valoresMaximos(){
        System.out.println("Terminando la ejecucion");
        //todos los metodos van dentro del main para que se puedan ejecutar
        //Ej. valoresMaximos(), otroMetodo(), miMetodo().
        //Sacara por consola el tipo de byte tiene un maximo MAX_VALUE
        //y un minimo MIN_VALUE
        System.out.println ("El valor minimo del byte es " +Byte.MIN_VALUE+ "y el maximo es" +Byte.MAX_VALUE);
        //system.out.printf("El valor minimo del byte es %d y el del maximo es %d\n", Byte.MIN_VALUE, Byte.MAX_VALUE);
        System.out.println ("Short min" +Short.MIN_VALUE+ "Short max" +Short.MAX_VALUE);
        System.out.println ("Long min" +Long.MIN_VALUE+ "Long max" +Long.MAX_VALUE);
        System.out.println ("Char min" +Character.MIN_VALUE+ "Char max" +Character.MAX_VALUE);
        System.out.println ("Double min" +Double.MIN_VALUE+ "Double max" +Double.MAX_VALUE);
        System.out.println ("Int min" +Integer.MIN_VALUE+ "Int max" +Integer.MAX_VALUE);
        System.out.println ("Float min" +Float.MIN_VALUE+ "Float max" +Float.MAX_VALUE);


    }
}
