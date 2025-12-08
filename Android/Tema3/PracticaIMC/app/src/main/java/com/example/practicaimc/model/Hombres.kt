package com.example.practicaimc.model

import java.io.Serializable

class Hombres(var peso: Double,var altura: Double): Serializable {
    override fun toString(): String {
        return "Hombres(altura=$altura, peso=$peso)"
    }

    fun calcularIMCHombres(): Double{
        return peso/(altura*altura)
    }

    fun calcularEstadoHombres(): String{
        val imc= calcularIMCHombres()
        return when{
            imc < 18.5 -> "Bajo peso"
            imc < 24.9 -> "Peso normal"
            imc < 29.9 -> "Sobrepeso"
            imc < 34.9 -> "Obesidad clase 1"
            imc < 39.9 -> "Obesidad clase 2"
            else -> "Obesidad clase 3"
        }

    }
}