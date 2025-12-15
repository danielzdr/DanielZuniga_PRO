def liberar_pistas():
    """Libera las pistas que han completado su tiempo de uso"""
    for pista in pistas:
        if pista['ocupada_hasta'] > 0 and reloj_simulado >= pista['ocupada_hasta']:
            if pista['vuelo_actual']:
                # Marcar vuelo como completado
                for vuelo in vuelos:
                    if vuelo['id'] == pista['vuelo_actual']:
                        vuelo['estado'] = 'COMPLETADO'
                        vuelos_completados.append({
                            'id': vuelo['id'],
                            'tipo': vuelo['tipo'],
                            't_inicio': pista['ocupada_hasta'] - pista['tiempo_uso'],
                            't_fin': pista['ocupada_hasta'],
                            'prioridad_final': vuelo['prioridad']
                        })
                        registrar_evento(f"COMPLETADO id_vuelo={vuelo['id']} pista={pista['id_pista']}")
                
                pista['vuelo_actual'] = None
            pista['ocupada_hasta'] = 0

def obtener_pista_libre():
    """Encuentra una pista habilitada y libre"""
    for pista in pistas:
        if pista['habilitada'] == 1 and pista['ocupada_hasta'] == 0:
            return pista
    return None