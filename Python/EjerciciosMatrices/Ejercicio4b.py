#Ejercicio 4b->Modificacion C
matriz=[]
print(f"\nIntroduce los elementos de la matriz {matriz}x matriz: ")
for i in range(matriz):
    fila=[]
    for j in range(matriz):
        elemento=int(input(f"Elemento [{i}][{j}]: "))
        fila.append(elemento)
        matriz.append(fila)

print("\nLa matriz es: ")
for i in range(matriz):
    for j in range (matriz):
        print(matriz[i],[j],end=" ")
        print()
