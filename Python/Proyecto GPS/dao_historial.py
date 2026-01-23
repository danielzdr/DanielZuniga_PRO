# database/dao_historial.py
from .connection import obtener_todos, obtener_uno, ejecutar_consulta

def guardar_ruta_historial(conn, origen_id, destino_id, coste_total, ruta_path, 
                          tipo_coste='distance', es_alternativa=False):
    """Guardar una ruta calculada en el historial"""
    query = """
    INSERT INTO historial_rutas 
    (origin_id, destination_id, total_cost, route_path, cost_type, is_alternative)
    VALUES (?, ?, ?, ?, ?, ?)
    """
    cursor = ejecutar_consulta(conn, query, (origen_id, destino_id, coste_total, 
                                           ruta_path, tipo_coste, es_alternativa))
    return cursor.lastrowid if cursor else None

def obtener_ruta_cacheada(conn, origen_id, destino_id, tipo_coste='distance'):
    """Obtener ruta del historial si existe (cache)"""
    query = """
    SELECT total_cost, route_path, is_alternative
    FROM historial_rutas 
    WHERE origin_id = ? AND destination_id = ? AND cost_type = ?
    ORDER BY calculation_date DESC
    LIMIT 1
    """
    return obtener_uno(conn, query, (origen_id, destino_id, tipo_coste))

def obtener_historial_reciente(conn, limite=10):
    """Obtener historial reciente de rutas calculadas"""
    query = """
    SELECT hr.id, hr.calculation_date, hr.total_cost, hr.cost_type,
           hr.is_alternative, hr.route_path,
           n1.name as origen_nombre, n2.name as destino_nombre
    FROM historial_rutas hr
    JOIN nodos n1 ON hr.origin_id = n1.id
    JOIN nodos n2 ON hr.destination_id = n2.id
    ORDER BY hr.calculation_date DESC
    LIMIT ?
    """
    return obtener_todos(conn, query, (limite,))

def limpiar_historial_antiguo(conn, dias=30):
    """Eliminar registros del historial más antiguos que X días"""
    query = """
    DELETE FROM historial_rutas 
    WHERE calculation_date < datetime('now', ?)
    """
    cursor = ejecutar_consulta(conn, query, (f'-{dias} days',))
    return cursor.rowcount if cursor else 0