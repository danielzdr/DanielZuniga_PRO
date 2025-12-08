package Ejercicio4Cajero;

import Ejercicio4Cajero.model.Banco;
import Ejercicio4Cajero.model.Cajero;

public class Entrada {

    public static void main(String[] args) {
        Cajero cajero = new Cajero();

        //Hacerlo con un menu iterativo
        Banco bbva = cajero.seleccionarBanco("BBVA");
        Banco santander = cajero.seleccionarBanco("Santander");

        // Abrir cuentas
        cajero.abrirCuenta(bbva, "12345678Z", "1234", 1000);
        cajero.abrirCuenta(santander, "98765432Y", "5678", 500);

        // Operaciones con la cuenta
        System.out.println(cajero.verResumen(bbva, "12345678Z", "1234"));  // Ver resumen BBVA
        cajero.ingresarDinero(bbva, "12345678Z", "1234", 200);  // Ingresar dinero
        System.out.println(cajero.verResumen(bbva, "12345678Z", "1234"));  // Ver resumen BBVA

        // Retirar dinero
        cajero.sacarDinero(bbva, "12345678Z", "1234", 50);
        System.out.println(cajero.verResumen(bbva, "12345678Z", "1234"));  // Ver resumen después del retiro

        // Cancelar cuenta
        cajero.cancelarCuenta(santander, "98765432Y", "5678");
        System.out.println(cajero.verResumen(santander, "98765432Y", "5678"));  // Cuenta eliminada
    }
    }

