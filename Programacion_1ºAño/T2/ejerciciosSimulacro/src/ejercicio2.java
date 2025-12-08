import java.util.Scanner;

public class ejercicio2 {
    static Scanner lectorTeclado = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Nombre participante: ");
        String nombreParticipante = lectorTeclado.next();
        System.out.println("Numero de jugadores del participante: ");
        int numeroParticipantes = lectorTeclado.nextInt();
        System.out.println("Presupuesto del participante ");
        float presupuestoParticipante = lectorTeclado.nextFloat();
        System.out.println("Nombre participante: ");
        String nombreParticipante2 = lectorTeclado.next();
        System.out.println("Numero de jugadores del participante: ");
       int numeroParticipantes2 = lectorTeclado.nextInt();
        System.out.println("Presupuesto del participante ");
        float presupuestoParticipante2 = lectorTeclado.nextFloat();
        System.out.println("Nombre participante: ");
        String nombreParticipante3 = lectorTeclado.next();
        System.out.println("Numero de jugadores del participante: ");
        int numeroParticipantes3 = lectorTeclado.nextInt();
        System.out.println("Presupuesto del participante ");
        float presupuestoParticipante3 = lectorTeclado.nextFloat();

        boolean jugadores=true;
        System.out.println("El primer jugador tiene 11 jugadores: "+jugadores);
        boolean pares= (numeroParticipantes2 %2==0);
        System.out.println("El segundo participante tiene jugadores pares: "+pares);
        boolean presupuestoPositivo=(presupuestoParticipante3>0);
        System.out.println("El tercer participante tiene presupuesto positivo: "+presupuestoPositivo);
         boolean liga=(numeroParticipantes==11 && presupuestoParticipante > 0) &&
                 (numeroParticipantes2 == 11 && presupuestoParticipante2 >0)
                 && (numeroParticipantes3==11 && presupuestoParticipante3>0);

        System.out.println("La liga esta preparada para jugar: "+liga);


    }

    public boolean numeroParticipantes(int[] jugadores) {
        return jugadores.length == 11;

    }








};