#Ejercicio5->Suma de matrices

numero = int(input("Introduce el tamaño de las matrices cuadradas (numero): "))

print("\nIntroduce los elementos de la Matriz 1:")
matriz1 = []
for i in range(numero):
    fila = []
    for j in range(numero):
        elemento = int(input(f"Elemento [{i+1}][{j+1}]: "))
        fila.append(elemento)
    matriz1.append(fila)

print("\nIntroduce los elementos de la Matriz 2:")
matriz2 = []
for i in range(numero):
    fila = []
    for j in range(numero):
        elemento = int(input(f"Elemento [{i+1}][{j+1}]: "))
        fila.append(elemento)
    matriz2.append(fila)

matriz_suma = []
for i in range(numero):
    fila_suma = []
    for j in range(numero):
        suma = matriz1[i][j] + matriz2[i][j]
        fila_suma.append(suma)
    matriz_suma.append(fila_suma)

print("\n=== RESULTADOS ===")
print("\nMatriz 1:")
for i in range(numero):
    for j in range(numero):
        print(matriz1[i][j], end=" ")
    print()

print("\nMatriz 2:")
for i in range(numero):
    for j in range(numero):
        print(matriz2[i][j], end=" ")
    print()

print("\nMatriz Suma:")
for i in range(numero):
    for j in range(numero):
        print(matriz_suma[i][j], end=" ")
    print()

print("\nProceso de suma:")
for i in range(numero):
    for j in range(numero):
        print(f"{matriz1[i][j]} + {matriz2[i][j]} = {matriz_suma[i][j]}")