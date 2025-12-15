#Ejercicio9-> Sumar diagonales de matriz sin esta definida
# Solicitar tamaño de la matriz
n = int(input("Introduce el tamaño de la matriz cuadrada: "))

# Crear matriz ingresando datos por teclado
matriz = []
print(f"\nIntroduce los elementos de la matriz {n}x{n}:")
for i in range(n):
    fila = []
    for j in range(n):
        elemento = int(input(f"Elemento [{i+1}][{j+1}]: "))
        fila.append(elemento)
    matriz.append(fila)

# Mostrar matriz
print("\nMatriz ingresada:")
for i in range(n):
    for j in range(n):
        print(f"{matriz[i][j]:2d}", end=" ")
    print()

# Preguntar sobre el elemento central
if n % 2 == 1:  # Solo preguntar si hay elemento central (matriz impar)
    opcion = input("\n¿Contar el elemento central en ambas diagonales? (s/n): ")
else:
    opcion = 's'  # Si es par, no hay elemento central que se repita

# Sumar diagonal principal
suma_principal = 0
for i in range(n):
    suma_principal += matriz[i][i]

# Sumar diagonal secundaria
suma_secundaria = 0
for i in range(n):
    suma_secundaria += matriz[i][n - 1 - i]

# Calcular suma total
if opcion.lower() == 's' or n % 2 == 0:
    suma_total = suma_principal + suma_secundaria
    print(f"\nSuma diagonal principal: {suma_principal}")
    print(f"Suma diagonal secundaria: {suma_secundaria}")
    print(f"Suma TOTAL: {suma_total}")
else:
    # Restar el elemento central que se contó dos veces
    centro = n // 2
    elemento_central = matriz[centro][centro]
    suma_total = suma_principal + suma_secundaria - elemento_central
    print(f"\nSuma diagonal principal: {suma_principal}")
    print(f"Suma diagonal secundaria: {suma_secundaria}")
    print(f"Elemento central: {elemento_central}")
    print(f"Suma TOTAL (sin repetir elemento central): {suma_total}")