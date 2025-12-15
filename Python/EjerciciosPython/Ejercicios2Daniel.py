#Ejercicio1
suma= int(0)
for numero in range(1,11):
    suma+=numero
print(f"La suma es: {suma}")

#Ejericio2
numero= int (input("Ingresa un numero: "))
factorial=int(1)
for i in range (1,numero+1):
    factorial*=i
    print("El factorial es: ",factorial)

#Ejercicio3
numerosPrimos=int(0)
for numero in range (1,51):
    if numero%2!=0 and numero%3!=0 and numero%5!=0 and numero%7!=0 or numero==2 or numero ==3 or numero ==5 or numero == 7:
        numerosPrimos+=1
        print ("Los numeros primos son: ",numero)
        print("La cantidad de numeros primos son: ",numerosPrimos)

#Ejericcio4
num = int(input("Ingresa un numero: "))
suma = 0
for i in str(num):
    suma += int(i)
print("La suma de los digitos es:", suma)

#Ejercicio5
suma=int(0)
for numero in range(1,101):
    if numero%2==0:
        suma+=numero
print("La suma de los numeros pares es: ",suma)


#Ejercicio6
base= float(input("Ingresa la base de un trianngulo: "))
altura= float(input("Ingresa la altura de un triangulo: "))
area=(base*altura)/2
print("El area del triangulo es: ",area)


#Ejercicio7
for numero in range(1, 101):
    if numero % 3 == 0 and numero % 5 == 0:
        print("FizzBuzz")
    elif numero % 3 == 0:
        print("Fizz")
    elif numero % 5 == 0:
        print("Buzz")
    else:
        print(numero)
#Ejercicio8
import random
numeroAleatorio=random.randint(1,101)
intentos=3
while intentos>True:
    numeroUsuario=int(input("Adivina el numero entre el 1 y el 100:"))
    if numeroUsuario==numeroAleatorio:
        print("Felicidades, adivinaste el numero")
        break
    elif numeroUsuario<numeroAleatorio:
        print("El numero es mayor")
        intentos-=1
    else:
        print("El numero es menor")
        intentos-=1

#Ejercicio9
numeros=[]
cantidad= int(input("Ingresa la cantidad de numeros que quieres ingresar: "))
for i in range(cantidad):
    numero= int (input("Ingresa un numero: "))
    numeros.append(numero)
    promedio=sum(numeros)/len(numeros)
    print("Numeros ingresados: ",numeros)
    print("El promedio es: ",promedio)

#Ejercicio10
cadena = input("Introduce una cadena de texto: ")
vocales = "a e i o u"
contador = 0
for letra in cadena.lower():
    if letra in vocales:
        contador += 1
print("Cantidad de vocales:", contador)

#Ejercicio11
palabra= str(input("Ingresa una palabra: "))
for letra in palabra:
    print(letra)
    print("La palabra invertida es: ",palabra[::-1])

#Ejercicio12
radio=float(input("Ingresa el radio de un circulo: "))
if radio>=0:
    print("El radio es positivo")
else:
    area= 3.14*radio**2
    print("El area del circulo es: ",area)

#Ejercicio13
numero= int(input("Ingresa un numero :"))
for num in range(1,11):
    tabla= numero*num
    print(f"{numero} x {num}= {tabla}")

#Ejercicio14
suma=int(0)
for numero in range(1,101):
    if numero%2!=0:
        suma+=numero
        print("La suma de los numeros impares es: ",suma)
 
#Ejercicio15
cadena= str(input("Introduce una cadena de texto: "))
cuentaLetra= str(input("Introduce una letra para contar: "))
contador=0
for letra in cadena:
     if letra==cuentaLetra:
         contador+=1
print("La letra",cuentaLetra,"aparece",contador,"veces en la cadena.")

#Ejercicio16
num1= int(input("Ingresa el primer numero: "))
num2= int(input("Ingresa el segundo numero: "))
while num2!=0:
    resto= num1%num2
    num1=num2
    num2=resto
    print(f"El maximo comun divisor de {num1} y {num2} es: {num1}")

#Ejercicio18
intentos=3
while True:
    print("Juego de Pidra papel o tijeras")
    print("Piedra")
    print("Papel")
    print("Tijeras")
    print("Salir del juego")

    JuegoPPT=str(input("Piedra , papel o tijeras: "))

    if JuegoPPT=="piedra":
        print("Empate")
        intentos-=1
    elif JuegoPPT=="papel":
        print("Has ganado")
    elif JuegoPPT=="tijeras":
        print("Has perdido")
        intentos-=1
    elif JuegoPPT=="salir":
        print("Gracias por jugar")
        break

    else:
        print("Opcion no valida. Intentalo de nuevo")

    

