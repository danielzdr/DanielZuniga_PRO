def generar_informe():
    """Genera el informe final de la simulación"""
    print("\n GENERANDO INFORME ")
    
    # Calcular estadísticas
    tiempo_medio_espera = calcular_tiempo_medio_espera()
    operaciones_por_pista = calcular_operaciones_por_pista()
    emergencias_gestionadas = sum(1 for v in vuelos_completados if v['prioridad_final'] == 2)
    
    # Crear contenido del informe
    contenido = f"""
        - Tiempo simulado (min): {reloj_simulado}
        - Vuelos atendidos: {len(vuelos_completados)}
        - Tiempo medio de espera (min): {tiempo_medio_espera:.1f}
        - Emergencias gestionadas: {emergencias_gestionadas}
        - Uso de pistas: {operaciones_por_pista}
        - Detalle de vuelos completados:"""
    
    for vuelo in vuelos_completados:
        emergencia_info = ", EMERGENCIA" if vuelo['prioridad_final'] == 2 else ""
        contenido += f"• {vuelo['id']} ({vuelo['tipo']}{emergencia_info}) t_inicio={vuelo['t_inicio']} t_fin={vuelo['t_fin']}\n"
    
    # Guardar en archivo
    with open('informe.log', 'w', encoding='utf-8') as f:
        f.write(contenido)
    
    # Mostrar en pantalla
    print(contenido)
    print(" Informe guardado en 'informe.log'")

def calcular_tiempo_medio_espera():
    """Calcula el tiempo medio de espera de los vuelos completados"""
    if not vuelos_completados:
        return 0.0
    
    total_espera = 0
    for vuelo in vuelos_completados:
        tiempo_espera = vuelo['t_inicio'] - next(v['eta_etd'] for v in vuelos if v['id'] == vuelo['id'])
        total_espera += max(0, tiempo_espera)
    
    return total_espera / len(vuelos_completados)

def calcular_operaciones_por_pista():
    """Calcula las operaciones realizadas por cada pista"""
    operaciones = {}
    for pista in pistas:
        # Contar eventos de asignación para esta pista
        count = sum(1 for e in eventos_log if f"pista={pista['id_pista']}" in e and "ASIGNACION" in e)
        operaciones[pista['id_pista']] = count
    
    return " ".join([f"{pista}={op} operaciones" for pista, op in operaciones.items()])