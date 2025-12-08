package model

class Autonomos(nombre: String, apellido: String, dni: String, salario: Double, var cuotasSS: Int, var seguro: Boolean) :
    Trabajador(nombre, apellido, dni, salario) {

    override fun calcularSalario(): Double {
        return salario - (cuotasSS * 12)
    }

    fun pedirDescuento(precioDes: Double) {
        if (seguro) {
            cuotasSS = (cuotasSS - cuotasSS * precioDes).toInt()
        } else {
            println("No ha sido posible aplicar descuento porque no hay seguro")
        }
    }

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("Cuotas SS: $cuotasSS")
    }
}
