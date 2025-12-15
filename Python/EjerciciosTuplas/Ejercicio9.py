#Ejercicio9
def operaciones_tuplas(tupla1, tupla2):
    # Intersección (elementos comunes)
    interseccion = tuple(set(tupla1) & set(tupla2))
    
    # Diferencia simétrica (elementos que están en una pero no en otra)
    diferencia = tuple(set(tupla1) ^ set(tupla2))
    
    return interseccion, diferencia

# Ejemplo de uso
tupla1 = (1, 2, 3, 4, 5)
tupla2 = (3, 4, 5, 6, 7)

interseccion, diferencia = operaciones_tuplas(tupla1, tupla2)
print(f"Tupla 1: {tupla1}")
print(f"Tupla 2: {tupla2}")
print(f"Intersección: {interseccion}")
print(f"Diferencia simétrica: {diferencia}")