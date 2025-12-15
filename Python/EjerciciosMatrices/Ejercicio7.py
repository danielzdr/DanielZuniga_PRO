#Ejercicio7->Dibujo de un rombo con asteriscos
while True:
    altura=int(input("Introduce la altura del rombo: "))
    if altura %2==0:
        break
    else:
        print("Error, la altura debe ser un numero impar. Intentalo de nuevo. ")

mitad=altura//2
for i in range(mitad+1):
    espacios=mitad-i
    arteriscos= 2*i+1
    print(" "*espacios+ "*"*arteriscos)
for i in range (mitad-1,-1,-1):#disminuir espacios y aumentar asteriscos
    espacios=mitad-i
    arteriscos= 2*i+1
    print(" "*espacios+ "*"*arteriscos)
    