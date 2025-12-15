from colorama import Fore, Style
import math


def suma(a, b):
    return a + b

def resta(a, b):
    return a - b

def multiplicacion(a, b):
    return a * b

def division(a, b):
    if b == 0:
        return "Error: No se puede dividir entre cero."
    return a / b

def valorAbsoluto(a, b):
    return abs(a - b)

def tangente(a):
    return math.tan(a)

def seno(a):
    return math.sin(a)

def coseno(a):
    return math.cos(a)

def potencia(a, b):
    return a ** b

def logaritmoBASE10(a):
    if a <= 0:
        return "Error: El logaritmo no está definido para números <= 0."
    return math.log10(a)

def logaritmoNeperiano(a):
    if a <= 0:
        return "Error: El logaritmo no está definido para números <= 0."
    return math.log(a)

def eElevadoX(a):
    return math.exp(a)

def factorial(a):
    if a < 0 or int(a) != a:
        return "Error: El factorial solo está definido para enteros no negativos."
    return math.factorial(int(a))

def raizCuadrada(a):
    if a < 0:
        return "Error: No se puede calcular la raíz cuadrada de un número negativo."
    return math.sqrt(a)

password_correcta = "1234"
intentos = 3

def pedirContrasena():
    """Pide la contraseña hasta 3 intentos."""
    global intentos
    while intentos > 0:
        contrasena_usuario = input("🔐 Introduce la contraseña: ")
        if contrasena_usuario == password_correcta:
            return True
        else:
            intentos -= 1
            print(Fore.RED + f"Contraseña incorrecta. Te quedan {intentos} intentos ❌" + Style.RESET_ALL)
    print(Fore.RED + "Se han agotado los intentos. Cerrando programa." + Style.RESET_ALL)
    return False

def mostrarMenu():
    print(Fore.CYAN + "\n----- Calculadora -----" + Style.RESET_ALL)
    print("1️⃣  Suma")
    print("2️⃣  Resta")
    print("3️⃣  Multiplicación")
    print("4️⃣  División")
    print("5️⃣  Valor absoluto (a-b)")
    print("6️⃣  Seno")
    print("7️⃣  Coseno")
    print("8️⃣  Tangente")
    print("9️⃣  Potencia (a^b)")
    print("🔟 Logaritmo base 10")
    print("1️⃣1️⃣ Logaritmo natural")
    print("1️⃣2️⃣ E elevado a X")
    print("1️⃣3️⃣ Factorial")
    print("1️⃣4️⃣ Raíz cuadrada")
    print("1️⃣5️⃣  Salir")


def calculadora():
    while True:
        mostrarMenu()
        opcion = input("Elige una opción del 1 al 15: ")

        if opcion == "15":
            print("Hasta luego 👋")
            break

        if not pedirContrasena():
            break

        # Pedir números según la operación
        if opcion in ["1","2","3","4","5","9"]:
            a = float(input("Introduce el primer número: "))
            b = float(input("Introduce el segundo número: "))
        elif opcion in ["6","7","8","10","11","12","13","14"]:
            a = float(input("Introduce el número: "))
            b = None
        else:
            print(Fore.RED + "Opción no válida ❌" + Style.RESET_ALL)
            continue

        # Realizar cálculo según opción
        if opcion == "1":
            print("Resultado:", suma(a, b))
        elif opcion == "2":
            print("Resultado:", resta(a, b))
        elif opcion == "3":
            print("Resultado:", multiplicacion(a, b))
        elif opcion == "4":
            print("Resultado:", division(a, b))
        elif opcion == "5":
            print("Resultado:", valorAbsoluto(a, b))
        elif opcion == "6":
            print("Resultado:", seno(a))
        elif opcion == "7":
            print("Resultado:", coseno(a))
        elif opcion == "8":
            print("Resultado:", tangente(a))
        elif opcion == "9":
            print("Resultado:", potencia(a, b))
        elif opcion == "10":
            print("Resultado:", logaritmoBASE10(a))
        elif opcion == "11":
            print("Resultado:", logaritmoNeperiano(a))
        elif opcion == "12":
            print("Resultado:", eElevadoX(a))
        elif opcion == "13":
            print("Resultado:", factorial(a))
        elif opcion == "14":
            print("Resultado:", raizCuadrada(a))


calculadora()