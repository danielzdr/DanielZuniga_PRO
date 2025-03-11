import utils.LongitudDNINoValidaException;
import utils.UltimoDigitoNoLetraException;

public class Formulario {
    private String nombre, apellidos,dni;

    public Formulario() {
    }

    public Formulario(String apellidos , String dni , String nombre) {
        this.apellidos = apellidos;
        this.dni = dni;
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws UltimoDigitoNoLetraException, LongitudDNINoValidaException {
        char ultimaLetra= dni.charAt(dni.length()-1);
        if (Character.isDigit(ultimaLetra)){
            throw new UltimoDigitoNoLetraException("El ultimo no es una letra");
        }
        if (Character.isDigit(ultimaLetra)){
            throw new LongitudDNINoValidaException("Longitud invalida");
        }
        }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void mostrarDatos(){
        System.out.println("Nombre " +nombre);
        System.out.println("Apellidos " +apellidos);
        System.out.println("Dni " +dni);
    }
}
