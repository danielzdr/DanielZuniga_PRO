package model

class Triangulo (nombre:String,var base:Double,var altura:Double): Figura( nombre){

    override fun calcularArea(): Double {
       return (base*altura)/2

    }



}