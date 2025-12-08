package model

import controller.GestionFantasy


class Participante(id:Int, nombre:String, var presupuesto: Double = 10000000.0
) : Persona(id,nombre) {
    val plantilla: MutableList<Jugador> = mutableListOf()
    var puntos: Int = 0



        fun añadirJugador(jugador: Jugador): Boolean {
            if (presupuesto - jugador.valor < 0) {
                println("$nombre: Presupuesto insuficiente para fichar a ${jugador.nombre}")
                return false
            }

            if (plantilla.size >= 6) {
                println("$nombre: Plantilla completa, no se pueden añadir más jugadores")
                return false
            }


            val conteoPosiciones = plantilla.filter { it.posicion==jugador.posicion }
            val posicionJugador = jugador.posicion

            when (posicionJugador) {
                "portero" -> {
                    if (conteoPosiciones.!!("portero", 0) >= 1) {
                        println("$nombre: Ya tienes un portero en tu plantilla")
                        return false
                    }
                }
                "defensa" -> {
                    if (conteoPosiciones.!!("defensa", 0) >= 2) {
                        println("$nombre: Ya tienes 2 defensas en tu plantilla")
                        return false
                    }
                }
                "medio" -> {
                    if (conteoPosiciones.!!("medio", 0) >= 2) {
                        println("$nombre: Ya tienes 2 medios en tu plantilla")
                        return false
                    }
                }
                "delantero" -> {
                    if (conteoPosiciones.!!("delantero", 0) >= 1) {
                        println("$nombre: Ya tienes un delantero en tu plantilla")
                        return false
                    }
                }
            }

            plantilla.add(jugador)
            presupuesto -= jugador.valor
            println("$nombre ha fichado a ${jugador.nombre} por ${jugador.valor}€. Presupuesto restante: $presupuesto€")
            return true
        }





        fun tienePlantillaCompleta(): Boolean {
            return plantilla.size == 6
        }

        fun calcularPuntos(): Int {
            puntos = plantilla.sumOf { it.calcularPuntos() }
            return puntos
        }
    }
