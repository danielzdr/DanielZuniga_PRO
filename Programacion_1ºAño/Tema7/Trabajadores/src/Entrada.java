import model.Asalariado;
import model.Autonomo;
import model.Jefe;
import model.Trabajador;

import java.util.ArrayList;

public class Entrada {
    public static void main(String[] args) {
        Asalariado asalariado= new Asalariado("Pablo" , "Soriano" , 12345 , 30000.0 , 0.21 , 14) {
            @Override
            public void realizarHuelga() {

            }

            @Override
            public void realizarTrabajo() {

            }
        };
        asalariado.calcularSalarioMes();
        Trabajador jefe = new Jefe("Borja" , "Martin" , 1234 , 60000.0) {
            @Override
            public void realizarTrabajo() {

            }
        };
        Autonomo autonomo= new Autonomo("Ruben" , "Parra" , 23454 , 24000.0 , 13) {
            @Override
            public void realizarHuelga() {

            }
        };
        autonomo.calcularSalarioMes();
        ArrayList<Trabajador> trabajadores= new ArrayList<>();
        trabajadores.add(asalariado);
        trabajadores.add(autonomo);
        trabajadores.add(jefe);
        for (Trabajador t: trabajadores){
            t.calcularSalarioMes();
            if (t instanceof Jefe){
                //t es trabajador y Jefe
                ((Jefe) t).realizarTrabajo(4);
            } else if (t instanceof Asalariado) {
                ((Asalariado) t).realizarTrabajo(6);
            }else {
                System.out.println("No es ni jefe ni asalariado");
            }
            break;
        }
    }
}
