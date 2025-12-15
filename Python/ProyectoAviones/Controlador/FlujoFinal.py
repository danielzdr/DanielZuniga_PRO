import time
from datetime import datetime
import os

# Estructuras de datos para almacenar la información
pistas = []
pistas_estado = {}  # {id_pista: {"estado": "LIBRE"/"OCUPADA", "vuelo": None, "fin_ocupacion": 0}}
reloj = 0
eventos_log = []
vuelos = []  # Lista de vuelos (todos, programados + activos + completados)
flujo_aterrizaje = []  # IDs de vuelos pendientes de aterrizar (ACTIVOS)
flujo_despegue = []    # IDs de vuelos pendientes de despegar (ACTIVOS)
vuelos_completados = []  # Histórico de vuelos completados

# NUEVO: vuelos que todavía no han entrado al sistema (esperando a su 'tiempo')
vuelos_programados = []

TIPOS_PERMITIDOS = {"ATERRIZAJE", "DESPEGUE"}
ESTADOS_PERMITIDOS = {"EN_COLA", "ASIGNADO", "COMPLETADO", "CANCELADO"}


def avanzar_reloj(minutos=1):
    """Avanza el reloj de simulación la cantidad de minutos especificada."""
    global reloj
    
    for minuto in range(minutos):
        reloj += 1
        print(f"\n⏰ [t={reloj}] Avanzando reloj... ⏰")
        
        # Ejecutar todas las rutinas de actualización
        actualizar_sistema()
        
        # Esperar 2 segundos reales por cada minuto simulado (reducido para pruebas)
        if minutos == 1:  
            time.sleep(2)


def crear_vuelo(id_, tipo, tiempo, prioridad, combustible, estado):
    """
    Crea y devuelve un diccionario que representa un vuelo.
    """
    vuelo = {
        "id": id_,
        "tipo": tipo,                  # "ATERRIZAJE" o "DESPEGUE"
        "tiempo": tiempo,              # int: eta/etd en minutos simulados (campo auxiliar)
        "eta": tiempo if tipo == 'ATERRIZAJE' else None,
        "etd": tiempo if tipo == 'DESPEGUE' else None,
        "prioridad": prioridad,        # int: 0, 1 o 2
        "combustible": combustible,    # int o None
        "estado": estado               # "EN_COLA", "ASIGNADO", etc.
    }
    return vuelo


def add_vuelo(vuelos_activos, vuelo):
    """
    Añade un vuelo al diccionario de vuelos activos usando el id como clave.
    """
    vuelos_activos[vuelo["id"]] = vuelo


# ==============================
#   CARGA Y PROGRAMACIÓN DE VUELOS
# ==============================

def cargar_vuelos_desde_txt(ruta_fichero, vuelos_activos):
    """
    Lee vuelos desde un archivo de texto (.txt), línea por línea.

    - TODOS los vuelos se guardan en la lista global `vuelos`.
    - Los vuelos con estado EN_COLA se consideran PROGRAMADOS y se guardan en
      `vuelos_programados`. Entrarán en las colas reales cuando reloj >= tiempo.
    - El resto (ASIGNADO, COMPLETADO, CANCELADO) se procesan como hasta ahora.
    """
    global vuelos, flujo_aterrizaje, flujo_despegue, vuelos_completados, vuelos_programados

    vuelos_programados = []   # reiniciamos la lista de programados
    vuelos.clear()            # limpiamos la lista global de vuelos
    flujo_aterrizaje.clear()
    flujo_despegue.clear()

    try:
        with open(ruta_fichero, mode="r", encoding="utf-8") as f:
            for num_linea, linea in enumerate(f, 1):
                linea = linea.strip()
                if not linea or linea.startswith("#") or linea.lower().startswith("id,"):
                    continue

                partes = [p.strip() for p in linea.split(",")]
                
                # Debug: mostrar la línea procesada
                print(f"📖 Procesando línea {num_linea}: {linea}")
                print(f"   Partes encontradas: {partes}")

                if len(partes) < 6:
                    print(f"[WARN] Línea {num_linea} incompleta. Se esperaban 6 campos, se encontraron {len(partes)}: {linea}")
                    continue

                try:
                    id_ = partes[0]
                    tipo = partes[1].upper()
                    
                    # Manejar campos de tiempo que pueden estar vacíos
                    tiempo_str = partes[2]
                    tiempo = int(tiempo_str) if tiempo_str else 0
                    
                    prioridad = int(partes[3])

                    combustible_str = partes[4]
                    combustible = int(combustible_str) if combustible_str else None

                    estado = partes[5].upper()

                    # Validaciones básicas
                    if tipo not in TIPOS_PERMITIDOS:
                        print(f"[WARN] Línea {num_linea}: Tipo de vuelo no válido en {id_}: {tipo}")
                        continue

                    if estado not in ESTADOS_PERMITIDOS:
                        print(f"[WARN] Línea {num_linea}: Estado no válido en {id_}: {estado}. Estados permitidos: {ESTADOS_PERMITIDOS}")
                        continue

                    if tipo == "ATERRIZAJE" and combustible is None:
                        print(f"[WARN] Línea {num_linea}: Vuelo de aterrizaje sin combustible: {id_}")

                    # Crear vuelo para estructura activa (diccionario)
                    vuelo_activo = crear_vuelo(
                        id_=id_,
                        tipo=tipo,
                        tiempo=tiempo,
                        prioridad=prioridad,
                        combustible=combustible,
                        estado=estado
                    )
                    add_vuelo(vuelos_activos, vuelo_activo)
                    
                    # Agregar a la lista global `vuelos`
                    vuelo_global = {
                        'id': id_,
                        'tipo': tipo,
                        'tiempo': tiempo,
                        'eta': tiempo if tipo == 'ATERRIZAJE' else None,
                        'etd': tiempo if tipo == 'DESPEGUE' else None,
                        'prioridad': prioridad,
                        'combustible': combustible,
                        'estado': estado  # Mantener el estado original del archivo
                    }
                    vuelos.append(vuelo_global)
                    
                    # Lógica según estado
                    if estado == 'EN_COLA':
                        # En lugar de meterlo ya en las colas, lo programamos
                        vuelos_programados.append(vuelo_global)
                        print(f"✓ Programado vuelo {id_} - {tipo} para el minuto {tiempo}")
                    elif estado == 'ASIGNADO':
                        # Para vuelos ASIGNADOS, necesitamos asignarlos a una pista automáticamente
                        asignar_vuelo_inicial(id_, tipo)
                        print(f"✓ Cargado vuelo {id_} - {tipo} - ASIGNADO")
                    elif estado == 'COMPLETADO':
                        vuelos_completados.append({
                            'id': id_,
                            'tipo': tipo,
                            'inicio': 0,
                            'fin': 0,
                            'prioridad_final': prioridad
                        })
                        print(f"✓ Cargado vuelo {id_} - {tipo} - COMPLETADO")
                    else:
                        # CANCELADO u otros estados válidos
                        print(f"✓ Cargado vuelo {id_} - {tipo} - {estado}")

                except ValueError as e:
                    print(f"[ERROR] Línea {num_linea} con datos inválidos: {linea}")
                    print(f"       Error: {e}")
                    print(f"       Partes: {partes}")
                    continue

        print(f"\n✅ CARGA COMPLETADA: {len(vuelos)} vuelos cargados en el sistema")
        print(f"   {len(vuelos_programados)} vuelos programados para entrar según su tiempo.")

    except FileNotFoundError:
        print(f"[ERROR] No se encontró el archivo {ruta_fichero}")
    except Exception as e:
        print(f"[ERROR] Error leyendo {ruta_fichero}: {e}")


def activar_vuelos_programados():
    """
    Mueve de vuelos_programados -> colas reales (flujo_aterrizaje / flujo_despegue)
    todos los vuelos cuyo 'tiempo' sea <= reloj actual.
    """
    global vuelos_programados, flujo_aterrizaje, flujo_despegue, reloj

    if not vuelos_programados:
        return

    restantes = []

    for vuelo in vuelos_programados:
        t_llegada = vuelo.get("tiempo", 0)
        vid = vuelo["id"]
        tipo = vuelo["tipo"]

        if t_llegada <= reloj:
            # Entra en el sistema
            vuelo["estado"] = "EN_COLA"

            if tipo == "ATERRIZAJE":
                if vid not in flujo_aterrizaje:
                    flujo_aterrizaje.append(vid)
            else:  # DESPEGUE
                if vid not in flujo_despegue:
                    flujo_despegue.append(vid)

            registrar_evento(f"LLEGADA_PROGRAMADA id_vuelo={vid} tipo={tipo} tiempo={t_llegada}")
            print(f"🛬 Activado vuelo {vid} ({tipo}) en minuto {reloj}")
        else:
            restantes.append(vuelo)

    vuelos_programados = restantes


def asignar_vuelo_inicial(id_vuelo, tipo):
    """Asigna automáticamente un vuelo a una pista libre al inicio"""
    global pistas_estado
    
    # Buscar una pista libre
    for pista_id, estado_pista in pistas_estado.items():
        pista = encontrar_pista_por_id(pista_id)
        if estado_pista['estado'] == 'LIBRE' and pista and pista['habilitada']:
            # Asignar el vuelo a la pista
            tiempo_uso = pista['tiempo_uso']
            pistas_estado[pista_id] = {
                'estado': 'OCUPADA',
                'vuelo': id_vuelo,
                'fin_ocupacion': reloj + tiempo_uso
            }
            
            # Actualizar el vuelo
            vuelo = encontrar_vuelo_por_id(id_vuelo)
            if vuelo:
                vuelo['estado'] = 'ASIGNADO'
            
            registrar_evento(f"ASIGNACION_INICIAL id_vuelo={id_vuelo} pista={pista_id} tipo={tipo}")
            print(f"🎯 Vuelo {id_vuelo} asignado automáticamente a pista {pista_id}")
            return True
    
    print(f"⚠️  No se pudo asignar vuelo {id_vuelo}: no hay pistas libres")
    return False


def mostrar_vuelos(vuelos_activos):
    """
    Muestra por consola los vuelos activos.
    """
    if not vuelos_activos:
        print("No hay vuelos activos.")
        return

    print("Vuelos activos cargados:")
    for vuelo in vuelos_activos.values():
        print(
            f"- {vuelo['id']} | {vuelo['tipo']} | t = {vuelo['tiempo']} | "
            f"prio = {vuelo['prioridad']} | comb = {vuelo['combustible']} | "
            f"estado = {vuelo['estado']}"
        )


def cargar_pistas_desde_archivo():
    """Carga las pistas desde el archivo pistas.txt"""
    global pistas, pistas_estado
    try:
        with open("pistas.txt", "r", encoding="utf-8") as f:
            lineas = f.readlines()
            
        print(f"📖 Leyendo {len(lineas)} líneas de pistas.txt")
        
        # Saltar la cabecera si existe
        start_index = 0
        if lineas and any(cabecera in lineas[0].upper() for cabecera in ['ID', 'CATEGORIA', 'NOMBRE']):
            print("📋 Saltando cabecera del archivo de pistas")
            start_index = 1
        
        pistas_cargadas = 0
        for i, linea in enumerate(lineas[start_index:], start_index + 1):
            linea = linea.strip()
            if not linea or linea.startswith("#"):
                continue
                
            datos = [d.strip() for d in linea.split(',')]
            print(f"   Procesando pista: {datos}")
            
            if len(datos) >= 4:
                try:
                    pista_id = datos[0]
                    categoria = datos[1]
                    
                    # Manejar diferentes formatos de tiempo_uso
                    tiempo_uso_str = datos[2]
                    if tiempo_uso_str.isdigit():
                        tiempo_uso = int(tiempo_uso_str)
                    else:
                        # Si no es número, usar valor por defecto
                        print(f"⚠️  Tiempo de uso no numérico para pista {pista_id}: '{tiempo_uso_str}'. Usando valor por defecto: 3")
                        tiempo_uso = 3
                    
                    habilitada = datos[3] in ['1', 'True', 'true', 'SI', 'si', 'Sí', 'sí']
                    
                    pista = {
                        'id': pista_id,
                        'categoria': categoria,
                        'tiempo_uso': tiempo_uso,
                        'habilitada': habilitada
                    }
                    pistas.append(pista)
                    pistas_estado[pista['id']] = {
                        'estado': 'LIBRE',
                        'vuelo': None,
                        'fin_ocupacion': 0
                    }
                    pistas_cargadas += 1
                    print(f"✓ Cargada pista {pista['id']} - {pista['categoria']} - Tiempo: {pista['tiempo_uso']}min")
                    
                except Exception as e:
                    print(f"✗ Error procesando pista en línea {i}: {linea}")
                    print(f"  Error: {e}")
                    continue
                
        print(f"✅ Cargadas {pistas_cargadas} pistas desde pistas.txt")
        
        # Si no se cargaron pistas, crear algunas por defecto
        if pistas_cargadas == 0:
            print("⚠️  No se pudieron cargar pistas. Creando pistas por defecto...")
            pistas_por_defecto = [
                {'id': 'P01', 'categoria': 'COMERCIAL', 'tiempo_uso': 3, 'habilitada': True},
                {'id': 'P02', 'categoria': 'COMERCIAL', 'tiempo_uso': 3, 'habilitada': True},
                {'id': 'P03', 'categoria': 'EMERGENCIA', 'tiempo_uso': 2, 'habilitada': True}
            ]
            for pista in pistas_por_defecto:
                pistas.append(pista)
                pistas_estado[pista['id']] = {
                    'estado': 'LIBRE',
                    'vuelo': None,
                    'fin_ocupacion': 0
                }
            print("✓ Creadas 3 pistas por defecto")
        
    except FileNotFoundError:
        print("✗ Error: No se encontró el archivo pistas.txt")
        # Crear pistas por defecto para que el sistema funcione
        pistas_por_defecto = [
            {'id': 'P01', 'categoria': 'COMERCIAL', 'tiempo_uso': 3, 'habilitada': True},
            {'id': 'P02', 'categoria': 'COMERCIAL', 'tiempo_uso': 3, 'habilitada': True},
            {'id': 'P03', 'categoria': 'EMERGENCIA', 'tiempo_uso': 2, 'habilitada': True}
        ]
        for pista in pistas_por_defecto:
            pistas.append(pista)
            pistas_estado[pista['id']] = {
                'estado': 'LIBRE',
                'vuelo': None,
                'fin_ocupacion': 0
            }
        print("✓ Creadas 3 pistas por defecto")
    except Exception as e:
        print(f"✗ Error al cargar pistas: {e}")
        # Crear pistas por defecto para que el sistema funcione
        pistas_por_defecto = [
            {'id': 'P01', 'categoria': 'COMERCIAL', 'tiempo_uso': 3, 'habilitada': True},
            {'id': 'P02', 'categoria': 'COMERCIAL', 'tiempo_uso': 3, 'habilitada': True},
            {'id': 'P03', 'categoria': 'EMERGENCIA', 'tiempo_uso': 2, 'habilitada': True}
        ]
        for pista in pistas_por_defecto:
            pistas.append(pista)
            pistas_estado[pista['id']] = {
                'estado': 'LIBRE',
                'vuelo': None,
                'fin_ocupacion': 0
            }
        print("✓ Creadas 3 pistas por defecto debido a error")


def registrar_evento(mensaje):
    """Registra un evento en el log"""
    global eventos_log
    evento = f"[t={reloj}] {mensaje}"
    eventos_log.append(evento)
    print(f"📝 {evento}")
    
    # Escribir inmediatamente en el archivo de eventos
    try:
        with open("eventos.txt", "a", encoding="utf-8") as f:
            f.write(evento + "\n")
    except Exception as e:
        print(f"✗ Error al escribir en eventos.txt: {e}")


def agregar_vuelo_manual():
    """Permite agregar un vuelo manualmente"""
    global vuelos, flujo_aterrizaje, flujo_despegue, vuelos_programados
    
    print("\n--- AGREGAR VUELO MANUAL ---")
    
    id_vuelo = input("ID del vuelo (ej: IB123): ").strip().upper()
    if not id_vuelo:
        print("✗ El ID del vuelo no puede estar vacío")
        return
    
    # Verificar si el ID ya existe
    for vuelo in vuelos:
        if vuelo['id'] == id_vuelo:
            print("✗ Ya existe un vuelo con ese ID")
            return
    
    print("Tipo de vuelo:")
    print("1. ATERRIZAJE")
    print("2. DESPEGUE")
    opcion_tipo = input("Seleccione (1-2): ").strip()
    
    if opcion_tipo == '1':
        tipo = 'ATERRIZAJE'
        try:
            eta = int(input("ETA (minuto estimado de llegada): "))
            combustible = int(input("Combustible (minutos restantes): "))
            etd = None
        except ValueError:
            print("✗ Los valores numéricos deben ser enteros")
            return
    elif opcion_tipo == '2':
        tipo = 'DESPEGUE'
        try:
            etd = int(input("ETD (minuto estimado de despegue): "))
            eta = None
            combustible = None
        except ValueError:
            print("✗ Los valores numéricos deben ser enteros")
            return
    else:
        print("✗ Opción inválida")
        return
    
    print("Prioridad:")
    print("0. Normal")
    print("1. Alta") 
    print("2. Emergencia")
    try:
        prioridad = int(input("Seleccione (0-2): ").strip())
        if prioridad not in [0, 1, 2]:
            print("✗ Prioridad debe ser 0, 1 o 2")
            return
    except ValueError:
        print("✗ La prioridad debe ser un número")
        return
    
    print("Estado inicial:")
    print("1. EN_COLA (programado, entrará cuando el reloj alcance ETA/ETD)")
    print("2. ASIGNADO")
    print("3. COMPLETADO")
    opcion_estado = input("Seleccione (1-3): ").strip()
    
    estado_map = {'1': 'EN_COLA', '2': 'ASIGNADO', '3': 'COMPLETADO'}
    if opcion_estado not in estado_map:
        print("✗ Opción de estado inválida")
        return
    
    estado = estado_map[opcion_estado]
    
    nuevo_vuelo = {
        'id': id_vuelo,
        'tipo': tipo,
        'eta': eta,
        'etd': etd,
        'tiempo': eta if tipo == "ATERRIZAJE" else etd,
        'prioridad': prioridad,
        'combustible': combustible,
        'estado': estado
    }
    
    vuelos.append(nuevo_vuelo)
    
    # Manejar según el estado
    if estado == 'EN_COLA':
        # Lo tratamos como PROGRAMADO
        vuelos_programados.append(nuevo_vuelo)
        registrar_evento(f"PROGRAMADO_MANUAL id_vuelo={id_vuelo} tipo={tipo} tiempo={nuevo_vuelo['tiempo']}")
    elif estado == 'ASIGNADO':
        # Intentar asignar automáticamente
        if not asignar_vuelo_inicial(id_vuelo, tipo):
            # Si no se pudo asignar, poner como programado en cola
            nuevo_vuelo['estado'] = 'EN_COLA'
            vuelos_programados.append(nuevo_vuelo)
            registrar_evento(f"PROGRAMADO_MANUAL id_vuelo={id_vuelo} tipo={tipo} (falló asignación inicial)")
    elif estado == 'COMPLETADO':
        vuelos_completados.append({
            'id': id_vuelo,
            'tipo': tipo,
            'inicio': 0,
            'fin': 0,
            'prioridad_final': prioridad
        })
        registrar_evento(f"COMPLETADO id_vuelo={id_vuelo} tipo={tipo}")
    
    print(f"✓ Vuelo {id_vuelo} agregado correctamente - Estado inicial: {estado}")


def encontrar_vuelo_por_id(id_vuelo):
    """Encuentra un vuelo por su ID"""
    for vuelo in vuelos:
        if vuelo['id'] == id_vuelo:
            return vuelo
    return None


def actualizar_combustible():
    """Reduce el combustible de los vuelos en cola de aterrizaje"""
    for vuelo_id in flujo_aterrizaje[:]:  # Usar copia para evitar problemas al modificar
        vuelo = encontrar_vuelo_por_id(vuelo_id)
        if vuelo and vuelo.get('combustible') is not None:
            vuelo['combustible'] -= 1
            
            # Verificar emergencia por combustible
            if vuelo['combustible'] <= 5 and vuelo['prioridad'] != 2:
                vuelo['prioridad'] = 2
                registrar_evento(f"EMERGENCIA id_vuelo={vuelo_id} prioridad=2 motivo=combustible_bajo ({vuelo['combustible']}min)")


def liberar_pistas():
    """Libera las pistas que han completado su tiempo de uso"""
    for pista_id, estado in list(pistas_estado.items()):
        if estado['estado'] == 'OCUPADA' and estado['fin_ocupacion'] <= reloj:
            vuelo_id = estado['vuelo']
            vuelo = encontrar_vuelo_por_id(vuelo_id)
            if vuelo:
                # Marcar vuelo como completado y registrar evento
                vuelo['estado'] = 'COMPLETADO'
                registrar_evento(f"COMPLETADO id_vuelo={vuelo_id} pista={pista_id}")
                
                # Mover a completados
                pista = encontrar_pista_por_id(pista_id)
                inicio_operacion = estado['fin_ocupacion'] - (pista['tiempo_uso'] if pista else 0)
                vuelos_completados.append({
                    'id': vuelo_id,
                    'tipo': vuelo['tipo'],
                    'inicio': inicio_operacion,
                    'fin': estado['fin_ocupacion'],
                    'pista': pista_id,
                    'prioridad_final': vuelo.get('prioridad', 0)
                })
                
                # Quitar de flujos si aún están allí
                if vuelo_id in flujo_aterrizaje:
                    flujo_aterrizaje.remove(vuelo_id)
                if vuelo_id in flujo_despegue:
                    flujo_despegue.remove(vuelo_id)
            
            # Liberar la pista
            estado['estado'] = 'LIBRE'
            estado['vuelo'] = None
            estado['fin_ocupacion'] = 0
            pistas_estado[pista_id] = estado
            print(f"🔄 Pista {pista_id} liberada")


def encontrar_pista_por_id(id_pista):
    """Encuentra una pista por su ID"""
    for pista in pistas:
        if pista['id'] == id_pista:
            return pista
    return None


def comparar_prioridad(vuelo_id1, vuelo_id2):
    """Compara dos vuelos según la política de prioridad"""
    vuelo1 = encontrar_vuelo_por_id(vuelo_id1)
    vuelo2 = encontrar_vuelo_por_id(vuelo_id2)
    
    if not vuelo1 or not vuelo2:
        return 0
    
    # 1. Prioridad (emergencia primero)
    if vuelo1['prioridad'] != vuelo2['prioridad']:
        return vuelo2['prioridad'] - vuelo1['prioridad']
    
    # 2. Aterrizajes con menor combustible primero
    if vuelo1['tipo'] == 'ATERRIZAJE' and vuelo2['tipo'] == 'ATERRIZAJE':
        if vuelo1.get('combustible') != vuelo2.get('combustible'):
            return (vuelo1.get('combustible', 0) - vuelo2.get('combustible', 0))
    
    # 3. Mayor atraso primero
    atraso1 = reloj - (vuelo1['eta'] if vuelo1['tipo'] == 'ATERRIZAJE' else vuelo1['etd'] if vuelo1['etd'] is not None else 0)
    atraso2 = reloj - (vuelo2['eta'] if vuelo2['tipo'] == 'ATERRIZAJE' else vuelo2['etd'] if vuelo2['etd'] is not None else 0)
    if atraso1 != atraso2:
        return atraso2 - atraso1
    
    # 4. Desempate alfabético
    return -1 if vuelo1['id'] < vuelo2['id'] else 1


def asignar_pistas():
    """Asigna pistas libres a vuelos según la política de prioridad"""
    pistas_libres = []
    
    # Encontrar pistas libres y habilitadas
    for pista in pistas:
        estado = pistas_estado[pista['id']]
        if estado['estado'] == 'LIBRE' and pista['habilitada']:
            pistas_libres.append(pista['id'])
    
    if not pistas_libres:
        print("ℹ️  No hay pistas libres disponibles")
        return
    
    # Combinar y ordenar flujos según prioridad
    todos_vuelos = flujo_aterrizaje + flujo_despegue
    
    if not todos_vuelos:
        print("ℹ️  No hay vuelos en cola")
        return
    
    # Ordenar según política de prioridad (burbuja cutre pero suficiente aquí)
    for i in range(len(todos_vuelos)):
        for j in range(i + 1, len(todos_vuelos)):
            if comparar_prioridad(todos_vuelos[i], todos_vuelos[j]) > 0:
                todos_vuelos[i], todos_vuelos[j] = todos_vuelos[j], todos_vuelos[i]
    
    # Asignar a pistas libres
    for pista_id in pistas_libres:
        if todos_vuelos:
            vuelo_id = todos_vuelos.pop(0)
            vuelo = encontrar_vuelo_por_id(vuelo_id)
            
            if vuelo:
                pista = encontrar_pista_por_id(pista_id)
                pistas_estado[pista_id] = {
                    'estado': 'OCUPADA',
                    'vuelo': vuelo_id,
                    'fin_ocupacion': reloj + (pista['tiempo_uso'] if pista else 3)
                }
                
                vuelo['estado'] = 'ASIGNADO'
                
                # Quitar de su flujo original
                if vuelo_id in flujo_aterrizaje:
                    flujo_aterrizaje.remove(vuelo_id)
                elif vuelo_id in flujo_despegue:
                    flujo_despegue.remove(vuelo_id)
                
                registrar_evento(f"ASIGNACION id_vuelo={vuelo_id} pista={pista_id} tipo={vuelo['tipo']} duracion={(pista['tiempo_uso'] if pista else 3)}min")
                print(f"🎯 Asignado {vuelo_id} a pista {pista_id}")


def actualizar_sistema():
    """Actualiza todo el sistema en cada minuto simulado"""
    print(f"\n📊 ACTUALIZANDO SISTEMA - Minuto {reloj}")

    # 0. Activar vuelos cuyo tiempo ya ha llegado
    activar_vuelos_programados()
    
    # 1. Actualizar combustible
    actualizar_combustible()
    
    # 2. Liberar pistas que han terminado
    liberar_pistas()
    
    # 3. Asignar pistas libres a vuelos pendientes
    asignar_pistas()
    
    # 4. Mostrar estado actual
    mostrar_estado_actual()


def mostrar_estado_actual():
    """Muestra el estado actual del sistema"""
    print(f"\n📈 ESTADO ACTUAL - Minuto {reloj}")
    print(f"✈️  Vuelos en cola para aterrizar: {len(flujo_aterrizaje)} -> {flujo_aterrizaje}")
    print(f"🛫 Vuelos en cola para despegar: {len(flujo_despegue)} -> {flujo_despegue}")
    print(f"✅ Vuelos completados: {len(vuelos_completados)}")
    print(f"📅 Vuelos aún programados: {len(vuelos_programados)}")
    
    # Mostrar vuelos asignados
    vuelos_asignados = [v for v in vuelos if v.get('estado') == 'ASIGNADO']
    if vuelos_asignados:
        print(f"🎯 Vuelos asignados a pistas: {len(vuelos_asignados)}")
    
    # Mostrar pistas
    print("\n🏁 ESTADO DE PISTAS:")
    for pista_id, estado in pistas_estado.items():
        pista = encontrar_pista_por_id(pista_id)
        if pista and pista['habilitada']:
            if estado['estado'] == 'OCUPADA':
                print(f"  {pista_id}: 🚫 OCUPADA por {estado['vuelo']} (hasta minuto {estado['fin_ocupacion']})")
            else:
                print(f"  {pista_id}: ✅ LIBRE")


def mostrar_vuelos_detallados():
    """Muestra todos los vuelos con detalles completos"""
    if not vuelos:
        print("No hay vuelos en el sistema.")
        return
        
    print(f"\n📋 DETALLES DE TODOS LOS VUELOS ({len(vuelos)} total):")
    
    # Contadores por estado
    en_cola = [v for v in vuelos if v.get('estado') == 'EN_COLA']
    asignados = [v for v in vuelos if v.get('estado') == 'ASIGNADO']
    completados = [v for v in vuelos if v.get('estado') == 'COMPLETADO']
    
    print(f"EN_COLA: {len(en_cola)} | ASIGNADOS: {len(asignados)} | COMPLETADOS: {len(completados)}")
    print()
    
    for vuelo in vuelos:
        tipo_icono = "✈️" if vuelo['tipo'] == 'ATERRIZAJE' else "🛫"
        prioridad_texto = {0: "Normal", 1: "Alta", 2: "🆘 EMERGENCIA"}.get(vuelo.get('prioridad', 0), "Desconocida")
        estado_icono = {
            'EN_COLA': '⏳',
            'ASIGNADO': '🎯', 
            'COMPLETADO': '✅',
            'CANCELADO': '❌'
        }.get(vuelo.get('estado', ''), '❓')
        
        print(f"{estado_icono} {tipo_icono} {vuelo['id']}: {vuelo['tipo']} | {prioridad_texto} | Estado: {vuelo.get('estado', '??')}")
        
        if vuelo['tipo'] == 'ATERRIZAJE':
            atraso = max(0, reloj - (vuelo['eta'] if vuelo['eta'] is not None else 0))
            print(f"   ETA: {vuelo.get('eta')} | Atraso: {atraso}min | Combustible: {vuelo.get('combustible')}")
        else:
            atraso = max(0, reloj - (vuelo['etd'] if vuelo.get('etd') is not None else 0))
            print(f"   ETD: {vuelo.get('etd')} | Atraso: {atraso}min")
        
        # Mostrar información de pista si está asignado
        if vuelo.get('estado') == 'ASIGNADO':
            for pista_id, estado_pista in pistas_estado.items():
                if estado_pista['vuelo'] == vuelo['id']:
                    tiempo_restante = estado_pista['fin_ocupacion'] - reloj
                    print(f"   Pista: {pista_id} | Tiempo restante: {tiempo_restante}min")
                    break
        print()


def mostrar_menu_principal():
    """Muestra el menú principal del sistema"""
    print("\n" + "="*60)
    print("🚀 CONTROLADOR AEREO DE D.I.R.A")
    print("="*60)
    print("1. Avanzar reloj 1 minuto")
    print("2. Avanzar reloj 5 minutos")
    print("3. Agregar vuelo manualmente")
    print("4. Mostrar estado actual")
    print("5. Mostrar todos los vuelos detallados")
    print("6. Mostrar eventos del log")
    print("7. Generar informe final")
    print("8. Salir")
    print("="*60)


# ------------------------------
# FUNCIONES AGREGADAS / INTEGRADAS (INFORME Y ESTADÍSTICAS)
# ------------------------------

def generar_informe():
    """Genera el informe final de la simulación"""
    print("\n GENERANDO INFORME DE D.I.R.A")
    
    # Calcular estadísticas
    tiempo_medio_espera = calcular_tiempo_medio_espera()
    operaciones_por_pista = calcular_operaciones_por_pista()
    emergencias_gestionadas = sum(1 for v in vuelos_completados if v.get('prioridad_final') == 2)
    
    # Crear contenido del informe (usar `reloj` como tiempo simulado)
    contenido = f"""
- Tiempo simulado (min): {reloj}
- Vuelos atendidos: {len(vuelos_completados)}
- Tiempo medio de espera (min): {tiempo_medio_espera:.1f}
- Emergencias gestionadas: {emergencias_gestionadas}
- Uso de pistas: {operaciones_por_pista}
- Detalle de vuelos completados:
"""
    
    for vuelo in vuelos_completados:
        prioridad_final = vuelo.get('prioridad_final', None)
        emergencia_info = ", EMERGENCIA" if prioridad_final == 2 else ""
        inicio = vuelo.get('inicio', 0)
        fin = vuelo.get('fin', 0)
        contenido += f"• {vuelo['id']} ({vuelo['tipo']}{emergencia_info}) t_inicio={inicio} t_fin={fin}\n"
    
    # Guardar en archivo
    try:
        with open('informe.log', 'w', encoding='utf-8') as f:
            f.write(contenido)
        print("✓ Informe guardado en 'informe.log'")
    except Exception as e:
        print(f"✗ Error al guardar informe: {e}")
    
    # Mostrar en pantalla
    print(contenido)


def calcular_tiempo_medio_espera():
    """Calcula el tiempo medio de espera de los vuelos completados"""
    if not vuelos_completados:
        return 0.0
    
    total_espera = 0
    cuenta = 0
    for vuelo_c in vuelos_completados:
        vuelo_original = next((v for v in vuelos if v['id'] == vuelo_c['id']), None)
        if not vuelo_original:
            # Si no encontramos el original, no podemos calcular - asumimos 0
            continue
        
        # Tiempo de inicio de la operación (cuando se ocupó la pista)
        t_inicio = vuelo_c.get('inicio', 0)
        
        # Determinar el tiempo programado (eta o etd)
        tiempo_programado = vuelo_original.get('eta') if vuelo_original['tipo'] == 'ATERRIZAJE' else vuelo_original.get('etd')
        if tiempo_programado is None:
            continue
        
        tiempo_espera = t_inicio - tiempo_programado
        total_espera += max(0, tiempo_espera)
        cuenta += 1
    
    if cuenta == 0:
        return 0.0
    return total_espera / cuenta


def calcular_operaciones_por_pista():
    """Calcula las operaciones realizadas por cada pista"""
    operaciones = {}
    for pista in pistas:
        pid = pista['id']
        # Contar eventos de asignación para esta pista (se buscará 'pista={id}' en el log)
        count = sum(1 for e in eventos_log if f"pista={pid}" in e and "ASIGNACION" in e)
        operaciones[pid] = count
    
    # Convertir a cadena legible
    return " | ".join([f"{pista}={op} operaciones" for pista, op in operaciones.items()])


# ------------------------------
# FIN FUNCIONES AGREGADAS
# ------------------------------

def main():
    """Función principal del sistema"""
    global reloj, vuelos, flujo_aterrizaje, flujo_despegue, eventos_log, vuelos_completados, vuelos_programados
    
    # Inicializar sistema
    print("🚀 INICIANDO SISTEMA DE GESTIÓN DE TRÁFICO AÉREO...")
    
    # Inicializar estructuras globales
    vuelos = []
    flujo_aterrizaje = []
    flujo_despegue = []
    vuelos_completados = []
    eventos_log = []
    vuelos_programados = []
    reloj = 0
    
    # Cargar pistas
    cargar_pistas_desde_archivo()
    
    # Cargar vuelos
    ruta_fichero = "vuelos.txt"
    vuelos_activos = {}
    cargar_vuelos_desde_txt(ruta_fichero, vuelos_activos)
    
    # Mostrar resumen inicial
    print(f"\n🎯 RESUMEN INICIAL DEL SISTEMA:")
    print(f"• Pistas cargadas: {len(pistas)}")
    print(f"• Vuelos totales: {len(vuelos)}")
    print(f"• Vuelos programados: {len(vuelos_programados)}")
    print(f"• Aterrizajes en cola: {len(flujo_aterrizaje)}")
    print(f"• Despegues en cola: {len(flujo_despegue)}")
    
    # Contar vuelos por estado
    en_cola = len([v for v in vuelos if v.get('estado') == 'EN_COLA'])
    asignados = len([v for v in vuelos if v.get('estado') == 'ASIGNADO'])
    completados = len([v for v in vuelos if v.get('estado') == 'COMPLETADO'])
    print(f"• EN_COLA (ya activos): {en_cola} | ASIGNADOS: {asignados} | COMPLETADOS: {completados}")
    
    # Inicializar archivo de eventos
    try:
        with open("eventos.txt", "w", encoding="utf-8") as f:
            f.write("=== LOG DE EVENTOS DEL SISTEMA ===\n")
            f.write(f"Inicio: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}\n\n")
    except Exception as e:
        print(f"⚠️  No se pudo crear eventos.txt: {e}")
    
    registrar_evento("SISTEMA_INICIADO")
    
    # Bucle principal
    while True:
        mostrar_menu_principal()
        
        opcion = input("Seleccione una opción (1-8): ").strip()
        
        if opcion == '1':
            avanzar_reloj(1)
        elif opcion == '2':
            avanzar_reloj(5)
        elif opcion == '3':
            agregar_vuelo_manual()
        elif opcion == '4':
            mostrar_estado_actual()
        elif opcion == '5':
            mostrar_vuelos_detallados()
        elif opcion == '6':
            print("\n📋 ÚLTIMOS EVENTOS DEL LOG:")
            if eventos_log:
                for evento in eventos_log[-30:]:  # Mostrar últimos 30 eventos
                    print(evento)
            else:
                print("No hay eventos registrados.")
        elif opcion == '7':
            generar_informe()
        elif opcion == '8':
            print("👋 Saliendo del sistema...")
            registrar_evento("SISTEMA_FINALIZADO")
            # Generar informe automático al salir
            try:
                generar_informe()
            except Exception:
                pass
            break
        else:
            print("❌ Opción inválida. Por favor, seleccione 1-8.")


if __name__ == "__main__":
    main()
