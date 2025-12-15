#Ejercicio 3 Reloj simulado

import time
from datetime import datetime

def avanzar_reloj(minutos=1):
    """Avanza el reloj simulado la cantidad especificada de minutos"""
    global reloj_simulado
    
    for minuto in range(minutos):
        reloj_simulado += 1
        print(f"\n--- Minuto simulado: {reloj_simulado} ---")
        
        # Actualizar sistema
        actualizar_combustible()
        liberar_pistas()
        procesar_colas()
        asignar_pistas()
        
        # Pausa para simular tiempo real (5 segundos por minuto simulado)
        time.sleep(5)

def actualizar_combustible():
    """Actualiza el combustible de los vuelos en espera de aterrizaje"""
    for vuelo in vuelos:
        if vuelo['tipo'] == 'ATERRIZAJE' and vuelo['estado'] == 'EN_COLA':
            vuelo['combustible'] -= 1
            
            # Verificar emergencia por combustible bajo
            if vuelo['combustible'] <= 5 and vuelo['prioridad'] < 2:
                vuelo['prioridad'] = 2
                registrar_evento(f"EMERGENCIA id_vuelo={vuelo['id']} prioridad=2 motivo=combustible<=5")