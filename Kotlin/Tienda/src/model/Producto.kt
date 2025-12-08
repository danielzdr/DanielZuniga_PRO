package model

//constructor primario
class Producto (
    var id:Int, var precio:Double=10.0,
    var nombre: String?=null, var descripcion : String?=null,
    var categoria: Categoria = Categoria.Generica) {



    //metodo para mostrar todos los datos del producto concreto
//si no hay , aparece sin nombre
//si no hay descripcion, aparece sin descripcion
    fun mostrarDatos() {
        println("ID: $id")
        println("Precio: $precio")
        println("Nombre: ${nombre ?: "SIN DEFINIR"}")
        println("Categoria: ${categoria}")
        println("Descripcion: ${descripcion ?: "SIN DEFINIR"}")
    }
}