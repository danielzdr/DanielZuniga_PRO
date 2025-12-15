#Ejercicio 8->Suma diagonales de una matriz Predefinida
matriz = [
    [1, 2, 3, 4, 5],
    [6, 7, 8, 9, 10],
    [11, 12, 13, 14, 15],
    [16, 17, 18, 19, 20],
    [21, 22, 23, 24, 25]
]

# Mostrar matriz
print("Matriz:")
for fila in matriz:
    for elemento in fila:
        print(f"{elemento:2d}", end=" ")
    print()

# Preguntar al usuario sobre el elemento central
opcion = input("\n¿Contar el elemento central en ambas diagonales? (s/n): ")

# Sumar diagonal principal (de izquierda a derecha)
suma_principal = 0
for i in range(5):
    suma_principal += matriz[i][i]

# Sumar diagonal secundaria (de derecha a izquierda)
suma_secundaria = 0
for i in range(5):
    suma_secundaria += matriz[i][4 - i]

# Calcular suma total según la opción del usuario
if opcion.lower() == 's':
    suma_total = suma_principal + suma_secundaria
    print(f"\nSuma diagonal principal: {suma_principal}")
    print(f"Suma diagonal secundaria: {suma_secundaria}")
    print(f"Suma TOTAL (con elemento central repetido): {suma_total}")
else:
    # Restar el elemento central que se contó dos veces
    elemento_central = matriz[2][2]
    suma_total = suma_principal + suma_secundaria - elemento_central
    print(f"\nSuma diagonal principal: {suma_principal}")
    print(f"Suma diagonal secundaria: {suma_secundaria}")
    print(f"Elemento central: {elemento_central}")
    print(f"Suma TOTAL (sin repetir elemento central): {suma_total}")
