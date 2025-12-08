fun main() {
    //imprimir por consola
    println("Este es mi primer programa Kotlin")
    //var-> variables mutables: que puede cambiar su valor
    //val->variables no mutables: que no cambian su valor(constante)
    var nombre="Pablo"
    var edad= 13//Int
    val DNI= "123456A"
    var correo:String? = null

    //
    lateinit var direccion:String

    println("Hola mi nombre es $nombre tengo $edad y mi DNI es $DNI")
    println("Mi nombre tiene ${nombre.length}letras")
    correo= "pablo.soriano@ces.com"
    //? detras del la variable para evitar el error NullPointerExeption
    println("Mi correo es ${correo?.length ?: "sin asignado"}")
    direccion= "Alcorcon"
    println("Mi direccion es $direccion")

}