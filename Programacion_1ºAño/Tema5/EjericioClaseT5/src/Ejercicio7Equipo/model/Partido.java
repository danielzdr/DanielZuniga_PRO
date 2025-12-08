package Ejercicio7Equipo.model;

public class Partido {
    private boolean jugado;
    private int golesLocal, golesVisitante;
    private Equipo equipoLocal, equipoVisitante;

    public Partido(){}

    public Partido(Equipo equipoLocal, Equipo equipoVisitante){
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        // golesLocales,golesVisante = 0
        // jugado = false
    }

    public void iniciarPartido(){
        if (equipoLocal != null && equipoVisitante !=null){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if ((equipoLocal.atacar())){
                        golesLocal++;
                    }
                    if (equipoVisitante.atacar()){
                        golesVisitante++;
                    }
                }
            }      jugado=true;
        }
    }

    public boolean anotarGol() {
        return Math.random() < 0.3; // 30% de probabilidad de anotar
    }

    public void setJugado(boolean jugado) {
        this.jugado = jugado;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }


    public boolean isJugado() {
        return jugado;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }



    public void mostrarResultado(){
        if (jugado){
            System.out.printf("El resultado final es de %s:%d-%s:%d ", equipoLocal.getNombre(),golesLocal, equipoVisitante.getNombre(),golesVisitante);
        }else {
            System.out.println("El partido aun no se ha jugado");
        }
    }
}
