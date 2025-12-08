package model

class Tienda (var nombre:String?=null){

    lateinit var almacen : Array<Producto?>
    var precio: Double= 0.0
    var caja: Double =0.0

    init {
        almacen= arrayOfNulls(6)
    }

    fun mostrarAlmacen() {
        var nulos=0
        almacen.forEach {
            it?.mostrarDatos()?:nulos++
        }
        if (nulos == almacen.size){
            println("No hay productos en el almacen")
        }
    }

    //agregar un elemento al almacen. En caso de no tener hueco disponible mostrara un aviso
    fun agregarAlmacen(producto: Producto){
       for (item in 0..almacen.size-1){
           if (item==null){
               almacen[item]=producto
               return
           }
       }
        println("El almacen esta completo")
    }

    //vender un producto.Para ello se solicita el id del producto a vender
    // En caso de estar en el almacen:
    // Se elimina de este
    // su precio se suma a la caja
    //si se vende se a un aviso de producto vendido y se da el valor de la caja
    //si no se encuentra se da aviso de que no esta en el almacen

    fun venderProducto(producto: Producto){
        for (i in 0..almacen.size){
            if (almacen[i]!=null && almacen[i]?.id == producto.id){
                caja+=almacen[i]!!.precio//para forzar que aqui nunca va a llegar a nulo
                almacen[i]= null
                return
            }
        }
        println("El id indicado no esta en la lista")
    }

    fun buscarProductosCategoria(categoria: Categoria){
        //filtrando ->obteniendo varios
    }

    fun buscarProductoId(id: Int):Unit{
        //buscando-> obtengo solo un elemento
       var productoBusqueda: Producto? = almacen.find {//retorna un find
            return@find it?.id == id//itera los productos y cuando el predicado diga TRUE se acaba el recorrido
        }
    }

    fun buscarProductoCatergoria(categoria: Categoria){
        //filtrando obteniendo varios
        val filtro: ArrayList<Producto?> = almacen.filter {
            return@filter it?.categoria==categoria
        }as ArrayList<Producto?>

        println("El numero de elementos resultantes es ${filtro.size}")
    }

}