package Model;

public class Persona {
    private String nombre, apellido,dni;
    private int edad;
    private double peso, altura;

    public Persona(String nombre, String apellido, String dni, int edad, double altura, double peso){
        this.nombre=nombre;
        this.apellido=apellido;
        this.dni=dni;
        this.edad=edad;
        this.altura=altura;
        this.peso=peso;
    }

    public Persona(String nombre, String apellido, String dni, int edad){
        this.nombre=nombre;
        this.apellido=apellido;
        this.dni=dni;
        this.edad=edad;
    }

    public Persona(String nombre, String apellido){
        this.nombre=nombre;
        this.apellido=apellido;
        this.dni="111111111X";
        this.edad=0;
        this.altura=0.0;
        this.peso=0.0;
    }

    public  Persona(){
        this.nombre="Sin definir";
        this.apellido="Sin definir";
        this.dni="111111111X";
        this.edad=0;
        this.altura=0.0;
        this.peso=0.0;
    }

    public void mostrarDatos(){
        System.out.printf("Nombre: %s"+
                "\nApellido: %s" +
                "\nDni: %s" +
                "\nEdad: %d" +
                "\nAltura %.2f" +
                "\nPeso %.2f\n"+ nombre+apellido+dni+edad+altura+peso);
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double setIMC(){
        double IMC= peso/Math.pow(altura,2);
        return IMC ;
    }

    public void mostrarIMC(){
        double imc = setIMC();
        if (imc < 18.5){
            System.out.println("Peso inferior al normal");
        }else if (imc>18.5 && imc<24.9){
            System.out.println("El peso es normal");
        } else if (imc>25.0 && imc<29.9) {
            System.out.println("El peso es superior al normal");
        } else if (imc>30.0) {
            System.out.println("Es obesidad");
        }
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Altura: " + altura + " cm");
        System.out.printf("IMC: %.2f\n", imc);
    }

    public void mostrarIMCMen(){
        double imcMEN=setIMC();
        if (imcMEN < 20){
            System.out.println("Bajo peso");
        }else if (imcMEN>20 && imcMEN<27){
            System.out.println("El peso es normal");
        } else if (imcMEN>27 && imcMEN<30) {
            System.out.println("El peso es superior al normal");
        } else if (imcMEN>30 && imcMEN<40) {
            System.out.println("Es obesidad");
        } else if (imcMEN>40) {
            System.out.println("Obesidad morbida");
        }
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Altura: " + altura + " cm");
        System.out.printf("IMC: %.2f\n", imcMEN);
    }

    public void mostrarIMCMUJ(){
        double imcMUJ=setIMC();
        if (imcMUJ < 20){
            System.out.println("Bajo peso");
        }else if (imcMUJ>20 && imcMUJ<25){
            System.out.println("El peso es normal");
        } else if (imcMUJ>25 && imcMUJ<30) {
            System.out.println("El peso es superior al normal");
        } else if (imcMUJ>30 && imcMUJ<40) {
            System.out.println("Es obesidad");
        } else if (imcMUJ>40) {
            System.out.println("Obesidad morbida");
        }
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Altura: " + altura + " cm");
        System.out.printf("IMC: %.2f\n", imcMUJ);
    }

    public void setEdad(int edad) {
        this.edad += edad;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDni(){
        return  dni;
    }

    public String getNombre(){
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public int getEdad(){
        return edad;
    }

    public double getAltura(){
        return altura;
    }

    public double getPeso(){
        return peso;
    }


}
