package Ejericio5Carrera.model;

public class Coche {
    private int cv, velocidad;
    private String matricula, modelo;
    private double kmRecorridos;

    public Coche(){
       this.cv=0;
       this.velocidad=0;
       this.matricula="0000AAA";
       this.modelo="sin especificar";
       this.kmRecorridos=0.0;
    }

    public Coche(String modelo, String matricula, int cv){
        this.cv=cv;
        this.velocidad=0;
        this.matricula=matricula;
        this.modelo=modelo;
        this.kmRecorridos=0.0;
    }

    public void acelerarCoche(int incremento){
        velocidad+=incremento;
            if (velocidad > 180) {
            velocidad=180;
                System.out.println("La velocidad maxima es de 180");
            }

            int numeroAleatorio= (int)Math.random()*11;
            kmRecorridos+=velocidad*(cv*numeroAleatorio);
            System.out.println("Velocidad actual: "+velocidad);
            System.out.println("Kilometros recorridos "+kmRecorridos);


        }

    public void frenarCoche(int incremento){
        velocidad-=incremento;
        if (velocidad<0){
            velocidad=0;
            System.out.println("La velocidad minima es de 0");
        }

    }

    public void pararCoche(){
        if (velocidad==0){
            System.out.println("La velocidad de parar es de 0");
        }
    }

    public void resetearCoche(){
        if (velocidad==0 && kmRecorridos==0){
            System.out.println("La velocidad y los kilometros recorridos seran de 0");
        }
    }

    public void mostrarCoche(){
        System.out.printf("Matricula :%s, Modelo: %s, CV: %d, Velocidad: %d, Kilometros: %.2f"
                , getMatricula(), getModelo(), getCv(), getVelocidad(), getKmRecorridos());
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getKmRecorridos() {
        return kmRecorridos;
    }

    public void setKmRecorridos(double kmRecorridos) {
        this.kmRecorridos = kmRecorridos;
    }
}



