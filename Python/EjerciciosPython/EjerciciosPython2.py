#Ejercicios python 2
#Ejercicio1
suma= int(0)
for numero in range(1,11):
    suma+=numero
    print(f"La suma es: {suma}")

#Ejercicio2
numero= int (input("Ingresa un numero: "))
factorial=int(1)
for i in range(1,numero+1):
    factorial=factorial*i
    print("El factorial es: ",factorial)

#Ejercicio3
suma=int(0)
for numero in range(1,101):
    if numero%2==0:
        suma+=numero
        print("La suma de los numeros pares es: ",suma)

#Ejercicio4
base = float(input("Ingresa la base del triangulo:"))
altura= float(input("Ingresa la altura del triangulo:"))
if base<=0 or altura <=0:
    print("La base y la altura deben ser positivos")
else:
    area=(base*altura/2)
    areaRedondeada=round(area,2)
    print("El area del triangulo es: ",areaRedondeada)
 
 #Ejercicio5
for numero in range(1,101):
    if numero %3==0:
        print("Triplete")
    elif numero %5==0:
        print ("Cinquillo")
    else:
        print("No es multiplo de 3 ni de 5")

#Ejercicio6
import random
numeroAleatorio=random.randint(1,100)
intentos=2
while intentos>0:
    numeroUsuario=int(input("Adivina el numero entre 1 y 100:"))
    if numeroUsuario==numeroAleatorio:
        print("Felicidades, adivinaste el numero" )
        break
    elif numeroUsuario<numeroAleatorio:
        print("El numero es mayor")
    else:
        print("El numero es menor")
    intentos-=1
if intentos==0:
    print("Lo siento, no adivinaste el numero. El numero era:", numeroAleatorio)

#Ejercicio7
radio=float(input("Ingresa el radio de un circulo:"))
if radio<=0:
    print("El radio es positivo")
else:
    import math
    area= math.pi*radio**2
    areaRedondeada=round(area,2)
    print("El area del circulo es: ",areaRedondeada)

#Ejercicio8
numero= int(input("Ingresa un numero :"))
for num in range(1,11):
    tabla= numero*num
    print(f"{numero} x {num}= {tabla}")

#Ejercicio9
suma=int(0)
for numero in range(1,101):
    if numero%2!=0:
        suma+=numero
        print("La suma de los numeros impares es: ",suma)

 #Ejercicio10
cadena= str(input("Introduce una cadena de texto:"))
cuentaLetra= str(input("Introduce una letra para contar:"))
contador=0
for letra in cadena:
     if letra==cuentaLetra:
         contador+=1
print("La letra",cuentaLetra,"aparece",contador,"veces en la cadena.")
