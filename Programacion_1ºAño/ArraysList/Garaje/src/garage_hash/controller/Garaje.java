package garage_hash.controller;

import java.util.Enumeration;
import java.util.Hashtable;

public class Garaje {
    private Hashtable<String,Object[]>listaCoches;

    public Garaje(){
        listaCoches=new Hashtable<>();
    }

    public void agregarCoche(){
        Object[] coche1={"12345A","Mercedes","ClaseA", 10000,150};
        Object[] coche2={"45676B","Mercedes","ClaseB", 20000,200};
        Object[] coche3={"98765C","Mercedes","ClaseC", 30000,300};
        Object[] coche4={"98123D","Mercedes","ClaseD", 40000,400};
        listaCoches.put(coche1[0].toString(),coche1);
        listaCoches.put(coche2[0].toString(),coche2);
        listaCoches.put(coche3[0].toString(),coche3);
        listaCoches.put(coche4[0].toString(),coche4);
    }

    public void getCoche(String matricula){
        //sacar solo el coche de la lista
        if (listaCoches.containsKey(matricula)) {
            Object[] cocheEncontrado = listaCoches.get(matricula);//->null
            System.out.println(cocheEncontrado[2].toString());
        }
    }

    public void borrarCoche(String matricula){
        listaCoches.remove(matricula);

    }

    public void modificarCoche(String matricula){
        Object[] coche=listaCoches.get(matricula);
        coche [3]=Integer.valueOf(coche[3].toString())+20000;
    }

    public void recorrerCochesKey(){
        Enumeration <String> keys= listaCoches.keys();
    while (keys.hasMoreElements()){
        String key= keys.nextElement();
        getCoche(key);
    }
    }

    public void recorrerCochesElement(){
        System.out.println("Llamada");
        Enumeration <Object[]> coches= listaCoches.elements();
        while (coches.hasMoreElements()){
            Object[] coche= coches.nextElement();
            System.out.println(coche[2]);
        }
    }

}
