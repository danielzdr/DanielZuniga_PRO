package model

class Alumno (var id:Int, var nombre:String, var apellido:String,
              var calificacion:Int, var matricula:Boolean){
    var telefono: Int? = null
    var correoE: String? = null

    constructor(id: Int, nombre: String, apellido: String, telefono: Int?, correo: String?, calificacion: Int,matricula: Boolean) :
            this(id,nombre,apellido,calificacion,matricula) {
        this.telefono = telefono
        this.correoE = correo
    }
    fun mostrarDatos(){
        println("Id $id")
        println("Nombre $nombre")
        println("Apellido $apellido")
        println("Calificacion $calificacion")
        println("Matriculado $matricula")
        println("Teléfono: ${telefono ?: "no especificado"}")
        println("Correo: ${correoE ?: "no especificado"}")
    }
    fun desmatricular():Boolean{
        if (matricula) {
            return true
            println("El alumno no ha sido desmatriculado")
        }else{
            return false
        }
    }
    fun calificar(nuevaCalificacion: Int){
        this.calificacion=nuevaCalificacion
    }

}