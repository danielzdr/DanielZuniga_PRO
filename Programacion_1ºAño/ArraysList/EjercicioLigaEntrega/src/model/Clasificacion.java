package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Clasificacion {
    private ArrayList<Equipo> equipos;
    private int jornada;

    public Clasificacion() {
        this.equipos = new ArrayList<>();
    }

    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
        equipos.sort(Comparator.comparing(Equipo::getPosicion));
        System.out.println(equipo);
    }

    public void ordenarClasificacion() {
        Collections.sort(equipos, (e1, e2) -> {
            if (e1.getPuntos() == e2.getPuntos()) {
                return Integer.compare((e2.getGolesFavor() - e2.getGolesContra()), (e1.getGolesFavor() - e1.getGolesContra()));
            } else {
                return Integer.compare(e2.getPuntos(), e1.getPuntos());
            }
        });
    }

    public void mostrarClasificacion() {
        ordenarClasificacion();
        System.out.println("Jornada " +jornada);
        for (Equipo equipo : equipos) {
            System.out.println(equipo);
        }
        jornada++;
    }

    public int obtenerPosicion(String nombreEquipo) {
        for (int i = 0; i < equipos.size(); i++) {
            if (equipos.get(i).getNombre().equals(nombreEquipo)) {
                return i + 1;
            }
        }
        return -1;
    }
}
