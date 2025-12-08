package EjSentenciasControl

//Dado un listado de notas 0..10,
// clasifícalas en Sobresaliente,
// Notable, Aprobado, Suspenso pero aplicando
// una curva según la media del grupo. Usa when y
// condiciones sobre media y desviación. Devuelve un resumen
// con porcentajes y el motivo de la curva.

fun main() {

    val nota:Int= readln().toInt()
    when(nota){
        !in 0..10->{
            println("Numero incorrecto")
        }
        in 1..4->{
            println("Insuficiente, Has supendido")
        }
        in 5..6->{
            println("Bien. Has aprobado")
        }
        in 7..8->{
            println("Notable. Buena nota")
        }
        in 9..10->{
            println("Sobresaliente, Enorabuena")
        }
        else->{
            println("El examen ni lo he empezado xd")
        }

    }
}