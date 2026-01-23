# database/dao_nodos.py
from .connection import crear_conexion, obtener_todos, obtener_uno, ejecutar_consulta

def obtener_todos_nodos(conn):
    """Obtener todos los nodos"""
    query = "SELECT id, name, latitude, longitude FROM nodos ORDER BY id"
    return obtener_todos(conn, query)

def obtener_nodo_por_id(conn, nodo_id):
    """Obtener nodo por ID"""
    query = "SELECT id, name, latitude, longitude FROM nodos WHERE id = ?"
    return obtener_uno(conn, query, (nodo_id,))

def obtener_nodo_por_nombre(conn, nombre):
    """Obtener nodo por nombre"""
    query = "SELECT id, name, latitude, longitude FROM nodos WHERE LOWER(name) = LOWER(?)"
    return obtener_uno(conn, query, (nombre,))

def obtener_nombre_nodo_por_id(conn, nodo_id):
    """Obtener solo el nombre de un nodo por su ID"""
    query = "SELECT name FROM nodos WHERE id = ?"
    resultado = obtener_uno(conn, query, (nodo_id,))
    return resultado['name'] if resultado else None

def agregar_nodo(conn, nombre, latitud=None, longitud=None):
    """Añadir un nuevo nodo"""
    query = "INSERT INTO nodos (name, latitude, longitude) VALUES (?, ?, ?)"
    cursor = ejecutar_consulta(conn, query, (nombre, latitud, longitud))
    return cursor.lastrowid if cursor else None