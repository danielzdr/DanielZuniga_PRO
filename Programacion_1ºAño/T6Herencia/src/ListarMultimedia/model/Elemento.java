package ListarMultimedia.model;

public class Elemento {
    private String id, titulo, formato;
    private Persona autor;
    private int identificador, tamanio;

    public Elemento() {
    }

    public Elemento(String id , String titulo , String formato , Persona autor , int tamanio) {
        this.id = id;
        this.titulo = titulo;
        this.formato = formato;
        this.autor = autor;
        this.tamanio = tamanio;
    }

    public void mostrarDatos(){
        System.out.println("id = " +id);
        System.out.println("titulo " +titulo);
        System.out.println("formato " +formato);
        System.out.println("autor " +autor);
        System.out.println("Tamaño " +tamanio);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Persona getAutor() {
        return autor;
    }

    public void setAutor(Persona autor) {
        this.autor = autor;
    }




    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
}
