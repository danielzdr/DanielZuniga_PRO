package utils;

public enum Sabores {
    //datos fijos
    Fresa(40,50,200),
    Vainilla(80,60,250),
    Mango(40,50,150),
    Chocolate(90,100,350);

    private int calorias;
    private int precio;
    private int grasas;
    private int azucar;


    Sabores(int calorias , int precio , int grasas , int azucar) {
        this.calorias = calorias;
        this.precio = precio;
        this.grasas = grasas;
        this.azucar = azucar;
    }

    Sabores(int calorias , int grasas , int azucar) {
        this.calorias = calorias;
        this.grasas = grasas;
        this.azucar = azucar;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setGrasas(int grasas) {
        this.grasas = grasas;
    }

    public void setAzucar(int azucar) {
        this.azucar = azucar;
    }

    public int getCalorias() {
        return calorias;
    }

    public int getPrecio() {
        return precio;
    }

    public int getGrasas() {
        return grasas;
    }

    public int getAzucar() {
        return azucar;
    }

    public void mostrarDatos(){
        System.out.println("Calorias: " +calorias);
        System.out.println("Precio: " +precio);
        System.out.println("Grasas: " +grasas);
        System.out.println("Azucar: " +azucar);
    }
}
