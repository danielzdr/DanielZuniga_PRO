# database/cargador_csv.py
import csv
from .connection import crear_conexion, ejecutar_consulta

def cargar_datos_desde_csv(ruta_csv, ruta_db):
    """Cargar datos iniciales desde archivo CSV a la base de datos"""
    
    conn = crear_conexion(ruta_db)
    if not conn:
        print("❌ No se pudo conectar a la base de datos")
        return False
    
    try:
        with open(ruta_csv, 'r', encoding='utf-8') as archivo:
            lector = csv.DictReader(archivo)
            
            # Suponemos que el CSV tiene las columnas:
            # from_name, to_name, distance_km, time_min, is_one_way
            
            nodos_procesados = set()
            artistas_creadas = 0
            
            for fila in lector:
                # 1. Asegurar que existen los nodos
                for nombre_nodo in [fila['from_name'], fila['to_name']]:
                    if nombre_nodo not in nodos_procesados:
                        # Verificar si el nodo ya existe
                        query_check = "SELECT id FROM nodos WHERE name = ?"
                        cursor = conn.execute(query_check, (nombre_nodo,))
                        existe = cursor.fetchone()
                        
                        if not existe:
                            # Insertar nuevo nodo
                            query_insert = "INSERT INTO nodos (name) VALUES (?)"
                            conn.execute(query_insert, (nombre_nodo,))
                        
                        nodos_procesados.add(nombre_nodo)
                
                # 2. Obtener IDs de los nodos
                query_from = "SELECT id FROM nodos WHERE name = ?"
                cursor = conn.execute(query_from, (fila['from_name'],))
                desde_nodo = cursor.fetchone()
                
                query_to = "SELECT id FROM nodos WHERE name = ?"
                cursor = conn.execute(query_to, (fila['to_name'],))
                hacia_nodo = cursor.fetchone()
                
                if desde_nodo and hacia_nodo:
                    desde_id = desde_nodo['id']
                    hacia_id = hacia_nodo['id']
                    
                    # 3. Crear arista
                    query_artista = """
                    INSERT OR REPLACE INTO artistas 
                    (from_node_id, to_node_id, distance_km, time_min, is_one_way)
                    VALUES (?, ?, ?, ?, ?)
                    """
                    
                    # Convertir valores
                    distancia = float(fila['distance_km'])
                    tiempo = float(fila['time_min'])
                    es_unidireccional = int(fila.get('is_one_way', 1))
                    
                    conn.execute(query_artista, (desde_id, hacia_id, distancia, 
                                               tiempo, es_unidireccional))
                    artistas_creadas += 1
        
        conn.commit()
        print(f"✅ Datos cargados desde CSV:")
        print(f"   - Nodos procesados: {len(nodos_procesados)}")
        print(f"   - Aristas creadas: {artistas_creadas}")
        
        return True
        
    except Exception as e:
        print(f"❌ Error cargando datos desde CSV: {e}")
        conn.rollback()
        return False
    
    finally:
        conn.close()

def verificar_requisitos_base_datos(conn):
    """Verificar que la base de datos cumple con los requisitos del proyecto"""
    
    # Contar nodos
    query_nodos = "SELECT COUNT(*) as total FROM nodos"
    resultado_nodos = obtener_uno(conn, query_nodos)
    total_nodos = resultado_nodos['total'] if resultado_nodos else 0
    
    # Contar aristas
    query_artistas = "SELECT COUNT(*) as total FROM artistas"
    resultado_artistas = obtener_uno(conn, query_artistas)
    total_artistas = resultado_artistas['total'] if resultado_artistas else 0
    
    # Contar aristas unidireccionales
    query_unidireccionales = """
    SELECT COUNT(*) as total 
    FROM artistas 
    WHERE is_one_way = 1
    """
    resultado_uni = obtener_uno(conn, query_unidireccionales)
    total_unidireccionales = resultado_uni['total'] if resultado_uni else 0
    
    # Contar aristas bidireccionales (se crean automáticamente)
    # Para contar correctamente, necesitamos verificar pares
    query_bidireccionales = """
    SELECT COUNT(DISTINCT a1.id) as total
    FROM artistas a1
    JOIN artistas a2 ON a1.from_node_id = a2.to_node_id 
                     AND a1.to_node_id = a2.from_node_id
    WHERE a1.is_one_way = 0 OR a2.is_one_way = 0
    """
    resultado_bi = obtener_uno(conn, query_bidireccionales)
    total_bidireccionales = resultado_bi['total'] if resultado_bi else 0
    
    print("\n" + "="*50)
    print("📊 ESTADÍSTICAS DE LA BASE DE DATOS")
    print("="*50)
    print(f"Nodos totales: {total_nodos}")
    print(f"Aristas totales: {total_artistas}")
    print(f"Aristas unidireccionales: {total_unidireccionales}")
    print(f"Aristas bidireccionales: {total_bidireccionales}")
    
    # Verificar requisitos mínimos
    print("\n" + "="*50)
    print("✅ VERIFICACIÓN DE REQUISITOS MÍNIMOS")
    print("="*50)
    
    cumple = True
    
    if total_nodos >= 40:
        print(f"✓ Nodos: {total_nodos}/40 (CUMPLE)")
    else:
        print(f"✗ Nodos: {total_nodos}/40 (NO CUMPLE)")
        cumple = False
    
    if total_artistas >= 80:
        print(f"✓ Aristas: {total_artistas}/80 (CUMPLE)")
    else:
        print(f"✗ Aristas: {total_artistas}/80 (NO CUMPLE)")
        cumple = False
    
    if total_unidireccionales >= 40:
        print(f"✓ Unidireccionales: {total_unidireccionales}/40 (CUMPLE)")
    else:
        print(f"✗ Unidireccionales: {total_unidireccionales}/40 (NO CUMPLE)")
        cumple = False
    
    return {
        'cumple': cumple,
        'total_nodos': total_nodos,
        'total_artistas': total_artistas,
        'unidireccionales': total_unidireccionales,
        'bidireccionales': total_bidireccionales
    }