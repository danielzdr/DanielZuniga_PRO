#Ejercicio1
num1 = int (input("Ingresa el primer numero:"))
num2= int (input("Ingresa el segundo numero:"))
suma = num1+num2
print ("La suma es ",suma)
#Ejercicio2
lado= int (input("Ingresa el lado del cuadrado: "))
area= lado*lado
print("El area del cuadrado es ",area)
#Ejercicio3
temperatura= int (input("Ingresa la temperatura en grados Celcius: "))
fahrenheit = (temperatura * 9/5) + 32
print("La temperatura en grados Fahrenheit es ",fahrenheit)
#Ejercicio4
peso= float (input("Ingresa el peso en kg:"))
altura= float (input("Ingresa la altura en metros: "))
imc= peso / (altura ** 2)
print("El índice de masa corporal (IMC) es ",imc)
#Ejercicio5
cadena1=input("Ingresa la primera cadena de texto: ")
cadena2=input("Ingresa la segunda cadena de texto: ")
concatenacion=cadena1+" "+cadena2
print("La cadena resultante es:",concatenacion)
#Ejercicio6
def determinar_tipo_dato(valor):
    try:
        int_val = int(valor)
        print(f"'{valor}' es de tipo: int")
    except ValueError:
        try:
            float_val = float(valor)
            print(f"'{valor}' es de tipo: float")
        except ValueError:
            if valor.lower() == "true" or valor.lower() == "false":
                print(f"'{valor}' es de tipo: bool")
            else:
                print(f"'{valor}' es de tipo: str")
entrada = input("Ingresa un valor: ")
determinar_tipo_dato(entrada)
#Ejercicio7
num1= int(input("Introduce el primer numero: "))
num2= int(input("Introduce el segundo numero: "))
num3= int(input("Intrduce el tercer numero: "))
promedio= ((num1+num2+num3)/3)
print("El promedio de los tres numeros es: ",promedio)
#Ejercicio8
base=float(input("Ingresa la base del triangulo: "))
altura=float(input("Ingresa la altura del triangulo: "))
area= (base*altura)/2
print("El área del triángulo es: ",area)
#Ejercicio9
edadActual= int (input("Ingresa tu edad actual: "))
aFuturo= int (input("Ingresa la edad futura: "))
edadFutura= edadActual+aFuturo
print("Tu edad en ", aFuturo, "Años sera: ",edadFutura)
#Ejercicio10
lado1= int (input("Ingresa el primer lado del rectangulo: "))
lado2= int (input("Ingresa el segundo lado del rectangulo: "))
area= lado1*lado2
print("El area del rectangulo es ",area)
#Ejercicio11
dia= int(input("Ingresa los numeros de dias:"))
segundos=dia*24*3600
print("El numero de segundos en ", dia ," dias es: ", segundos)
#Ejercicio12
import math
num= int(input("Ingresa un numero: "))
raiz_cuadrada= math.sqrt(num)
print("La raiz cuadrada de ",num,"es: ",raiz_cuadrada)
#Ejercicio13
cambio=float(input("Ingresa la cantidad en dolares: "))
tasa_cambio= float(input("Ingresa la tasa de cambio de dolares a euros:"))
euros=cambio*tasa_cambio
print("La cantidad en euros es: ",euros)
