import java.io.OutputStreamWriter

fun main() {
    val proceso= ProcessBuilder("")
    val procesoArrancado = proceso.start()

    val array = arrayOf("hola","desde","el proceso","padre")

    val out = OutputStreamWriter(procesoArrancado.outputStream)
    out.write(array[0])

    println(array.joinToString {" , "})

}