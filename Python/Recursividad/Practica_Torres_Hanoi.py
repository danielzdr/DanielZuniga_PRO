def hanoi(n, origen, destino, auxiliar, movimientos=None):
    """ Resuelve el problema de las torres de hanoi """
    # Si es la primera llamada, inicializamos la lista de movimientos
    if movimientos is None:
        movimientos = []
    
    # Caso base: si solo hay un disco, lo movemos directamente
    if n == 1:
        movimientos.append(f"mover disco 1 de {origen} a {destino} ")
        return movimientos
    
    #1: Mover n-1 discos de origen a auxiliar, usando destino como auxiliar
    hanoi(n-1, origen, auxiliar, destino, movimientos)
    
    #2: Mover el disco más grande de origen a destino
    movimientos.append(f"Mover disco {n} de {origen} a {destino} ")
    
    #3: Mover los n-1 discos de auxiliar a destino, usando origen como auxiliar
    hanoi(n-1, auxiliar, destino, origen, movimientos)
    return movimientos

def mostrar_movimientos(movimientos):
    """ Muestra la lista de movimientos de forma numerada """
    print(f"\nSecuencia de {len(movimientos)} movimientos: ")
    print("-" * 40)
    for i, movimiento in enumerate(movimientos, 1):
        print(f"{i:3d}. {movimiento}")

def Resol_Problema():
    """ Funcion principal del programa """
    print("=" * 50)
    print("resolvedor torres de hanoi ")
    print(" = " * 50)
    
    # Solicitar número de discos al usuario
    while True:
        try:
            n = int(input("Ingresa el numero de discos: "))
            if n < 1:
                print("Ingresa un número entero ")
            else:
                break
        except ValueError:
            print("Entrada invalida. Por favor, ingresa un numero entero ")
    
    print(f"\nResolviendo Torres de hanoi con {n} discos... ")
    print(f"Torres: A , B , C ")
    
    # Resolver el problema
    movimientos = hanoi(n, 'A', 'C', 'B')
    
    # Mostrar los movimientos
    mostrar_movimientos(movimientos)
    
    # Mostrar información adicional
    print("-" * 40)
    print(f"Numero minimo de movimientos requeridos: {len(movimientos)} ")
    print(f"Formula matematica: 2^{n} - 1 = {2**n - 1} ")
    print(" = " * 50)

if __name__ == " Resol_Problema ":
    Resol_Problema()