# database/dao_pesos.py
from .connection import obtener_todos, obtener_uno, ejecutar_consulta

def obtener_pesos_por_artista(conn, artista_id):
    """Obtener todos los pesos de una arista"""
    query = """
    SELECT p.id, p.artista_id, p.coste,
           a.from_node_id, a.to_node_id
    FROM pesos p
    JOIN artistas a ON p.artista_id = a.id
    WHERE p.artista_id = ?
    ORDER BY p.id
    """
    return obtener_todos(conn, query, (artista_id,))

def agregar_peso(conn, artista_id, coste):
    """Añadir un nuevo peso (coste adicional) a una arista"""
    query = "INSERT INTO pesos (artista_id, coste) VALUES (?, ?)"
    cursor = ejecutar_consulta(conn, query, (artista_id, coste))
    return cursor.lastrowid if cursor else None

def calcular_coste_total_artista(conn, artista_id):
    """Calcular coste total de una arista (distancia/tiempo + pesos adicionales)"""
    # Obtener arista base
    query_artista = "SELECT distance_km, time_min FROM artistas WHERE id = ?"
    artista = obtener_uno(conn, query_artista, (artista_id,))
    
    if not artista:
        return None
    
    # Sumar pesos adicionales
    query_pesos = "SELECT SUM(coste) as total_pesos FROM pesos WHERE artista_id = ?"
    pesos = obtener_uno(conn, query_pesos, (artista_id,))
    
    total_pesos = pesos['total_pesos'] if pesos and pesos['total_pesos'] else 0
    
    return {
        'distancia_total': artista['distance_km'] + total_pesos,
        'tiempo_total': artista['time_min'] + total_pesos,
        'distancia_base': artista['distance_km'],
        'tiempo_base': artista['time_min'],
        'pesos_adicionales': total_pesos
    }