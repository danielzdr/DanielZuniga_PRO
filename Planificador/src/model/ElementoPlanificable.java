package model;

public interface ElementoPlanificable {
    int getId();
    String getDescripcion();
    boolean isCompleta();
    void setCompleta(boolean completado);
}
