package Ejemplo.model;

public class ClasePadre {
    private String nombrePadre;
    private String apellidoPadre;
    private int edadPadre;
    private String correoPadre;
    private ClaseHija claseHija;

    public ClasePadre(String nombrePadre , String apellidoPadre , int edadPadre , String correoPadre) {
        this.nombrePadre = nombrePadre;
        this.apellidoPadre = apellidoPadre;
        this.edadPadre = edadPadre;
        this.correoPadre = correoPadre;

    }

    public ClasePadre() {
    }

    public ClaseHija getClaseHija() {
        return claseHija;
    }

    public void setClaseHija(ClaseHija claseHija) {
        this.claseHija = claseHija;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public String getApellidoPadre() {
        return apellidoPadre;
    }

    public void setApellidoPadre(String apellidoPadre) {
        this.apellidoPadre = apellidoPadre;
    }

    public int getEdadPadre() {
        return edadPadre;
    }

    public void setEdadPadre(int edadPadre) {
        this.edadPadre = edadPadre;
    }

    public String getCorreoPadre() {
        return correoPadre;
    }

    public void setCorreoPadre(String correoPadre) {
        this.correoPadre = correoPadre;
    }

    public void mostrarDatos(){
        System.out.println("Nombre " +nombrePadre);
        System.out.println("Apellidos " +apellidoPadre);
        System.out.println("Edad "+edadPadre);
        System.out.println("Correo " +correoPadre);
        System.out.println("Tengo una clase hija con los siguentes datos");
        claseHija.mostrarDatos();
    }


    public class ClaseHija{
        private String nombreHija;
        private String apellidoHija;
        private int edadHija;
        private String correoHija;

        public ClaseHija() {
        }

        public ClaseHija(String nombreHija , String apellidoHija , int edadHija , String correoHija) {
            this.nombreHija = nombreHija;
            this.apellidoHija = apellidoHija;
            this.edadHija = edadHija;
            this.correoHija = correoHija;
            claseHija=this;
        }

        public String getNombreHija() {
            return nombreHija;
        }

        public void setNombreHija(String nombreHija) {
            this.nombreHija = nombreHija;
        }

        public String getApellidoHija() {
            return apellidoHija;
        }

        public void setApellidoHija(String apellidoHija) {
            this.apellidoHija = apellidoHija;
        }

        public int getEdadHija() {
            return edadHija;
        }

        public void setEdadHija(int edadHija) {
            this.edadHija = edadHija;
        }

        public String getCorreoHija() {
            return correoHija;
        }

        public void setCorreoHija(String correoHija) {
            this.correoHija = correoHija;
        }

        public void mostrarDatos(){
            System.out.println("Nombre " +nombreHija);
            System.out.println("Apellidos " +apellidoHija);
            System.out.println("Edad "+edadHija);
            System.out.println("Correo " +correoHija);
        }


    }
}
