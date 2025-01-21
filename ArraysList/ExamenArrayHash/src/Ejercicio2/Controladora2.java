package Ejercicio2;

import java.util.ArrayList;
import java.util.Comparator;

public class Controladora2 {
    private ArrayList<Object[]> listaAlumnos;
    public Controladora2() {
        listaAlumnos=new ArrayList<>();
    }

    public static void agregarAlumno(String nombre){
        //-1 para poner que no esta calificado
    Object[] alumno =new Object[]{nombre,-1};


    }

    public  void calificar(){
        for (int i = 0; i < listaAlumnos.size(); i++) {
            int nota= (int) (Math.random()*10)+1;
            Object[] alumno= listaAlumnos.get(i);
            alumno[1]=nota;
        }
    }

    public void verSuspensos(){
        for (Object[] item: listaAlumnos){
            if ((int)item[1] < 5){
                System.out.println("El alumno es "+item[0]+" y tiene una nota de "+item[1]);
            }
        }
    }

    public void listarOrdenado(){
        listaAlumnos.sort(new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1 , Object[] o2) {
                if ((int)o1[0] > (int)o2[1]){
                    return 1;
                    //casteo los object array a int
                } else if ((int)o1[0] < (int)o2[1]) {
                    return -1;
                }else {
                    return 0;
                }
            }
        });
    }
}

