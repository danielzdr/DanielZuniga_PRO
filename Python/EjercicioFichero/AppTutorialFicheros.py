#Aplicacion tutorial ejercicio ficheros
import os
import time

def mostrar_titulo():
    print("=" * 60)
    print("       TUTORIAL: MANEJO DE FICHEROS EN PYTHON")
    print("=" * 60)
    print()

def pausa():
    input("Presiona Enter para continuar...")
    print()

def explicacion_lectura():
    print("📖 PASO 1: LECTURA DE FICHEROS")
    print("-" * 40)
    print("Para leer un fichero en Python usamos:")
    print("""
    with open('nombre_archivo.txt', 'r', encoding='utf-8') as archivo:
        contenido = archivo.read()
    """)
    print("• 'r' significa modo lectura (read)")
    print("• 'encoding=utf-8' permite leer caracteres especiales")
    print("• 'with open()' cierra automáticamente el archivo")
    pausa()
    
    # Demostración práctica
    print("🔍 DEMOSTRACIÓN PRÁCTICA:")
    if os.path.exists('palabras.txt'):
        with open('palabras.txt', 'r', encoding='utf-8') as archivo:
            contenido = archivo.read()
            palabras = contenido.split()
        
        print(f"✓ Se leyó el archivo 'palabras.txt'")
        print(f"✓ Contenido dividido en {len(palabras)} palabras")
        print(f"✓ Primeras 5 palabras: {palabras[:5]}")
    else:
        print("❌ El archivo 'palabras.txt' no existe")
    pausa()

def explicacion_procesamiento():
    print("🔄 PASO 2: PROCESAMIENTO DE DATOS")
    print("-" * 40)
    print("Una vez leído el contenido, podemos procesarlo:")
    print("""
    # Contar palabras totales
    total_palabras = len(palabras)
    
    # Contar vocales
    vocales = 'aeiouáéíóú'
    total_vocales = 0
    for palabra in palabras:
        for letra in palabra.lower():
            if letra in vocales:
                total_vocales += 1
    """)
    pausa()
    
    # Demostración práctica
    print("🔍 DEMOSTRACIÓN PRÁCTICA:")
    if os.path.exists('palabras.txt'):
        with open('palabras.txt', 'r', encoding='utf-8') as archivo:
            contenido = archivo.read()
            palabras = contenido.split()
        
        # Procesamiento
        total_palabras = len(palabras)
        vocales = 'aeiouáéíóú'
        total_vocales = 0
        for palabra in palabras:
            for letra in palabra.lower():
                if letra in vocales:
                    total_vocales += 1
        
        print(f"✓ Total de palabras: {total_palabras}")
        print(f"✓ Total de vocales: {total_vocales}")
    pausa()

def explicacion_contador_palabras():
    print("📊 PASO 3: CONTEO DE PALABRAS REPETIDAS")
    print("-" * 40)
    print("Para contar repeticiones usamos un diccionario:")
    print("""
    contador = {}
    for palabra in palabras:
        if palabra in contador:
            contador[palabra] += 1
        else:
            contador[palabra] = 1
    """)
    pausa()
    
    # Demostración práctica
    print("🔍 DEMOSTRACIÓN PRÁCTICA:")
    if os.path.exists('palabras.txt'):
        with open('palabras.txt', 'r', encoding='utf-8') as archivo:
            contenido = archivo.read()
            palabras = contenido.split()
        
        # Contar palabras
        contador = {}
        for palabra in palabras:
            if palabra in contador:
                contador[palabra] += 1
            else:
                contador[palabra] = 1
        
        # Mostrar algunas estadísticas
        palabras_unicas = len(contador)
        print(f"✓ Palabras únicas: {palabras_unicas}")
        
        # Mostrar las 3 primeras palabras con su conteo
        print("✓ Ejemplo de conteo (primeras 3 palabras únicas):")
        contador_lista = list(contador.items())
        for i in range(min(3, len(contador_lista))):
            palabra, count = contador_lista[i]
            print(f"   '{palabra}': {count} veces")
    pausa()

def explicacion_escritura():
    print("✍️ PASO 4: ESCRITURA DE FICHEROS")
    print("-" * 40)
    print("Para escribir en un fichero usamos:")
    print("""
    with open('salida.txt', 'w', encoding='utf-8') as archivo:
        archivo.write("Texto a escribir\\n")
        archivo.write(f"Resultados: {variable}\\n")
    """)
    print("• 'w' significa modo escritura (write)")
    print("• Si el archivo existe, se sobreescribe")
    print("• Si no existe, se crea automáticamente")
    pausa()
    
    # Demostración práctica
    print("🔍 DEMOSTRACIÓN PRÁCTICA:")
    with open('ejemplo_salida.txt', 'w', encoding='utf-8') as archivo:
        archivo.write("Este es un archivo de ejemplo\\n")
        archivo.write("Creado por el tutorial de Python\\n")
        archivo.write(f"Fecha: {time.strftime('%Y-%m-%d')}\\n")
    
    print("✓ Se creó el archivo 'ejemplo_salida.txt'")
    
    # Leer para mostrar el contenido
    with open('ejemplo_salida.txt', 'r', encoding='utf-8') as archivo:
        contenido = archivo.read()
    
    print("✓ Contenido del archivo creado:")
    print(contenido)
    pausa()

def explicacion_modo_append():
    print("📝 PASO 5: MODO APPEND (AÑADIR)")
    print("-" * 40)
    print("Para añadir contenido sin borrar lo existente:")
    print("""
    with open('archivo.txt', 'a', encoding='utf-8') as archivo:
        archivo.write("Nueva línea\\n")
    """)
    print("• 'a' significa modo append (añadir)")
    print("• El contenido se añade al final del archivo")
    pausa()
    
    # Demostración práctica
    print("🔍 DEMOSTRACIÓN PRÁCTICA:")
    with open('ejemplo_salida.txt', 'a', encoding='utf-8') as archivo:
        archivo.write("--- Línea añadida en modo append ---\\n")
    
    print("✓ Se añadió una línea al archivo 'ejemplo_salida.txt'")
    
    # Leer para mostrar el contenido actualizado
    with open('ejemplo_salida.txt', 'r', encoding='utf-8') as archivo:
        contenido = archivo.read()
    
    print("✓ Contenido actualizado:")
    print(contenido)
    pausa()

def demostracion_completa():
    print("🚀 DEMOSTRACIÓN COMPLETA")
    print("-" * 40)
    print("Ahora ejecutaremos el programa completo del ejercicio anterior:")
    
    if os.path.exists('palabras.txt'):
        # Leer el archivo
        with open('palabras.txt', 'r', encoding='utf-8') as archivo:
            contenido = archivo.read()
        
        palabras = contenido.split()
        
        # Procesar datos
        total_palabras = len(palabras)
        
        vocales = 'aeiouáéíóú'
        total_vocales = 0
        for palabra in palabras:
            for letra in palabra.lower():
                if letra in vocales:
                    total_vocales += 1
        
        # Contar palabras
        contador = {}
        for palabra in palabras:
            if palabra in contador:
                contador[palabra] += 1
            else:
                contador[palabra] = 1
        
        # Encontrar palabras repetidas
        palabras_repetidas = {}
        for palabra, count in contador.items():
            if count > 1:
                palabras_repetidas[palabra] = count
        
        # Ordenar manualmente
        lista_para_ordenar = []
        for palabra, count in palabras_repetidas.items():
            lista_para_ordenar.append((palabra, count))
        
        # Bubble sort
        n = len(lista_para_ordenar)
        for i in range(n):
            for j in range(0, n - i - 1):
                if lista_para_ordenar[j][1] < lista_para_ordenar[j + 1][1]:
                    temp = lista_para_ordenar[j]
                    lista_para_ordenar[j] = lista_para_ordenar[j + 1]
                    lista_para_ordenar[j + 1] = temp
        
        # Encontrar máximo
        max_repeticiones = 0
        for count in contador.values():
            if count > max_repeticiones:
                max_repeticiones = count
        
        palabras_mas_repetidas = []
        for palabra, count in contador.items():
            if count == max_repeticiones:
                palabras_mas_repetidas.append(palabra)
        
        # Escribir resultados
        with open('salida_tutorial.txt', 'w', encoding='utf-8') as archivo:
            archivo.write("RESULTADOS COMPLETOS - TUTORIAL\\n")
            archivo.write("=" * 50 + "\\n\\n")
            archivo.write(f"Total palabras: {total_palabras}\\n")
            archivo.write(f"Total vocales: {total_vocales}\\n\\n")
            archivo.write("Palabras repetidas:\\n")
            for palabra, count in lista_para_ordenar:
                archivo.write(f"- '{palabra}': {count} veces\\n")
            archivo.write(f"\\nPalabra(s) más repetida(s):\\n")
            for palabra in palabras_mas_repetidas:
                archivo.write(f"- '{palabra}': {max_repeticiones} veces\\n")
        
        print("✓ Procesamiento completado")
        print("✓ Archivo 'salida_tutorial.txt' creado")
        print("\\n📊 RESULTADOS:")
        print(f"  • Total de palabras: {total_palabras}")
        print(f"  • Total de vocales: {total_vocales}")
        print(f"  • Palabras únicas: {len(contador)}")
        print(f"  • Palabras repetidas: {len(palabras_repetidas)}")
        print(f"  • Palabra(s) más repetida(s): {', '.join(palabras_mas_repetidas)}")
        
    else:
        print("❌ El archivo 'palabras.txt' no existe para la demostración")
    pausa()

def resumen_modos_archivo():
    print("📋 RESUMEN: MODOS DE APERTURA DE ARCHIVOS")
    print("-" * 45)
    modos = [
        ("'r'", "Lectura", "Solo lectura, archivo debe existir"),
        ("'w'", "Escritura", "Crea o sobreescribe el archivo"),
        ("'a'", "Append", "Añade al final, crea si no existe"),
        ("'r+'", "Lectura/Escritura", "Archivo debe existir"),
        ("'w+'", "Escritura/Lectura", "Crea o sobreescribe"),
        ("'a+'", "Append/Lectura", "Añade y permite lectura")
    ]
    
    for modo, nombre, descripcion in modos:
        print(f"{modos:>4} - {nombre:<12} : {descripcion}")
    pausa()

def tutorial_completo():
    mostrar_titulo()
    
    print("¡Bienvenido al tutorial de manejo de ficheros en Python!")
    print("Este tutorial te guiará paso a paso por el ejercicio de análisis de palabras.")
    pausa()
    
    explicacion_lectura()
    explicacion_procesamiento()
    explicacion_contador_palabras()
    explicacion_escritura()
    explicacion_modo_append()
    resumen_modos_archivo()
    demostracion_completa()
    
    print("🎉 ¡TUTORIAL COMPLETADO!")
    print("=" * 60)
    print("Has aprendido:")
    print("✓ Cómo leer archivos en Python")
    print("✓ Cómo procesar y analizar datos de texto")
    print("✓ Cómo contar elementos usando diccionarios")
    print("✓ Cómo escribir resultados en archivos")
    print("✓ Los diferentes modos de apertura de archivos")
    print()
    print("Archivos creados durante el tutorial:")
    print("• ejemplo_salida.txt - Ejemplo de escritura")
    print("• salida_tutorial.txt - Resultados completos")
    print()
    print("¡Sigue practicando! 📚")

# Ejecutar el tutorial
if __name__ == "__main__":
    tutorial_completo()