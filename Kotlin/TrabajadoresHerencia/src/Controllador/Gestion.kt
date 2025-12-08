package Controllador
import model.Persona
import model.Trabajador
import model.Asalariado
import model.Autonomos
import model.Jefes
import model.Directivo
import model.Sindicato

class Gestion {
        private val trabajadores = mutableListOf<Persona>()

        init {
            trabajadores.add(Asalariado("Alvaro", "Cano", "12345A", 3000.0, 12, 21.0))
            trabajadores.add(Autonomos("Pablo", "Soriano", "12345A", 3000.0, 12, true))
            trabajadores.add(Jefes("Daniel", "Zuñiga", "12345B", 30000.0, 4))
            trabajadores.add(Directivo("Ruben", "Parra", "09876S", 666666, "Ruben.parra@gmail.com", 5))
        }

        fun añadirTrabajador() {
            println("Introduce tipo de trabajador (asalariado / autonomo / jefe / directivo):")
            val tipo= readLine()!!.toString()

            println("Nombre:")
            val nombre = readLine()!!
            println("Apellidos:")
            val apellidos = readLine()!!
            println("DNI:")
            val dni = readLine()!!
            println("Salario:")
            val salario = readLine()!!.toDouble()

            when (tipo) {
                "asalariado" -> {
                    println("Pagas:")
                    val nPagas = readLine()!!.toInt()
                    println("IRPF:")
                    val irpf = readLine()!!.toDouble()
                    trabajadores.add(Asalariado(nombre, apellidos, dni, salario, nPagas, irpf))
                }
                "autonomo" -> {
                    println("Pagas:")
                    val nPagas = readLine()!!.toInt()
                    println("Tiene descuento? (true/false):")
                    val descuento = readLine()!!.toBoolean()
                    trabajadores.add(Autonomos(nombre, apellidos, dni, salario, nPagas, descuento))
                }
                "jefe" -> {
                    println("Nivel de responsabilidad")
                    val nivel = readLine()!!.toInt()
                    trabajadores.add(Jefes(nombre, apellidos, dni, salario, nivel))
                }
                "directivo" -> {
                    println("Email:")
                    val email = readLine()!!
                    println("Numero de acciones")
                    val nAcciones = readLine()!!.toInt()
                    trabajadores.add(Directivo(nombre, apellidos, dni, 12,email, nAcciones))
                }
                else -> println("Tipo no válido. Intentalo de nuevo")
            }

            println("$tipo añadida correctamente.")
        }

        fun eliminarTrabajador() {
            println("Introduce el DNI del trabajador a eliminar:")
            val dni = readLine()!!
            if (trabajadores.removeIf { it.dni == dni }) {
                println("Trabajador eliminado.")
            } else {
                println("No se encontró el trabajador.")
            }
        }

        fun listarTrabajadores() {
            println("¿Qué tipo listar? (asalariado / autonomo / jefe / todos):")
            val tipo = readLine()!!.trim().lowercase()

            val lista = when (tipo) {
                "asalariado" -> trabajadores.filter{it is Asalariado}
                "autonomo" -> trabajadores.filter{it is Autonomos}
                "jefe" -> trabajadores.filter{it is Jefes}
                "todos" -> trabajadores
                else -> {
                    println("Tipo no válido."); return
                }
            }

            if (lista.isEmpty()) {
                println("No hay trabajadores de este tipo.")
            } else {
                lista.forEach { it.mostrarDatos() }
            }
        }

        fun calcularSalarios() {
            for (t in trabajadores) {
                if (t is Trabajador) {
                    println("Salario de ${t.nombre}: ${t.calcularSalario()}")
                }
            }
        }

    fun bajarSueldos() {
        for (it in trabajadores) {
            if (it is Sindicato) {
                val lista = ArrayList(trabajadores.filterIsInstance<Trabajador>())
                it.bajarSueldos(lista)
            }
        }
        println("Sueldos bajados donde era posible.")
    }

}




