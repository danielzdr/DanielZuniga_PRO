package Ejericio3F1;
public class Entrada {


    class Piloto {
        private String nombre;
        private String bastidor;
        private int puntos;

        public Piloto(String nombre, String bastidor) {
            this.nombre = nombre;
            this.bastidor = bastidor;
            this.puntos = 0;
        }

        public String getNombre() {
            return nombre;
        }

        public String getBastidor() {
            return bastidor;
        }

        public int getPuntos() {
            return puntos;
        }

        public void sumarPuntos(int nuevosPuntos) {
            this.puntos += nuevosPuntos;
        }

        @Override
        public String toString() {
            return "Piloto: " + nombre + ", Bastidor: " + bastidor + ", Puntos: " + puntos;
        }
    }
}
