package com.example.practicaimc.model

import java.io.Serializable

class Mujeres(var peso: Double, var altura: Double): Serializable {

    override fun toString(): String {
        return "Mujeres(altura=$altura, peso=$peso)"
    }

    fun calcularIMCMujeres(): Double{
        return peso/(altura*altura)
    }

    fun calcularEstadoMujeres(): String{
        val imc=calcularIMCMujeres()
        return when {
            imc < 16.5 -> "Bajo peso"
            imc < 22.9 -> "Peso normal"
            imc < 25.9 -> "Sobrepeso"
            imc < 30.9 -> "Obesidad clase 1"
            imc < 33.9 -> "Obesidad clase 2"
            else -> "Obesidad clase 3"
        }

    }
}