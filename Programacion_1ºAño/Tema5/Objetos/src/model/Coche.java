package model;
import java.util.Comparator;
import java.util.ArrayList;

public class Coche {

    //1º propiedades
    private String marca, modelo, color, matricula, bastidor;
    private int cv, par;
    private int km;
    private ArrayList<Coche> listaCoche;


    //2º constructores-> por defecto tengo 1 constructor vacio sin escribir nada->valor implicito
    // si no, es valor explicito
    //conocido como sobrecarga de constructrores


    public Coche() {
        listaCoche = new ArrayList<>();
        //inicializar el objeto y por ello todos los atributos de este
        //para ejecutar funciones cuando el objeto se crea
        matricula = "Sin definir";
        modelo = "Sin definir";
        marca = "Sin definir";
        bastidor = "Sin definir";
        color = "Sin definir";
    }

    public Coche(String marca , String modelo , String color) {

    }

    /*
    public Coche(String marca, String modelo, int cv){
        this.marca= marca;
        this.modelo= modelo;
        this.cv= cv;
        this.par=(int)Math.pow(cv*2,2);

    } */


    public Coche(String marca , String modelo , String matricula , int cv) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.cv = cv;

    }

    public void setKm(int km) {
        this.km += km;
    }

    //En los get siempre se pone en vez de void el tipo de variable que es y se retorna //
    public int getKm() {
        return this.cv;
    }

    public String getMatricula() {
            return this.matricula;
    }
}




    //inicializa un constructor del coche con marca, modelo, color, matricula, cv, precio, bastidor,
    //par ->calcular
    //precio-> lo que me dan +15%
    /*public Coche(String marca, String modelo, String color, String matricula, String bastidor, int cv, double precio){
        this. marca= marca;
        this.modelo=modelo;
        this.color=color;
        this.matricula=matricula;
        this.bastidor=bastidor;
        this.cv=cv;
        this.par=(int)Math.pow(cv*2,2);
        this.precio=precio+(precio*0.15);

    }
    //3º metodos
    public void mostrarDatos(){
        //palabra reservada this. hace referencia al de arriba
        System.out.println("La marca es "+this.marca);
        System.out.println("El modelo es "+this.modelo);
        System.out.println("El color es "+this.color);
        System.out.println("La matricula es "+this.matricula);
        System.out.println("El bastidor es "+this.bastidor);
        System.out.println("El precio es "+this.precio);
        System.out.println("El cv es "+this.cv);
        System.out.println("El par es "+this.par);
    }

    public void setNombre(String marca){
        this.marca=marca;
    }

    public String getDistanciaTotal (String marca){
        return this.marca;
    }

        //metodos especiales
        
     */

