public class Entrada {

    public static void main(String[] args) {


    OperacionesFicheros operacionesFicheros= new OperacionesFicheros();
   //operacionesFicheros.cifrarMensaje("Naiara es la novia mas guapa y hermosa del mundo mundial",3,"src/recursos/cifrado_codigo.txt");
    //operacionesFicheros.descifrarMensaje("src/recursos/cifrado_codigo.txt",3);
    operacionesFicheros.descifrarMensajeCodigo(3,"src/recursos/cifrado_codigo.txt");
    }
}
