#Ejercicio4->Modificacion a y b
matriz=int(input("Introduce el tamaño de la matriz: "))
numeroImpar=1
print(f"Matriz de numeros impares{matriz}x{matriz} es: ")
for i in range(matriz):
    for j in range(matriz):
        print(numeroImpar, end=" ")
        numeroImpar+=2
        print()