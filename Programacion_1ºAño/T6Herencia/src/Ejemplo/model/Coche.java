package Ejemplo.model;


public class Coche extends Vehiculo {
        //atributos->
        // metodos-> mostrarDatos, getter, setter

        private String traccion;
        public Coche (Motor motor, String bastidor, String traccion){
            super(motor, bastidor);
            this.traccion=traccion;
        }

        @Override
        public void mostrarDatos() {
            super.mostrarDatos();
        }
    }

