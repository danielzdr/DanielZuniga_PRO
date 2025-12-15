#Ejercicio6->Calculo de suma de una fila y columna de una matriz
numero=int(input("Introduce la dimesion de la matriz: "))
matriz=[]
for i in range(numero):
    listaNumeros=[]
    for j in range (numero):
        valor=i+j
        listaNumeros.append(valor)
    matriz.append(listaNumeros)
for i in matriz:
    print(i)

