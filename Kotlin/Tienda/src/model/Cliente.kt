package model

class Cliente(var nombre:String?,
              var id: Int  ){
    lateinit var carrito:ArrayList<Producto?>
    var factura:Double=0.0

    init {
        carrito= arrayListOf()
    }

    fun agregarProductoCarrito(producto: Producto){
         carrito.add(producto)
        println("Producto agregado al carrito correctamente")
    }

    fun mostrarCarrito(){
        if (carrito.isEmpty()){
            println("No hay productos en el carrito")
        }else{
            carrito.forEach {
                it?.mostrarDatos()
            }
        }
    }

    //mostrar el producto que esta en una posicion indicada

    fun accesoPorPosicion(posicion:Int){
        if (posicion>carrito.size-1){
            println("El ID queda fuera del rango")
        }else {
            carrito[posicion]?.mostrarDatos()
        }
    }

    //eliminar un producto del carrito
    //en caso de que el ID no este en el carrito salta aviso
    //en caso de si estar ID y estar solo 1 lo elimina
    //en caso de si estar ID en el carrito y existir varios, confirmacion de eliminar 1 o varios

    fun eliminarProductoCarrito(id: Int){

        val productosEncontrados = carrito.filter {
            return@filter it?.id == id
        }

        if (productosEncontrados.isEmpty()) {
            println("El numero de resultado es ${productosEncontrados.size} por lo que no se puede guardar.")
        }else if (productosEncontrados.size == 1){
            carrito.remove(productosEncontrados.first())
            println("Producto borrado correctamente")
        }else{
            println("Se han encontrado varias coincidencias" +
            "cual quieres borrar: (1 para el primero / n para todos)")
            val opcion:String = readln()
            if (opcion.equals("1", true))//para que no confunda Mayuscula y minuscula
                carrito.remove(productosEncontrados.first())
            else if (opcion.equals("n", true)) {
                carrito.removeAll(productosEncontrados.toSet())//para que no haya objetos duplicados
            }
        }


    }

    //hacer un metodo para calcular la factura del cliente
    //cuando pide la factura se muestra por consola y vacia el carrito

    fun mostrarDatos(){
        println("Nombre: ${nombre} ")
        println("ID: ${id}")
        println("Carrito ${carrito}")
        println("Factura ${factura}")
    }
}