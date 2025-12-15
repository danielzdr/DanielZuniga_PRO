#Ejericicio 1: Tablas de multiplicar
tablasMultiplicar=[1,10]
for i in range(tablasMultiplicar[0],tablasMultiplicar[1]+1):
    print(f"Tabla del {i}: ")
    for j in range(1,11):
        print(f"{i} x {j} = {i*j} ")
    print("----------------")
