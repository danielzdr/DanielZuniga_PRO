import Controller.Coche;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class Carrera {


    private int vueltas;
    private String nombre;

//un campeonato tiene 10 participantes, se van inscribiendo
    // tiene 8 carreras y n circuitos
    //cada carrera se corre en un circuito
    //un circuito tiene nombre y vueltas
    //el gandor del campeonato e decide por puntos
    //en cada carrera se reparten el primer clasificado 10 puntos, el 2º 7 puntos y el 3º 6 puntos
    // es interesante saber el ganador de cada carrera, ganador de cada campeonato,
    // si hay empate en una carrera el que antes llegue a unos km marcados es el que ha ganado
    //campeonato el que mas carreras gane o mas kilometros ha realizado
    private ArrayList<Coche> participantes;


    public Carrera(int vueltas, String nombre){
        this.vueltas=vueltas;
        this.nombre=nombre;

        participantes=new ArrayList<>();
    }



    private  void ordenarParticipante(){
        participantes.sort(new Comparator<Coche>() {
            @Override
            public int compare(Coche o1 , Coche o2) {
                if (o1.getKM() > o2.getKM()) {
                    return 1;
                } else if (o2.getKM() < o2.getKM()) {
                    return -1;
                }else {
                    return 0;
                }
            }
        });
    }

    public  void mostrarClasificacion(){
        //la lista debe estar ordenada
        if (participantes.isEmpty()) {
            System.out.println("No se ha inscrito nadie");
        }else {
            ordenarParticipante();
            int posicion = 1;
            for (Coche item : participantes) {
                System.out.printf("%d - %s %s %s km:%d\n" , posicion , item.getMarca() , item.getModelo() , item.getMatricula() , item.getKM());
                posicion++;
            }
        }
    }

    public void inscribirParticipante( Coche coche, int cv){
            if (participantes.size() == 8 || coche.getCv() >= 300 || buscarCoche(coche.getMatricula())!=null){
                System.out.println("No ha sido posible agregar el coche correctamente");
            }else {
                System.out.println("Ha sido agregado correctamente");
            }

    }

    public void mostrarGanador(){


    }

    public Coche buscarCoche(String matricula){
        for (Coche item: participantes ){
            if (item.getMatricula().equalsIgnoreCase(matricula)){
                System.out.println(item);
                return item;
            }
        }
        return null;
    }

    public void descalificar(String matricula){
       if (buscarCoche(matricula)!=null){
           participantes.remove(buscarCoche(matricula));
           System.out.println("Coche borrado");
       }else {
           System.out.println("No se encuentra el participante");
       }

       //
       if ( participantes.removeIf(new Predicate<Coche>() {
            @Override
             public boolean test(Coche coche) {
                return  coche.getMatricula().equalsIgnoreCase(matricula);
            }
        })){
           System.out.println("Borrado correctamente");
       }else {
           System.out.println("No se ha borrado correctamente");}
    }

    public  void iniciarCarrera(){
        //vueltas y que tenga 8 participantes
        if (participantes.size()==8 && vueltas>0){
            for (int i = 0; i < vueltas; i++) {
                for (Coche coche: participantes){
                    int aleatorio=(int) (Math.random()*26)+50;
                    coche.setKm(aleatorio);
                }
                System.out.println("Clasificacion en la vuelta "+(i+1));
                mostrarClasificacion();
            }
        }
    }

    public void mostrarParticipantes(){

    }




}
