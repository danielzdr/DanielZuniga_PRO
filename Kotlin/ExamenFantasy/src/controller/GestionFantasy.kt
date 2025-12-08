package controller

import model.Jugador
import model.Participante

class GestionFantasy {

    var jugadores = mutableListOf<Jugador>()
    var participantes = mutableListOf<Participante>()




        fun añadirParticipante(participante: Participante){
            participantes.add(participante)
        }

        fun listarParticipantes(){
            if (participantes.isEmpty()){
                println("La lista esta vacia")
            }else{
                participantes.forEach { it.mostrarDatos() }
                println("Mostrando la lista correspondiente")
            }
        }

        fun eliminarParticipante(participante: Participante){
            participantes.remove(participante)

        }

    fun añadirJugador(jugador: Jugador){
        jugadores.add(jugador)
    }


        fun listarJugadores() {
            if (jugadores.isEmpty()) {
                println("La lista esta vacia")
            } else {
                jugadores.forEach { it.mostrarDatos() }
                println("Jugadores listados correctamente")
            }
        }

        fun eliminarJugadores(jugador: Jugador){
            jugadores.remove(jugador)
        }


    }


