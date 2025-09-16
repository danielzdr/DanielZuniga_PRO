import controller.CentroSalud;
import model.*;

import java.util.Objects;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CentroSalud centro = new CentroSalud();
        Centro centros= new Centro();

        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Admitir paciente");
            System.out.println("2. Contratar doctor");
            System.out.println("3. Ver doctores");
            System.out.println("4. Ver pacientes");
            System.out.println("5. Pedir cita");
            System.out.println("6. Ver citas de un paciente");
            System.out.println("7. Ver citas de un doctor");
            System.out.println("8. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:

                    System.out.print("Ingrese el nombre del paciente: ");
                    String nombrePaciente = scanner.nextLine();
                    System.out.print("Ingrese el apellido del paciente: ");
                    String apellidoPaciente = scanner.nextLine();
                    System.out.print("Ingrese el NSS del paciente: ");
                    String nssPaciente = scanner.nextLine();
                    System.out.print("Ingrese la dolencia del paciente: ");
                    String dolenciaPaciente = scanner.nextLine();
                    Paciente paciente = new Paciente(nombrePaciente, apellidoPaciente, nssPaciente, dolenciaPaciente);
                    centro.admitirPaciente(paciente);
                    break;

                case 2:

                    System.out.println("Seleccione tipo de doctor:");
                    System.out.println("1. Digestivo");
                    System.out.println("2. General");
                    System.out.println("3. Traumatología");
                    int tipoDoctor = scanner.nextInt();


                    System.out.print("Ingrese el nombre del doctor: ");
                    String nombreDoctor = scanner.nextLine();
                    System.out.print("Ingrese el apellido del doctor: ");
                    String apellidoDoctor = scanner.nextLine();
                    System.out.print("Ingrese el número colegiado del doctor: ");
                    String numeroColegiado = scanner.nextLine();

                    if (tipoDoctor == 1) {
                        System.out.print("Ingrese los años de experiencia del doctor: ");
                        int anosExperiencia = scanner.nextInt();
                        Doctor doctor = new Digestivo(nombreDoctor, apellidoDoctor, numeroColegiado, anosExperiencia);
                        centro.contratarDoctor(doctor);
                    } else if (tipoDoctor == 2) {
                        System.out.print("Ingrese el tipo de doctor (familiar o infantil): ");
                        String tipoGeneral = scanner.nextLine();
                        Doctor doctor = new General(nombreDoctor, apellidoDoctor, numeroColegiado, tipoGeneral);
                        centro.contratarDoctor(doctor);
                    } else if (tipoDoctor == 3) {
                        Doctor doctor = new Traumatologia(nombreDoctor, apellidoDoctor, numeroColegiado);
                        centro.contratarDoctor(doctor);
                    } else {
                        System.out.println("Tipo de doctor no válido.");
                    }
                    break;

                case 3:
                    centro.verDoctores();
                    break;

                case 4:
                    centro.verPacientes();
                    break;

                case 5:

                    System.out.print("Ingrese el NSS del paciente: ");
                    String nssCita = scanner.nextLine();
                    System.out.print("Ingrese la especialidad: ");
                    String especialidadCita = scanner.nextLine();
                    System.out.print("Ingrese la fecha de la cita : ");
                    String fechaCita = scanner.nextLine();
                    centro.pedirCita(nssCita, Especialidad.valueOf(especialidadCita) , fechaCita);
                    break;

                case 6:

                    System.out.print("Ingrese el NSS del paciente: ");
                    String nssPacienteConsulta = scanner.nextLine();
                    Paciente pacienteConsulta = null;
                    for (Paciente p : centros.getListaPaciente()){
                        if (Objects.equals(p.getNumeroSS() , nssPacienteConsulta)) {
                            pacienteConsulta = p;
                            break;
                        }
                    }
                    if (pacienteConsulta != null) {
                        centro.verCitasPaciente(pacienteConsulta);
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    break;

                case 7:

                    System.out.print("Ingrese el nombre del doctor: ");
                    String nombreDoctorConsulta = scanner.nextLine();
                    Doctor doctorConsulta = null;
                    for (Doctor d : centros.getListaDoctor()) {
                        if (d.getNombre().equals(nombreDoctorConsulta)) {
                            doctorConsulta = d;
                            break;
                        }
                    }
                    if (doctorConsulta != null) {
                        centro.verCitasDoctor(doctorConsulta);
                    } else {
                        System.out.println("Doctor no encontrado.");
                    }
                    break;

                case 8:
                    System.out.println("Saliendo del programa......");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 8);
    }
}
