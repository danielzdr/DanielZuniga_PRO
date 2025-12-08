package model

class Rectangulo(nombre:String,var base:Double, var altura:Double): Figura(nombre) {

    override fun calcularArea(): Double {
        return base*altura
    }

     fun calcularPerimetro(): Double {
        return 2*(base+altura)
    }
}