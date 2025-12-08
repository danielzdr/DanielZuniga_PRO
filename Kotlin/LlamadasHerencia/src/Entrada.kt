import Controlador.Colegio
import model.Alumno

fun main() {
    var colegio=Colegio()

    var opcion= 0
    do {
        println("1. Agregar alumno")
        println("2. Calificar alumno")
        println("3. Listar Alumnos")
        println("4. Baja alumno")
        println("5. Salir del programa")
        println("Introduce la opcion a elegir")
        opcion=readln().toInt()
        when(opcion){
            1-> {
                println("Id: ")
                val id= readln().toInt()
                println("Nombre: ")
                val nombre= readln()
                println("Apellido: ")
                val apellido= readln()
                println("Calificacion: ")
                val calificacion= readln().toInt()
                println("Matriculado: ")
                val matricula= readln().toBoolean()
                val alumno= Alumno(id,nombre,apellido,calificacion,matricula)
                colegio.agregarAlumno(alumno)
            }
            2->{
                println("Id: ")
                val id= readln().toInt()
                println("Calificacion: ")
                val calificacion= readln().toInt()
                colegio.calificarAlumno(id,calificacion)
            }
            3->{
                println("Opcion matriculados/ no matriculados")
                var matriculados= readln()
                colegio.listarAlumno(matriculados)
            }
            4->{
                println("Id :")
                val id= readln().toInt()
                colegio.bajaAlumno(id)

            }
            5->{
                println("Saliendo del programa")
            }
            else-> println("Opcion invalida, intentalo de nuevo")

        }
    }while (opcion!=5)
}