package model

class Directivo(nombre: String, apellido: String, dni: String, telefono: Int, correo: String, var nAcciones: Int) :
    Persona(nombre, apellido, dni, telefono, correo), Sindicato {

    override fun mostrarDatos() {
        super.mostrarDatos()
        println("Número de acciones: $nAcciones")
    }

    fun venderAcciones(cant: Int) {
        if (cant > nAcciones) println("No puedes vender tantas acciones")
        else {
            nAcciones -= cant
            println("Acciones actualizadas: $nAcciones")
        }
    }

    override fun bajarSueldos(lista: ArrayList<Trabajador>): Boolean {
        lista.forEach {
            it.salario *= if (it is Jefes) 0.9 else 0.8
        }
        return true
    }

    override fun calcularBeneficios(): Double {
        println("Calculo de beneficios de directivo")
        return 0.0
    }
}
