#Ejercicio1
mi_tupla=(1,2,3,"cuatro","cinco")
print(mi_tupla)
#Ejercicio2
mi_tupla=(1,2,3,"cuatro","cinco")
for i in range(len(mi_tupla)):
    print(mi_tupla[i])

#Ejercicio3
print(f"'cuatro' está en la tupla: {'cuatro' in mi_tupla}")
print(f"'2' está en la tupla: {'2' in mi_tupla}")
print(f"2 está en la tupla: {2 in mi_tupla}")

#Ejercicio4
def buscar_en_tupla(tupla):
    elemento = input("Introduce el elemento a buscar: ")
    
    # Si es un dígito, convertirlo a entero para buscar como número
    if elemento.isdigit():
        elemento_int = int(elemento)
        if elemento_int in tupla:
            print(f"El número {elemento_int} está en la tupla")
            return
        elif elemento in tupla:
            print(f"La cadena '{elemento}' está en la tupla")
            return
    
    # Buscar como cadena
    if elemento in tupla:
        print(f"'{elemento}' está en la tupla")
    else:
        print(f"'{elemento}' no está en la tupla")

buscar_en_tupla(mi_tupla)
