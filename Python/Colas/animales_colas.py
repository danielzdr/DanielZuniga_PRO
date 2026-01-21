import json
import os
from datetime import datetime
#estructuras de datos
# Nodo para lista enlazada
def crear_nodo(animal):
    """Crea un nuevo nodo para la lista enlazada"""
    return {
        'animal': animal,
        'siguiente': None
    }

# Cola implementada con lista enlazada
def crear_cola():
    """Crea una cola vacía"""
    return {
        'frente': None,
        'final': None,
        'tamano': 0
    }

#operaciones basicas de la cola
def cola_vacia(cola):
    """Verifica si la cola está vacía"""
    return cola['frente'] is None

def frente(cola):
    """Devuelve el elemento del frente sin eliminarlo"""
    if cola_vacia(cola):
        return None
    return cola['frente']['animal']

def encolar(cola, animal):
    """Añade un animal al final de la cola"""
    nuevo_nodo = crear_nodo(animal)
    
    if cola_vacia(cola):
        cola['frente'] = nuevo_nodo
    else:
        cola['final']['siguiente'] = nuevo_nodo
    
    cola['final'] = nuevo_nodo
    cola['tamano'] += 1
    return True

def desencolar(cola):
    """Elimina y devuelve el animal del frente de la cola"""
    if cola_vacia(cola):
        return None
    
    nodo_eliminado = cola['frente']
    animal = nodo_eliminado['animal']
    
    cola['frente'] = cola['frente']['siguiente']
    
    # Si la cola queda vacía, actualizar final
    if cola['frente'] is None:
        cola['final'] = None
    
    cola['tamano'] -= 1
    return animal

def imprimir_cola(cola):
    """Muestra todos los elementos de la cola"""
    if cola_vacia(cola):
        print("La cola está vacía.")
        return
    
    print("\n=== CONTENIDO DE LA COLA ===")
    print(f"Tamaño: {cola['tamano']} animales")
    print("-" * 40)
    
    actual = cola['frente']
    posicion = 1
    
    while actual is not None:
        animal = actual['animal']
        print(f"{posicion}. {animal['nombre']} ({animal['tipo']}) - {animal['clase']}")
        actual = actual['siguiente']
        posicion += 1
    
    print("-" * 40)

def contar(cola):
    """Muestra el número de elementos de la cola"""
    print(f"\nLa cola tiene {cola['tamano']} animales vertebrados.")

def invertir_cola(cola_origen):
    """Crea una nueva cola con los elementos invertidos"""
    cola_invertida = crear_cola()
    
    # Usar una lista temporal para invertir
    elementos = []
    actual = cola_origen['frente']
    
    while actual is not None:
        elementos.append(actual['animal'])
        actual = actual['siguiente']
    
    # Insertar en orden inverso
    for animal in reversed(elementos):
        encolar(cola_invertida, animal)
    
    return cola_invertida

def copiar_cola(cola_origen):
    """Crea una copia exacta de la cola"""
    cola_copia = crear_cola()
    
    actual = cola_origen['frente']
    while actual is not None:
        # Crear una copia profunda del animal
        animal_copia = actual['animal'].copy()
        encolar(cola_copia, animal_copia)
        actual = actual['siguiente']
    
    return cola_copia

def vaciar_cola(cola):
    """Vacía completamente la cola"""
    while not cola_vacia(cola):
        desencolar(cola)
    
    cola['tamano'] = 0
    print("\n Cola vaciada completamente.")

def guardar_cola(cola, nombre_archivo=None):
    """Guarda la cola en un archivo JSON"""
    if nombre_archivo is None:
        fecha_actual = datetime.now().strftime("%Y%m%d_%H%M%S")
        nombre_archivo = f"cola_animales_{fecha_actual}.json"
    
    # Preparar datos para guardar
    datos = {
        'fecha_guardado': datetime.now().isoformat(),
        'tamano_cola': cola['tamano'],
        'animales': []
    }
    
    # Recorrer la cola y guardar animales
    actual = cola['frente']
    while actual is not None:
        datos['animales'].append(actual['animal'])
        actual = actual['siguiente']
    
    # Guardar en archivo
    try:
        with open(nombre_archivo, 'w', encoding='utf-8') as archivo:
            json.dump(datos, archivo, ensure_ascii=False, indent=2)
        print(f"\n Cola guardada exitosamente en: {nombre_archivo}")
        return True
    except Exception as e:
        print(f"\n Error al guardar la cola: {e}")
        return False

def cargar_cola(nombre_archivo):
    """Carga una cola desde un archivo JSON"""
    if not os.path.exists(nombre_archivo):
        print(f"\n El archivo '{nombre_archivo}' no existe.")
        return None
    
    try:
        with open(nombre_archivo, 'r', encoding='utf-8') as archivo:
            datos = json.load(archivo)
        
        cola = crear_cola()
        for animal in datos['animales']:
            encolar(cola, animal)
        
        print(f"\n Cola cargada exitosamente desde: {nombre_archivo}")
        print(f"   Fecha original: {datos['fecha_guardado']}")
        print(f"   Tamaño original: {datos['tamano_cola']} animales")
        
        return cola
    except Exception as e:
        print(f"\n Error al cargar la cola: {e}")
        return None
#funciones entradas de datos
def solicitar_animal():
    """Solicita los datos de un animal vertebrado por teclado"""
    print("\n" + "="*50)
    print("NUEVO ANIMAL VERTEBRADO")
    print("="*50)
    
    # Lista de clases válidas
    clases_validas = ['Mamífero', 'Ave', 'Reptil', 'Anfibio', 'Pez']
    
    # Solicitar datos
    nombre = input("Nombre del animal: ").strip()
    
    while True:
        tipo = input("Tipo (ej: felino, canino, ave rapaz, etc.): ").strip()
        if tipo:
            break
        print("❌ El tipo no puede estar vacío.")
    
    print("\nClases disponibles:")
    for i, clase in enumerate(clases_validas, 1):
        print(f"  {i}. {clase}")
    
    while True:
        try:
            opcion = int(input(f"Seleccione clase (1-{len(clases_validas)}): "))
            if 1 <= opcion <= len(clases_validas):
                clase = clases_validas[opcion - 1]
                break
            else:
                print(f"❌ Opción inválida. Seleccione 1-{len(clases_validas)}")
        except ValueError:
            print("❌ Por favor, ingrese un número válido.")
    
    while True:
        try:
            edad = int(input("Edad (en años): "))
            if edad >= 0:
                break
            print("❌ La edad no puede ser negativa.")
        except ValueError:
            print("❌ Por favor, ingrese un número válido.")
    
    habitat = input("Hábitat natural: ").strip()
    alimentacion = input("Tipo de alimentación: ").strip()
    
    # Crear diccionario del animal
    animal = {
        'nombre': nombre,
        'tipo': tipo,
        'clase': clase,
        'edad': edad,
        'habitat': habitat,
        'alimentacion': alimentacion,
        'fecha_ingreso': datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    }
    
    print(f"\n✅ Animal '{nombre}' registrado exitosamente.")
    return animal

def agregar_varios_animales(cola):
    """Permite agregar varios animales de una vez"""
    print("\n" + "="*50)
    print("AGREGAR VARIOS ANIMALES")
    print("="*50)
    
    while True:
        try:
            cantidad = int(input("¿Cuántos animales desea agregar? (0 para cancelar): "))
            if cantidad == 0:
                print("Operación cancelada.")
                return
            if cantidad > 0:
                break
            print(" La cantidad debe ser positiva.")
        except ValueError:
            print(" Por favor, ingrese un número válido.")
    
    for i in range(1, cantidad + 1):
        print(f"\n--- Animal {i} de {cantidad} ---")
        animal = solicitar_animal()
        encolar(cola, animal)
    
    print(f"\n Se agregaron {cantidad} animales a la cola.")

def mostrar_menu():
    """Muestra el menú principal"""
    print("\n" + "="*60)
    print("SISTEMA DE GESTIÓN DE COLAS - ANIMALES VERTEBRADOS")
    print("="*60)
    print("1. Agregar animal (encolar)")
    print("2. Atender animal (desencolar)")
    print("3. Ver animal al frente")
    print("4. Mostrar toda la cola")
    print("5. Contar animales en cola")
    print("6. Invertir cola")
    print("7. Copiar cola")
    print("8. Vaciar cola")
    print("9. Guardar cola en archivo")
    print("10. Cargar cola desde archivo")
    print("11. Agregar varios animales")
    print("12. Salir")
    print("-"*60)

def mostrar_submenu_invertir():
    """Muestra submenú para operaciones de inversión"""
    print("\n" + "="*50)
    print("OPCIONES DE INVERSIÓN DE COLA")
    print("="*50)
    print("1. Mostrar cola invertida (sin modificar original)")
    print("2. Reemplazar cola original con versión invertida")
    print("3. Guardar cola invertida en archivo")
    print("4. Volver al menú principal")
    print("-"*50)

def mostrar_submenu_copiar():
    """Muestra submenú para operaciones de copia"""
    print("\n" + "="*50)
    print("OPCIONES DE COPIA DE COLA")
    print("="*50)
    print("1. Mostrar copia de la cola")
    print("2. Guardar copia en archivo")
    print("3. Volver al menú principal")
    print("-"*50)
#Menu principal
def main():
    """Función principal del programa"""
    print("BIENVENIDO AL SISTEMA DE GESTIÓN DE COLAS DE ANIMALES VERTEBRADOS")
    
    # Crear cola principal
    cola_principal = crear_cola()
    cola_copia = None
    cola_invertida = None
    
    while True:
        mostrar_menu()
        
        try:
            opcion = int(input("\nSeleccione una opción (1-12): "))
            
            if opcion == 1:  # Agregar animal
                animal = solicitar_animal()
                encolar(cola_principal, animal)
                print(f" Animal '{animal['nombre']}' agregado a la cola.")
                
            elif opcion == 2:  # Atender animal
                if cola_vacia(cola_principal):
                    print(" La cola está vacía. No hay animales para atender.")
                else:
                    animal_atendido = desencolar(cola_principal)
                    print("\n ANIMAL ATENDIDO:")
                    print(f"   Nombre: {animal_atendido['nombre']}")
                    print(f"   Tipo: {animal_atendido['tipo']}")
                    print(f"   Clase: {animal_atendido['clase']}")
                    print(f"   Edad: {animal_atendido['edad']} años")
                    print(f"   Ingresó: {animal_atendido['fecha_ingreso']}")
                    
            elif opcion == 3:  # Ver frente
                if cola_vacia(cola_principal):
                    print(" La cola está vacía.")
                else:
                    animal_frente = frente(cola_principal)
                    print("\n🐾 ANIMAL AL FRENTE DE LA COLA:")
                    print(f"   Nombre: {animal_frente['nombre']}")
                    print(f"   Tipo: {animal_frente['tipo']}")
                    print(f"   Clase: {animal_frente['clase']}")
                    print(f"   Edad: {animal_frente['edad']} años")
                    
            elif opcion == 4:  # Mostrar cola
                imprimir_cola(cola_principal)
                
            elif opcion == 5:  # Contar animales
                contar(cola_principal)
                
            elif opcion == 6:  # Invertir cola
                if cola_vacia(cola_principal):
                    print(" La cola está vacía. No se puede invertir.")
                    continue
                
                while True:
                    mostrar_submenu_invertir()
                    sub_opcion = input("\nSeleccione opción (1-4): ")
                    
                    if sub_opcion == '1':
                        cola_invertida = invertir_cola(cola_principal)
                        print("\n=== COLA INVERTIDA ===")
                        imprimir_cola(cola_invertida)
                        
                    elif sub_opcion == '2':
                        confirmar = input("\n ¿Está seguro de reemplazar la cola original? (s/n): ")
                        if confirmar.lower() == 's':
                            cola_invertida = invertir_cola(cola_principal)
                            cola_principal = cola_invertida
                            print(" Cola invertida y reemplazada exitosamente.")
                            imprimir_cola(cola_principal)
                        else:
                            print("Operación cancelada.")
                            
                    elif sub_opcion == '3':
                        if cola_invertida is None:
                            cola_invertida = invertir_cola(cola_principal)
                        nombre_archivo = input("Nombre del archivo (dejar en blanco para automático): ").strip()
                        if not nombre_archivo:
                            guardar_cola(cola_invertida)
                        else:
                            guardar_cola(cola_invertida, nombre_archivo)
                            
                    elif sub_opcion == '4':
                        break
                    else:
                        print(" Opción inválida. Intente de nuevo.")
                        
            elif opcion == 7:  # Copiar cola
                if cola_vacia(cola_principal):
                    print(" La cola está vacía. No se puede copiar.")
                    continue
                
                while True:
                    mostrar_submenu_copiar()
                    sub_opcion = input("\nSeleccione opción (1-3): ")
                    
                    if sub_opcion == '1':
                        cola_copia = copiar_cola(cola_principal)
                        print("\n=== COPIA DE LA COLA ===")
                        imprimir_cola(cola_copia)
                        
                    elif sub_opcion == '2':
                        if cola_copia is None:
                            cola_copia = copiar_cola(cola_principal)
                        nombre_archivo = input("Nombre del archivo para la copia (dejar en blanco para automático): ").strip()
                        if not nombre_archivo:
                            nombre_archivo = f"copia_cola_{datetime.now().strftime('%Y%m%d_%H%M%S')}.json"
                        guardar_cola(cola_copia, nombre_archivo)
                        
                    elif sub_opcion == '3':
                        break
                    else:
                        print(" Opción inválida. Intente de nuevo.")
                        
            elif opcion == 8:  # Vaciar cola
                if cola_vacia(cola_principal):
                    print(" La cola ya está vacía.")
                else:
                    confirmar = input("\n ¿Está seguro de vaciar toda la cola? (s/n): ")
                    if confirmar.lower() == 's':
                        vaciar_cola(cola_principal)
                    else:
                        print("Operación cancelada.")
                        
            elif opcion == 9:  # Guardar cola
                if cola_vacia(cola_principal):
                    print(" La cola está vacía. No hay nada que guardar.")
                else:
                    nombre_archivo = input("Nombre del archivo (dejar en blanco para automático): ").strip()
                    if not nombre_archivo:
                        guardar_cola(cola_principal)
                    else:
                        guardar_cola(cola_principal, nombre_archivo)
                        
            elif opcion == 10:  # Cargar cola
                nombre_archivo = input("Nombre del archivo a cargar: ").strip()
                if nombre_archivo:
                    if not cola_vacia(cola_principal):
                        confirmar = input(" ¿Ya existe una cola cargada. ¿Desea reemplazarla? (s/n): ")
                        if confirmar.lower() != 's':
                            print("Operación cancelada.")
                            continue
                    
                    nueva_cola = cargar_cola(nombre_archivo)
                    if nueva_cola is not None:
                        cola_principal = nueva_cola
                        print(" Cola principal actualizada.")
                        imprimir_cola(cola_principal)
                        
            elif opcion == 11:  # Agregar varios animales
                agregar_varios_animales(cola_principal)
                
            elif opcion == 12:  # Salir
                if not cola_vacia(cola_principal):
                    guardar = input("\n ¿Desea guardar la cola antes de salir? (s/n): ")
                    if guardar.lower() == 's':
                        guardar_cola(cola_principal)
                
                print("\n" + "="*60)
                print("¡GRACIAS POR USAR EL SISTEMA DE GESTIÓN DE COLAS!")
                print("="*60)
                break
                
            else:
                print(" Opción inválida. Por favor seleccione 1-12.")
                
        except ValueError:
            print(" Error: Por favor ingrese un número válido.")
        except Exception as e:
            print(f" Error inesperado: {e}")
#ejecucion del programa
if __name__ == "__main__":
    # Verificar si existe el directorio para guardar archivos
    if not os.path.exists("archivos_colas"):
        os.makedirs("archivos_colas")
        print("📁 Directorio 'archivos_colas' creado para guardar las colas.")
    main()