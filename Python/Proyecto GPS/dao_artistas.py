# database/dao_artistas.py
from .connection import obtener_todos, obtener_uno

def obtener_todas_artistas(conn):
    """Obtener todas las aristas"""
    query = """
    SELECT a.id, a.from_node_id, a.to_node_id, 
           a.distance_km, a.time_min, a.is_one_way,
           a.has_toll, a.is_blocked,
           n1.name as from_name, n2.name as to_name
    FROM artistas a
    JOIN nodos n1 ON a.from_node_id = n1.id
    JOIN nodos n2 ON a.to_node_id = n2.id
    WHERE a.is_blocked = 0
    ORDER BY a.from_node_id, a.to_node_id
    """
    return obtener_todos(conn, query)

def obtener_artistas_desde_nodo(conn, nodo_id):
    """Obtener aristas salientes de un nodo"""
    query = """
    SELECT a.to_node_id as vecino_id, 
           a.distance_km, a.time_min, a.is_one_way,
           a.has_toll, a.is_blocked,
           n.name as vecino_nombre
    FROM artistas a
    JOIN nodos n ON a.to_node_id = n.id
    WHERE a.from_node_id = ? AND a.is_blocked = 0
    ORDER BY a.distance_km
    """
    return obtener_todos(conn, query, (nodo_id,))

def obtener_artista_entre_nodos(conn, desde_id, hacia_id):
    """Obtener arista entre dos nodos específicos"""
    query = """
    SELECT id, distance_km, time_min, is_one_way, has_toll
    FROM artistas 
    WHERE from_node_id = ? AND to_node_id = ? AND is_blocked = 0
    """
    return obtener_uno(conn, query, (desde_id, hacia_id))

def agregar_artista(conn, desde_id, hacia_id, distancia_km, tiempo_min, es_unidireccional=True, tiene_peaje=False):
    """Añadir una nueva arista"""
    query = """
    INSERT INTO artistas 
    (from_node_id, to_node_id, distance_km, time_min, is_one_way, has_toll)
    VALUES (?, ?, ?, ?, ?, ?)
    """
    from .connection import ejecutar_consulta
    cursor = ejecutar_consulta(conn, query, (desde_id, hacia_id, distancia_km, tiempo_min, 
                                           es_unidireccional, tiene_peaje))
    return cursor.lastrowid if cursor else None

def bloquear_artista(conn, artista_id, bloqueado=True):
    """Bloquear o desbloquear una arista (carretera cortada)"""
    query = "UPDATE artistas SET is_blocked = ? WHERE id = ?"
    cursor = ejecutar_consulta(conn, query, (bloqueado, artista_id))
    return cursor.rowcount if cursor else 0