def mostrar_menu():
    """Muestra el menú principal de la aplicación con todas las operaciones requeridas"""
    print(f"""
=== SISTEMA DE GESTIÓN DE AEROPUERTO ===
Reloj actual: {reloj_simulado} minutos
Vuelos activos: {len([v for v in vuelos if v['estado'] != 'COMPLETADO'])}
Pistas operativas: {len([p for p in pistas if p['habilitada'] == 1])}

OPERACIONES DISPONIBLES:
1. Cargar datos (vuelos.csv, pistas.csv)
2. Añadir vuelo manual
3. Avanzar 1 minuto
4. Avanzar N minutos
5. Ver estado (pistas, colas, vuelos en curso)
6. Generar informe.log
7. Guardar estado.csv y salir
""")

def ejecutar_menu():
    """Ejecuta la opción seleccionada del menú"""
    while True:
        mostrar_menu()
        opcion = input("Seleccione una opción (1-7): ").strip()
        
        if opcion == '1':
            cargar_datos()
        elif opcion == '2':
            añadir_vuelo_manual()
        elif opcion == '3':
            avanzar_1_minuto()
        elif opcion == '4':
            avanzar_N_minutos()
        elif opcion == '5':
            ver_estado_completo()
        elif opcion == '6':
            generar_informe_log()
        elif opcion == '7':
            guardar_estado_y_salir()
            break
        else:
            print("✗ Opción no válida. Intente nuevamente.")

def cargar_datos():
    """Carga los datos desde los archivos CSV"""
    print("\n--- CARGA DE DATOS ---")
    vuelos_cargados = cargar_vuelos()
    pistas_cargadas = cargar_pistas()
    
    if vuelos_cargados and pistas_cargadas:
        print("✓ Todos los datos cargados correctamente")
    else:
        print("✗ Error al cargar algunos datos")

def añadir_vuelo_manual():
    """Añade un vuelo manualmente mediante entrada por consola"""
    print("\n--- AÑADIR VUELO MANUAL ---")
    
    # Solicitar datos del vuelo
    id_vuelo = input("ID del vuelo (ej: IB123): ").strip().upper()
    
    # Verificar si el ID ya existe
    if any(vuelo['id'] == id_vuelo for vuelo in vuelos):
        print("✗ Error: Ya existe un vuelo con ese ID")
        return
    
    # Solicitar tipo de vuelo
    tipo = input("Tipo (ATERRIZAJE/DESPEGUE): ").strip().upper()
    if tipo not in ['ATERRIZAJE', 'DESPEGUE']:
        print("✗ Error: Tipo debe ser 'ATERRIZAJE' o 'DESPEGUE'")
        return
    
    try:
        # Solicitar ETA/ETD
        eta_etd = int(input("ETA/ETD (minuto previsto): "))
        
        # Solicitar prioridad
        prioridad = int(input("Prioridad (0=normal, 1=alta, 2=emergencia): "))
        if prioridad not in [0, 1, 2]:
            print("✗ Error: Prioridad debe ser 0, 1 o 2")
            return
        
        # Solicitar combustible solo para aterrizajes
        combustible = 0
        if tipo == 'ATERRIZAJE':
            combustible = int(input("Combustible (minutos restantes): "))
            if combustible < 0:
                print("✗ Error: El combustible no puede ser negativo")
                return
        
        # Crear nuevo vuelo
        nuevo_vuelo = {
            'id': id_vuelo,
            'tipo': tipo,
            'eta_etd': eta_etd,
            'prioridad': prioridad,
            'combustible': combustible,
            'estado': 'EN_COLA',
            'minuto_entrada': reloj_simulado
        }
        
        # Agregar a las estructuras de datos
        vuelos.append(nuevo_vuelo)
        if tipo == 'ATERRIZAJE':
            flujo_aterrizaje.append(id_vuelo)
        else:
            flujo_despegue.append(id_vuelo)
        
        registrar_evento(f"VUELO_MANUAL id_vuelo={id_vuelo} tipo={tipo}")
        print(f"✓ Vuelo {id_vuelo} agregado correctamente")
        
    except ValueError:
        print("✗ Error: Los valores numéricos deben ser enteros")

def avanzar_1_minuto():
    """Avanza el reloj 1 minuto simulado"""
    print(f"\n--- AVANZANDO 1 MINUTO ---")
    avanzar_reloj(1)

def avanzar_N_minutos():
    """Avanza el reloj N minutos simulados"""
    try:
        n = int(input("¿Cuántos minutos desea avanzar? "))
        if n <= 0:
            print("✗ Error: El número debe ser positivo")
            return
        
        print(f"\n--- AVANZANDO {n} MINUTOS ---")
        avanzar_reloj(n)
        
    except ValueError:
        print("✗ Error: Debe ingresar un número válido")

def ver_estado_completo():
    """Muestra el estado completo del sistema"""
    print(f"\n=== ESTADO COMPLETO DEL SISTEMA (Minuto {reloj_simulado}) ===")
    
    # Estado de pistas
    print("\n--- ESTADO DE PISTAS ---")
    for pista in pistas:
        estado = "🟢 LIBRE" if pista['ocupada_hasta'] == 0 else f"🔴 OCUPADA (hasta minuto {pista['ocupada_hasta']})"
        habilitada = "✅ HABILITADA" if pista['habilitada'] == 1 else "❌ DESHABILITADA"
        vuelo_actual = pista['vuelo_actual'] if pista['vuelo_actual'] else "Ninguno"
        print(f"Pista {pista['id_pista']} ({pista['categoria']}): {estado} | {habilitada} | Vuelo: {vuelo_actual}")
    
    # Estado de colas
    print("\n--- ESTADO DE COLAS ---")
    print(f"Cola de ATERRIZAJE: {len(flujo_aterrizaje)} vuelos")
    for i, id_vuelo in enumerate(flujo_aterrizaje, 1):
        vuelo = next(v for v in vuelos if v['id'] == id_vuelo)
        emergencia = "🚨 " if vuelo['prioridad'] == 2 else ""
        print(f"  {i}. {emergencia}{id_vuelo} - Combustible: {vuelo['combustible']} min - Prioridad: {vuelo['prioridad']}")
    
    print(f"\nCola de DESPEGUE: {len(flujo_despegue)} vuelos")
    for i, id_vuelo in enumerate(flujo_despegue, 1):
        vuelo = next(v for v in vuelos if v['id'] == id_vuelo)
        print(f"  {i}. {id_vuelo} - Prioridad: {vuelo['prioridad']}")
    
    # Vuelos en curso
    print("\n--- VUELOS EN CURSO ---")
    vuelos_activos = [v for v in vuelos if v['estado'] in ['ASIGNADO', 'EN_COLA']]
    if vuelos_activos:
        for vuelo in vuelos_activos:
            estado_color = "🟡 ASIGNADO" if vuelo['estado'] == 'ASIGNADO' else "🟠 EN COLA"
            combustible_info = f" | Combustible: {vuelo['combustible']} min" if vuelo['tipo'] == 'ATERRIZAJE' else ""
            print(f"{vuelo['id']} ({vuelo['tipo']}): {estado_color} | Prioridad: {vuelo['prioridad']}{combustible_info}")
    else:
        print("No hay vuelos activos en este momento")
    
    # Vuelos completados
    print(f"\n--- VUELOS COMPLETADOS: {len(vuelos_completados)} ---")

def generar_informe_log():
    """Genera el archivo informe.log con el resumen de la simulación"""
    print("\n--- GENERANDO INFORME.LOG ---")
    generar_informe()

def guardar_estado_y_salir():
    """Guarda el estado actual y sale del programa"""
    print("\n--- GUARDANDO ESTADO Y SALIENDO ---")
    
    # Guardar estado de vuelos
    with open('estado.csv', 'w', newline='', encoding='utf-8') as f:
        fieldnames = ['id', 'tipo', 'eta_etd', 'prioridad', 'combustible', 'estado', 'minuto_entrada']
        writer = csv.DictWriter(f, fieldnames=fieldnames)
        writer.writeheader()
        for vuelo in vuelos:
            writer.writerow({
                'id': vuelo['id'],
                'tipo': vuelo['tipo'],
                'eta_etd': vuelo['eta_etd'],
                'prioridad': vuelo['prioridad'],
                'combustible': vuelo['combustible'],
                'estado': vuelo['estado'],
                'minuto_entrada': vuelo['minuto_entrada']
            })
    
    # Guardar eventos
    guardar_eventos_log()
    
    # Generar informe final
    generar_informe()
    
    print("✓ Estado guardado en 'estado.csv'")
    print("✓ Eventos guardados en 'eventos.log'")
    print("✓ Informe guardado en 'informe.log'")
    print("\n¡Hasta luego! 👋")