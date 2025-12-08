import controller.GestionSupers
import model.Armas
import model.Superheroes

fun main() {

    val gestionSupers=GestionSupers()

    var opcion=0
    do {
        println("Bienvenidos a la app De Heroes")
        println("1. Crear heroes")
        println("2. Crear armas")
        println("3. Listar Heroes")
        println("4. Listar Armas")
        println("5. Crear combate")
        println("6 . Salir del programa")
        println("Introduce la opcion ")
        opcion= readln().toInt()
        when(opcion){
            1->{
                println("Id del heroe: ")
                val id= readln().toInt()
                println("Nombre del heroe: ")
                val nombre= readln()
                println("Nivel del heroe: ")
                val nivel= readln().toInt()
                gestionSupers.crearSuperheroe(Superheroes(id,nombre,nivel))
                println("Id del heroe 2: ")
                val id2= readln().toInt()
                println("Nombre del heroe 2: ")
                val nombre2= readln()
                println("Nivel del heroe 2: ")
                val nivel2= readln().toInt()
                gestionSupers.crearSuperheroe(Superheroes(id2,nombre2,nivel2))
            }
            2->{
                println("Id del arma: ")
                val id= readln().toInt()
                println("Nombre del arma: ")
                val nombre= readln()
                println("Nivel de potencia: ")
                val nivel= readln().toInt()
                println("Nivel de daño: ")
                val nivelD= readln().toInt()
                gestionSupers.crearArmas(Armas(id,nombre,nivel,nivelD))
                println("Id del arma 2: ")
                val id2= readln().toInt()
                println("Nombre del arma 2: ")
                val nombre2= readln()
                println("Nivel de potencia del arma 2: ")
                val nivel2= readln().toInt()
                println("Nivel de daño del arma 2: ")
                val nivelD2= readln().toInt()
                gestionSupers.crearArmas(Armas(id2,nombre2,nivel2,nivelD2))
            }
            3->{
                gestionSupers.listarSuperheroes()
            }
            4->{
                gestionSupers.listarArmas()
            }
            5->{
                println("Introduce el id del Heroe 1: ")
                val id1= readln().toInt()
                println("Introduce el id del arma del heroe 1: ")
                val arma1= readln().toInt()
                println("Introduce el id del Heroe 2: ")
                val id2= readln().toInt()
                println("Introduce el id del arma2 del heroe 2: ")
                val arma2= readln().toInt()
                gestionSupers.combatir(id1,arma1,id2,arma2)
            }
            6->{
                println("Saliendo del programa...")
            }
            else->{
                println("Opcion invalida, intentalo de nuevo")
            }
        }
    }while (opcion!=6)
}