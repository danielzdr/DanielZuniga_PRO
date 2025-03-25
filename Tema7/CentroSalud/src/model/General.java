package model;

 public class General extends Doctor{
     String tipo;

    public General(String apellidos , String nombre , int numeroColegiado , String tipo) {
        super(apellidos , nombre , numeroColegiado);
        this.tipo = tipo;
    }

     public General(String nombre , String apellido , String numeroColegiado , String tipoGeneral) {
         super(apellido , nombre , Integer.parseInt(numeroColegiado));
         this.tipo = tipo;
     }

     @Override
    public void realizarAccion() {
        System.out.println("Recetando pastillas para los enfermos");
    }
}
