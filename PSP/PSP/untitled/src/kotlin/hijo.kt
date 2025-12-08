import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val input = BufferedReader (InputStreamReader(System.`in`))
    val output = OutputStreamWriter(System.out)
    output.write(input.readLine().uppercase())
    //para limpiar o vaciar el buffer de trozos de procesos de lo anterior
    output.flush()
}