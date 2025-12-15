#Ejercicio5
def tupla():
    print("Introduce tres valores para añadir a la tupla:")
    x = input("Valor 1: ")
    y = input("Valor 2: ")
    z = input("Valor 3: ")
    
    # Convertir a números si es posible
    def convertir_valor(valor):
        if valor.isdigit():
            return int(valor)
        try:
            return float(valor)
        except ValueError:
            return valor
    
    x = convertir_valor(x)
    y = convertir_valor(y)
    z = convertir_valor(z)
    
    nueva_tupla = (x, y, z)
    print("Tupla resultante:", nueva_tupla)
    return nueva_tupla

tupla_ej5 = tupla()
#Ejercicio6
def tupla_letra_numero():
    while True:
        letra = input("Introduce una letra: ")
        if len(letra) == 1 and letra.isalpha():
            break
        print("Por favor, introduce solo una letra")
    
    while True:
        numero = input("Introduce un número: ")
        if numero.isdigit() or (numero.replace('.', '', 1).isdigit() and numero.count('.') <= 1):
            break
        print("Por favor, introduce un número válido")
    
    # Convertir número
    if '.' in numero:
        numero = float(numero)
    else:
        numero = int(numero)
    
    mi_tupla = (letra, numero)
    print("Tupla resultante:", mi_tupla)
    return mi_tupla

tupla_ej6 = tupla_letra_numero()
#Ejercicio7
def crear_tupla_pares():
    pares = []
    
    for i in range(3):
        print(f"\nPar {i+1}:")
        while True:
            letra = input("Introduce una letra: ")
            if len(letra) == 1 and letra.isalpha():
                break
            print("Por favor, introduce solo una letra")
        
        while True:
            numero = input("Introduce un número: ")
            if numero.isdigit() or (numero.replace('.', '', 1).isdigit() and numero.count('.') <= 1):
                break
            print("Por favor, introduce un número válido")
        
        # Convertir número
        if '.' in numero:
            numero = float(numero)
        else:
            numero = int(numero)
        
        pares.append((letra, numero))
    
    mi_tupla = tuple(pares)
    
    print("\nResultado:")
    for letra, numero in mi_tupla:
        print(f"el valor {letra} vale {numero}")
    
    return mi_tupla

tupla_ej7 = crear_tupla_pares()

def desempaquetar_tupla(tupla):
    (a, x), (b, y), (c, z) = tupla
    print(f"El valor {a} vale {x}")
    print(f"El valor {b} vale {y}")
    print(f"El valor {c} vale {z}")

print("Desempaquetando la tupla del ejercicio 7:")
desempaquetar_tupla(tupla_ej7)