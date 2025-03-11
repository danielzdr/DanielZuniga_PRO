package Trabajadores.model;

public abstract class Accionista implements Beneficiario{
    private double acciones;

    public Accionista(){}


    @Override
    public void repartirBeneficio(int beneficio) {
        double beneficioTotal= beneficio*acciones;
        System.out.println("El total del beneficio es de " +beneficioTotal);
    }
}
