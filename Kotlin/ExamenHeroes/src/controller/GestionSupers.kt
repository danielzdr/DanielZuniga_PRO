package controller

import model.Armas
import model.Superheroes
import kotlin.collections.ArrayList

class GestionSupers {

    val heroes= ArrayList<Superheroes>()
    val arma= ArrayList<Armas>()

    fun crearSuperheroe(superheroes: Superheroes){
        heroes.add(superheroes)
        println("Heroe añadido correctamente")
    }

    fun crearArmas(armas: Armas){
        arma.add(armas)
        println("Arma añadida correctamente")
    }

    fun listarSuperheroes(){
        if (heroes.isEmpty()){
            println("La lista esta vacia")
        }else{
            heroes.forEach { it.mostrarDatos() }
            println("Mostrando la lista de heroes correcpondiente")
        }
    }

    fun listarArmas(){
        if (arma.isEmpty()){
            println("La lista esta vacia")
        }else{
            arma.forEach { it.mostrarDatos() }
            println("Mostrando la lista de armas correspondientes")
        }
    }

   private fun obtenerHeroePorId(id: Int): Superheroes? {
        return heroes.find { it.id == id }
    }

    private fun obtenerArmaPorId(id: Int): Armas? {
        return arma.find { it.id == id }
    }
    private fun calcularPoder(superheroes:Superheroes, armas: Armas): Int {
        return superheroes.nivel + (armas.nivelPotencia * armas.nivelDanio)
    }

    fun combatir(idHeroe1: Int, idArma1: Int, idHeroe2: Int, idArma2: Int) {
        val h1 = obtenerHeroePorId(idHeroe1)
        val h2 = obtenerHeroePorId(idHeroe2)
        val a1 = obtenerArmaPorId(idArma1)
        val a2 = obtenerArmaPorId(idArma2)

        if (h1 == null || h2 == null || a1 == null || a2 == null) {
            println("Error: ID de héroe o arma inválido")
            return
        }

        val poder1 = calcularPoder(h1, a1)
        val poder2 = calcularPoder(h2, a2)

        println("${h1.nombre} con ${a1.nombre} tiene poder $poder1")
        println("${h2.nombre} con ${a2.nombre} tiene poder $poder2")

        when {
            poder1 > poder2 -> println("¡${h1.nombre} gana!")
            poder2 > poder1 -> println("¡${h2.nombre} gana!")
            else -> println("¡Empate!")
        }

    }
}