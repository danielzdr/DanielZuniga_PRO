import os
import pickle
from datetime import datetime

# Definición de nodo para lista enlazada
class Nodo:
    def __init__(self, dato):
        self.dato = dato
        self.siguiente = None

# Funciones básicas de la pila
def crear_pila():
    """Crea una pila vacía"""
    return None

def pila_vacia(pila):
    """Verifica si la pila está vacía"""
    return pila is None

def apilar(pila, dato):
    """Apila un nuevo elemento"""
    nuevo_nodo = Nodo(dato)
    nuevo_nodo.siguiente = pila
    return nuevo_nodo

def desapilar(pila):
    """Desapila el elemento superior"""
    if pila_vacia(pila):
        print("Error: Pila vacía")
        return pila, None
    
    dato = pila.dato
    pila = pila.siguiente
    return pila, dato

def tope(pila):
    """Devuelve el elemento en el tope sin desapilar"""
    if pila_vacia(pila):
        print("Pila vacía")
        return None
    return pila.dato

# Funciones adicionales
def imprimir_pila(pila):
    """Muestra todos los elementos de la pila"""
    if pila_vacia(pila):
        print("Pila vacía")
        return
    
    print("\nElementos de la pila (desde el tope hasta la base):")
    print("=" * 40)
    actual = pila
    i = 1
    while actual is not None:
        print(f"{i}. {actual.dato}")
        actual = actual.siguiente
        i += 1
    print("=" * 40)

def contar_elementos(pila):
    """Cuenta el número de elementos en la pila"""
    contador = 0
    actual = pila
    while actual is not None:
        contador += 1
        actual = actual.siguiente
    return contador

def invertir_pila(pila):
    """Crea una nueva pila con los elementos en orden inverso"""
    pila_invertida = crear_pila()
    actual = pila
    
    while actual is not None:
        pila_invertida = apilar(pila_invertida, actual.dato)
        actual = actual.siguiente
    
    return pila_invertida

def copiar_pila(pila):
    """Copia exacta de una pila manteniendo el mismo orden"""
    # Primero invertimos la pila original
    temp_pila = crear_pila()
    actual = pila
    
    while actual is not None:
        temp_pila = apilar(temp_pila, actual.dato)
        actual = actual.siguiente
    
    # Luego invertimos la pila temporal para obtener la copia
    pila_copia = crear_pila()
    actual = temp_pila
    
    while actual is not None:
        pila_copia = apilar(pila_copia, actual.dato)
        actual = actual.siguiente
    
    return pila_copia

def vaciar_pila(pila):
    """Vacía completamente una pila"""
    return crear_pila()

def guardar_pila(pila, nombre_archivo="pila_marcas.pkl"):
    """Guarda la pila en un archivo"""
    try:
        # Convertir pila a lista para guardar
        elementos = []
        actual = pila
        while actual is not None:
            elementos.append(actual.dato)
            actual = actual.siguiente
        
        # Crear diccionario con información de la pila
        datos_pila = {
            'elementos': elementos,
            'fecha_guardado': datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
            'numero_elementos': len(elementos),
            'tope': elementos[0] if elementos else None
        }
        
        with open(nombre_archivo, 'wb') as archivo:
            pickle.dump(datos_pila, archivo)
        
        print(f"Pila guardada exitosamente en '{nombre_archivo}'")
        return True
    except Exception as e:
        print(f"Error al guardar la pila: {e}")
        return False

def cargar_pila(nombre_archivo="pila_marcas.pkl"):
    """Carga una pila desde un archivo"""
    try:
        with open(nombre_archivo, 'rb') as archivo:
            datos_pila = pickle.load(archivo)
        
        # Reconstruir pila desde la lista
        pila = crear_pila()
        for elemento in reversed(datos_pila['elementos']):
            pila = apilar(pila, elemento)
        
        print(f"Pila cargada exitosamente desde '{nombre_archivo}'")
        print(f"Fecha de guardado: {datos_pila['fecha_guardado']}")
        print(f"Número de elementos: {datos_pila['numero_elementos']}")
        
        return pila
    except FileNotFoundError:
        print(f"Archivo '{nombre_archivo}' no encontrado")
        return crear_pila()
    except Exception as e:
        print(f"Error al cargar la pila: {e}")
        return crear_pila()

def mostrar_menu():
    """Muestra el menú principal"""
    print("\n" + "=" * 50)
    print("MENÚ PRINCIPAL - PILA DE MARCAS DE COCHES")
    print("=" * 50)
    print("1. Apilar una nueva marca")
    print("2. Desapilar marca del tope")
    print("3. Mostrar tope de la pila")
    print("4. Mostrar todos los elementos")
    print("5. Contar elementos en la pila")
    print("6. Invertir pila (crear nueva pila invertida)")
    print("7. Copiar pila (crear copia exacta)")
    print("8. Vaciar pila completamente")
    print("9. Guardar pila en archivo")
    print("10. Cargar pila desde archivo")
    print("11. Salir")
    print("=" * 50)

def main():
    pila_actual = crear_pila()
    pila_auxiliar = crear_pila()
    
    while True:
        mostrar_menu()
        
        try:
            opcion = int(input("\nSeleccione una opción (1-11): "))
        except ValueError:
            print("Por favor, ingrese un número válido.")
            continue
        
        if opcion == 1:
            marca = input("Ingrese la marca de coche a apilar: ").strip()
            if marca:
                pila_actual = apilar(pila_actual, marca)
                print(f"Marca '{marca}' apilada correctamente.")
            else:
                print("Error: La marca no puede estar vacía.")
        
        elif opcion == 2:
            if pila_vacia(pila_actual):
                print("No se puede desapilar: Pila vacía")
            else:
                pila_actual, marca_desapilada = desapilar(pila_actual)
                print(f"Marca desapilada: '{marca_desapilada}'")
        
        elif opcion == 3:
            marca_tope = tope(pila_actual)
            if marca_tope is not None:
                print(f"Tope de la pila: '{marca_tope}'")
        
        elif opcion == 4:
            imprimir_pila(pila_actual)
        
        elif opcion == 5:
            cantidad = contar_elementos(pila_actual)
            print(f"La pila contiene {cantidad} elemento(s).")
        
        elif opcion == 6:
            pila_invertida = invertir_pila(pila_actual)
            print("Pila invertida creada correctamente.")
            print("Contenido de la pila invertida:")
            imprimir_pila(pila_invertida)
            
            # Preguntar si quiere guardar como pila auxiliar
            guardar = input("\n¿Desea guardar esta pila invertida como pila auxiliar? (s/n): ").lower()
            if guardar == 's':
                pila_auxiliar = pila_invertida
                print("Pila invertida guardada como pila auxiliar.")
        
        elif opcion == 7:
            pila_copiada = copiar_pila(pila_actual)
            print("Pila copiada correctamente.")
            print("Contenido de la pila copiada:")
            imprimir_pila(pila_copiada)
            
            # Preguntar si quiere guardar como pila auxiliar
            guardar = input("\n¿Desea guardar esta copia como pila auxiliar? (s/n): ").lower()
            if guardar == 's':
                pila_auxiliar = pila_copiada
                print("Copia guardada como pila auxiliar.")
        
        elif opcion == 8:
            if not pila_vacia(pila_actual):
                confirmar = input("¿Está seguro de vaciar la pila? (s/n): ").lower()
                if confirmar == 's':
                    pila_actual = vaciar_pila(pila_actual)
                    print("Pila vaciada completamente.")
            else:
                print("La pila ya está vacía.")
        
        elif opcion == 9:
            if pila_vacia(pila_actual):
                print("No se puede guardar una pila vacía.")
            else:
                nombre = input("Ingrese nombre del archivo (Enter para 'pila_marcas.pkl'): ").strip()
                if not nombre:
                    nombre = "pila_marcas.pkl"
                guardar_pila(pila_actual, nombre)
        
        elif opcion == 10:
            nombre = input("Ingrese nombre del archivo a cargar (Enter para 'pila_marcas.pkl'): ").strip()
            if not nombre:
                nombre = "pila_marcas.pkl"
            
            confirmar = input("¿Desea cargar sobre la pila actual? (s/n): ").lower()
            if confirmar == 's':
                pila_actual = cargar_pila(nombre)
            else:
                pila_cargada = cargar_pila(nombre)
                if not pila_vacia(pila_cargada):
                    guardar = input("¿Desea guardar la pila cargada como pila auxiliar? (s/n): ").lower()
                    if guardar == 's':
                        pila_auxiliar = pila_cargada
                        print("Pila cargada guardada como pila auxiliar.")
        
        elif opcion == 11:
            print("\n¡Gracias por usar el sistema de pilas de marcas de coches!")
            print("Saliendo del programa...")
            break
        
        else:
            print("Opción no válida. Por favor, seleccione una opción del 1 al 11.")
        
        # Mostrar estado actual de la pila
        if not pila_vacia(pila_actual):
            cantidad = contar_elementos(pila_actual)
            marca_tope = tope(pila_actual)
            print(f"\n[Estado actual] Elementos: {cantidad} | Tope: '{marca_tope}'")
        
        input("\nPresione Enter para continuar...")

if __name__ == "__main__":
    main()