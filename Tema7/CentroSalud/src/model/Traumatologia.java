package model;

public class Traumatologia extends Doctor {
    public Traumatologia(String apellidos , String nombre , String numeroColegiado) {
        super(apellidos , nombre , Integer.parseInt(numeroColegiado));
    }

    @Override
    public void realizarAccion() {
        System.out.println("Realizando vendaje en el area de traumatologia");
    }
}
