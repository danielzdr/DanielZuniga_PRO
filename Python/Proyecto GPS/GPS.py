#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
SISTEMA DE RUTAS ÓPTIMAS CON INTERFAZ GRÁFICA
Autores: Varios
Fecha: [Fecha actual]
Descripción: Sistema completo para cálculo de rutas óptimas usando Dijkstra, 
             con interfaz gráfica de login/registro y sistema de usuarios.
"""

import mysql.connector 
import hashlib
import json
import os
import csv
import heapq
import re
from datetime import datetime, timedelta
import tkinter as tk
from tkinter import messagebox, ttk, filedialog
import threading

# ============================================================================
# CONFIGURACIÓN GLOBAL
# ============================================================================
CONFIG_BASE_DATOS = {
    'host': 'localhost',
    'user': 'root',
    'password': '3306',
    'database': 'sistema_rutas'
}

# Configuración de seguridad
MAX_INTENTOS = 5
BLOQUEO_SEGUNDOS = 300  # 5 minutos

# Variables globales para estado
usuario_actual = None
grafo_memoria = None
ventana_principal = None

# ============================================================================
# FASE 1: FUNCIONES DAO (Acceso a Base de Datos)
# ============================================================================

def conectar_base_datos():
    """Crea y devuelve conexión a MySQL"""
    try:
        conexion = mysql.connector.connect(**CONFIG_BASE_DATOS)
        return conexion
    except mysql.connector.Error as err:
        print(f"Error conectando a BD: {err}")
        return None

# ----------------------------------------------------------------------------
# Funciones para nodos
# ----------------------------------------------------------------------------
def obtener_nodos():
    """Obtiene todos los nodos de la base de datos"""
    conexion = conectar_base_datos()
    if not conexion:
        return []
    
    cursor = conexion.cursor(dictionary=True)
    cursor.execute("SELECT id, nombre, coordenadas FROM nodos ORDER BY nombre")
    nodos = cursor.fetchall()
    
    cursor.close()
    conexion.close()
    return nodos

def obtener_nodo_por_id(nodo_id):
    """Obtiene un nodo específico por ID"""
    conexion = conectar_base_datos()
    if not conexion:
        return None
    
    cursor = conexion.cursor(dictionary=True)
    cursor.execute("SELECT id, nombre FROM nodos WHERE id = %s", (nodo_id,))
    nodo = cursor.fetchone()
    
    cursor.close()
    conexion.close()
    return nodo

def obtener_nodo_por_nombre(nombre):
    """Obtiene un nodo por su nombre"""
    conexion = conectar_base_datos()
    if not conexion:
        return None
    
    cursor = conexion.cursor(dictionary=True)
    cursor.execute("SELECT id, nombre FROM nodos WHERE nombre LIKE %s", (f"%{nombre}%",))
    nodo = cursor.fetchone()
    
    cursor.close()
    conexion.close()
    return nodo

# ----------------------------------------------------------------------------
# Funciones para aristas y pesos
# ----------------------------------------------------------------------------
def obtener_aristas():
    """Obtiene todas las aristas con sus pesos"""
    conexion = conectar_base_datos()
    if not conexion:
        return []
    
    cursor = conexion.cursor(dictionary=True)
    query = """
    SELECT a.id, a.origen_id, a.destino_id, a.tipo, p.coste 
    FROM aristas a 
    JOIN pesos p ON a.id = p.arista_id
    """
    cursor.execute(query)
    aristas = cursor.fetchall()
    
    cursor.close()
    conexion.close()
    return aristas

def obtener_pesos():
    """Obtiene todos los pesos de las aristas"""
    conexion = conectar_base_datos()
    if not conexion:
        return []
    
    cursor = conexion.cursor(dictionary=True)
    cursor.execute("SELECT id, arista_id, coste FROM pesos")
    pesos = cursor.fetchall()
    
    cursor.close()
    conexion.close()
    return pesos

# ----------------------------------------------------------------------------
# Funciones para usuarios (SEGURIDAD) - NO MODIFICAR
# ----------------------------------------------------------------------------
def generar_salt():
    """Genera un salt aleatorio para el hash de la contraseña"""
    import secrets
    return secrets.token_hex(16)

def hash_contrasena(contrasena, salt):
    """Genera hash SHA-512 de la contraseña con salt"""
    return hashlib.sha512((contrasena + salt).encode()).hexdigest()

def email_valido(email):
    """Valida el formato de un email"""
    return re.match(r"[^@]+@[^@]+\.[^@]+", email)

def ocultar_correo(correo):
    """Oculta parte del correo para mostrar en mensajes"""
    try:
        nombre, dominio = correo.split("@")
        return nombre[0] + "***@" + dominio[0] + "..."
    except:
        return "***@***"

def validar_telefono(telefono):
    """Valida formato de teléfono internacional"""
    return re.match(r'^\+?\d{9,15}$', telefono)

def registrar_usuario(correo, usuario, nombre, apellido, telefono, fecha_nac, contrasena):
    """Registra un nuevo usuario con todos los datos"""
    # Validar correo
    if not email_valido(correo):
        return False, "Correo electrónico no válido"
    
    # Validar teléfono
    if not validar_telefono(telefono):
        return False, "Teléfono no válido. Use formato: +34123456789"
    
    # Verificar usuario único
    conexion = conectar_base_datos()
    if not conexion:
        return False, "Error de conexión"
    
    try:
        cursor = conexion.cursor()
        
        # Verificar si el usuario ya existe (SOLO POR USERNAME, no por correo)
        cursor.execute("SELECT id FROM usuarios WHERE username = %s", (usuario,))
        if cursor.fetchone():
            cursor.close()
            conexion.close()
            return False, "Usuario ya existente"
        
        # Generar hash seguro
        salt = generar_salt()
        hash_pass = hash_contrasena(contrasena, salt)
        
        # Insertar nuevo usuario (solo los campos que existen en tu BD)
        cursor.execute(
            "INSERT INTO usuarios (username, password_hash, salt) VALUES (%s, %s, %s)",
            (usuario, hash_pass, salt)
        )
        
        conexion.commit()
        usuario_id = cursor.lastrowid
        
        cursor.close()
        conexion.close()
        
        return True, f"Usuario {usuario} registrado con ID: {usuario_id}"
    except mysql.connector.Error as err:
        return False, f"Error al registrar usuario: {err}"

def validar_usuario(username, contrasena):
    """Valida credenciales de usuario con sistema de bloqueo"""
    conexion = conectar_base_datos()
    if not conexion:
        return None, "Error de conexión"
    
    cursor = conexion.cursor(dictionary=True)
    cursor.execute(
        """SELECT id, username, password_hash, salt, intentos, bloqueado_hasta 
           FROM usuarios WHERE username = %s""",
        (username,)
    )
    usuario = cursor.fetchone()
    
    if not usuario:
        cursor.close()
        conexion.close()
        return None, "Usuario no encontrado"
    
    # Verificar si la cuenta está bloqueada
    if usuario['bloqueado_hasta'] and usuario['bloqueado_hasta'] > datetime.now():
        cursor.close()
        conexion.close()
        return None, "Cuenta bloqueada temporalmente por demasiados intentos"
    
    # Verificar contraseña
    hash_calculado = hash_contrasena(contrasena, usuario['salt'])
    
    if hash_calculado == usuario['password_hash']:
        # Login exitoso: resetear intentos
        cursor.execute(
            "UPDATE usuarios SET intentos = 0, bloqueado_hasta = NULL WHERE id = %s",
            (usuario['id'],)
        )
        conexion.commit()
        cursor.close()
        conexion.close()
        return usuario, "Credenciales válidas"
    else:
        # Login fallido: incrementar intentos
        intentos = usuario['intentos'] + 1
        
        if intentos >= MAX_INTENTOS:
            # Bloquear cuenta
            bloqueo_hasta = datetime.now() + timedelta(seconds=BLOQUEO_SEGUNDOS)
            cursor.execute(
                """UPDATE usuarios SET intentos = %s, bloqueado_hasta = %s 
                   WHERE id = %s""",
                (intentos, bloqueo_hasta, usuario['id'])
            )
        else:
            cursor.execute(
                "UPDATE usuarios SET intentos = %s WHERE id = %s",
                (intentos, usuario['id'])
            )
        
        conexion.commit()
        cursor.close()
        conexion.close()
        return None, "Contraseña incorrecta"

def recuperar_contrasena(usuario):
    """Simula el proceso de recuperación de contraseña"""
    conexion = conectar_base_datos()
    if not conexion:
        return False, "Error de conexión"
    
    cursor = conexion.cursor(dictionary=True)
    cursor.execute("SELECT username FROM usuarios WHERE username = %s", (usuario,))
    fila = cursor.fetchone()
    
    cursor.close()
    conexion.close()
    
    if not fila:
        return False, "Usuario no encontrado"
    
    # En un sistema real, aquí se enviaría un email al correo asociado
    # Como no tenemos correo en BD, mostramos un mensaje genérico
    return True, f"Se enviarán instrucciones de recuperación al email asociado a '{usuario}'"

# ----------------------------------------------------------------------------
# Funciones para historial
# ----------------------------------------------------------------------------
def guardar_ruta_historico(usuario_id, origen_id, destino_id, coste_total, es_alternativa=False):
    """Guarda una ruta calculada en el historial"""
    conexion = conectar_base_datos()
    if not conexion:
        return False
    
    try:
        cursor = conexion.cursor()
        cursor.execute(
            """INSERT INTO historico_rutas 
            (usuario_id, origen_id, destino_id, coste_total, ruta_alternativa) 
            VALUES (%s, %s, %s, %s, %s)""",
            (usuario_id, origen_id, destino_id, coste_total, es_alternativa)
        )
        conexion.commit()
        
        cursor.close()
        conexion.close()
        return True
    except mysql.connector.Error:
        return False

def obtener_historico_usuario(usuario_id, limite=10):
    """Obtiene historial de rutas de un usuario"""
    conexion = conectar_base_datos()
    if not conexion:
        return []
    
    cursor = conexion.cursor(dictionary=True)
    query = """
    SELECT hr.*, o.nombre as origen_nombre, d.nombre as destino_nombre
    FROM historico_rutas hr
    JOIN nodos o ON hr.origen_id = o.id
    JOIN nodos d ON hr.destino_id = d.id
    WHERE hr.usuario_id = %s
    ORDER BY hr.fecha_hora DESC
    LIMIT %s
    """
    cursor.execute(query, (usuario_id, limite))
    historial = cursor.fetchall()
    
    cursor.close()
    conexion.close()
    return historial

# ----------------------------------------------------------------------------
# Funciones para favoritos
# ----------------------------------------------------------------------------
def guardar_favorito(usuario_id, nodo_id, nombre_favorito):
    """Guarda una ubicación favorita"""
    conexion = conectar_base_datos()
    if not conexion:
        return False, "Error de conexión"
    
    try:
        cursor = conexion.cursor()
        cursor.execute(
            "INSERT INTO favoritos (usuario_id, nodo_id, nombre_favorito) VALUES (%s, %s, %s)",
            (usuario_id, nodo_id, nombre_favorito)
        )
        conexion.commit()
        
        cursor.close()
        conexion.close()
        return True, "Favorito guardado correctamente"
    except mysql.connector.Error as err:
        return False, f"Error al guardar favorito: {err}"

def obtener_favoritos(usuario_id):
    """Obtiene todos los favoritos de un usuario"""
    conexion = conectar_base_datos()
    if not conexion:
        return []
    
    cursor = conexion.cursor(dictionary=True)
    query = """
    SELECT f.*, n.nombre as nodo_nombre
    FROM favoritos f
    JOIN nodos n ON f.nodo_id = n.id
    WHERE f.usuario_id = %s
    ORDER BY f.nombre_favorito
    """
    cursor.execute(query, (usuario_id,))
    favoritos = cursor.fetchall()
    
    cursor.close()
    conexion.close()
    return favoritos

def eliminar_favorito(favorito_id, usuario_id):
    """Elimina un favorito"""
    conexion = conectar_base_datos()
    if not conexion:
        return False
    
    try:
        cursor = conexion.cursor()
        cursor.execute(
            "DELETE FROM favoritos WHERE id = %s AND usuario_id = %s",
            (favorito_id, usuario_id)
        )
        conexion.commit()
        
        cursor.close()
        conexion.close()
        return cursor.rowcount > 0
    except mysql.connector.Error:
        return False

# ----------------------------------------------------------------------------
# Funciones para importar CSV
# ----------------------------------------------------------------------------
def importar_csv_nodos(archivo_csv):
    """Importa nodos desde archivo CSV"""
    conexion = conectar_base_datos()
    if not conexion:
        return False, "Error de conexión"
    
    try:
        cursor = conexion.cursor()
        contador = 0
        
        with open(archivo_csv, 'r', encoding='utf-8') as file:
            lector = csv.reader(file)
            next(lector)  # Saltar encabezados si existen
            
            for fila in lector:
                if len(fila) >= 2:
                    nombre = fila[0].strip()
                    coordenadas = fila[1].strip() if len(fila) > 1 and fila[1] else None
                    
                    # Verificar si el nodo ya existe
                    cursor.execute("SELECT id FROM nodos WHERE nombre = %s", (nombre,))
                    if not cursor.fetchone():
                        cursor.execute(
                            "INSERT INTO nodos (nombre, coordenadas) VALUES (%s, %s)",
                            (nombre, coordenadas)
                        )
                        contador += 1
        
        conexion.commit()
        cursor.close()
        conexion.close()
        
        return True, f"Importados {contador} nodos correctamente"
    except Exception as err:
        return False, f"Error al importar CSV: {err}"

def importar_csv_aristas(archivo_csv):
    """Importa aristas desde archivo CSV"""
    conexion = conectar_base_datos()
    if not conexion:
        return False, "Error de conexión"
    
    try:
        cursor = conexion.cursor()
        contador = 0
        
        with open(archivo_csv, 'r', encoding='utf-8') as file:
            lector = csv.reader(file)
            next(lector)  # Saltar encabezados
            
            for fila in lector:
                if len(fila) >= 4:
                    origen_id = int(fila[0])
                    destino_id = int(fila[1])
                    coste = float(fila[2])
                    tipo = fila[3].strip()
                    
                    # Verificar si la arista ya existe
                    cursor.execute(
                        "SELECT id FROM aristas WHERE origen_id = %s AND destino_id = %s",
                        (origen_id, destino_id)
                    )
                    if not cursor.fetchone():
                        # Insertar arista
                        cursor.execute(
                            "INSERT INTO aristas (origen_id, destino_id, tipo) VALUES (%s, %s, %s)",
                            (origen_id, destino_id, tipo)
                        )
                        
                        arista_id = cursor.lastrowid
                        
                        # Insertar peso
                        cursor.execute(
                            "INSERT INTO pesos (arista_id, coste) VALUES (%s, %s)",
                            (arista_id, coste)
                        )
                        
                        contador += 1
        
        conexion.commit()
        cursor.close()
        conexion.close()
        
        return True, f"Importadas {contador} aristas correctamente"
    except Exception as err:
        return False, f"Error al importar CSV: {err}"

# ============================================================================
# FASE 2: REPRESENTACIÓN DEL GRAFO
# ============================================================================

def construir_grafo():
    """Construye el grafo en memoria desde la base de datos"""
    grafo = {}
    aristas = obtener_aristas()
    
    # Inicializar todos los nodos
    nodos = obtener_nodos()
    for nodo in nodos:
        grafo[nodo['id']] = []
    
    # Agregar conexiones
    for arista in aristas:
        origen = arista['origen_id']
        destino = arista['destino_id']
        coste = float(arista['coste'])
        
        # Agregar conexión origen -> destino
        if destino not in [v[0] for v in grafo[origen]]:
            grafo[origen].append((destino, coste))
        
        # Si es bidireccional, agregar también destino -> origen
        if arista['tipo'] == 'doble':
            if origen not in [v[0] for v in grafo[destino]]:
                grafo[destino].append((origen, coste))
    
    return grafo

def obtener_vecinos(grafo, nodo):
    """Obtiene los nodos vecinos de un nodo"""
    if nodo not in grafo:
        return []
    return [vecino for vecino, _ in grafo[nodo]]

def obtener_coste(grafo, origen, destino):
    """Obtiene el coste entre dos nodos conectados directamente"""
    if origen not in grafo:
        return None
    
    for vecino, coste in grafo[origen]:
        if vecino == destino:
            return coste
    
    return None

def verificar_integridad_grafo(grafo):
    """Verifica que el grafo esté correctamente formado"""
    problemas = []
    
    # 1. Verificar que todos los nodos existan
    if not grafo:
        problemas.append("El grafo está vacío")
        return problemas
    
    # 2. Verificar costes positivos
    for nodo, conexiones in grafo.items():
        for destino, coste in conexiones:
            if coste <= 0:
                problemas.append(f"Coste no positivo: {nodo} -> {destino} = {coste}")
    
    # 3. Verificar nodos aislados
    for nodo in grafo:
        if not grafo[nodo]:
            problemas.append(f"Nodo aislado: {nodo}")
    
    if not problemas:
        return ["Grafo verificado: OK"]
    return problemas

# ============================================================================
# FASE 3: ALGORITMO DE DIJKSTRA
# ============================================================================

def dijkstra(grafo, origen, destino):
    """Implementa el algoritmo de Dijkstra para encontrar el camino más corto"""
    if origen not in grafo or destino not in grafo:
        return None, float('inf'), []
    
    # Inicializar estructuras
    distancias = {nodo: float('inf') for nodo in grafo}
    predecesores = {nodo: None for nodo in grafo}
    visitados = set()
    
    distancias[origen] = 0
    cola_prioridad = [(0, origen)]
    
    while cola_prioridad:
        distancia_actual, nodo_actual = heapq.heappop(cola_prioridad)
        
        if nodo_actual in visitados:
            continue
        
        visitados.add(nodo_actual)
        
        # Si llegamos al destino, podemos terminar antes
        if nodo_actual == destino:
            break
        
        # Explorar vecinos
        for vecino, coste in grafo[nodo_actual]:
            if vecino in visitados:
                continue
            
            nueva_distancia = distancia_actual + coste
            
            if nueva_distancia < distancias[vecino]:
                distancias[vecino] = nueva_distancia
                predecesores[vecino] = nodo_actual
                heapq.heappush(cola_prioridad, (nueva_distancia, vecino))
    
    # Reconstruir camino
    if distancias[destino] == float('inf'):
        return None, float('inf'), []
    
    camino = []
    nodo_actual = destino
    
    while nodo_actual is not None:
        camino.append(nodo_actual)
        nodo_actual = predecesores.get(nodo_actual)
    
    camino.reverse()
    
    return camino, distancias[destino], predecesores

def encontrar_ruta_alternativa(grafo, origen, destino, camino_optimo, coste_optimo, margen_porcentaje=15):
    """Encuentra una ruta alternativa (al menos 15% diferente)"""
    if not camino_optimo or len(camino_optimo) < 3:
        return None, float('inf')
    
    # Coste máximo para alternativa
    coste_maximo = coste_optimo * (1 + margen_porcentaje / 100)
    
    # Intentar evitar alguna arista del camino óptimo
    for i in range(len(camino_optimo) - 1):
        nodo_a = camino_optimo[i]
        nodo_b = camino_optimo[i + 1]
        
        # Guardar coste original
        coste_original = obtener_coste(grafo, nodo_a, nodo_b)
        if coste_original is None:
            continue
        
        # Temporalmente eliminar esta arista
        conexiones_originales = grafo[nodo_a].copy()
        grafo[nodo_a] = [(v, c) for v, c in grafo[nodo_a] if v != nodo_b]
        
        # Calcular nueva ruta
        camino_alt, coste_alt, _ = dijkstra(grafo, origen, destino)
        
        # Restaurar arista
        grafo[nodo_a] = conexiones_originales
        
        # Verificar si es válida y diferente
        if (camino_alt and 
            camino_alt != camino_optimo and 
            coste_alt <= coste_maximo and
            coste_alt > coste_optimo):
            return camino_alt, coste_alt
    
    return None, float('inf')

def calcular_ruta_con_intermedios(grafo, origen, destino, intermedios):
    """Calcula ruta pasando por nodos intermedios en orden"""
    if not intermedios:
        return dijkstra(grafo, origen, destino)
    
    ruta_completa = []
    coste_total = 0
    ultimo_nodo = origen
    
    # Calcular ruta por segmentos
    for siguiente in intermedios + [destino]:
        segmento, coste_segmento, _ = dijkstra(grafo, ultimo_nodo, siguiente)
        
        if not segmento or coste_segmento == float('inf'):
            return None, float('inf'), []
        
        # Evitar duplicar el último nodo
        if ruta_completa:
            ruta_completa.extend(segmento[1:])
        else:
            ruta_completa.extend(segmento)
        
        coste_total += coste_segmento
        ultimo_nodo = siguiente
    
    return ruta_completa, coste_total, []

# ============================================================================
# FUNCIONES AUXILIARES PARA RUTAS REALES
# ============================================================================

def cargar_datos_ejemplo():
    """Carga datos de ejemplo con rutas reales de Madrid"""
    try:
        conexion = conectar_base_datos()
        if not conexion:
            return False
        
        cursor = conexion.cursor()
        
        # Limpiar tablas primero
        cursor.execute("DELETE FROM pesos")
        cursor.execute("DELETE FROM aristas")
        cursor.execute("DELETE FROM nodos")
        
        # Insertar nodos (lugares reales de Madrid)
        lugares = [
            ("Parla Centro", "40.2374, -3.7731"),
            ("Parla Este", "40.2456, -3.7558"),
            ("Getafe Centro", "40.3050, -3.7320"),
            ("Leganés Centro", "40.3278, -3.7645"),
            ("Alcorcón Centro", "40.3492, -3.8296"),
            ("Móstoles Norte", "40.3225, -3.8647"),
            ("Fuenlabrada Central", "40.2842, -3.7945"),
            ("Madrid Atocha", "40.4069, -3.6896"),
            ("Madrid Sol", "40.4169, -3.7034"),
            ("Madrid Chamartín", "40.4722, -3.6836"),
            ("Alcobendas", "40.5475, -3.6420"),
            ("Las Rozas", "40.4929, -3.8737"),
            ("Pozuelo de Alarcón", "40.4350, -3.8137"),
            ("Villaverde", "40.3475, -3.7008"),
            ("Carabanchel", "40.3864, -3.7442"),
            ("Vallecas", "40.3922, -3.6231"),
            ("Barajas Aeropuerto", "40.4722, -3.5608"),
            ("Arganda del Rey", "40.3008, -3.4375"),
            ("Torrejón de Ardoz", "40.4553, -3.4692"),
            ("Coslada", "40.4236, -3.5619")
        ]
        
        for nombre, coordenadas in lugares:
            cursor.execute(
                "INSERT INTO nodos (nombre, coordenadas) VALUES (%s, %s)",
                (nombre, coordenadas)
            )
        
        # Obtener IDs de los nodos insertados
        cursor.execute("SELECT id, nombre FROM nodos")
        nodos = {nombre: id for id, nombre in cursor.fetchall()}
        
        # Crear aristas (rutas reales con distancias aproximadas en km)
        rutas = [
            # Conexiones de Parla
            (nodos["Parla Centro"], nodos["Parla Este"], 2.5, "doble"),
            (nodos["Parla Centro"], nodos["Fuenlabrada Central"], 8.3, "doble"),
            (nodos["Parla Centro"], nodos["Getafe Centro"], 12.5, "doble"),
            
            # Conexiones de Alcorcón
            (nodos["Alcorcón Centro"], nodos["Móstoles Norte"], 5.8, "doble"),
            (nodos["Alcorcón Centro"], nodos["Leganés Centro"], 7.2, "doble"),
            (nodos["Alcorcón Centro"], nodos["Pozuelo de Alarcón"], 9.4, "doble"),
            
            # Conexiones de Getafe
            (nodos["Getafe Centro"], nodos["Leganés Centro"], 6.1, "doble"),
            (nodos["Getafe Centro"], nodos["Fuenlabrada Central"], 7.8, "doble"),
            (nodos["Getafe Centro"], nodos["Villaverde"], 8.9, "doble"),
            
            # Conexiones hacia Madrid centro
            (nodos["Villaverde"], nodos["Carabanchel"], 4.5, "doble"),
            (nodos["Carabanchel"], nodos["Madrid Atocha"], 5.2, "doble"),
            (nodos["Madrid Atocha"], nodos["Madrid Sol"], 1.8, "doble"),
            (nodos["Madrid Sol"], nodos["Madrid Chamartín"], 7.3, "doble"),
            
            # Conexiones norte
            (nodos["Madrid Chamartín"], nodos["Alcobendas"], 11.5, "doble"),
            (nodos["Madrid Chamartín"], nodos["Las Rozas"], 14.2, "doble"),
            
            # Conexiones este
            (nodos["Madrid Atocha"], nodos["Vallecas"], 6.8, "doble"),
            (nodos["Vallecas"], nodos["Arganda del Rey"], 18.5, "doble"),
            (nodos["Arganda del Rey"], nodos["Torrejón de Ardoz"], 12.3, "doble"),
            
            # Conexiones aeropuerto
            (nodos["Madrid Chamartín"], nodos["Barajas Aeropuerto"], 15.8, "doble"),
            (nodos["Barajas Aeropuerto"], nodos["Coslada"], 5.2, "doble"),
            (nodos["Coslada"], nodos["Torrejón de Ardoz"], 8.7, "doble"),
            
            # Conexión directa Parla-Alcorcón (por M-406)
            (nodos["Parla Centro"], nodos["Alcorcón Centro"], 22.5, "doble"),
            
            # Conexión alternativa Parla-Madrid
            (nodos["Parla Este"], nodos["Vallecas"], 19.8, "doble"),
        ]
        
        for origen_id, destino_id, coste, tipo in rutas:
            # Insertar arista
            cursor.execute(
                "INSERT INTO aristas (origen_id, destino_id, tipo) VALUES (%s, %s, %s)",
                (origen_id, destino_id, tipo)
            )
            
            arista_id = cursor.lastrowid
            
            # Insertar peso
            cursor.execute(
                "INSERT INTO pesos (arista_id, coste) VALUES (%s, %s)",
                (arista_id, coste)
            )
        
        conexion.commit()
        cursor.close()
        conexion.close()
        
        print("✓ Datos de ejemplo cargados correctamente")
        return True
        
    except Exception as e:
        print(f"✗ Error cargando datos de ejemplo: {e}")
        return False

# ============================================================================
# FASE 4 & 5: INTERFAZ GRÁFICA E INTERACCIÓN CON USUARIO
# ============================================================================

# ----------------------------------------------------------------------------
# INTERFAZ DE LOGIN/REGISTRO (NO MODIFICAR)
# ----------------------------------------------------------------------------

def crear_interfaz_login():
    """Crea la interfaz gráfica de login/registro"""
    global ventana_login
    
    ventana_login = tk.Tk()
    ventana_login.title("MAPSoriano - Sistema de Login Seguro")
    ventana_login.geometry("500x600")
    ventana_login.configure(bg="#f0f0f0")
    ventana_login.resizable(True, True)
    
    # Estilos de colores
    COLOR_PRIMARIO = "#2c3e50"
    COLOR_SECUNDARIO = "#3498db"
    COLOR_EXITO = "#2ecc71"
    COLOR_FONDO = "#ecf0f1"
    COLOR_TEXTO = "#2c3e50"
    COLOR_BORDE = "#bdc3c7"
    COLOR_ETIQUETA = "#34495e"
    
    # Frame principal con scroll
    main_container = tk.Frame(ventana_login, bg=COLOR_FONDO)
    main_container.pack(fill="both", expand=True, padx=20, pady=20)
    
    # Canvas para scroll
    canvas = tk.Canvas(main_container, bg=COLOR_FONDO, highlightthickness=0)
    scrollbar = ttk.Scrollbar(main_container, orient="vertical", command=canvas.yview)
    scrollable_frame = tk.Frame(canvas, bg=COLOR_FONDO)
    
    scrollable_frame.bind(
        "<Configure>",
        lambda e: canvas.configure(scrollregion=canvas.bbox("all"))
    )
    
    canvas.create_window((0, 0), window=scrollable_frame, anchor="nw")
    canvas.configure(yscrollcommand=scrollbar.set)
    
    canvas.pack(side="left", fill="both", expand=True)
    scrollbar.pack(side="right", fill="y")
    
    # Título principal
    title_frame = tk.Frame(scrollable_frame, bg=COLOR_FONDO)
    title_frame.pack(pady=(0, 20))
    
    tk.Label(title_frame, text="MAPSoriano", font=("Arial", 28, "bold"),
             bg=COLOR_FONDO, fg=COLOR_PRIMARIO).pack()
    
    tk.Label(title_frame, text="Sistema de Login Seguro", font=("Arial", 12),
             bg=COLOR_FONDO, fg=COLOR_TEXTO).pack()
    
    # Notebook para pestañas
    style = ttk.Style()
    style.theme_use('clam')
    style.configure('TNotebook', background=COLOR_FONDO)
    style.configure('TNotebook.Tab', font=('Arial', 10, 'bold'), padding=[15, 5])
    
    notebook = ttk.Notebook(scrollable_frame)
    notebook.pack(fill="both", expand=True, pady=(0, 20))
    
    # ===================== PESTAÑA DE LOGIN =====================
    login_frame = tk.Frame(notebook, bg=COLOR_FONDO, padx=30, pady=30)
    notebook.add(login_frame, text="INICIA SESIÓN")
    
    # Contenido login
    tk.Label(login_frame, text="Bienvenido a MAPSoriano", font=("Arial", 16, "bold"),
             bg=COLOR_FONDO, fg=COLOR_PRIMARIO).pack(pady=(0, 30))
    
    # Campo usuario
    user_frame = tk.Frame(login_frame, bg=COLOR_FONDO)
    user_frame.pack(fill="x", pady=(0, 15))
    
    tk.Label(user_frame, text="USUARIO", font=("Arial", 9, "bold"),
             bg=COLOR_FONDO, fg=COLOR_ETIQUETA).pack(anchor="w", pady=(0, 3))
    
    entry_login_user = tk.Entry(user_frame, font=("Arial", 12), relief="flat",
                                bg="white", fg=COLOR_TEXTO, highlightbackground=COLOR_BORDE,
                                highlightcolor=COLOR_SECUNDARIO, highlightthickness=1)
    entry_login_user.pack(fill="x", pady=(0, 0))
    
    # Campo contraseña
    pass_frame = tk.Frame(login_frame, bg=COLOR_FONDO)
    pass_frame.pack(fill="x", pady=(0, 25))
    
    tk.Label(pass_frame, text="CONTRASEÑA", font=("Arial", 9, "bold"),
             bg=COLOR_FONDO, fg=COLOR_ETIQUETA).pack(anchor="w", pady=(0, 3))
    
    entry_login_pass = tk.Entry(pass_frame, font=("Arial", 12), show="*",
                                relief="flat", bg="white", fg=COLOR_TEXTO,
                                highlightbackground=COLOR_BORDE, highlightcolor=COLOR_SECUNDARIO,
                                highlightthickness=1)
    entry_login_pass.pack(fill="x", pady=(0, 0))
    
    # Botón de login
    btn_login = tk.Button(login_frame, text="CONTINUAR", font=("Arial", 12, "bold"),
                          bg=COLOR_SECUNDARIO, fg="white", relief="flat",
                          cursor="hand2", activebackground="#2980b9",
                          activeforeground="white", padx=30, pady=10,
                          command=lambda: proceso_login(entry_login_user.get(), entry_login_pass.get()))
    btn_login.pack(pady=(0, 20))
    
    # Separador
    separator = tk.Frame(login_frame, height=2, bg=COLOR_BORDE)
    separator.pack(fill="x", pady=20)
    
    # Enlace a registro
    register_link = tk.Label(login_frame, text="¿No tienes cuenta? Regístrate aquí",
                             font=("Arial", 10), bg=COLOR_FONDO, fg=COLOR_SECUNDARIO,
                             cursor="hand2")
    register_link.pack(pady=(0, 10))
    register_link.bind("<Button-1>", lambda e: notebook.select(1))
    
    # Enlace a recuperación
    recover_link = tk.Label(login_frame, text="¿Olvidaste tu contraseña?",
                            font=("Arial", 10), bg=COLOR_FONDO, fg=COLOR_SECUNDARIO,
                            cursor="hand2")
    recover_link.pack()
    recover_link.bind("<Button-1>", lambda e: notebook.select(2))
    
    # ===================== PESTAÑA DE REGISTRO =====================
    register_frame = tk.Frame(notebook, bg=COLOR_FONDO, padx=30, pady=30)
    notebook.add(register_frame, text="REGÍSTRATE")
    
    # Contenido registro
    tk.Label(register_frame, text="Crear nueva cuenta", font=("Arial", 16, "bold"),
             bg=COLOR_FONDO, fg=COLOR_PRIMARIO).pack(pady=(0, 20))
    
    # Función para crear campos
    def crear_campo(frame, texto_etiqueta, es_password=False):
        contenedor = tk.Frame(frame, bg=COLOR_FONDO)
        contenedor.pack(fill="x", pady=(0, 12))
        
        etiqueta = tk.Label(contenedor, text=texto_etiqueta, font=("Arial", 9, "bold"),
                           bg=COLOR_FONDO, fg=COLOR_ETIQUETA)
        etiqueta.pack(anchor="w", pady=(0, 3))
        
        if es_password:
            entry = tk.Entry(contenedor, font=("Arial", 12), show="*", relief="flat",
                            bg="white", fg=COLOR_TEXTO, highlightbackground=COLOR_BORDE,
                            highlightcolor=COLOR_SECUNDARIO, highlightthickness=1)
        else:
            entry = tk.Entry(contenedor, font=("Arial", 12), relief="flat",
                            bg="white", fg=COLOR_TEXTO, highlightbackground=COLOR_BORDE,
                            highlightcolor=COLOR_SECUNDARIO, highlightthickness=1)
        
        entry.pack(fill="x", pady=(0, 0))
        return entry
    
    # Campos de registro
    entry_correo = crear_campo(register_frame, "CORREO ELECTRÓNICO")
    entry_usuario = crear_campo(register_frame, "USUARIO")
    entry_nombre = crear_campo(register_frame, "NOMBRE")
    entry_apellido = crear_campo(register_frame, "APELLIDO")
    entry_telefono = crear_campo(register_frame, "TELÉFONO")
    entry_fecha_nac = crear_campo(register_frame, "FECHA DE NACIMIENTO (YYYY-MM-DD)")
    entry_pass1 = crear_campo(register_frame, "CONTRASEÑA", es_password=True)
    entry_pass2 = crear_campo(register_frame, "CONFIRMAR CONTRASEÑA", es_password=True)
    
    # Separador
    separator_reg = tk.Frame(register_frame, height=2, bg=COLOR_BORDE)
    separator_reg.pack(fill="x", pady=(10, 15))
    
    # Botón de registro
    btn_register = tk.Button(register_frame, text="CREAR CUENTA", font=("Arial", 12, "bold"),
                             bg=COLOR_EXITO, fg="white", relief="flat",
                             cursor="hand2", activebackground="#27ae60",
                             activeforeground="white", padx=30, pady=10,
                             command=lambda: proceso_registro(
                                 entry_correo.get(), entry_usuario.get(),
                                 entry_nombre.get(), entry_apellido.get(),
                                 entry_telefono.get(), entry_fecha_nac.get(),
                                 entry_pass1.get(), entry_pass2.get()
                             ))
    btn_register.pack(pady=(0, 20))
    
    # Enlace a login
    login_link = tk.Label(register_frame, text="¿Ya tienes cuenta? Inicia sesión aquí",
                          font=("Arial", 10), bg=COLOR_FONDO, fg=COLOR_SECUNDARIO,
                          cursor="hand2")
    login_link.pack(pady=(10, 0))
    login_link.bind("<Button-1>", lambda e: notebook.select(0))
    
    # ===================== PESTAÑA DE RECUPERACIÓN =====================
    recover_frame = tk.Frame(notebook, bg=COLOR_FONDO, padx=30, pady=30)
    notebook.add(recover_frame, text="RECUPERAR")
    
    # Contenido recuperación
    tk.Label(recover_frame, text="Recuperar contraseña", font=("Arial", 16, "bold"),
             bg=COLOR_FONDO, fg=COLOR_PRIMARIO).pack(pady=(0, 20))
    
    tk.Label(recover_frame, text="Introduce tu nombre de usuario y te enviaremos instrucciones a tu correo electrónico",
             font=("Arial", 10), bg=COLOR_FONDO, fg=COLOR_TEXTO, wraplength=400,
             justify="center").pack(pady=(0, 30))
    
    # Campo usuario para recuperación
    frame_recover = tk.Frame(recover_frame, bg=COLOR_FONDO)
    frame_recover.pack(fill="x", pady=(0, 20))
    
    tk.Label(frame_recover, text="USUARIO", font=("Arial", 9, "bold"),
             bg=COLOR_FONDO, fg=COLOR_ETIQUETA).pack(anchor="w", pady=(0, 3))
    
    entry_recover_user = tk.Entry(frame_recover, font=("Arial", 12), relief="flat",
                                  bg="white", fg=COLOR_TEXTO, highlightbackground=COLOR_BORDE,
                                  highlightcolor=COLOR_SECUNDARIO, highlightthickness=1)
    entry_recover_user.pack(fill="x", pady=(0, 0))
    
    # Botón de recuperación
    btn_recover = tk.Button(recover_frame, text="ENVIAR INSTRUCCIONES", font=("Arial", 12, "bold"),
                            bg=COLOR_SECUNDARIO, fg="white", relief="flat",
                            cursor="hand2", activebackground="#2980b9",
                            activeforeground="white", padx=30, pady=10,
                            command=lambda: proceso_recuperacion(entry_recover_user.get()))
    btn_recover.pack(pady=(0, 20))
    
    # Enlace a login
    back_link = tk.Label(recover_frame, text="← Volver al inicio de sesión",
                         font=("Arial", 10), bg=COLOR_FONDO, fg=COLOR_SECUNDARIO,
                         cursor="hand2")
    back_link.pack(pady=(10, 0))
    back_link.bind("<Button-1>", lambda e: notebook.select(0))
    
    # Footer
    footer_frame = tk.Frame(scrollable_frame, bg=COLOR_FONDO)
    footer_frame.pack(fill="x", pady=(20, 0))
    
    tk.Label(footer_frame, text="MAPSoriano © 2024 - Sistema de Login Seguro",
             font=("Arial", 9), bg=COLOR_FONDO, fg=COLOR_TEXTO).pack()
    
    # Configurar pestaña activa inicial
    notebook.select(0)
    
    # Configurar scroll con rueda del ratón
    def on_mousewheel(event):
        canvas.yview_scroll(int(-1*(event.delta/120)), "units")
    
    canvas.bind_all("<MouseWheel>", on_mousewheel)
    
    ventana_login.mainloop()

# ----------------------------------------------------------------------------
# PROCESOS DE LOGIN/REGISTRO/RECUPERACIÓN
# ----------------------------------------------------------------------------

def proceso_login(username, password):
    """Procesa el intento de login"""
    if not username or not password:
        messagebox.showerror("Error", "Usuario y contraseña son obligatorios")
        return
    
    usuario, mensaje = validar_usuario(username, password)
    
    if usuario:
        global usuario_actual
        usuario_actual = usuario
        messagebox.showinfo("Éxito", f"¡Bienvenido, {username}!")
        ventana_login.destroy()
        iniciar_sistema_principal()
    else:
        messagebox.showerror("Error", mensaje)

def proceso_registro(correo, usuario, nombre, apellido, telefono, fecha_nac, pass1, pass2):
    """Procesa el registro de un nuevo usuario"""
    # Validar campos obligatorios
    campos = [
        (correo, "Correo electrónico"),
        (usuario, "Usuario"),
        (nombre, "Nombre"),
        (apellido, "Apellido"),
        (telefono, "Teléfono"),
        (pass1, "Contraseña"),
        (pass2, "Confirmar contraseña")
    ]
    
    for campo, nombre_campo in campos:
        if not campo:
            messagebox.showerror("Error", f"El campo '{nombre_campo}' es obligatorio")
            return
    
    if pass1 != pass2:
        messagebox.showerror("Error", "Las contraseñas no coinciden")
        return
    
    # Registrar usuario
    exito, mensaje = registrar_usuario(correo, usuario, nombre, apellido, telefono, fecha_nac, pass1)
    
    if exito:
        messagebox.showinfo("Éxito", "Cuenta creada correctamente. Ahora puedes iniciar sesión.")
        # Volver a la pestaña de login
        notebook = ventana_login.winfo_children()[0].winfo_children()[0].winfo_children()[1]
        notebook.select(0)
    else:
        messagebox.showerror("Error", mensaje)

def proceso_recuperacion(usuario):
    """Procesa la recuperación de contraseña"""
    if not usuario:
        messagebox.showerror("Error", "Debes ingresar un nombre de usuario")
        return
    
    exito, mensaje = recuperar_contrasena(usuario)
    
    if exito:
        messagebox.showinfo("Éxito", mensaje)
    else:
        messagebox.showerror("Error", mensaje)

# ----------------------------------------------------------------------------
# INTERFAZ PRINCIPAL DEL SISTEMA (MEJORADA)
# ----------------------------------------------------------------------------

def iniciar_sistema_principal():
    """Inicia la interfaz principal del sistema de rutas"""
    global ventana_principal, grafo_memoria
    
    # Cargar grafo en memoria
    grafo_memoria = construir_grafo()
    
    # Si no hay datos, cargar ejemplo
    if not grafo_memoria or len(grafo_memoria) < 5:
        respuesta = messagebox.askyesno("Datos de Ejemplo", 
                                        "No se encontraron datos de rutas.\n¿Deseas cargar datos de ejemplo con rutas reales de Madrid?")
        if respuesta:
            if cargar_datos_ejemplo():
                grafo_memoria = construir_grafo()
                messagebox.showinfo("Éxito", "Datos de ejemplo cargados correctamente")
            else:
                messagebox.showerror("Error", "No se pudieron cargar los datos de ejemplo")
    
    if not grafo_memoria:
        messagebox.showerror("Error", "No se pudo cargar el grafo. Verifica la conexión a la BD.")
        return
    
    ventana_principal = tk.Tk()
    ventana_principal.title(f"🚀 MAPSoriano - Sistema de Rutas | Usuario: {usuario_actual['username']}")
    ventana_principal.geometry("1000x750")
    ventana_principal.configure(bg="#1a1a2e")
    
    # Frame superior con título y usuario
    frame_superior = tk.Frame(ventana_principal, bg="#162447", height=90)
    frame_superior.pack(fill="x", side="top")
    frame_superior.pack_propagate(False)
    
    # Logo y título
    logo_frame = tk.Frame(frame_superior, bg="#162447")
    logo_frame.pack(side="left", padx=20, pady=15)
    
    tk.Label(logo_frame, text="🗺️", font=("Arial", 28), bg="#162447", fg="white").pack(side="left")
    tk.Label(logo_frame, text="MAPSoriano", font=("Arial", 24, "bold"), 
             bg="#162447", fg="white").pack(side="left", padx=10)
    
    # Info usuario
    user_frame = tk.Frame(frame_superior, bg="#162447")
    user_frame.pack(side="right", padx=20, pady=15)
    
    tk.Label(user_frame, text=f"👤 {usuario_actual['username']}", 
             font=("Arial", 12, "bold"), bg="#162447", fg="#00b4d8").pack(side="right")
    
    # Frame principal con menú y contenido
    frame_principal = tk.Frame(ventana_principal, bg="#1f4068")
    frame_principal.pack(fill="both", expand=True)
    
    # Frame del menú lateral
    frame_menu = tk.Frame(frame_principal, bg="#1b1b2f", width=220)
    frame_menu.pack(side="left", fill="y")
    frame_menu.pack_propagate(False)
    
    # Título del menú
    tk.Label(frame_menu, text="NAVEGACIÓN", font=("Arial", 14, "bold"),
             bg="#1b1b2f", fg="#00b4d8").pack(pady=(25, 20))
    
    # Botones del menú con estilo moderno
    btn_style = {"font": ("Arial", 11, "bold"), "bg": "#1b1b2f", "fg": "#e6e6e6", 
                 "relief": "flat", "anchor": "w", "padx": 25, "pady": 15,
                 "activebackground": "#162447", "activeforeground": "#00b4d8",
                 "cursor": "hand2"}
    
    menu_botones = [
        ("📍 Calcular Ruta", "ruta"),
        ("📋 Historial", "historial"),
        ("⭐ Favoritos", "favoritos"),
        ("📊 Estadísticas", "estadisticas"),
        ("🔍 Verificar Grafo", "verificar"),
        ("⚙️ Configuración", "configuracion")
    ]
    
    for texto, comando in menu_botones:
        btn = tk.Button(frame_menu, text=texto, **btn_style,
                       command=lambda c=comando: mostrar_pantalla(c))
        btn.pack(fill="x")
    
    # Separador
    tk.Frame(frame_menu, height=2, bg="#00b4d8").pack(fill="x", pady=20, padx=20)
    
    # Botón cerrar sesión
    btn_cerrar = tk.Button(frame_menu, text="🚪 Cerrar Sesión", **btn_style,
                           command=cerrar_sesion)
    btn_cerrar.pack(side="bottom", fill="x", pady=(0, 20))
    
    # Frame del contenido
    global frame_contenido
    frame_contenido = tk.Frame(frame_principal, bg="#e6e6e6", relief="flat")
    frame_contenido.pack(side="right", fill="both", expand=True)
    
    # Mostrar pantalla inicial
    mostrar_pantalla("ruta")
    
    ventana_principal.mainloop()

def mostrar_pantalla(pantalla):
    """Muestra la pantalla correspondiente en el frame de contenido"""
    global frame_contenido
    
    # Limpiar frame de contenido
    for widget in frame_contenido.winfo_children():
        widget.destroy()
    
    # Frame con scroll para contenido
    main_content = tk.Frame(frame_contenido, bg="#e6e6e6")
    main_content.pack(fill="both", expand=True)
    
    canvas = tk.Canvas(main_content, bg="#e6e6e6", highlightthickness=0)
    scrollbar = tk.Scrollbar(main_content, orient="vertical", command=canvas.yview)
    scrollable_frame = tk.Frame(canvas, bg="#e6e6e6")
    
    scrollable_frame.bind(
        "<Configure>",
        lambda e: canvas.configure(scrollregion=canvas.bbox("all"))
    )
    
    canvas.create_window((0, 0), window=scrollable_frame, anchor="nw")
    canvas.configure(yscrollcommand=scrollbar.set)
    
    canvas.pack(side="left", fill="both", expand=True, padx=20, pady=20)
    scrollbar.pack(side="right", fill="y")
    
    # Configurar scroll con rueda del ratón
    def on_mousewheel(event):
        canvas.yview_scroll(int(-1*(event.delta/120)), "units")
    
    canvas.bind_all("<MouseWheel>", on_mousewheel)
    
    if pantalla == "ruta":
        mostrar_pantalla_ruta(scrollable_frame)
    elif pantalla == "historial":
        mostrar_pantalla_historial(scrollable_frame)
    elif pantalla == "favoritos":
        mostrar_pantalla_favoritos(scrollable_frame)
    elif pantalla == "estadisticas":
        mostrar_pantalla_estadisticas(scrollable_frame)
    elif pantalla == "verificar":
        mostrar_pantalla_verificar(scrollable_frame)
    elif pantalla == "configuracion":
        mostrar_pantalla_configuracion(scrollable_frame)

def mostrar_pantalla_ruta(frame):
    """Muestra la pantalla para calcular rutas"""
    # Título
    title_frame = tk.Frame(frame, bg="#e6e6e6")
    title_frame.pack(fill="x", pady=(0, 30))
    
    tk.Label(title_frame, text="📍 CALCULAR NUEVA RUTA", font=("Arial", 22, "bold"),
             bg="#e6e6e6", fg="#162447").pack(side="left")
    
    tk.Label(title_frame, text="Encuentra el camino más corto entre dos puntos", 
             font=("Arial", 12), bg="#e6e6e6", fg="#666666").pack(side="left", padx=(15, 0))
    
    # Panel de selección
    panel_seleccion = tk.Frame(frame, bg="white", relief="solid", bd=1)
    panel_seleccion.pack(fill="x", pady=(0, 20))
    
    # Encabezado del panel
    tk.Frame(panel_seleccion, height=5, bg="#00b4d8").pack(fill="x")
    
    panel_header = tk.Frame(panel_seleccion, bg="#f8f9fa")
    panel_header.pack(fill="x", padx=20, pady=15)
    
    tk.Label(panel_header, text="SELECCIONA TU RUTA", font=("Arial", 16, "bold"),
             bg="#f8f9fa", fg="#162447").pack(side="left")
    
    # Frame para formulario
    form_frame = tk.Frame(panel_seleccion, bg="white")
    form_frame.pack(fill="x", padx=30, pady=20)
    
    # Obtener datos
    nodos = obtener_nodos()
    favoritos = obtener_favoritos(usuario_actual['id']) if usuario_actual else []
    
    # Preparar opciones
    opciones_nodos = []
    if favoritos:
        for fav in favoritos:
            opciones_nodos.append(f"⭐ {fav['nombre_favorito']} - {fav['nodo_nombre']}")
    
    for nodo in nodos:
        opciones_nodos.append(f"📍 {nodo['nombre']}")
    
    # Origen
    tk.Label(form_frame, text="Punto de Origen:", font=("Arial", 12, "bold"),
             bg="white", fg="#162447").grid(row=0, column=0, sticky="w", pady=(0, 10))
    
    var_origen = tk.StringVar()
    combo_origen = ttk.Combobox(form_frame, textvariable=var_origen, 
                                font=("Arial", 12), width=45, state="normal")
    combo_origen.grid(row=0, column=1, padx=(20, 0), pady=(0, 10), sticky="w")
    combo_origen['values'] = opciones_nodos
    
    # Destino
    tk.Label(form_frame, text="Punto de Destino:", font=("Arial", 12, "bold"),
             bg="white", fg="#162447").grid(row=1, column=0, sticky="w", pady=(0, 10))
    
    var_destino = tk.StringVar()
    combo_destino = ttk.Combobox(form_frame, textvariable=var_destino,
                                 font=("Arial", 12), width=45, state="normal")
    combo_destino.grid(row=1, column=1, padx=(20, 0), pady=(0, 10), sticky="w")
    combo_destino['values'] = opciones_nodos
    
    # Frame para intermedios
    inter_frame = tk.Frame(panel_seleccion, bg="white")
    inter_frame.pack(fill="x", padx=30, pady=(10, 20))
    
    tk.Label(inter_frame, text="Paradas intermedias (opcional):", 
             font=("Arial", 12, "bold"), bg="white", fg="#162447").pack(anchor="w", pady=(0, 10))
    
    frame_inter_lista = tk.Frame(inter_frame, bg="#f8f9fa", relief="solid", bd=1)
    frame_inter_lista.pack(fill="x", pady=(0, 10))
    
    lista_intermedios = tk.Listbox(frame_inter_lista, height=4, font=("Arial", 10),
                                   bg="white", selectbackground="#00b4d8")
    lista_intermedios.pack(fill="x", padx=5, pady=5)
    
    # Controles para intermedios
    frame_inter_controles = tk.Frame(inter_frame, bg="white")
    frame_inter_controles.pack(fill="x")
    
    var_intermedio = tk.StringVar()
    combo_intermedio = ttk.Combobox(frame_inter_controles, textvariable=var_intermedio,
                                    font=("Arial", 11), width=35, state="normal")
    combo_intermedio.pack(side="left", padx=(0, 10))
    combo_intermedio['values'] = opciones_nodos
    
    btn_agregar_inter = tk.Button(frame_inter_controles, text="➕ Agregar", font=("Arial", 10, "bold"),
                                  bg="#00b4d8", fg="white", relief="flat", padx=15, pady=5,
                                  cursor="hand2", activebackground="#0077b6",
                                  command=lambda: agregar_intermedio(lista_intermedios, combo_intermedio.get()))
    btn_agregar_inter.pack(side="left", padx=(0, 5))
    
    btn_eliminar_inter = tk.Button(frame_inter_controles, text="➖ Eliminar", font=("Arial", 10, "bold"),
                                   bg="#e63946", fg="white", relief="flat", padx=15, pady=5,
                                   cursor="hand2", activebackground="#d00000",
                                   command=lambda: eliminar_intermedio(lista_intermedios))
    btn_eliminar_inter.pack(side="left")
    
    # Botón calcular
    btn_frame = tk.Frame(frame, bg="#e6e6e6")
    btn_frame.pack(pady=(10, 20))
    
    btn_calcular = tk.Button(btn_frame, text="🚀 CALCULAR RUTA ÓPTIMA", font=("Arial", 14, "bold"),
                             bg="#2a9d8f", fg="white", relief="flat", padx=40, pady=15,
                             cursor="hand2", activebackground="#21867a",
                             command=lambda: calcular_ruta_gui(var_origen.get(), 
                                                               var_destino.get(),
                                                               obtener_intermedios(lista_intermedios)))
    btn_calcular.pack()
    
    # Panel de resultados
    resultados_frame = tk.Frame(frame, bg="white", relief="solid", bd=1)
    resultados_frame.pack(fill="both", expand=True)
    
    # Encabezado resultados
    tk.Frame(resultados_frame, height=5, bg="#2a9d8f").pack(fill="x")
    
    resultados_header = tk.Frame(resultados_frame, bg="#f8f9fa")
    resultados_header.pack(fill="x", padx=20, pady=15)
    
    tk.Label(resultados_header, text="RESULTADOS DE LA RUTA", font=("Arial", 16, "bold"),
             bg="#f8f9fa", fg="#162447").pack(side="left")
    
    # Área de texto para resultados
    text_resultados = tk.Text(resultados_frame, font=("Consolas", 10), 
                              wrap="word", bg="#f8f9fa", fg="#162447",
                              height=20, padx=20, pady=20)
    text_resultados.pack(fill="both", expand=True, padx=10, pady=10)
    
    # Scrollbar para resultados
    scrollbar_resultados = tk.Scrollbar(text_resultados)
    scrollbar_resultados.pack(side="right", fill="y")
    text_resultados.config(yscrollcommand=scrollbar_resultados.set)
    scrollbar_resultados.config(command=text_resultados.yview)

def agregar_intermedio(listbox, valor):
    """Agrega un nodo intermedio a la lista"""
    if valor and valor not in listbox.get(0, tk.END):
        listbox.insert(tk.END, valor)
        listbox.selection_clear(0, tk.END)
        listbox.selection_set(tk.END)

def eliminar_intermedio(listbox):
    """Elimina el nodo intermedio seleccionado"""
    try:
        seleccion = listbox.curselection()[0]
        listbox.delete(seleccion)
    except:
        pass

def obtener_intermedios(listbox):
    """Obtiene los IDs de los nodos intermedios de la lista"""
    intermedios = []
    for item in listbox.get(0, tk.END):
        # Buscar nodo por nombre en el texto
        nombre_buscar = item.replace("⭐ ", "").replace("📍 ", "").split(" - ")[-1]
        nodo = obtener_nodo_por_nombre(nombre_buscar)
        if nodo:
            intermedios.append(nodo['id'])
    return intermedios

def extraer_nodo_desde_texto(texto):
    """Extrae el nombre del nodo desde el texto del combobox"""
    if not texto:
        return None
    
    # Limpiar el texto
    texto_limpio = texto.replace("⭐ ", "").replace("📍 ", "")
    
    # Buscar nodo por nombre
    nodo = obtener_nodo_por_nombre(texto_limpio)
    return nodo['id'] if nodo else None

def calcular_ruta_gui(origen_text, destino_text, intermedios_ids):
    """Calcula la ruta desde la interfaz gráfica"""
    global grafo_memoria
    
    # Extraer IDs
    origen_id = extraer_nodo_desde_texto(origen_text)
    destino_id = extraer_nodo_desde_texto(destino_text)
    
    if not origen_id or not destino_id:
        messagebox.showerror("Error", "Debes seleccionar origen y destino válidos")
        return
    
    if origen_id == destino_id:
        messagebox.showerror("Error", "El origen y destino no pueden ser iguales")
        return
    
    # Calcular ruta
    if intermedios_ids:
        ruta, coste_total, _ = calcular_ruta_con_intermedios(grafo_memoria, origen_id, destino_id, intermedios_ids)
    else:
        ruta, coste_total, _ = dijkstra(grafo_memoria, origen_id, destino_id)
    
    if not ruta or coste_total == float('inf'):
        messagebox.showerror("Error", "No se encontró una ruta válida entre los puntos seleccionados")
        return
    
    # Mostrar resultados
    for widget in frame_contenido.winfo_children():
        if isinstance(widget, tk.Frame):
            for child in widget.winfo_children():
                if isinstance(child, tk.Canvas):
                    text_widget = child.winfo_children()[0].winfo_children()[-1]
                    if isinstance(text_widget, tk.Text):
                        text_widget.delete(1.0, tk.END)
                        
                        # Información de la ruta
                        origen_nodo = obtener_nodo_por_id(origen_id)
                        destino_nodo = obtener_nodo_por_id(destino_id)
                        
                        resultado = "═"*60 + "\n"
                        resultado += "RUTA ÓPTIMA ENCONTRADA 🗺️\n"
                        resultado += "═"*60 + "\n\n"
                        
                        resultado += f"🚀 DE: {origen_nodo['nombre'] if origen_nodo else 'Desconocido'}\n"
                        resultado += f"🎯 A: {destino_nodo['nombre'] if destino_nodo else 'Desconocido'}\n"
                        
                        if intermedios_ids:
                            resultado += f"🛑 Paradas intermedias: {len(intermedios_ids)}\n"
                        
                        resultado += f"\n📏 DISTANCIA TOTAL: {coste_total:.2f} km\n"
                        resultado += f"⏱️ TIEMPO ESTIMADO: {coste_total/50*60:.0f} minutos (a 50 km/h)\n"
                        resultado += "\n" + "═"*60 + "\n"
                        resultado += "ITINERARIO DETALLADO:\n"
                        resultado += "═"*60 + "\n\n"
                        
                        for i, nodo_id in enumerate(ruta):
                            nodo_info = obtener_nodo_por_id(nodo_id)
                            nombre_nodo = nodo_info['nombre'] if nodo_info else f"Nodo {nodo_id}"
                            
                            if i == 0:
                                resultado += f"🏁 INICIO: {nombre_nodo}\n"
                            elif i == len(ruta) - 1:
                                resultado += f"🎯 DESTINO: {nombre_nodo}\n"
                            else:
                                resultado += f"   ↳ Paso {i}: {nombre_nodo}\n"
                        
                        # Buscar ruta alternativa
                        if not intermedios_ids:
                            resultado += "\n" + "═"*60 + "\n"
                            resultado += "BUSCANDO RUTA ALTERNATIVA...\n"
                            resultado += "═"*60 + "\n\n"
                            
                            ruta_alt, coste_alt = encontrar_ruta_alternativa(
                                grafo_memoria, origen_id, destino_id, ruta, coste_total
                            )
                            
                            if ruta_alt and coste_alt != float('inf'):
                                resultado += f"✅ RUTA ALTERNATIVA DISPONIBLE:\n"
                                resultado += f"   • Distancia: {coste_alt:.2f} km\n"
                                resultado += f"   • Diferencia: +{((coste_alt/coste_total)-1)*100:.1f}%\n\n"
                                resultado += "   Camino alternativo:\n"
                                
                                for i, nodo_id in enumerate(ruta_alt):
                                    nodo_info = obtener_nodo_por_id(nodo_id)
                                    nombre_nodo = nodo_info['nombre'] if nodo_info else f"Nodo {nodo_id}"
                                    
                                    if i == 0:
                                        resultado += f"   🏁 {nombre_nodo}\n"
                                    elif i == len(ruta_alt) - 1:
                                        resultado += f"   🎯 {nombre_nodo}\n"
                                    else:
                                        resultado += f"     ↳ {nombre_nodo}\n"
                                
                                # Guardar alternativa en historial
                                if usuario_actual:
                                    guardar_ruta_historico(
                                        usuario_actual['id'], origen_id, destino_id, coste_alt, True
                                    )
                            else:
                                resultado += "❌ No se encontró una ruta alternativa válida\n"
                        
                        # Guardar en historial
                        if usuario_actual:
                            guardar_ruta_historico(
                                usuario_actual['id'], origen_id, destino_id, coste_total, False
                            )
                            resultado += "\n✅ Ruta guardada en tu historial\n"
                        
                        text_widget.insert(1.0, resultado)
                        break
    
    # Preguntar si guardar como favorito
    respuesta = messagebox.askyesno("Guardar Favorito", 
                                   "¿Deseas guardar esta ruta como favorita?")
    
    if respuesta and usuario_actual:
        nombre_fav = tk.simpledialog.askstring("Nombre del Favorito", 
                                              "Ingresa un nombre para esta ruta\n(Ej: 'Casa → Trabajo'):")
        
        if nombre_fav:
            exito, mensaje = guardar_favorito(usuario_actual['id'], destino_id, nombre_fav)
            if exito:
                messagebox.showinfo("Favorito Guardado", "✅ Ruta guardada en favoritos")
            else:
                messagebox.showerror("Error", f"❌ {mensaje}")

def mostrar_pantalla_historial(frame):
    """Muestra el historial de rutas"""
    if not usuario_actual:
        tk.Label(frame, text="Debes iniciar sesión para ver el historial",
                 font=("Arial", 12), bg="#e6e6e6", fg="#e63946").pack(pady=50)
        return
    
    # Título
    title_frame = tk.Frame(frame, bg="#e6e6e6")
    title_frame.pack(fill="x", pady=(0, 30))
    
    tk.Label(title_frame, text="📋 HISTORIAL DE RUTAS", font=("Arial", 22, "bold"),
             bg="#e6e6e6", fg="#162447").pack(side="left")
    
    tk.Label(title_frame, text="Tus rutas calculadas recientemente", 
             font=("Arial", 12), bg="#e6e6e6", fg="#666666").pack(side="left", padx=(15, 0))
    
    historial = obtener_historico_usuario(usuario_actual['id'], 20)
    
    if not historial:
        # Mensaje cuando no hay historial
        empty_frame = tk.Frame(frame, bg="#e6e6e6")
        empty_frame.pack(fill="both", expand=True, pady=100)
        
        tk.Label(empty_frame, text="📭", font=("Arial", 48), 
                 bg="#e6e6e6", fg="#666666").pack()
        
        tk.Label(empty_frame, text="No hay rutas en tu historial", 
                 font=("Arial", 16), bg="#e6e6e6", fg="#666666").pack(pady=10)
        
        tk.Label(empty_frame, text="Calcula tu primera ruta para verla aquí", 
                 font=("Arial", 12), bg="#e6e6e6", fg="#999999").pack()
        return
    
    # Contenedor para cards
    cards_frame = tk.Frame(frame, bg="#e6e6e6")
    cards_frame.pack(fill="both", expand=True)
    
    # Mostrar cada ruta como una card
    for i, ruta in enumerate(historial):
        card = tk.Frame(cards_frame, bg="white", relief="solid", bd=1)
        card.pack(fill="x", pady=(0, 15), padx=5)
        
        # Encabezado de la card
        header = tk.Frame(card, bg="#00b4d8" if not ruta['ruta_alternativa'] else "#2a9d8f")
        header.pack(fill="x")
        
        tk.Label(header, text=f"RUTA #{i+1}", font=("Arial", 11, "bold"),
                 bg="transparent", fg="white").pack(side="left", padx=15, pady=8)
        
        tk.Label(header, text=f"{ruta['fecha_hora']}", font=("Arial", 10),
                 bg="transparent", fg="white").pack(side="right", padx=15, pady=8)
        
        # Contenido de la card
        content = tk.Frame(card, bg="white", padx=20, pady=15)
        content.pack(fill="x")
        
        # Información de la ruta
        route_info = tk.Frame(content, bg="white")
        route_info.pack(fill="x", pady=(0, 10))
        
        tk.Label(route_info, text="🚀", font=("Arial", 14), 
                 bg="white", fg="#162447").pack(side="left")
        tk.Label(route_info, text=f" {ruta['origen_nombre']}", font=("Arial", 12, "bold"),
                 bg="white", fg="#162447").pack(side="left", padx=(5, 20))
        
        tk.Label(route_info, text="➔", font=("Arial", 14), 
                 bg="white", fg="#666666").pack(side="left")
        tk.Label(route_info, text=f" {ruta['destino_nombre']}", font=("Arial", 12, "bold"),
                 bg="white", fg="#162447").pack(side="left", padx=(5, 20))
        
        # Detalles
        details = tk.Frame(content, bg="white")
        details.pack(fill="x")
        
        tk.Label(details, text=f"📏 Distancia: {ruta['coste_total']:.2f} km", 
                 font=("Arial", 10), bg="white", fg="#666666").pack(side="left", padx=(0, 20))
        
        tipo = "RUTA ÓPTIMA" if not ruta['ruta_alternativa'] else "RUTA ALTERNATIVA"
        color = "#00b4d8" if not ruta['ruta_alternativa'] else "#2a9d8f"
        tk.Label(details, text=f"📌 {tipo}", font=("Arial", 10, "bold"),
                 bg="white", fg=color).pack(side="left")

def mostrar_pantalla_favoritos(frame):
    """Muestra y gestiona los favoritos"""
    if not usuario_actual:
        tk.Label(frame, text="Debes iniciar sesión para gestionar favoritos",
                 font=("Arial", 12), bg="#e6e6e6", fg="#e63946").pack(pady=50)
        return
    
    # Título
    title_frame = tk.Frame(frame, bg="#e6e6e6")
    title_frame.pack(fill="x", pady=(0, 30))
    
    tk.Label(title_frame, text="⭐ MIS FAVORITOS", font=("Arial", 22, "bold"),
             bg="#e6e6e6", fg="#162447").pack(side="left")
    
    tk.Label(title_frame, text="Tus ubicaciones guardadas", 
             font=("Arial", 12), bg="#e6e6e6", fg="#666666").pack(side="left", padx=(15, 0))
    
    favoritos = obtener_favoritos(usuario_actual['id'])
    
    # Frame principal para favoritos
    main_fav_frame = tk.Frame(frame, bg="#e6e6e6")
    main_fav_frame.pack(fill="both", expand=True)
    
    if not favoritos:
        # Mensaje cuando no hay favoritos
        empty_frame = tk.Frame(main_fav_frame, bg="#e6e6e6")
        empty_frame.pack(fill="both", expand=True, pady=100)
        
        tk.Label(empty_frame, text="⭐", font=("Arial", 48), 
                 bg="#e6e6e6", fg="#ffd166").pack()
        
        tk.Label(empty_frame, text="No tienes favoritos guardados", 
                 font=("Arial", 16), bg="#e6e6e6", fg="#666666").pack(pady=10)
        
        tk.Label(empty_frame, text="Guarda tus ubicaciones más usadas para acceder rápidamente", 
                 font=("Arial", 12), bg="#e6e6e6", fg="#999999").pack()
    else:
        # Mostrar favoritos existentes
        for fav in favoritos:
            card = tk.Frame(main_fav_frame, bg="white", relief="solid", bd=1)
            card.pack(fill="x", pady=(0, 10), padx=5)
            
            card_content = tk.Frame(card, bg="white", padx=20, pady=15)
            card_content.pack(fill="x")
            
            # Información del favorito
            tk.Label(card_content, text="⭐", font=("Arial", 16), 
                     bg="white", fg="#ffd166").pack(side="left")
            
            info_frame = tk.Frame(card_content, bg="white")
            info_frame.pack(side="left", fill="x", expand=True, padx=(10, 20))
            
            tk.Label(info_frame, text=fav['nombre_favorito'], font=("Arial", 14, "bold"),
                     bg="white", fg="#162447").pack(anchor="w")
            
            tk.Label(info_frame, text=f"📍 {fav['nodo_nombre']}", font=("Arial", 11),
                     bg="white", fg="#666666").pack(anchor="w")
            
            # Botón eliminar
            btn_eliminar = tk.Button(card_content, text="🗑️ Eliminar", font=("Arial", 10),
                                     bg="#e63946", fg="white", relief="flat", padx=15, pady=5,
                                     cursor="hand2", activebackground="#d00000",
                                     command=lambda f_id=fav['id']: eliminar_favorito_gui(f_id))
            btn_eliminar.pack(side="right")
    
    # Panel para agregar nuevo favorito
    add_panel = tk.Frame(frame, bg="white", relief="solid", bd=1)
    add_panel.pack(fill="x", pady=(30, 0))
    
    tk.Frame(add_panel, height=5, bg="#2a9d8f").pack(fill="x")
    
    panel_header = tk.Frame(add_panel, bg="#f8f9fa")
    panel_header.pack(fill="x", padx=20, pady=15)
    
    tk.Label(panel_header, text="➕ AGREGAR NUEVO FAVORITO", font=("Arial", 16, "bold"),
             bg="#f8f9fa", fg="#162447").pack(side="left")
    
    # Formulario para nuevo favorito
    form_frame = tk.Frame(add_panel, bg="white", padx=30, pady=20)
    form_frame.pack(fill="x")
    
    # Campos
    tk.Label(form_frame, text="Nombre del favorito:", font=("Arial", 12),
             bg="white", fg="#162447").grid(row=0, column=0, sticky="w", pady=(0, 10))
    
    entry_nombre_fav = tk.Entry(form_frame, font=("Arial", 12), width=30)
    entry_nombre_fav.grid(row=0, column=1, padx=(20, 0), pady=(0, 10), sticky="w")
    
    tk.Label(form_frame, text="Ubicación:", font=("Arial", 12),
             bg="white", fg="#162447").grid(row=1, column=0, sticky="w", pady=(0, 10))
    
    # Obtener nodos para el combobox
    nodos = obtener_nodos()
    opciones_nodos = [f"📍 {nodo['nombre']}" for nodo in nodos]
    
    var_nodo_fav = tk.StringVar()
    combo_nodo_fav = ttk.Combobox(form_frame, textvariable=var_nodo_fav,
                                  font=("Arial", 12), width=30)
    combo_nodo_fav.grid(row=1, column=1, padx=(20, 0), pady=(0, 10), sticky="w")
    combo_nodo_fav['values'] = opciones_nodos
    
    # Botón agregar
    btn_frame = tk.Frame(form_frame, bg="white")
    btn_frame.grid(row=2, column=0, columnspan=2, pady=(10, 0))
    
    btn_agregar = tk.Button(btn_frame, text="💾 GUARDAR FAVORITO", font=("Arial", 12, "bold"),
                            bg="#2a9d8f", fg="white", relief="flat", padx=30, pady=10,
                            cursor="hand2", activebackground="#21867a",
                            command=lambda: agregar_favorito_gui(entry_nombre_fav.get(), var_nodo_fav.get()))
    btn_agregar.pack()

def eliminar_favorito_gui(favorito_id):
    """Elimina un favorito desde la GUI"""
    if eliminar_favorito(favorito_id, usuario_actual['id']):
        messagebox.showinfo("Éxito", "✅ Favorito eliminado correctamente")
        # Refrescar pantalla
        mostrar_pantalla("favoritos")
    else:
        messagebox.showerror("Error", "❌ Error al eliminar el favorito")

def agregar_favorito_gui(nombre, nodo_texto):
    """Agrega un favorito desde la GUI"""
    if not nombre or not nodo_texto:
        messagebox.showerror("Error", "❌ Nombre y ubicación son obligatorios")
        return
    
    nodo_id = extraer_nodo_desde_texto(nodo_texto)
    
    if not nodo_id:
        messagebox.showerror("Error", "❌ Debes seleccionar una ubicación válida")
        return
    
    exito, mensaje = guardar_favorito(usuario_actual['id'], nodo_id, nombre)
    
    if exito:
        messagebox.showinfo("Éxito", "✅ Favorito guardado correctamente")
        # Refrescar pantalla
        mostrar_pantalla("favoritos")
    else:
        messagebox.showerror("Error", f"❌ {mensaje}")

def mostrar_pantalla_estadisticas(frame):
    """Muestra estadísticas del sistema"""
    global grafo_memoria
    
    # Título
    title_frame = tk.Frame(frame, bg="#e6e6e6")
    title_frame.pack(fill="x", pady=(0, 30))
    
    tk.Label(title_frame, text="📊 ESTADÍSTICAS DEL SISTEMA", font=("Arial", 22, "bold"),
             bg="#e6e6e6", fg="#162447").pack(side="left")
    
    # Panel de estadísticas
    stats_panel = tk.Frame(frame, bg="white", relief="solid", bd=1)
    stats_panel.pack(fill="both", expand=True)
    
    tk.Frame(stats_panel, height=5, bg="#00b4d8").pack(fill="x")
    
    # Contenido
    content_frame = tk.Frame(stats_panel, bg="white", padx=40, pady=30)
    content_frame.pack(fill="both", expand=True)
    
    if not grafo_memoria:
        tk.Label(content_frame, text="No hay datos disponibles", 
                 font=("Arial", 14), bg="white", fg="#666666").pack(pady=50)
        return
    
    # Estadísticas del grafo
    total_nodos = len(grafo_memoria)
    total_conexiones = sum(len(vecinos) for vecinos in grafo_memoria.values())
    
    # Crear cuadros de estadísticas
    stats_data = [
        ("🌍 NODOS TOTALES", f"{total_nodos}", "#00b4d8"),
        ("🛣️ CONEXIONES", f"{total_conexiones}", "#2a9d8f"),
        ("📈 PROMEDIO CONEXIONES/NODO", f"{total_conexiones/total_nodos:.1f}", "#e9c46a"),
        ("🏙️ RUTAS CARGADAS", "20+ ubicaciones reales", "#f4a261")
    ]
    
    # Mostrar estadísticas en cuadros
    row_frame = tk.Frame(content_frame, bg="white")
    row_frame.pack(fill="x", pady=(0, 20))
    
    for i, (titulo, valor, color) in enumerate(stats_data):
        stat_box = tk.Frame(row_frame, bg=color, relief="solid", bd=1)
        stat_box.pack(side="left", fill="both", expand=True, padx=(0 if i==0 else 10, 0))
        
        tk.Label(stat_box, text=titulo, font=("Arial", 11, "bold"),
                 bg=color, fg="white", pady=10).pack()
        
        tk.Label(stat_box, text=valor, font=("Arial", 24, "bold"),
                 bg=color, fg="white", pady=10).pack()
    
    # Lista de ubicaciones disponibles
    tk.Label(content_frame, text="📍 UBICACIONES DISPONIBLES", 
             font=("Arial", 16, "bold"), bg="white", fg="#162447").pack(anchor="w", pady=(30, 15))
    
    # Frame para la lista
    list_frame = tk.Frame(content_frame, bg="#f8f9fa", relief="solid", bd=1)
    list_frame.pack(fill="both", expand=True)
    
    # Área de texto con scroll
    text_lugares = tk.Text(list_frame, font=("Consolas", 10), wrap="word", 
                          bg="#f8f9fa", fg="#162447", height=15)
    text_lugares.pack(side="left", fill="both", expand=True, padx=10, pady=10)
    
    scrollbar = tk.Scrollbar(text_lugares)
    scrollbar.pack(side="right", fill="y")
    text_lugares.config(yscrollcommand=scrollbar.set)
    scrollbar.config(command=text_lugares.yview)
    
    # Obtener y mostrar nodos
    nodos = obtener_nodos()
    text_lugares.insert(1.0, "LISTA COMPLETA DE UBICACIONES:\n" + "="*50 + "\n\n")
    
    for nodo in nodos:
        text_lugares.insert(tk.END, f"• {nodo['nombre']}\n")
        
        # Mostrar algunas coordenadas de ejemplo
        if nodo['coordenadas']:
            text_lugares.insert(tk.END, f"  Coordenadas: {nodo['coordenadas']}\n")
        
        text_lugares.insert(tk.END, "\n")
    
    text_lugares.config(state="disabled")

def mostrar_pantalla_verificar(frame):
    """Muestra la verificación de integridad del grafo"""
    global grafo_memoria
    
    # Título
    title_frame = tk.Frame(frame, bg="#e6e6e6")
    title_frame.pack(fill="x", pady=(0, 30))
    
    tk.Label(title_frame, text="🔍 VERIFICACIÓN DE INTEGRIDAD", font=("Arial", 22, "bold"),
             bg="#e6e6e6", fg="#162447").pack(side="left")
    
    tk.Label(title_frame, text="Verifica el estado del sistema de rutas", 
             font=("Arial", 12), bg="#e6e6e6", fg="#666666").pack(side="left", padx=(15, 0))
    
    # Panel de verificación
    verify_panel = tk.Frame(frame, bg="white", relief="solid", bd=1)
    verify_panel.pack(fill="both", expand=True)
    
    tk.Frame(verify_panel, height=5, bg="#00b4d8").pack(fill="x")
    
    # Contenido
    content_frame = tk.Frame(verify_panel, bg="white", padx=30, pady=30)
    content_frame.pack(fill="both", expand=True)
    
    # Botón para ejecutar verificación
    btn_frame = tk.Frame(content_frame, bg="white")
    btn_frame.pack(pady=(0, 30))
    
    btn_verificar = tk.Button(btn_frame, text="▶️ EJECUTAR VERIFICACIÓN COMPLETA", 
                              font=("Arial", 14, "bold"), bg="#2a9d8f", fg="white",
                              relief="flat", padx=40, pady=15, cursor="hand2",
                              activebackground="#21867a",
                              command=lambda: ejecutar_verificacion(content_frame))
    btn_verificar.pack()
    
    # Área de resultados
    resultados_frame = tk.LabelFrame(content_frame, text="Resultados de la verificación",
                                     font=("Arial", 14, "bold"), bg="white", fg="#162447")
    resultados_frame.pack(fill="both", expand=True)
    
    text_resultados = tk.Text(resultados_frame, font=("Consolas", 10), 
                              wrap="word", bg="#f8f9fa", fg="#162447", height=20)
    text_resultados.pack(fill="both", expand=True, padx=10, pady=10)
    
    # Configurar tags para colores
    text_resultados.tag_config("exito", foreground="#2a9d8f", font=("Consolas", 10, "bold"))
    text_resultados.tag_config("error", foreground="#e63946", font=("Consolas", 10))
    text_resultados.tag_config("titulo", font=("Consolas", 11, "bold"))
    text_resultados.tag_config("normal", font=("Consolas", 10))

def ejecutar_verificacion(content_frame):
    """Ejecuta la verificación de integridad"""
    global grafo_memoria
    
    # Encontrar el widget de texto de resultados
    for widget in content_frame.winfo_children():
        if isinstance(widget, tk.LabelFrame):
            text_widget = widget.winfo_children()[0]
            break
    else:
        return
    
    text_widget.delete(1.0, tk.END)
    
    if grafo_memoria is None:
        text_widget.insert(1.0, "❌ ERROR: Grafo no cargado en memoria\n", "error")
        return
    
    text_widget.insert(1.0, "🔍 VERIFICANDO INTEGRIDAD DEL SISTEMA...\n\n", "titulo")
    
    resultados = verificar_integridad_grafo(grafo_memoria)
    
    # Mostrar resultados
    for resultado in resultados:
        if resultado.startswith("Grafo verificado"):
            text_widget.insert(tk.END, f"✅ {resultado}\n\n", "exito")
        else:
            text_widget.insert(tk.END, f"❌ {resultado}\n", "error")
    
    # Estadísticas detalladas
    text_widget.insert(tk.END, "\n" + "═"*50 + "\n", "normal")
    text_widget.insert(tk.END, "📊 ESTADÍSTICAS DETALLADAS:\n", "titulo")
    text_widget.insert(tk.END, "═"*50 + "\n\n", "normal")
    
    total_nodos = len(grafo_memoria)
    total_conexiones = sum(len(vecinos) for vecinos in grafo_memoria.values())
    
    text_widget.insert(tk.END, f"• Nodos totales: {total_nodos}\n", "normal")
    text_widget.insert(tk.END, f"• Conexiones totales: {total_conexiones}\n", "normal")
    text_widget.insert(tk.END, f"• Promedio conexiones por nodo: {total_conexiones/total_nodos:.2f}\n", "normal")
    
    # Nodos aislados
    nodos_aislados = [nodo for nodo in grafo_memoria if not grafo_memoria[nodo]]
    text_widget.insert(tk.END, f"• Nodos aislados: {len(nodos_aislados)}\n", "normal")
    
    if nodos_aislados:
        text_widget.insert(tk.END, f"  IDs de nodos aislados: {nodos_aislados}\n", "error")
    
    # Verificar conexiones problemáticas
    problemas = 0
    for nodo, conexiones in grafo_memoria.items():
        for destino, coste in conexiones:
            if coste <= 0:
                problemas += 1
    
    text_widget.insert(tk.END, f"• Conexiones problemáticas: {problemas}\n", "normal")
    
    # Resumen final
    text_widget.insert(tk.END, "\n" + "═"*50 + "\n", "normal")
    if len(resultados) == 1 and resultados[0].startswith("Grafo verificado"):
        text_widget.insert(tk.END, "✅ SISTEMA VERIFICADO: TODO CORRECTO\n", "exito")
        text_widget.insert(tk.END, "El grafo está listo para calcular rutas\n", "normal")
    else:
        text_widget.insert(tk.END, "⚠️  SISTEMA CON PROBLEMAS\n", "error")
        text_widget.insert(tk.END, "Se encontraron problemas que deben ser corregidos\n", "normal")

def mostrar_pantalla_configuracion(frame):
    """Muestra la pantalla de configuración"""
    # Título
    title_frame = tk.Frame(frame, bg="#e6e6e6")
    title_frame.pack(fill="x", pady=(0, 30))
    
    tk.Label(title_frame, text="⚙️ CONFIGURACIÓN", font=("Arial", 22, "bold"),
             bg="#e6e6e6", fg="#162447").pack(side="left")
    
    # Panel de configuración
    config_panel = tk.Frame(frame, bg="white", relief="solid", bd=1)
    config_panel.pack(fill="both", expand=True)
    
    tk.Frame(config_panel, height=5, bg="#00b4d8").pack(fill="x")
    
    # Contenido
    content_frame = tk.Frame(config_panel, bg="white", padx=40, pady=30)
    content_frame.pack(fill="both", expand=True)
    
    # Opciones de configuración
    tk.Label(content_frame, text="IMPORTAR DATOS", font=("Arial", 16, "bold"),
             bg="white", fg="#162447").pack(anchor="w", pady=(0, 20))
    
    # Importar nodos
    frame_import_nodos = tk.Frame(content_frame, bg="white")
    frame_import_nodos.pack(fill="x", pady=(0, 15))
    
    tk.Label(frame_import_nodos, text="Importar nodos desde CSV:", font=("Arial", 12),
             bg="white", fg="#162447").pack(anchor="w", pady=(0, 5))
    
    tk.Label(frame_import_nodos, text="Formato: nombre,coordenadas", font=("Arial", 10),
             bg="white", fg="#666666").pack(anchor="w", pady=(0, 10))
    
    btn_import_nodos = tk.Button(frame_import_nodos, text="📁 SELECCIONAR ARCHIVO CSV", 
                                 font=("Arial", 11, "bold"), bg="#00b4d8", fg="white",
                                 relief="flat", padx=20, pady=10, cursor="hand2",
                                 activebackground="#0077b6",
                                 command=lambda: importar_csv_gui("nodos"))
    btn_import_nodos.pack()
    
    # Separador
    tk.Frame(content_frame, height=2, bg="#e0e0e0").pack(fill="x", pady=20)
    
    # Importar aristas
    frame_import_aristas = tk.Frame(content_frame, bg="white")
    frame_import_aristas.pack(fill="x", pady=(0, 15))
    
    tk.Label(frame_import_aristas, text="Importar conexiones desde CSV:", font=("Arial", 12),
             bg="white", fg="#162447").pack(anchor="w", pady=(0, 5))
    
    tk.Label(frame_import_aristas, text="Formato: origen_id,destino_id,coste,tipo", font=("Arial", 10),
             bg="white", fg="#666666").pack(anchor="w", pady=(0, 10))
    
    btn_import_aristas = tk.Button(frame_import_aristas, text="📁 SELECCIONAR ARCHIVO CSV", 
                                   font=("Arial", 11, "bold"), bg="#2a9d8f", fg="white",
                                   relief="flat", padx=20, pady=10, cursor="hand2",
                                   activebackground="#21867a",
                                   command=lambda: importar_csv_gui("aristas"))
    btn_import_aristas.pack()
    
    # Separador
    tk.Frame(content_frame, height=2, bg="#e0e0e0").pack(fill="x", pady=20)
    
    # Cargar datos de ejemplo
    frame_ejemplo = tk.Frame(content_frame, bg="white")
    frame_ejemplo.pack(fill="x", pady=(0, 15))
    
    tk.Label(frame_ejemplo, text="Datos de ejemplo:", font=("Arial", 12),
             bg="white", fg="#162447").pack(anchor="w", pady=(0, 5))
    
    tk.Label(frame_ejemplo, text="Cargar rutas reales de Madrid (Parla, Alcorcón, etc.)", 
             font=("Arial", 10), bg="white", fg="#666666").pack(anchor="w", pady=(0, 10))
    
    btn_ejemplo = tk.Button(frame_ejemplo, text="🗺️ CARGAR DATOS DE EJEMPLO", 
                            font=("Arial", 11, "bold"), bg="#e9c46a", fg="#162447",
                            relief="flat", padx=20, pady=10, cursor="hand2",
                            activebackground="#f4a261",
                            command=cargar_datos_de_ejemplo_gui)
    btn_ejemplo.pack()

def importar_csv_gui(tipo):
    """Importa datos CSV desde la GUI"""
    filetypes = [("Archivos CSV", "*.csv"), ("Todos los archivos", "*.*")]
    
    archivo = filedialog.askopenfilename(
        title=f"Seleccionar archivo CSV de {tipo}",
        filetypes=filetypes
    )
    
    if not archivo:
        return
    
    if tipo == "nodos":
        exito, mensaje = importar_csv_nodos(archivo)
    elif tipo == "aristas":
        exito, mensaje = importar_csv_aristas(archivo)
    else:
        messagebox.showerror("Error", "❌ Tipo de importación no válido")
        return
    
    if exito:
        messagebox.showinfo("Éxito", f"✅ {mensaje}")
        # Actualizar grafo en memoria
        global grafo_memoria
        grafo_memoria = construir_grafo()
        # Actualizar interfaz si está en pantalla de rutas
        mostrar_pantalla("ruta")
    else:
        messagebox.showerror("Error", f"❌ {mensaje}")

def cargar_datos_de_ejemplo_gui():
    """Carga datos de ejemplo desde la GUI"""
    respuesta = messagebox.askyesno("Cargar datos de ejemplo", 
                                   "¿Deseas cargar datos de ejemplo con rutas reales de Madrid?\n\n" +
                                   "Esto incluirá:\n" +
                                   "• Parla, Alcorcón, Getafe, Leganés\n" +
                                   "• Madrid centro y principales conexiones\n" +
                                   "• Más de 20 ubicaciones reales\n\n" +
                                   "⚠️ Los datos existentes se conservarán.")
    
    if respuesta:
        if cargar_datos_ejemplo():
            messagebox.showinfo("Éxito", "✅ Datos de ejemplo cargados correctamente")
            # Actualizar grafo en memoria
            global grafo_memoria
            grafo_memoria = construir_grafo()
            # Actualizar interfaz
            mostrar_pantalla("ruta")
        else:
            messagebox.showerror("Error", "❌ Error al cargar datos de ejemplo")

def cerrar_sesion():
    """Cierra la sesión actual y vuelve al login"""
    global usuario_actual, ventana_principal
    
    respuesta = messagebox.askyesno("Cerrar sesión", 
                                   "¿Estás seguro de que deseas cerrar sesión?")
    
    if respuesta:
        usuario_actual = None
        
        if ventana_principal:
            ventana_principal.destroy()
        
        crear_interfaz_login()

# ============================================================================
# PUNTO DE ENTRADA PRINCIPAL
# ============================================================================
if __name__ == "__main__":
    try:
        # Iniciar con la interfaz de login
        crear_interfaz_login()
    except Exception as e:
        print(f"✗ Error inesperado: {e}")
        import traceback
        traceback.print_exc()
        input("\nPresiona Enter para salir...")