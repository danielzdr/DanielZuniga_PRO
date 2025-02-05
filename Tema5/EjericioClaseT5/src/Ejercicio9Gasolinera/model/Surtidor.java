package Ejercicio9Gasolinera.model;

public class Surtidor {
    private int capacidadTotal, capacidadActual;
    private String tipoGasolina;
    private boolean  funciona;

    public Surtidor(int capacidadTotal , String tipoGasolina ) {
        this.capacidadTotal = capacidadTotal;
        this.capacidadActual = capacidadTotal;
        this.tipoGasolina = tipoGasolina;
        this.funciona = true;
    }

    public Surtidor(String tipoGasolina) {
        this.tipoGasolina = tipoGasolina;
        this.capacidadActual=0;
        this.capacidadTotal=0;
        this.funciona=true;
    }

    public void arreglarSurtidor(){

    }
    public void restarLitros(double litros) {
        this.capacidadActual -= litros;
    }

    public void rellenarSurtidor(int capacidadActual){
        capacidadActual+=capacidadTotal;

    }

    public void quitarGasolina(){
            capacidadActual-=capacidadTotal;
    }

    public int getCapacidadTotal() {
        return capacidadTotal;
    }

    public void setCapacidadTotal(int capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }

    public int getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public String getTipoGasolina() {
        return tipoGasolina;
    }

    public void setTipoGasolina(String tipoGasolina) {
        this.tipoGasolina = tipoGasolina;
    }

    public boolean isFunciona() {
        return funciona;
    }

    public void setFunciona(boolean funciona) {
        this.funciona = funciona;
    }
}
