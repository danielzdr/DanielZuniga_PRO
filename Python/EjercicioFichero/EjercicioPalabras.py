#Ejercicios palabras
def procesar_archivo():
    # Leer el archivo de entrada
    with open('palabras.txt', 'r') as archivo:
        contenido = archivo.read()
    
    # Dividir en palabras
    palabras = contenido.split()
    
    # 1. Numero total de palabras
    total_palabras = len(palabras)
    
    # 2. Numero total de vocales
    vocales = 'aeiouáéíóú'
    total_vocales = 0
    for palabra in palabras:
        for letra in palabra.lower():
            if letra in vocales:
                total_vocales += 1
    
    # 3. Contar repeticiones de palabras
    contador_palabras = {}
    for palabra in palabras:
        if palabra in contador_palabras:
            contador_palabras[palabra] += 1
        else:
            contador_palabras[palabra] = 1
    
    # Encontrar las palabras que se repiten
    palabras_repetidas = {}
    for palabra, count in contador_palabras.items():
        if count > 1:
            palabras_repetidas[palabra] = count
    
    # Ordenar palabras repetidas por frecuencia
    # Convertimos el diccionario a lista de tuplas
    lista_para_ordenar = []
    for palabra, count in palabras_repetidas.items():
        lista_para_ordenar.append((palabra, count))
    
    # Ordenamiento manual
    n = len(lista_para_ordenar)
    for i in range(n):
        for j in range(0, n - i - 1):
            # Comparamos por el numero de repeticiones
            if lista_para_ordenar[j][1] < lista_para_ordenar[j + 1][1]:
                # Intercambiamos las posiciones
                temp = lista_para_ordenar[j]
                lista_para_ordenar[j] = lista_para_ordenar[j + 1]
                lista_para_ordenar[j + 1] = temp
    
    lista_ordenada = lista_para_ordenar
    
    # Encontrar las palabras que mas se repiten
    max_repeticiones = 0
    for count in contador_palabras.values():
        if count > max_repeticiones:
            max_repeticiones = count
    
    palabras_mas_repetidas = []
    for palabra, count in contador_palabras.items():
        if count == max_repeticiones:
            palabras_mas_repetidas.append(palabra)
    
    # Escribir en salida.txt
    with open('salida.txt', 'w') as archivo_salida:
        archivo_salida.write("RESULTADOS DEL ANÁLISIS DE palabras.txt\n")
        archivo_salida.write("=" * 50 + "\n\n")
        
        archivo_salida.write(f"1. Número total de palabras: {total_palabras}\n")
        archivo_salida.write(f"2. Número total de vocales: {total_vocales}\n\n")
        
        archivo_salida.write("3. Palabras que se repiten:\n")
        if lista_ordenada:
            for palabra, count in lista_ordenada:
                archivo_salida.write(f"   - '{palabra}': {count} veces\n")
        else:
            archivo_salida.write("   No hay palabras repetidas\n")
        
        archivo_salida.write(f"\n4. Palabras que mas se repite(n):\n")
        for palabra in palabras_mas_repetidas:
            archivo_salida.write(f"   - '{palabra}': {max_repeticiones} veces\n")
    
    # Escribir en salida2.txt (solo la informacion de repeticiones)
    with open('salida2.txt', 'w') as archivo_salida2:
        archivo_salida2.write("INFORMACION DE PALABRAS REPETIDAS\n")
        archivo_salida2.write("=" * 40 + "\n\n")
        
        archivo_salida2.write("Palabras que se repiten:\n")
        if lista_ordenada:
            for palabra, count in lista_ordenada:
                archivo_salida2.write(f"   - '{palabra}': {count} veces\n")
        else:
            archivo_salida2.write("   No hay palabras repetidas\n")
        
        archivo_salida2.write(f"\nPalabras que mas se repite(n):\n")
        for palabra in palabras_mas_repetidas:
            archivo_salida2.write(f"   - '{palabra}': {max_repeticiones} veces\n")
    
    # Mostrar resultados por pantalla
    print("RESULTADOS DEL ANALISIS DE palabras.txt")
    print("=" * 50)
    print(f"1. Numero total de palabras: {total_palabras}")
    print(f"2. Numero total de vocales: {total_vocales}")
    
    print("\n3. Palabras que se repiten:")
    if lista_ordenada:
        for palabra, count in lista_ordenada:
            print(f"   - '{palabra}': {count} veces")
    else:
        print("   No hay palabras repetidas")
    
    print(f"\n4. Palabras que mas se repite(n):")
    for palabra in palabras_mas_repetidas:
        print(f"   - '{palabra}': {max_repeticiones} veces")
    
    print(f"\nLos resultados completos se han guardado en 'salida.txt'")
    print(f"La informacion de repeticiones se ha guardado en 'salida2.txt'")

# Ejecutar la funcion
    procesar_archivo()