package util;

public enum Proveedor {
    //tipos predefinidos

    MAHOU("Daniel", 30, "Pablo"),
    COCACOLA("Ruben", 10, "Ivan"),
    COMIDASSL("Valentino", 40, "Hugo"),
    BEBIDASSL("Arthur", 25, "Alvaro");

    //variables

    private String nombre;
    private int descuento;
    private String contacto;

    //constructores
    private Proveedor(){
        this.nombre="sin nombre";
        this.descuento=0;
        this.contacto="sin contactto";
    }

    private Proveedor(String nombre, int descuento, String contacto){
        this.nombre=nombre;
        this.descuento=descuento;
        this.contacto=contacto;
    }
    //metodos

    public void cacularDescuento(int precio){
        System.out.println("El descuento que te podria aplicar como proveedor es: ");

    }


    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
