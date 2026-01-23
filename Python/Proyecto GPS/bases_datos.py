# database/connection.py
import sqlite3
import os

def crear_conexion(db_file):
    """Crear conexión a la base de datos SQLite"""
    conn = None
    try:
        conn = sqlite3.connect(db_file)
        conn.row_factory = sqlite3.Row  # Para acceso por nombre de columna
        return conn
    except sqlite3.Error as e:
        print(f"❌ Error conectando a la base de datos: {e}")
    return conn

def ejecutar_consulta(conn, query, params=()):
    """Ejecutar una consulta SQL (INSERT, UPDATE, DELETE)"""
    try:
        cursor = conn.cursor()
        cursor.execute(query, params)
        conn.commit()
        return cursor
    except sqlite3.Error as e:
        print(f"❌ Error ejecutando consulta: {e}")
        return None

def obtener_todos(conn, query, params=()):
    """Obtener todos los resultados de una consulta SELECT"""
    try:
        cursor = conn.cursor()
        cursor.execute(query, params)
        return cursor.fetchall()
    except sqlite3.Error as e:
        print(f"❌ Error obteniendo datos: {e}")
        return []

def obtener_uno(conn, query, params=()):
    """Obtener un solo resultado de una consulta SELECT"""
    try:
        cursor = conn.cursor()
        cursor.execute(query, params)
        return cursor.fetchone()
    except sqlite3.Error as e:
        print(f"❌ Error obteniendo dato: {e}")
        return None