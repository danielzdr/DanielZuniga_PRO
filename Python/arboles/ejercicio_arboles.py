#1. Implementacion de recorridos inorder y preorder en árboles binarios
def inorder_recursivo(raiz):
    """Recorrido inorder: izquierda -> raíz -> derecha"""
    resultado = []
    
    def recorrer(nodo):
        if nodo is None:
            return
        recorrer(nodo[1])  # izquierda
        resultado.append(nodo[0])  # valor
        recorrer(nodo[2])  # derecha
    
    recorrer(raiz)
    return resultado


def preorder_recursivo(raiz):
    """Recorrido preorder: raíz -> izquierda -> derecha"""
    resultado = []
    
    def recorrer(nodo):
        if nodo is None:
            return
        resultado.append(nodo[0])  # valor
        recorrer(nodo[1])  # izquierda
        recorrer(nodo[2])  # derecha
    
    recorrer(raiz)
    return resultado


# 2. FUNCIONES DE RECORRIDO ITERATIVAS
def inorder_iterativo(raiz):
    """Recorrido inorder iterativo usando pila"""
    resultado = []
    pila = []
    actual = raiz
    
    while actual or pila:
        # Ir lo más a la izquierda posible
        while actual:
            pila.append(actual)
            actual = actual[1]  # izquierda
        
        # Procesar el nodo más a la izquierda
        actual = pila.pop()
        resultado.append(actual[0])  # valor
        
        # Mover al subárbol derecho
        actual = actual[2]  # derecha
    
    return resultado


def preorder_iterativo(raiz):
    """Recorrido preorder iterativo usando pila"""
    if raiz is None:
        return []
    
    resultado = []
    pila = [raiz]
    
    while pila:
        nodo = pila.pop()
        resultado.append(nodo[0])  # valor
        
        # Apilar primero derecha, luego izquierda (pila LIFO)
        if nodo[2]:  # derecha
            pila.append(nodo[2])
        if nodo[1]:  # izquierda
            pila.append(nodo[1])
    
    return resultado

# 3. FUNCIONES AUXILIARES PARA CREAR ÁRBOLES
def crear_nodo(valor, izquierda=None, derecha=None):
    """Crea un nodo como tupla: (valor, izquierda, derecha)"""
    return (valor, izquierda, derecha)


def crear_arbol_ejemplo(): 
    nodo4 = crear_nodo(4)
    nodo5 = crear_nodo(5)
    nodo6 = crear_nodo(6)
    nodo7 = crear_nodo(7)
    
    nodo2 = crear_nodo(2, nodo4, nodo5)
    nodo3 = crear_nodo(3, nodo6, nodo7)
    
    raiz = crear_nodo(1, nodo2, nodo3)
    return raiz


def crear_arbol_bst():
    # Crear hojas
    nodo1 = crear_nodo(1)
    nodo3 = crear_nodo(3)
    nodo5 = crear_nodo(5)
    nodo7 = crear_nodo(7)
    
    # Nodos intermedios
    nodo2 = crear_nodo(2, nodo1, nodo3)
    nodo6 = crear_nodo(6, nodo5, nodo7)
    
    # Raíz
    raiz = crear_nodo(4, nodo2, nodo6)
    return raiz


def crear_arbol_simple():
    
    nodo4 = crear_nodo(4)
    nodo2 = crear_nodo(2, None, nodo4)
    nodo3 = crear_nodo(3)
    raiz = crear_nodo(1, nodo2, nodo3)
    return raiz

# 4. FUNCIONES PARA VISUALIZAR
def visualizar_arbol(raiz, nivel=0, prefijo="R"):
    if raiz is None:
        return
    
    valor, izquierda, derecha = raiz
    print("  " * nivel + prefijo + ": " + str(valor))
    
    if izquierda or derecha:
        if izquierda:
            visualizar_arbol(izquierda, nivel + 1, "L")
        if derecha:
            visualizar_arbol(derecha, nivel + 1, "R")



def demostrar_todos():
    print("=" * 50)
    print("DEMOSTRACIÓN DE RECORRIDOS INORDER Y PREORDER")
    print("=" * 50)
    
    print("\n1. ÁRBOL BINARIO COMPLETO")
    print("-" * 30)
    arbol1 = crear_arbol_ejemplo()
    
    print("Árbol:")
    visualizar_arbol(arbol1)
    
    print("\nRecorridos:")
    print(f"Inorder (recursivo):  {inorder_recursivo(arbol1)}")
    print(f"Inorder (iterativo):  {inorder_iterativo(arbol1)}")
    print(f"Preorder (recursivo): {preorder_recursivo(arbol1)}")
    print(f"Preorder (iterativo): {preorder_iterativo(arbol1)}")
    
    
    print("\n\n2. ÁRBOL BINARIO DE BÚSQUEDA (BST)")
    print("-" * 30)
    arbol2 = crear_arbol_bst()
    
    print("Árbol (BST - valores ordenados en inorder):")
    visualizar_arbol(arbol2)
    
    print("\nRecorridos:")
    print(f"Inorder (recursivo):  {inorder_recursivo(arbol2)}  ← Valores ordenados!")
    print(f"Inorder (iterativo):  {inorder_iterativo(arbol2)}")
    print(f"Preorder (recursivo): {preorder_recursivo(arbol2)}")
    print(f"Preorder (iterativo): {preorder_iterativo(arbol2)}")
    
    
    print("\n\n3. ÁRBOL SIMPLE (no balanceado)")
    print("-" * 30)
    arbol3 = crear_arbol_simple()
    
    print("Árbol:")
    visualizar_arbol(arbol3)
    
    print("\nRecorridos:")
    print(f"Inorder (recursivo):  {inorder_recursivo(arbol3)}")
    print(f"Inorder (iterativo):  {inorder_iterativo(arbol3)}")
    print(f"Preorder (recursivo): {preorder_recursivo(arbol3)}")
    print(f"Preorder (iterativo): {preorder_iterativo(arbol3)}")
    
    
    print("\n\n4. ÁRBOL VACÍO")
    print("-" * 30)
    arbol_vacio = None
    
    print("Árbol: (vacío)")
    
    print("\nRecorridos:")
    print(f"Inorder (recursivo):  {inorder_recursivo(arbol_vacio)}")
    print(f"Inorder (iterativo):  {inorder_iterativo(arbol_vacio)}")
    print(f"Preorder (recursivo): {preorder_recursivo(arbol_vacio)}")
    print(f"Preorder (iterativo): {preorder_iterativo(arbol_vacio)}")
    

    print("\n\n5. RESUMEN DE RESULTADOS")
    print("-" * 30)
    print("=" * 50)

# 6. FUNCIÓN PARA PROBAR CON ÁRBOL PERSONALIZADO
def probar_arbol_personalizado():
    """Permite al usuario probar con su propio árbol"""
    print("\n" + "=" * 50)
    print("PROBAR CON ÁRBOL PERSONALIZADO")
    print("=" * 50)
    
    # Ejemplo de árbol personalizado
    print("Creando árbol personalizado:")
    print("        A")
    print("       / \\")
    print("      B   C")
    print("     /   / \\")
    print("    D   E   F")
    
    # Crear el árbol
    nodo_d = crear_nodo('D')
    nodo_e = crear_nodo('E')
    nodo_f = crear_nodo('F')
    
    nodo_b = crear_nodo('B', nodo_d, None)
    nodo_c = crear_nodo('C', nodo_e, nodo_f)
    
    arbol = crear_nodo('A', nodo_b, nodo_c)
    
    print("\nÁrbol creado:")
    visualizar_arbol(arbol)
    
    print("\nRecorridos:")
    print(f"Inorder (recursivo):  {inorder_recursivo(arbol)}")
    print(f"Preorder (recursivo): {preorder_recursivo(arbol)}")
    
    return arbol

# 7. FUNCIÓN PARA COMPARAR RESULTADOS
def comparar_recursivo_iterativo():
    """Compara que los resultados recursivos e iterativos sean iguales"""
    print("\n" + "=" * 50)
    print("COMPARACIÓN RECURSIVO vs ITERATIVO")
    print("=" * 50)
    
    arboles = [
        ("Árbol completo", crear_arbol_ejemplo()),
        ("BST", crear_arbol_bst()),
        ("Árbol simple", crear_arbol_simple()),
        ("Árbol vacío", None)]
    
    for nombre, arbol in arboles:
        print(f"\n{nombre}:")
        
        # Inorder
        in_rec = inorder_recursivo(arbol)
        in_iter = inorder_iterativo(arbol)
        igual_inorder = in_rec == in_iter
        
        # Preorder
        pre_rec = preorder_recursivo(arbol)
        pre_iter = preorder_iterativo(arbol)
        igual_preorder = pre_rec == pre_iter
        
        print(f"  Inorder:  Recursivo {in_rec} == Iterativo {in_iter} -> {'✓' if igual_inorder else '✗'}")
        print(f"  Preorder: Recursivo {pre_rec} == Iterativo {pre_iter} -> {'✓' if igual_preorder else '✗'}")
        
        if igual_inorder and igual_preorder:
            print(" Todas las implementaciones coinciden")
        else:
            print(" Hay diferencias entre implementaciones")
#8. BLOQUE PRINCIPAL
if __name__ == "__main__":
    # Ejecutar demostración completa
    demostrar_todos()
    
    # Comparar implementaciones
    comparar_recursivo_iterativo()
    
    # Probar árbol personalizado
    probar_arbol_personalizado()
    
    print("\n" + "=" * 50)
    print("FIN DE LA DEMOSTRACIÓN")
    print("=" * 50)