package model;

import java.util.ArrayList;
import java.util.List;

public class Liga {

        private String nombre;
        private List<Equipo> equipos;

        public Liga(String nombre) {
            this.nombre = nombre;
            this.equipos = new ArrayList<>();
        }

        public String getNombre() {
            return nombre;
        }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public List<Equipo> getEquipos() {
            return equipos;
        }

        @Override
        public String toString() {
            return "Liga{" +
                    "nombre='" + nombre + '\'' +
                    ", equipos=" + equipos +
                    '}';
        }
}