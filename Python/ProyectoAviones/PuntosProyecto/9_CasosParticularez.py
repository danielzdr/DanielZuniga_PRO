def resolver_empates(candidatos):
    """Resuelve empates según la política establecida"""
    if len(candidatos) <= 1:
        return candidatos[0] if candidatos else None
    
    # Ordenar por ID alfabéticamente para desempate
    candidatos.sort(key=lambda x: x['id'])
    return candidatos[0]

def verificar_combustible_critico():
    """Verifica y actualiza prioridad por combustible crítico"""
    for vuelo in vuelos:
        if (vuelo['tipo'] == 'ATERRIZAJE' and 
            vuelo['estado'] == 'EN_COLA' and 
            vuelo['combustible'] <= 5 and 
            vuelo['prioridad'] < 2):
            
            vuelo['prioridad'] = 2
            registrar_evento(f"EMERGENCIA id_vuelo={vuelo['id']} prioridad=2 motivo=combustible<=5")
            print(f"🚨 ALERTA: Vuelo {vuelo['id']} en emergencia por combustible crítico!")

def manejar_pista_deshabilitada(pista):
    """Maneja el caso de pista deshabilitada"""
    if pista['habilitada'] == 0:
        registrar_evento(f"PISTA_DESHABILITADA id_pista={pista['id_pista']}")
        return True
    return False

def reintentar_asignacion_pista():
    """Reintenta asignación de pistas en el siguiente minuto"""
    # Esta función se llama automáticamente en cada avance de minuto
    # Las pistas deshabilitadas se omiten naturalmente en obtener_pista_libre()
    pistas_deshabilitadas = [p for p in pistas if p['habilitada'] == 0]
    if pistas_deshabilitadas:
        for pista in pistas_deshabilitadas:
            registrar_evento(f"PISTA_NO_DISPONIBLE id_pista={pista['id_pista']}")

def cierre_temporal_pista(id_pista, minutos):
    """Cierra temporalmente una pista por N minutos (opcional)"""
    pista = next((p for p in pistas if p['id_pista'] == id_pista), None)
    if pista:
        pista['habilitada'] = 0
        registrar_evento(f"CIERRE_TEMPORAL id_pista={id_pista} duracion={minutos}min")
        
        # Programar reapertura (simplificado)
        # En una implementación completa, se usaría un sistema de eventos programados
        print(f"⚠️  Pista {id_pista} cerrada temporalmente por {minutos} minutos")

def cancelar_vuelo(id_vuelo, motivo="operativo"):
    """Cancela un vuelo y lo retira del sistema (opcional)"""
    vuelo = next((v for v in vuelos if v['id'] == id_vuelo), None)
    if vuelo:
        if vuelo['estado'] == 'EN_COLA':
            # Remover de las colas
            if vuelo['id'] in flujo_aterrizaje:
                flujo_aterrizaje.remove(vuelo['id'])
            elif vuelo['id'] in flujo_despegue:
                flujo_despegue.remove(vuelo['id'])
        
        vuelo['estado'] = 'CANCELADO'
        registrar_evento(f"CANCELACION id_vuelo={id_vuelo} motivo={motivo}")
        print(f"✓ Vuelo {id_vuelo} cancelado por motivo: {motivo}")
    else:
        print(f"✗ Error: Vuelo {id_vuelo} no encontrado")

# Modificar la función de actualización para incluir casos particulares
def actualizar_sistema_completo():
    """Actualización completa del sistema incluyendo casos particulares"""
    # Verificar combustible crítico
    verificar_combustible_critico()
    
    # Reintentar asignación de pistas deshabilitadas
    reintentar_asignacion_pista()
    
    # Actualizar combustible
    actualizar_combustible()
    
    # Liberar pistas
    liberar_pistas()
    
    # Procesar colas
    procesar_colas()
    
    # Asignar pistas
    asignar_pistas()

# Actualizar la función avanzar_reloj para usar la actualización completa
def avanzar_reloj(minutos=1):
    """Avanza el reloj simulado la cantidad especificada de minutos"""
    global reloj_simulado
    
    for minuto in range(minutos):
        reloj_simulado += 1
        print(f"\n--- Minuto simulado: {reloj_simulado} ---")
        
        # Actualizar sistema completo incluyendo casos particulares
        actualizar_sistema_completo()
        
        # Pausa para simular tiempo real
        time.sleep(5)