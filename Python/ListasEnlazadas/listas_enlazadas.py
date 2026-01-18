"""PRÁCTICA: Implementación de Listas con Punteros Simulados"""
import json
import os

# Constantes para valores especiales
NULL_INICIO = 0  # Valor que representa el inicio nulo
NULL_FINAL = 99  # Valor que representa el final nulo
VALOR_MIN = 1    # Valor mínimo permitido en los nodos
VALOR_MAX = 98   # Valor máximo permitido en los nodos

class ListaEnlazada:
    """Clase base para listas enlazadas simples"""
    
    def __init__(self, tipo="enlazada"):
        """
        Inicializa una lista vacía
        Args:
            tipo: Tipo de lista (enlazada, enlazada_d, circular, circular_d)
        """
        self.cabeza = None  # Primer nodo de la lista
        self.tipo = tipo    # Tipo de lista
        self.nombre_archivo = f"lista_{tipo}.txt"  # Nombre del archivo para copiar
    
    def crear_nodo(self, valor):
        """
        Crea un nuevo nodo con el valor especificado
        Args:
            valor: Valor del nodo (entero entre 1 y 98)
        Returns:
            dict: Nodo creado como diccionario
        """
        if not (VALOR_MIN <= valor <= VALOR_MAX):
            print(f"Error: El valor debe estar entre {VALOR_MIN} y {VALOR_MAX}")
            return None
        
        nodo = {
            'valor': valor,
            'siguiente': NULL_FINAL  # Por defecto, apunta a NULL_FINAL
        }
        return nodo
    
    def insertar_inicio(self, valor):
        """
        Inserta un nodo al inicio de la lista
        Args:
            valor: Valor del nodo a insertar
        """
        nuevo_nodo = self.crear_nodo(valor)
        if nuevo_nodo is None:
            return
        
        if self.cabeza is None:
            # Lista vacía, el nuevo nodo es la cabeza
            self.cabeza = nuevo_nodo
        else:
            # El nuevo nodo apunta a la antigua cabeza
            nuevo_nodo['siguiente'] = self.cabeza
            self.cabeza = nuevo_nodo
        
        print(f"Nodo con valor {valor} insertado al inicio")
    
    def insertar_final(self, valor):
        """
        Inserta un nodo al final de la lista
        Args:
            valor: Valor del nodo a insertar
        """
        nuevo_nodo = self.crear_nodo(valor)
        if nuevo_nodo is None:
            return
        
        if self.cabeza is None:
            print("Error: No se puede insertar al final en una lista vacía")
            return
        
        # Recorremos hasta el último nodo
        actual = self.cabeza
        while actual['siguiente'] != NULL_FINAL:
            actual = self.buscar_nodo_por_valor(actual['siguiente'])
        
        # El último nodo ahora apunta al nuevo nodo
        actual['siguiente'] = valor
        print(f"Nodo con valor {valor} insertado al final")
    
    def insertar_nodo(self, valor, valor_anterior=None, valor_siguiente=None):
        """
        Inserta un nodo entre dos nodos existentes
        Args:
            valor: Valor del nuevo nodo
            valor_anterior: Valor del nodo anterior (opcional)
            valor_siguiente: Valor del nodo siguiente (opcional)
        """
        nuevo_nodo = self.crear_nodo(valor)
        if nuevo_nodo is None:
            return
        
        # Caso 1: Insertar al inicio (sin nodo anterior)
        if valor_anterior is None or valor_anterior == NULL_INICIO:
            self.insertar_inicio(valor)
            return
        
        # Caso 2: Insertar al final (sin nodo siguiente)
        if valor_siguiente is None or valor_siguiente == NULL_FINAL:
            self.insertar_final(valor)
            return
        
        # Caso 3: Insertar entre dos nodos
        nodo_anterior = self.buscar_nodo(valor_anterior)
        nodo_siguiente = self.buscar_nodo(valor_siguiente)
        
        if nodo_anterior and nodo_siguiente:
            # Verificamos que nodo_anterior apunte a nodo_siguiente
            if nodo_anterior['siguiente'] == valor_siguiente:
                # Actualizamos referencias
                nuevo_nodo['siguiente'] = valor_siguiente
                nodo_anterior['siguiente'] = valor
                print(f"Nodo con valor {valor} insertado entre {valor_anterior} y {valor_siguiente}")
            else:
                print(f"Error: {valor_anterior} no apunta a {valor_siguiente}")
        else:
            print("Error: No se encontraron los nodos especificados")
    
    def contar_nodos(self):
        """
        Cuenta el número de nodos en la lista
        Returns:
            int: Número de nodos
        """
        contador = 0
        actual = self.cabeza
        
        while actual is not None:
            contador += 1
            if actual['siguiente'] == NULL_FINAL:
                break
            actual = self.buscar_nodo_por_valor(actual['siguiente'])
        
        return contador
    
    def eliminar_nodo(self):
        """
        Elimina un nodo especificado por el usuario
        """
        if self.cabeza is None:
            print("La lista está vacía")
            return
        
        # Pedir al usuario el valor del nodo a eliminar
        try:
            valor = int(input("Ingrese el valor del nodo a eliminar: "))
        except ValueError:
            print("Error: Debe ingresar un número entero")
            return
        
        # Caso especial: eliminar la cabeza
        if self.cabeza['valor'] == valor:
            self.cabeza = self.buscar_nodo_por_valor(self.cabeza['siguiente']) if self.cabeza['siguiente'] != NULL_FINAL else None
            print(f"Nodo con valor {valor} eliminado")
            return
        
        # Buscar el nodo anterior al que queremos eliminar
        actual = self.cabeza
        anterior = None
        
        while actual is not None:
            if actual['valor'] == valor:
                # Encontramos el nodo a eliminar
                if anterior:
                    # El nodo anterior apunta al siguiente del nodo actual
                    anterior['siguiente'] = actual['siguiente']
                print(f"Nodo con valor {valor} eliminado")
                return
            
            anterior = actual
            if actual['siguiente'] == NULL_FINAL:
                break
            actual = self.buscar_nodo_por_valor(actual['siguiente'])
        
        print(f"No se encontró el nodo con valor {valor}")
    
    def buscar_nodo(self, valor):
        """
        Busca un nodo por su valor
        Args:
            valor: Valor a buscar
        Returns:
            dict: Nodo encontrado o None
        """
        return self.buscar_nodo_por_valor(valor)
    
    def buscar_nodo_por_valor(self, valor):
        """
        Busca un nodo por su valor (método interno)
        Args:
            valor: Valor a buscar
        Returns:
            dict: Nodo encontrado o None
        """
        actual = self.cabeza
        
        while actual is not None:
            if actual['valor'] == valor:
                return actual
            if actual['siguiente'] == NULL_FINAL:
                break
            actual = self.buscar_nodo_por_valor(actual['siguiente'])
        
        return None
    
    def imprimir_valor_lista(self):
        """
        Imprime solo los valores de los nodos
        """
        if self.cabeza is None:
            print("La lista está vacía")
            return
        
        actual = self.cabeza
        valores = []
        
        while actual is not None:
            valores.append(str(actual['valor']))
            if actual['siguiente'] == NULL_FINAL:
                break
            actual = self.buscar_nodo_por_valor(actual['siguiente'])
        
        print(" -> ".join(valores) + " -> NULL(99)")
    
    def imprimir_lista_completa(self):
        """
        Imprime todos los campos de cada nodo
        """
        if self.cabeza is None:
            print("La lista está vacía")
            return
        
        actual = self.cabeza
        print("\n=== Lista Completa ===")
        
        while actual is not None:
            print(f"Valor: {actual['valor']}, Siguiente: {actual['siguiente']}")
            if actual['siguiente'] == NULL_FINAL:
                break
            actual = self.buscar_nodo_por_valor(actual['siguiente'])
    
    def imprimir_reves(self):
        """
        Imprime la lista en orden inverso
        """
        if self.cabeza is None:
            print("La lista está vacía")
            return
        
        # Recopilamos todos los valores
        valores = []
        actual = self.cabeza
        
        while actual is not None:
            valores.append(actual['valor'])
            if actual['siguiente'] == NULL_FINAL:
                break
            actual = self.buscar_nodo_por_valor(actual['siguiente'])
        
        # Imprimimos en orden inverso
        print(" <- ".join(str(v) for v in reversed(valores)) + " <- NULL(0)")
    
    def copiar_lista(self):
        """
        Copia la lista a un archivo de texto
        """
        if self.cabeza is None:
            print("La lista está vacía, no hay nada que copiar")
            return
        
        try:
            with open(self.nombre_archivo, 'w') as archivo:
                # Escribimos encabezado
                archivo.write(f"=== Lista {self.tipo.replace('_', ' ').title()} ===\n")
                archivo.write("Formato: Nodo(Valor) -> Nodo Siguiente\n\n")
                
                # Escribimos cada nodo
                actual = self.cabeza
                while actual is not None:
                    siguiente_str = f"Nodo({actual['siguiente']})" if actual['siguiente'] != NULL_FINAL else "NULL(99)"
                    archivo.write(f"Nodo({actual['valor']}) -> {siguiente_str}\n")
                    
                    if actual['siguiente'] == NULL_FINAL:
                        break
                    actual = self.buscar_nodo_por_valor(actual['siguiente'])
            
            print(f"Lista copiada exitosamente a '{self.nombre_archivo}'")
        except Exception as e:
            print(f"Error al copiar la lista: {e}")
    
    def ordenar_lista(self):
        """
        Ordena la lista de menor a mayor (implementación extra)
        """
        if self.cabeza is None:
            print("La lista está vacía")
            return
        
        # Recopilamos todos los valores
        valores = []
        actual = self.cabeza
        
        while actual is not None:
            valores.append(actual['valor'])
            if actual['siguiente'] == NULL_FINAL:
                break
            actual = self.buscar_nodo_por_valor(actual['siguiente'])
        
        # Ordenamos los valores
        valores.sort()
        
        # Reconstruimos la lista
        self.cabeza = None
        for valor in valores:
            if self.cabeza is None:
                self.cabeza = self.crear_nodo(valor)
                actual = self.cabeza
            else:
                nuevo_nodo = self.crear_nodo(valor)
                actual['siguiente'] = valor
                actual = nuevo_nodo
        
        print("Lista ordenada de menor a mayor")


class ListaDoblementeEnlazada(ListaEnlazada):
    """Clase para listas doblemente enlazadas"""
    
    def __init__(self):
        super().__init__("enlazada_d")
    
    def crear_nodo(self, valor):
        """
        Crea un nuevo nodo con referencias anterior y siguiente
        Args:
            valor: Valor del nodo
        Returns:
            dict: Nodo creado
        """
        if not (VALOR_MIN <= valor <= VALOR_MAX):
            print(f"Error: El valor debe estar entre {VALOR_MIN} y {VALOR_MAX}")
            return None
        
        nodo = {
            'valor': valor,
            'anterior': NULL_INICIO,  # Apunta a NULL_INICIO por defecto
            'siguiente': NULL_FINAL    # Apunta a NULL_FINAL por defecto
        }
        return nodo
    
    def insertar_inicio(self, valor):
        """
        Inserta un nodo al inicio de la lista
        Args:
            valor: Valor del nodo
        """
        nuevo_nodo = self.crear_nodo(valor)
        if nuevo_nodo is None:
            return
        
        if self.cabeza is None:
            self.cabeza = nuevo_nodo
        else:
            # Configuramos las referencias
            nuevo_nodo['siguiente'] = self.cabeza['valor']
            self.cabeza['anterior'] = valor
            self.cabeza = nuevo_nodo
        
        print(f"Nodo con valor {valor} insertado al inicio")
    
    def insertar_final(self, valor):
        """
        Inserta un nodo al final de la lista
        Args:
            valor: Valor del nodo
        """
        nuevo_nodo = self.crear_nodo(valor)
        if nuevo_nodo is None:
            return
        
        if self.cabeza is None:
            print("Error: No se puede insertar al final en una lista vacía")
            return
        
        # Buscamos el último nodo
        actual = self.cabeza
        while actual['siguiente'] != NULL_FINAL:
            actual = self.buscar_nodo_por_valor(actual['siguiente'])
        
        # Configuramos referencias
        actual['siguiente'] = valor
        nuevo_nodo['anterior'] = actual['valor']
        
        print(f"Nodo con valor {valor} insertado al final")
    
    def imprimir_lista_completa(self):
        """
        Imprime todos los campos de cada nodo
        """
        if self.cabeza is None:
            print("La lista está vacía")
            return
        
        actual = self.cabeza
        print("\n=== Lista Completa (Doblemente Enlazada) ===")
        
        while actual is not None:
            anterior_str = f"Nodo({actual['anterior']})" if actual['anterior'] != NULL_INICIO else "NULL(0)"
            siguiente_str = f"Nodo({actual['siguiente']})" if actual['siguiente'] != NULL_FINAL else "NULL(99)"
            print(f"Valor: {actual['valor']}, Anterior: {actual['anterior']}, Siguiente: {actual['siguiente']}")
            print(f"  {anterior_str} <- Nodo({actual['valor']}) -> {siguiente_str}\n")
            
            if actual['siguiente'] == NULL_FINAL:
                break
            actual = self.buscar_nodo_por_valor(actual['siguiente'])


class ListaCircular(ListaEnlazada):
    """Clase para listas circulares enlazadas"""
    
    def __init__(self):
        super().__init__("circular")
        self.ultimo = None  # Referencia al último nodo
    
    def insertar_inicio(self, valor):
        """
        Inserta un nodo al inicio de la lista circular
        Args:
            valor: Valor del nodo
        """
        nuevo_nodo = self.crear_nodo(valor)
        if nuevo_nodo is None:
            return
        
        if self.cabeza is None:
            # Primer nodo, apunta a sí mismo
            self.cabeza = nuevo_nodo
            self.ultimo = nuevo_nodo
            nuevo_nodo['siguiente'] = valor  # Apunta a sí mismo
        else:
            # El nuevo nodo apunta a la antigua cabeza
            nuevo_nodo['siguiente'] = self.cabeza['valor']
            # El último nodo apunta al nuevo nodo
            if self.ultimo:
                self.ultimo['siguiente'] = valor
            self.cabeza = nuevo_nodo
        
        print(f"Nodo con valor {valor} insertado al inicio de la lista circular")
    
    def imprimir_valor_lista(self):
        """
        Imprime los valores de la lista circular
        """
        if self.cabeza is None:
            print("La lista está vacía")
            return
        
        actual = self.cabeza
        valores = []
        nodos_vistos = set()
        
        # Usamos un conjunto para evitar ciclos infinitos
        while actual is not None and actual['valor'] not in nodos_vistos:
            valores.append(str(actual['valor']))
            nodos_vistos.add(actual['valor'])
            
            if actual['siguiente'] == actual['valor']:
                # Único nodo en la lista
                break
            
            actual = self.buscar_nodo_por_valor(actual['siguiente'])
        
        if valores:
            print(" -> ".join(valores) + " -> ... (circular)")


class ListaCircularDoblementeEnlazada(ListaDoblementeEnlazada):
    """Clase para listas circulares doblemente enlazadas"""
    
    def __init__(self):
        super().__init__()
        self.tipo = "circular_d"
        self.nombre_archivo = f"lista_{self.tipo}.txt"
        self.ultimo = None
    
    def insertar_inicio(self, valor):
        """
        Inserta un nodo al inicio de la lista circular doblemente enlazada
        Args:
            valor: Valor del nodo
        """
        nuevo_nodo = self.crear_nodo(valor)
        if nuevo_nodo is None:
            return
        
        if self.cabeza is None:
            # Primer nodo
            self.cabeza = nuevo_nodo
            self.ultimo = nuevo_nodo
            nuevo_nodo['siguiente'] = valor
            nuevo_nodo['anterior'] = valor
        else:
            # Configuramos referencias circulares
            nuevo_nodo['siguiente'] = self.cabeza['valor']
            nuevo_nodo['anterior'] = self.ultimo['valor']
            
            self.cabeza['anterior'] = valor
            self.ultimo['siguiente'] = valor
            
            self.cabeza = nuevo_nodo
        
        print(f"Nodo con valor {valor} insertado al inicio de la lista circular doblemente enlazada")
    
    def imprimir_lista_completa(self):
        """
        Imprime todos los campos de la lista circular doblemente enlazada
        """
        if self.cabeza is None:
            print("La lista está vacía")
            return
        
        actual = self.cabeza
        print("\n=== Lista Completa (Circular Doblemente Enlazada) ===")
        nodos_vistos = set()
        
        while actual is not None and actual['valor'] not in nodos_vistos:
            anterior_str = f"Nodo({actual['anterior']})" if actual['anterior'] != actual['valor'] else "Nodo(mismo)"
            siguiente_str = f"Nodo({actual['siguiente']})" if actual['siguiente'] != actual['valor'] else "Nodo(mismo)"
            
            print(f"Valor: {actual['valor']}, Anterior: {actual['anterior']}, Siguiente: {actual['siguiente']}")
            print(f"  {anterior_str} <-> Nodo({actual['valor']}) <-> {siguiente_str}\n")
            
            nodos_vistos.add(actual['valor'])
            
            if actual['siguiente'] == actual['valor']:
                # Único nodo
                break
            
            actual = self.buscar_nodo_por_valor(actual['siguiente'])


def mostrar_menu():
    """
    Muestra el menú principal del programa
    """
    print("\n" + "="*50)
    print("SISTEMA DE GESTIÓN DE LISTAS CON PUNTEROS SIMULADOS")
    print("="*50)
    print("Seleccione el tipo de lista:")
    print("1. Lista Enlazada Simple")
    print("2. Lista Doblemente Enlazada")
    print("3. Lista Circular Enlazada")
    print("4. Lista Circular Doblemente Enlazada")
    print("5. Salir")
    print("="*50)

def mostrar_submenu():
    """
    Muestra el submenú de operaciones
    """
    print("\n" + "-"*40)
    print("OPERACIONES DISPONIBLES")
    print("-"*40)
    print("1. Insertar nodo al inicio")
    print("2. Insertar nodo al final")
    print("3. Insertar nodo entre dos nodos")
    print("4. Contar nodos")
    print("5. Eliminar un nodo")
    print("6. Buscar un nodo")
    print("7. Imprimir valores de la lista")
    print("8. Imprimir lista completa")
    print("9. Imprimir lista al revés")
    print("10. Copiar lista a archivo")
    print("11. Ordenar lista (extra)")
    print("12. Cambiar tipo de lista")
    print("13. Salir")
    print("-"*40)

def main():
    """
    Función principal del programa
    """
    lista_actual = None
    
    while True:
        mostrar_menu()
        
        try:
            opcion = int(input("\nSeleccione una opción: "))
        except ValueError:
            print("Error: Ingrese un número válido")
            continue
        
        if opcion == 5:
            print("¡Hasta luego!")
            break
        
        # Crear la lista seleccionada
        if opcion == 1:
            lista_actual = ListaEnlazada()
            print("\nLista Enlazada Simple creada")
        elif opcion == 2:
            lista_actual = ListaDoblementeEnlazada()
            print("\nLista Doblemente Enlazada creada")
        elif opcion == 3:
            lista_actual = ListaCircular()
            print("\nLista Circular Enlazada creada")
        elif opcion == 4:
            lista_actual = ListaCircularDoblementeEnlazada()
            print("\nLista Circular Doblemente Enlazada creada")
        else:
            print("Opción no válida")
            continue
        
        # Submenú de operaciones
        while True:
            mostrar_submenu()
            
            try:
                sub_opcion = int(input("\nSeleccione una operación: "))
            except ValueError:
                print("Error: Ingrese un número válido")
                continue
            
            if sub_opcion == 13:
                print("Volviendo al menú principal...")
                break
            elif sub_opcion == 12:
                print("Cambiando tipo de lista...")
                break
            
            # Ejecutar la operación seleccionada
            if lista_actual is None:
                print("Error: No hay lista creada")
                continue
            
            if sub_opcion == 1:
                # Insertar al inicio
                try:
                    valor = int(input("Ingrese el valor del nodo (1-98): "))
                    lista_actual.insertar_inicio(valor)
                except ValueError:
                    print("Error: Ingrese un número válido")
            
            elif sub_opcion == 2:
                # Insertar al final
                try:
                    valor = int(input("Ingrese el valor del nodo (1-98): "))
                    lista_actual.insertar_final(valor)
                except ValueError:
                    print("Error: Ingrese un número válido")
            
            elif sub_opcion == 3:
                # Insertar entre nodos
                try:
                    valor = int(input("Ingrese el valor del nuevo nodo (1-98): "))
                    anterior = input("Ingrese valor del nodo anterior (0 para inicio, Enter para omitir): ")
                    siguiente = input("Ingrese valor del nodo siguiente (99 para final, Enter para omitir): ")
                    
                    # Convertir a enteros o None
                    anterior = int(anterior) if anterior and anterior != "" else None
                    siguiente = int(siguiente) if siguiente and siguiente != "" else None
                    
                    lista_actual.insertar_nodo(valor, anterior, siguiente)
                except ValueError:
                    print("Error: Ingrese números válidos")
            
            elif sub_opcion == 4:
                # Contar nodos
                print(f"\nLa lista tiene {lista_actual.contar_nodos()} nodos")
            
            elif sub_opcion == 5:
                # Eliminar nodo
                lista_actual.eliminar_nodo()
            
            elif sub_opcion == 6:
                # Buscar nodo
                try:
                    valor = int(input("Ingrese el valor a buscar: "))
                    nodo = lista_actual.buscar_nodo(valor)
                    if nodo:
                        print(f"\nNodo encontrado: Valor={nodo['valor']}")
                    else:
                        print(f"\nNo se encontró el nodo con valor {valor}")
                except ValueError:
                    print("Error: Ingrese un número válido")
            
            elif sub_opcion == 7:
                # Imprimir valores
                lista_actual.imprimir_valor_lista()
            
            elif sub_opcion == 8:
                # Imprimir lista completa
                lista_actual.imprimir_lista_completa()
            
            elif sub_opcion == 9:
                # Imprimir al revés
                lista_actual.imprimir_reves()
            
            elif sub_opcion == 10:
                # Copiar a archivo
                lista_actual.copiar_lista()
            
            elif sub_opcion == 11:
                # Ordenar lista (extra)
                lista_actual.ordenar_lista()
                lista_actual.imprimir_valor_lista()
            
            else:
                print("Opción no válida")

if __name__ == "__main__":
    main()