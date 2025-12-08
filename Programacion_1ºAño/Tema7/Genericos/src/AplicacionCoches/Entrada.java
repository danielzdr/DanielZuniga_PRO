package AplicacionCoches;


import AplicacionCoches.model.Coche;
import AplicacionCoches.model.Deportivo;
import AplicacionCoches.model.Todoterreno;
import AplicacionCoches.utils.Incremento;

import java.util.ArrayList;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        ArrayList<Coche> listaCoches= new ArrayList<>();
        Deportivo deportivo= new Deportivo("seat","ibiza","3434GHM",200,20,200.90,2, Incremento.DEPORTIVO);
        Todoterreno todoterreno = new Todoterreno("Mercedes", "benz", "2323MMM",120,23,30000.50,23,Incremento.TODOTERRENO);

        deportivo.reprogramarMotor(23);
        todoterreno.reprogramarMotor(50);
    }
}
