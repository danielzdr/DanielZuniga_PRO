package Ejercicio9Gasolinera.model;

public class Coche {

    private String tipoGasolina;
    private int litrosDeposito;

    public Coche(String tipoGasolina) {
        this.tipoGasolina = tipoGasolina;
        this.litrosDeposito = 0;
    }

    public String getTipoGasolina() {
        return tipoGasolina;
    }

    public void setTipoGasolina(String tipoGasolina) {
        this.tipoGasolina = tipoGasolina;
    }

    public int getLitrosDeposito() {
        return litrosDeposito;
    }

    public void setLitrosDeposito(int litrosDeposito) {
        this.litrosDeposito = litrosDeposito;
    }

    public void ponerGasolina(Surtidor surtidor, int litrosARepostar) {
        // Verificar si el surtidor está averiado
        if (surtidor.isFunciona()) {
            System.out.println("¡El surtidor está averiado! No se puede repostar gasolina.");
            return;
        }

        // Verificar si el tipo de gasolina del coche es diferente al del surtidor
        if (!this.tipoGasolina.equals(surtidor.getTipoGasolina())) {
            System.out.println("¡El tipo de gasolina no coincide con el del surtidor!");
            return;
        }

        // Verificar si el surtidor tiene suficientes litros
        if (surtidor.getCapacidadActual() <= 0) {
            System.out.println("¡El surtidor está vacío! No se puede repostar gasolina.");
            return;
        }

        // Verificar si el surtidor tiene suficientes litros para el coche
        if (surtidor.getCapacidadTotal() < litrosARepostar) {
            System.out.println("¡No hay suficientes litros en el surtidor para completar el repostaje!");
            return;
        }

        // Si todo es correcto, sumar la gasolina al coche y restar del surtidor
        this.litrosDeposito += litrosARepostar;
        surtidor.restarLitros(litrosARepostar);

        System.out.println("Gasolina repostada correctamente. El depósito ahora tiene " + this.litrosDeposito + " litros.");
    }
}
