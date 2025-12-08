package Ejercicio9Casa.model;

import Ejercicio9Casa.util.Orientacion;

public class Terreno {
    private Casa casa;
    private int m2;
    private int  valoracion;
    private Orientacion orientacion;



    public Terreno(int m2, Orientacion valoracion) {
        this.m2 = m2;
        this.valoracion = 100000;
        this.orientacion=orientacion;
        this.casa=null;
    }

    public Terreno() {
    }

    public void mostrarDatos(){
        System.out.println("Metros Cuadrados " +m2);
        System.out.println("Valoracion " +valoracion);
        casa.mostrarDatos();

    }

    public void revalorizarCasa() {
        if (this.casa != null) {

            if (this.casa.getM2() > 100) {
                this.valoracion *= 1.30; // 30% más si tiene más de 100m2
            } else {
                this.valoracion *= 1.25; // 25% más si tiene casa
            }


            if (this.orientacion == Orientacion.NORTE || this.orientacion == Orientacion.SUR) {
                this.valoracion *= 1.10; // 10% más si es norte-sur
            } else if (this.orientacion == Orientacion.ESTE || this.orientacion == Orientacion.OESTE) {
                this.valoracion *= 1.05; // 5% más si es este-oeste
            }


            if (this.casa.isPiscina()) {
                this.valoracion *= 1.50; // 50% más si tiene piscina
            }
        }
    }

    public boolean contruirCasa(int m2) {
        if (this.m2 >= m2) {
            this.casa = new Casa();
            this.m2 -= m2; // Restamos el espacio ocupado por la casa
            return true;
        }
        System.out.println("No hay suficiente espacio en el terreno para construir la casa.");
        return false;
    }

    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    public int getM2() {
        return m2;
    }

    public void setM2(int m2) {
        this.m2 = m2;
    }

    public Orientacion getOrientacion() {
        return orientacion;
    }

    public double getValoracion() {
        return valoracion;
    }



    public class Casa{
        private int m2;
        private boolean piscina;
        private int habitacion;

        public Casa() {
        }

        public boolean construirHabitacion(int metros){
            if (this.m2 >= metros) {
                this.habitacion++;
                this.m2 -= metros;
                return true;
            }
            return false;
        }

        public void construirPiscina(){
            if (!this.piscina){
                this.piscina=true;
                revalorizarCasa();
            }
        }

        public void contruirAnexo(int metros){
            if (this.m2 >= metros) {
                this.m2 -= metros;
            } else {
                System.out.println("No hay suficiente espacio para construir el anexo.");
            }
        }

        public Casa(int m2 , boolean piscina , int habitacion) {
            this.m2 = m2;
            this.piscina = piscina;
            this.habitacion = habitacion;
            casa=this;
        }

        public int getM2() {
            return m2;
        }

        public void setM2(int m2) {
            this.m2 = m2;
        }

        public boolean isPiscina() {
            return piscina;
        }

        public void setPiscina(boolean piscina) {
            this.piscina = piscina;
        }

        public int getHabitacion() {
            return habitacion;
        }

        public void setHabitacion(int habitacion) {
            this.habitacion = habitacion;
        }


        public void mostrarDatos(){
            System.out.println("Metros cuadrados " +m2);
            System.out.println("Piscina " +piscina);
            System.out.println("Numero de habitaciones " +habitacion);

        }
    }
}
