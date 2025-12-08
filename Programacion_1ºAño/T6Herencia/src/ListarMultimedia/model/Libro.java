package ListarMultimedia.model;

public class Libro extends Elemento{
    private String ISBN;
    private int nPaginas;

    public Libro(){}



    public Libro(String id , String titulo , String formato , Persona autor , int identificador , int tamanio , String ISBN , int nPaginas) {
        super(id , titulo , formato , autor , tamanio);
        this.ISBN = ISBN;
        this.nPaginas = nPaginas;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("ISBN = " +ISBN);
        System.out.println("Nmuero de paginas = " +nPaginas);
        super.mostrarDatos();
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getnPaginas() {
        return nPaginas;
    }

    public void setnPaginas(int nPaginas) {
        this.nPaginas = nPaginas;
    }
}
