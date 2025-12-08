import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.random.Random

fun main() {
    val proceso= ProcessBuilder("C:\\Users\\Usuario\\.jdks\\openjdk-23.0.1\\bin\\java.exe\" \"-javaagent:C:\\Program Files\\JetBrains\\IntelliJ IDEA Community Edition 2024.2.1\\lib\\idea_rt.jar=50099:C:\\Program Files\\JetBrains\\" +
            "IntelliJ IDEA Community Edition 2024.2.1\\bin\" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath C:\\Users\\Usuario\\Documents\\GitHub\\PSP\\ProyectoPadreHijo\\out\\production\\ProyectoPadreHijo;" +
            "C:\\Users\\Usuario\\.m2\\repository\\org\\jetbrains\\kotlin\\kotlin-stdlib\\2.0.10\\kotlin-stdlib-2.0.10.jar;C:\\Users\\Usuario" +
            "\\.m2\\repository\\org\\jetbrains\\annotations\\13.0\\annotations-13.0.jar HijoKt").start()

    val numeroAleatorio=Random.nextInt(1,51)
    println("Generando el numero aleatorio entre 1 y 50: $numeroAleatorio")

    val out = OutputStreamWriter(proceso.outputStream)
    out.write("$numeroAleatorio\n")
    out.flush()

    //Para leer la respuesta del hijo
    val input=BufferedReader(InputStreamReader(proceso.inputStream))
    var linea: String?
    while (input.readLine().also { linea = it } !=null){//devolver el mismo objeto sin modificar
        println("Padre recibe del hijo: $linea")
        if (linea!!.contains("Numero acertado" ,ignoreCase = true))//comprueba si dentro de una cadena hay otra subcadena
            break
    }
    out.close()
    input.close()

}