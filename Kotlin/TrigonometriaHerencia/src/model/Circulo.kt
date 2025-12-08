package model

class Circulo(var radio: Double, nombre: String) : Figura(nombre){


    override fun calcularArea(): Double {
        var area= 3.14*radio*2
        return area
    }

     fun calcularDiametro(): Double {
        return 2*radio
    }
}