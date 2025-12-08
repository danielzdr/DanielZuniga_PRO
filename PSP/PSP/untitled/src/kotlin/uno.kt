

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val process = ProcessBuilder("java", "-cp", "C:\\Users\\Usuario\\Documents\\GitHub\\PSP\\untitled\\out\\production\\untitled").start()
    val linea = process.inputStream.bufferedReader().readLine() ?: ""
    println(linea.uppercase())

}
