package Controlador

import model.Alumno

class Colegio {
    private val listaAlumnos= ArrayList<Alumno>()

    fun agregarAlumno(alumno: Alumno){
        listaAlumnos.add(alumno)
        println("Alumno agregado correctamente")
    }

    fun calificarAlumno(id:Int,calificacion:Int){
        val alumno=listaAlumnos.find { it.id==id }
        if (alumno!=null){
            alumno.calificar(calificacion)
            println("El alumno $alumno ha sido calificado con $calificacion")
        }else{
            println("No se encontro ningun alumno con ese id")
        }
    }

    fun listarAlumno(opcion:String){
        val lista= when (opcion.lowercase()){
            "matriculados"->listaAlumnos.filter { it.matricula }
            "no matriculados"-> listaAlumnos.filter { !it.matricula }
            else->listaAlumnos
        }
        if (lista.isEmpty()){
            println("No hay alumnos en la lista")
        }else{
            println("La lista de alumnos $opcion")
            lista.forEach{
                println("ID ${it.id}, Nombre ${it.nombre}, Apellido ${it.apellido}, Calificacion ${it.calificacion}")
            }
        }
    }

    fun bajaAlumno(id:Int){
       val alumno= listaAlumnos.find { it.id==id }
        if (alumno!=null){
            if (!alumno.matricula){
                println("El alumno ya esta dado de baja")
            }else{
                alumno.matricula=false
                println("Alumno dado de baja correctamente")
            }
        }else{
            println("No se encontro nigun alumno con ese $id")
        }
    }

}