package model
import kotlin.random.Random


class Jefes(nombre: String, apellido: String, dni: String, salario: Double, var nivelResponsabilidad: Int) :
    Trabajador(nombre, apellido, dni, salario), Sindicato {

    override fun calcularSalario(): Double {
        nivelResponsabilidad = Random.nextInt(1, 6)
        salario *= if (nivelResponsabilidad <= 3) 0.97 else 1.03
        return salario
    }

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("Nivel de responsabilidad: $nivelResponsabilidad")
    }

    fun aumentarResponsabilidad() {
        if (nivelResponsabilidad < 5) nivelResponsabilidad++
        println("Nivel de responsabilidad: $nivelResponsabilidad")
    }

    fun disminuirResponsabilidad() {
        if (nivelResponsabilidad > 1) nivelResponsabilidad--
        println("Nivel de responsabilidad: $nivelResponsabilidad")
    }

    override fun bajarSueldos(lista: ArrayList<Trabajador>): Boolean {
        lista.forEach { if (it !is Jefes) it.salario *= 0.90 }
        println("Sueldos bajados donde era posible")
        return true
    }

    override fun calcularBeneficios(): Double {
        println("Calculo de beneficios de jefe")
        return 0.0
    }
}
