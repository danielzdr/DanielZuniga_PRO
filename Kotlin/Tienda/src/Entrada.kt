import model.Categoria
import model.Cliente
import model.Producto
import model.Tienda

fun main() {

    //Inicializar clases en el main
    var pantalones= Producto (1,20.0,"Pantalones", categoria = Categoria.Generica)
    var gorra= Producto (2,20.0, descripcion = "Gorra molona", categoria = Categoria.Ropa)
    var cartera= Producto (3,30.0, "Estanteria", "Queda muy elegante", categoria = Categoria.Muebles)
    var telefono= Producto(4, 50.0, "Iphone 17", "Muy moderno y nuevo", categoria = Categoria.Tecnologia)
    //cartera.mostrarDatos()

    //Crear un array en Kotlin
    val listaProductos: Array<Producto?> = arrayOf(pantalones, gorra, cartera)//permite al array guardar null

    val listaProductoVacio: Array<Producto?> = arrayOfNulls(5)//Array con 5 valores nulos
    //listaProdcutos[0].mostrarDatos()->datos de pantalones
    //listaProdcutos[listaProdcutos.size -1].mostrarDatos()//sacar los datos de la cartera que es el ultimo
    //listaProductos.last()?.mostrarDatos()//muestra el ultimo producto

    //recorrer toda la lista de productos
   /* for (i in listaProductos){
        i?.mostrarDatos()
    }*/

    /*listaProductos[2]=null
    listaProductos.forEach {
        it?.mostrarDatos()}*/

   /* listaProductos.forEachIndexed { index, producto ->
        println("Mostrando producto en posicion $index")
        producto?.mostrarDatos()
    }*/

    //vamos a crear una tienda. Para ellos crear la clase necesaria
    //donde se pueda asignar
    //1- Nombre a la tienda (obligatorio)
    //2-Almacen: sitio donde se guardaran los productos
    // Tiene un tamaño fijo de 6
    //3-Caja : se guardara el dinero cuando se venda un producto

    val tienda = Tienda()
    //tienda.mostrarAlmacen()
    //tienda.buscarProductoCatergoria(Categoria.Muebles)
    var cliente:Cliente=Cliente("Pablo",1)
    cliente.agregarProductoCarrito(gorra)
    cliente.mostrarCarrito()
}