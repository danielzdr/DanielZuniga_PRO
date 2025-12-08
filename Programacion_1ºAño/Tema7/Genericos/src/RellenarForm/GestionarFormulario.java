package RellenarForm;

import RellenarForm.utils.LongitudDNINoValidaException;
import RellenarForm.utils.UltimoDigitoNoLetraException;

public class GestionarFormulario {
    private String dni;

    public GestionarFormulario() {
    }



    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void validarDNI(String dni)throws LongitudDNINoValidaException {
        if (dni.length() !=9){
            throw new LongitudDNINoValidaException("El dni debe tener 9 caracteres, los demas son invalidos");
        }

        char ultimoCaracter = dni.charAt(dni.length()-1);
        if (!Character.isLetter(ultimoCaracter)){
            throw new UltimoDigitoNoLetraException("El ultimo caracter del dni debe ser una letra, un numero no sera admitido");
        }

        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(dni.charAt(i))){
                throw new LongitudDNINoValidaException("La longitud del dni debe contener 8 digitos");
            }

        }


    }
}
