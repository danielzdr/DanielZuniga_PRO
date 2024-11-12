package ArrayIndice;


public class objetsARRAY {

    public static void main(String[] args) {
            Object[]cosas={5,"dam",true,5.9,'a',7};
        for (int i = 0; i < cosas.length; i++) {
            if (cosas[i] instanceof Integer){
                System.out.println(cosas[i]);
                System.out.println("La longitud de la palabra es");

                //busquedas->{1,2,3,4,5,6,6,7,8,8,764,53,34}
                //7-> recorro y termino cuando encuentro. Lo he encontrado cuando == elemento.
                
            }
        }
    }
}
