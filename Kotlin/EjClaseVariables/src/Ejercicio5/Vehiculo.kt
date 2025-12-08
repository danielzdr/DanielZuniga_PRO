package Ejercicio5

open class Vehiculo (var tipo: String, var marca:String, var modelo:String, var velocidad:Int, var anio:Int) {

    fun arrancar(){
        println("El vehiculo $marca esta arrancado")
    }
    fun mostrarDatos(){
        println("Tipo $tipo")
        println("Marca $marca")
        println("Modelo $modelo")
        println("Cilindrada $velocidad")
        println("Año $anio")

    }

    private fun combrobarAnio(){
        println("Combrbando $anio")
    }

    protected fun acelerar(cantidad:Int){
        velocidad+=cantidad
        println("Acelerando a $velocidad KM/h")
    }
}