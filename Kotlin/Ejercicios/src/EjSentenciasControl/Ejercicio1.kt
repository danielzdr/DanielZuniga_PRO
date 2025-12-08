package EjSentenciasControl

//Dada una hora del día y el día
// de la semana, calcula el precio
// por kWh según tramos (valle, llano, punta)
// y festivos. Usa when con rangos y múltiples condiciones
// compuestas. Devuelve además un texto explicativo del
// tramo aplicado.
fun main() {
    val hora: Int= readLine()!!.toInt()
    when(hora){
         in 0 .. 5->{
            println("De madrugada")
        }
        in 6..11->{
            println("Por la mañana")
        }
        in 12..17->{
            println("Por la tarde")
        }
        in 17 .. 21->{
            println("Por la tarde noche")
        }
        in 22 .. 24->{
            println("De noche")
        }
        else->{
            println("Se acabo los tramos del dia")
        }

    }
    println(hora)
}