fun main(args :Array<String>) {
    val nombre = args[0]
    val proceso = ProcessBuilder(nombre)
    proceso.start()
    println("Se ha ejecutado $nombre")
}