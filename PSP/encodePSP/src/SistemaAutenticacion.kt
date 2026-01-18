import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*


class SistemaAutenticacion {
    private val usuarios = mutableMapOf<String, Usuario>()

    data class Usuario(val username: String, val passwordHash: String, val salt: String)

    fun registrarUsuario(username: String, password: String) {
        if (usuarios.containsKey(username)) {
            println("El usuario ya existe")
            return
        }

        val salt = generarSalt()
        val passwordHash = hashPassword(password, salt)
        usuarios[username] = Usuario(username, passwordHash, salt)
        println("Usuario $username registrado exitosamente")
    }

    fun login(username: String, password: String): Boolean {
        val usuario = usuarios[username]
        if (usuario == null) {
            println("Usuario no encontrado")
            return false
        }

        val hashIngresado = hashPassword(password, usuario.salt)
        val esValido = hashIngresado == usuario.passwordHash

        if (esValido) {
            println("Login exitoso para $username")
        } else {
            println("Contraseña incorrecta")
        }

        return esValido
    }

    private fun generarSalt(): String {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return Base64.getEncoder().encodeToString(salt)
    }

    fun hashPassword(password: String, salt: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val saltedPassword = password + salt
        val hash = digest.digest(saltedPassword.toByteArray())
        return hash.joinToString("") { "%02x".format(it) }
    }

    fun mostrarUsuarios() {
        println("\nusuarios")
        usuarios.values.forEach { usuario ->
            println("Usuario: ${usuario.username}")
            println("Hash: ${usuario.passwordHash}")
            println("Salt: ${usuario.salt}")
            println("---")
        }
    }
}


class AtaqueFuerzaBruta {

    fun fuerzaBrutaSimple(hashObjetivo: String, maxLongitud: Int = 4): String? {
        val caracteres = "0123456789" 

        println("\niniciando ataque con fuerza brutal")
        println("Hash objetivo: $hashObjetivo")
        println("Probando combinaciones...")

        for (longitud in 1..maxLongitud) {
            val combinaciones = Math.pow(caracteres.length.toDouble(), longitud.toDouble()).toLong()
            println("Probando longitud $longitud ($combinaciones combinaciones)")

            if (probarCombinaciones("", caracteres, longitud, hashObjetivo)) {
                return "Contraseña encontrada (se detuvo la búsqueda)"
            }
        }

        println("No se encontró la contraseña en el rango probado")
        return null
    }

    private fun probarCombinaciones(prefijo: String, caracteres: String, longitud: Int, hashObjetivo: String): Boolean {
        if (longitud == 0) {
            val hash = hashSHA256(prefijo)
            if (hash == hashObjetivo) {
                println("\ncontraseña encontrada")
                println("Texto original: $prefijo")
                println("Hash: $hash")
                return true
            }
            return false
        }

        for (i in caracteres.indices) {
            if (probarCombinaciones(prefijo + caracteres[i], caracteres, longitud - 1, hashObjetivo)) {
                return true
            }
        }

        return false
    }

    fun fuerzaBrutaConDiccionario(hashObjetivo: String, diccionario: List<String>): String? {
        println("\n=== INICIANDO ATAQUE CON DICCIONARIO ===")

        for (password in diccionario) {
            val hash = hashSHA256(password)
            if (hash == hashObjetivo) {
                println("\ncontraseña encontrada")
                println("Contraseña: $password")
                println("Hash: $hash")
                return password
            }
        }

        println("No se encontró la contraseña en el diccionario")
        return null
    }

    fun hashSHA256(texto: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(texto.toByteArray())
        return hash.joinToString("") { "%02x".format(it) }
    }
}


fun main() {
    val scanner = Scanner(System.`in`)
    val sistema = SistemaAutenticacion()
    val ataque = AtaqueFuerzaBruta()

    println("autenticacion con hash")

    
    sistema.registrarUsuario("admin", "admin123")
    sistema.registrarUsuario("usuario1", "1234")
    sistema.registrarUsuario("test", "password")

    sistema.mostrarUsuarios()

    
    println("\nLOGIN ")
    sistema.login("admin", "admin123")  
    sistema.login("admin", "wrongpass")  

    

    
    val hashConocido = "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4"

    println("Hash de ejemplo para ataque: $hashConocido")
    println("(Este es el hash de '1234')")

    print("\n¿Deseas ejecutar un ataque de fuerza bruta? (s/n): ")
    val respuesta = scanner.nextLine()

    if (respuesta.equals("s", ignoreCase = true)) {
        println("\n1. Ataque de fuerza bruta simple (solo números)")
        println("2. Ataque con diccionario")
        print("Selecciona una opción: ")

        when (scanner.nextLine()) {
            "1" -> {
                print("Longitud máxima a probar (recomendado 4): ")
                val longitud = scanner.nextLine().toIntOrNull() ?: 4
                ataque.fuerzaBrutaSimple(hashConocido, longitud)
            }
            "2" -> {
                val diccionario = listOf(
                    "password", "123456", "admin", "1234", "test",
                    "qwerty", "password123", "admin123", "letmein"
                )
                println("Diccionario: ${diccionario.joinToString(", ")}")
                ataque.fuerzaBrutaConDiccionario(hashConocido, diccionario)
            }
        }
    }
    
    println("\n hash")
    print("Ingresa una palabra para generar su hash SHA-256: ")
    val palabra = scanner.nextLine()

    val hash = ataque.hashSHA256(palabra)
    println("Hash SHA-256 de '$palabra':")
    println(hash)

    scanner.close()
}

fun hashSimple(palabra: String): String {
    val instancia = MessageDigest.getInstance("SHA-256")
    val hash = instancia.digest(palabra.toByteArray())
    return hash.joinToString("") { "%02x".format(it) }
}