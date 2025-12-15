from datetime import datetime, timedelta
import random
import hashlib
import re

#Punto 1: estructuras principales
distritos = {
    "Norte": {
        "centro": "Centro Norte",
        "inventario": {},
        "actividades": ["natación", "baloncesto", "tenis", "pádel", "fitness", "yoga"],
        "usuarios_por_actividad": {}
    },
    "Sur": {
        "centro": "Centro Sur", 
        "inventario": {},
        "actividades": ["natación", "baloncesto", "tenis", "pádel", "fitness", "artes marciales"],
        "usuarios_por_actividad": {}
    },
    "Este": {
        "centro": "Centro Este",
        "inventario": {},
        "actividades": ["natación", "baloncesto", "tenis", "pádel", "fitness", "danza"],
        "usuarios_por_actividad": {}
    },
    "Oeste": {
        "centro": "Centro Oeste",
        "inventario": {},
        "actividades": ["natación", "baloncesto", "tenis", "pádel", "fitness", "ciclismo"],
        "usuarios_por_actividad": {}
    }
}

# Almacén central con 15 materiales
almacen_central = {
    "pelotas_tenis": {"cantidad": 50, "descripcion": "Pelotas de tenis profesionales"},
    "raquetas": {"cantidad": 30, "descripcion": "Raquetas de tenis"},
    "balones_baloncesto": {"cantidad": 25, "descripcion": "Balones de baloncesto"},
    "balones_futbol": {"cantidad": 20, "descripcion": "Balones de fútbol"},
    "colchonetas": {"cantidad": 40, "descripcion": "Colchonetas para ejercicio"},
    "pesas": {"cantidad": 60, "descripcion": "Juego de pesas"},
    "bicicletas": {"cantidad": 15, "descripcion": "Bicicletas estáticas"},
    "tablas_padel": {"cantidad": 20, "descripcion": "Tablas de pádel"},
    "gafas_natacion": {"cantidad": 35, "descripcion": "Gafas de natación"},
    "gorros_natacion": {"cantidad": 45, "descripcion": "Gorros de baño"},
    "redes": {"cantidad": 10, "descripcion": "Redes deportivas"},
    "conos": {"cantidad": 100, "descripcion": "Conos de entrenamiento"},
    "cuerdas": {"cantidad": 25, "descripcion": "Cuerdas para saltar"},
    "chalecos": {"cantidad": 40, "descripcion": "Chalecos deportivos"},
    "cronometros": {"cantidad": 12, "descripcion": "Cronómetros deportivos"}
}


#Punto 2: Sistema de usuarios y reservas
usuarios = {}
reservas = {} 
contador_usuarios = 1
usuario_actual = None  # Para mantener la sesión activa

# Definición de materiales deportivos con su información completa
def crear_material(nombre, marca, actividad=None):
    """Crea un material deportivo con toda la información requerida"""
    return {
        "nombre": nombre,
        "marca": marca,
        "fecha_alta": datetime.now().strftime("%d/%m/%Y"),
        "estado": "disponible",  # Inicialmente todos disponibles
        "actividad": actividad
    }

# Marcas disponibles
marcas = ["Nike", "Adidas", "Puma", "Decathlon", "NewBalance", "Timberland", "Asics"]

# Materiales específicos por actividad
materiales_por_actividad = {
    "natación": [
        ("Gafas de natación", "gafas_natacion"),
        ("Gorro de baño", "gorro_natacion"),
        ("Aletas", "aletas"),
        ("Tabla de flotación", "tabla_flotacion"),
        ("Pull buoy", "pull_buoy")
    ],
    "baloncesto": [
        ("Balón de baloncesto", "balon_baloncesto"),
        ("Red de canasta", "red_canasta"),
        ("Conos de entrenamiento", "conos_entrenamiento"),
        ("Chaleco deportivo", "chaleco_deportivo"),
        ("Silbato", "silbato")
    ],
    "tenis": [
        ("Raqueta de tenis", "raqueta_tenis"),
        ("Pelotas de tenis", "pelotas_tenis"),
        ("Red de tenis", "red_tenis"),
        ("Overgrips", "overgrips"),
        ("Porta raquetas", "porta_raquetas")
    ],
    "pádel": [
        ("Palas de pádel", "palas_padel"),
        ("Pelotas de pádel", "pelotas_padel"),
        ("Red de pádel", "red_padel"),
        ("Fundas para palas", "fundas_palas"),
        ("Cintas de grip", "cintas_grip")
    ],
    "fitness": [
        ("Mancuernas", "mancuernas"),
        ("Colchoneta", "colchoneta"),
        ("Pesa rusa", "pesa_rusa"),
        ("Cinta elástica", "cinta_elastica"),
        ("Step", "step")
    ],
    "yoga": [
        ("Esterilla de yoga", "esterilla_yoga"),
        ("Bloques de yoga", "bloques_yoga"),
        ("Cinta de yoga", "cinta_yoga"),
        ("Cojín de meditación", "cojin_meditacion"),
        ("Ropa de yoga", "ropa_yoga")
    ],
    "artes marciales": [
        ("Guantillas", "guantillas"),
        ("Protector bucal", "protector_bucal"),
        ("Saco de boxeo", "saco_boxeo"),
        ("Kimono", "kimono"),
        ("Vendas", "vendas")
    ],
    "danza": [
        ("Barra de ballet", "barra_ballet"),
        ("Zapatillas de ballet", "zapatillas_ballet"),
        ("Leotardo", "leotardo"),
        ("Mallas", "mallas"),
        ("Grabadora musical", "grabadora_musical")
    ],
    "ciclismo": [
        ("Bicicleta estática", "bicicleta_estatica"),
        ("Casco", "casco"),
        ("Guantes de ciclismo", "guantes_ciclismo"),
        ("Bidón", "bidon"),
        ("Kit de herramientas", "kit_herramientas")
    ]
}

# Función para inicializar el inventario de los centros
def inicializar_inventarios():
    """Inicializa los inventarios de cada centro con materiales propios y de otros distritos"""
    
    for distrito_nombre, info_distrito in distritos.items():
        inventario = {}
        
        # 1. Agregar materiales de las actividades del distrito
        for actividad in info_distrito["actividades"]:
            if actividad in materiales_por_actividad:
                for material_nombre, material_id in materiales_por_actividad[actividad]:
                    inventario[f"{material_id}_{distrito_nombre}"] = crear_material(
                        material_nombre, 
                        random.choice(marcas),#para elegir la marca de forma aleatorias
                        actividad
                    )
        
        # 2. Agregar al menos 5 materiales de otros distritos
        otros_distritos = [d for d in distritos.keys() if d != distrito_nombre]
        materiales_otros = []
        
        for otro_distrito in otros_distritos:
            actividades_otro = distritos[otro_distrito]["actividades"]
            for actividad in actividades_otro:
                if actividad in materiales_por_actividad:
                    for material_nombre, material_id in materiales_por_actividad[actividad]:
                        materiales_otros.append((material_nombre, material_id, actividad))
        
        # Seleccionar 5 materiales aleatorios de otros distritos
        if len(materiales_otros) >= 5:
            materiales_seleccionados = random.sample(materiales_otros, 5)
            for material_nombre, material_id, actividad in materiales_seleccionados:
                inventario[f"{material_id}_prestado_{distrito_nombre}"] = crear_material(
                    f"{material_nombre} (prestado)", 
                    random.choice(marcas),
                    f"{actividad} - {otro_distrito}"
                )
        
        info_distrito["inventario"] = inventario

#Punto 3: gestion de reservas y usuarios

def hash_password(password):
    """Genera un hash simple para la contraseña"""
    return hashlib.sha256(password.encode()).hexdigest()

def alta_usuario(nombre, distrito, actividad_principal, password):
    """Da de alta un nuevo usuario"""
    global contador_usuarios
    
    # Validaciones de entrada
    if not nombre or not nombre.strip():
        return False, "El nombre no puede estar vacío"
    if not password or not password.strip():
        return False, "La contraseña no puede estar vacía"
    
    # Verificar si el distrito existe
    if distrito not in distritos:
        return False, "Distrito no válido"
    
    # Verificar si la actividad existe en el distrito
    if actividad_principal not in distritos[distrito]["actividades"]:
        return False, "Actividad no disponible en este distrito"
    
    # Verificar cupo máximo (5 usuarios por actividad y distrito)
    if actividad_principal not in distritos[distrito]["usuarios_por_actividad"]:
        distritos[distrito]["usuarios_por_actividad"][actividad_principal] = []
    
    if len(distritos[distrito]["usuarios_por_actividad"][actividad_principal]) >= 5:
        return False, "Cupo máximo alcanzado para esta actividad en el distrito"
    
    # Crear usuario con ID único
    user_id = contador_usuarios
    contador_usuarios += 1
    
    usuario = {
        "user_id": user_id,
        "nombre": nombre.strip(),
        "distrito": distrito,
        "actividad_principal": actividad_principal,
        "password_hash": hash_password(password),
        "fecha_alta": datetime.now().strftime("%d/%m/%Y")
    }
    
    # Guardar usuario
    usuarios[user_id] = usuario
    distritos[distrito]["usuarios_por_actividad"][actividad_principal].append(user_id)
    
    # Inicializar lista de reservas vacía
    reservas[user_id] = []
    
    return True, f"Usuario creado con ID: {user_id}"

def baja_usuario(user_id):
    """Da de baja un usuario"""
    if user_id not in usuarios:
        return False, "Usuario no encontrado"
    
    usuario = usuarios[user_id]
    distrito = usuario["distrito"]
    actividad = usuario["actividad_principal"]
    
    # Liberar cupo en la actividad
    if actividad in distritos[distrito]["usuarios_por_actividad"]:
        if user_id in distritos[distrito]["usuarios_por_actividad"][actividad]:
            distritos[distrito]["usuarios_por_actividad"][actividad].remove(user_id)
    
    # Liberar materiales reservados
    for reserva in reservas[user_id]:
        material_id = reserva["material_id"]
        for distrito_info in distritos.values():
            if material_id in distrito_info["inventario"]:
                distrito_info["inventario"][material_id]["estado"] = "disponible"
                break
    
    # Eliminar usuario
    del usuarios[user_id]
    del reservas[user_id]
    
    return True, "Usuario eliminado correctamente"

def autenticar_usuario(user_id, password):
    """Autentica un usuario"""
    if user_id not in usuarios:
        return False, "Usuario no encontrado"
    
    if usuarios[user_id]["password_hash"] == hash_password(password):
        global usuario_actual
        usuario_actual = user_id
        return True, "Autenticación exitosa"
    else:
        return False, "Contraseña incorrecta"

def ver_cupos_actividad(distrito, actividad):
    """Muestra los cupos disponibles para una actividad"""
    if distrito not in distritos:
        return "Distrito no válido"
    
    if actividad not in distritos[distrito]["usuarios_por_actividad"]:
        inscritos = 0
    else:
        inscritos = len(distritos[distrito]["usuarios_por_actividad"][actividad])
    
    disponibles = 5 - inscritos
    return f"Cupos para {actividad} en {distrito}: {disponibles} de 5 disponibles"

#Punto 4: gestion de reservas de materiales

def reservar_material(user_id, material_id, distrito_busqueda):
    """Reserva un material para un usuario"""
    if user_id not in usuarios:
        return False, "Usuario no encontrado"
    
    # Verificar límite de 3 reservas simultáneas
    if len(reservas[user_id]) >= 3:
        return False, "Límite de 3 reservas simultáneas alcanzado"
    
    # Buscar el material en el distrito especificado
    if distrito_busqueda not in distritos:
        return False, "Distrito no válido"
    
    inventario = distritos[distrito_busqueda]["inventario"]
    
    if material_id not in inventario:
        return False, "Material no encontrado en este distrito"
    
    material = inventario[material_id]
    
    # Verificar disponibilidad
    if material["estado"] != "disponible":
        return False, f"Material no disponible. Estado actual: {material['estado']}"
    
    # Crear reserva
    reserva = {
        "material_id": material_id,
        "material_nombre": material["nombre"],
        "distrito": distrito_busqueda,
        "actividad": material["actividad"],
        "fecha_reserva": datetime.now().strftime("%d/%m/%Y %H:%M"),
        "fecha_devolucion": (datetime.now() + timedelta(days=4)).strftime("%d/%m/%Y %H:%M")
    }
    
    # Actualizar estado del material
    material["estado"] = "reservado"
    
    # Guardar reserva
    reservas[user_id].append(reserva)
    
    return True, f"Material '{material['nombre']}' reservado correctamente. Devolución: {reserva['fecha_devolucion']}"

def devolver_material(user_id, material_id):
    """Devuelve un material reservado"""
    if user_id not in usuarios:
        return False, "Usuario no encontrado"
    
    # Buscar la reserva
    reserva_encontrada = None
    for reserva in reservas[user_id]:
        if reserva["material_id"] == material_id:
            reserva_encontrada = reserva
            break
    
    if not reserva_encontrada:
        return False, "Material no encontrado en las reservas del usuario"
    
    # Actualizar estado del material
    distrito = reserva_encontrada["distrito"]
    material = distritos[distrito]["inventario"][material_id]
    material["estado"] = "disponible"
    
    # Eliminar reserva
    reservas[user_id].remove(reserva_encontrada)
    
    return True, f"Material '{material['nombre']}' devuelto correctamente"

def consultar_reservas_usuario(user_id):
    """Consulta las reservas de un usuario"""
    if user_id not in usuarios:
        return "Usuario no encontrado"
    
    usuario = usuarios[user_id]
    reservas_usuario = reservas[user_id]
    
    if not reservas_usuario:
        return f"El usuario {usuario['nombre']} (ID: {user_id}) no tiene reservas activas"
    
    resultado = f" RESERVAS DE {usuario['nombre'].upper()} (ID: {user_id}) \n"
    resultado += f"Distrito: {usuario['distrito']}\n"
    resultado += f"Actividad principal: {usuario['actividad_principal']}\n\n"
    resultado += "Materiales reservados:\n"
    
    for i, reserva in enumerate(reservas_usuario, 1):
        resultado += f"{i}. {reserva['material_nombre']}\n"
        resultado += f"   ID Material: {reserva['material_id']}\n"
        resultado += f"   Distrito: {reserva['distrito']}\n"
        resultado += f"   Actividad: {reserva['actividad']}\n"
        resultado += f"   Fecha reserva: {reserva['fecha_reserva']}\n"
        resultado += f"   Fecha devolución: {reserva['fecha_devolucion']}\n\n"
    
    return resultado

#Menu principal y su gestion

def listar_almacen_central():
    """1. Acceder al almacén central"""
    print("\n ALMACÉN CENTRAL ")
    print(f"Total de materiales: {len(almacen_central)}")
    for material, info in almacen_central.items():
        print(f"  - {material}: {info['cantidad']} unidades")
        print(f"    Descripción: {info['descripcion']}")

def acceder_distrito():
    """4. Acceder a un distrito y su centro general"""
    print("\n ACCEDER A DISTRITO ")
    print("Distritos disponibles:", ", ".join(distritos.keys()))
    
    distrito = input("Selecciona un distrito: ").strip()
    if distrito not in distritos:
        print(" Distrito no válido")
        return
    
    info_distrito = distritos[distrito]
    print(f"\n--- {info_distrito['centro']} ({distrito}) ---")
    print(f"Actividades: {', '.join(info_distrito['actividades'])}")
    
    # Listar materiales del centro
    print(f"\nMateriales del centro ({len(info_distrito['inventario'])}):")
    for material_id, material in info_distrito["inventario"].items():
        print(f"  - ID: {material_id}")
        print(f"    Nombre: {material['nombre']}")
        print(f"    Estado: {material['estado']}")
        print(f"    Actividad: {material['actividad']}")
    
    # Mostrar materiales de otros distritos
    opcion = input("\n¿Quieres ver materiales de otros distritos? (s/n): ").lower()
    if opcion == 's':
        otros_distritos = [d for d in distritos.keys() if d != distrito]
        distrito_elegido = input(f"Selecciona distrito ({', '.join(otros_distritos)}): ").strip()
        if distrito_elegido in otros_distritos:
            print(f"\nMateriales de {distrito_elegido}:")
            for material_id, material in distritos[distrito_elegido]["inventario"].items():
                print(f"  - {material['nombre']} (Estado: {material['estado']})")

def acceder_actividad():
    """5. Acceder a una actividad específica (requiere autenticación)"""
    print("\n ACCESO A ACTIVIDAD ")
    
    if usuario_actual is None:
        print(" Debes autenticarte primero")
        return
    
    usuario = usuarios[usuario_actual]
    print(f"Usuario: {usuario['nombre']} (Distrito: {usuario['distrito']})")
    print(f"Actividad principal: {usuario['actividad_principal']}")
    
    # Mostrar materiales de la actividad del usuario
    distrito = usuario['distrito']
    actividad = usuario['actividad_principal']
    
    print(f"\nMateriales disponibles para {actividad} en {distrito}:")
    materiales_encontrados = False
    
    for material_id, material in distritos[distrito]["inventario"].items():
        if actividad in material["actividad"]:
            print(f"  - ID: {material_id}")
            print(f"    Nombre: {material['nombre']}")
            print(f"    Estado: {material['estado']}")
            print(f"    Marca: {material['marca']}")
            materiales_encontrados = True
    
    if not materiales_encontrados:
        print("  No hay materiales específicos para esta actividad")

def listar_materiales_ubicacion():
    """6. Listar materiales de la ubicación actual"""
    if usuario_actual is None:
        print(" Debes autenticarte primero")
        return
    
    usuario = usuarios[usuario_actual]
    distrito = usuario['distrito']
    
    print(f"\n MATERIALES EN {distrito.upper()} ")
    
    opcion = input("¿Ver todos los materiales o solo los de tu actividad? (todos/actividad): ").lower()
    
    materiales_encontrados = False
    for material_id, material in distritos[distrito]["inventario"].items():
        if opcion == 'actividad' and usuario['actividad_principal'] not in material["actividad"]:
            continue
        
        print(f"  - ID: {material_id}")
        print(f"    Nombre: {material['nombre']}")
        print(f"    Marca: {material['marca']}")
        print(f"    Estado: {material['estado']}")
        print(f"    Actividad: {material['actividad']}")
        print(f"    Fecha alta: {material['fecha_alta']}")
        print()
        materiales_encontrados = True
    
    if not materiales_encontrados:
        print("  No se encontraron materiales con los criterios especificados")

def buscar_material():
    """7. Buscar material por nombre/marca/fecha/estado"""
    print("\n BUSCAR MATERIAL ")
    print("Criterios de búsqueda: nombre, marca, fecha (dd/mm/aaaa), estado")
    
    criterio = input("Introduce el texto a buscar: ").strip().lower()
    
    if not criterio:
        print(" El criterio de búsqueda no puede estar vacío")
        return
    
    encontrados = []
    
    for distrito_nombre, info_distrito in distritos.items():
        for material_id, material in info_distrito["inventario"].items():
            # Búsqueda insensible a mayúsculas en todos los campos
            if (criterio in material['nombre'].lower() or
                criterio in material['marca'].lower() or
                criterio in material['estado'].lower() or
                criterio in material['fecha_alta'] or
                criterio in material['actividad'].lower()):
                
                encontrados.append((distrito_nombre, material_id, material))
    
    if not encontrados:
        print(" No se encontraron materiales con ese criterio")
        return
    
    print(f"\nSe encontraron {len(encontrados)} materiales:")
    for distrito, material_id, material in encontrados:
        print(f"  - Distrito: {distrito}")
        print(f"    ID: {material_id}")
        print(f"    Nombre: {material['nombre']}")
        print(f"    Marca: {material['marca']}")
        print(f"    Estado: {material['estado']}")
        print(f"    Actividad: {material['actividad']}")
        print(f"    Fecha alta: {material['fecha_alta']}")
        print()

def menu_principal():
    """Menú principal del sistema"""
    global usuario_actual  # Añadir esta línea para acceder a la variable global
    
    while True:
        print("\n" + "="*50)
        print(" SISTEMA DE GESTIÓN DEPORTIVA")
        print("="*50)
        print("1. Alta de usuario")
        print("2. Baja de usuario")
        print("3. Acceder al almacén central")
        print("4. Acceder a un distrito y su centro general")
        print("5. Acceder a una actividad específica")
        print("6. Listar materiales de la ubicación actual")
        print("7. Buscar material")
        print("8. Reservar material")
        print("9. Ver mis reservas")
        print("10. Devolver material")
        print("11. Autenticarse")
        print("12. Salir de la aplicacion")
        
        # Verificar si usuario_actual no es None antes de acceder a él
        if usuario_actual is not None:
            usuario = usuarios.get(usuario_actual)
            if usuario:
                print(f"\n Usuario actual: {usuario['nombre']} (ID: {usuario_actual})")
        
        opcion = input("\nSelecciona una opción (1-12): ").strip()
        
        try:
            if opcion == '1':
                print("\n ALTA DE USUARIO ")
                nombre = input("Nombre: ").strip()
                print("Distritos disponibles:", ", ".join(distritos.keys()))
                distrito = input("Distrito: ").strip()
                print("Actividades disponibles: natación, baloncesto, tenis, pádel, fitness, yoga, artes marciales, danza, ciclismo")
                actividad = input("Actividad principal: ").strip()
                password = input("Contraseña: ").strip()
                
                resultado, mensaje = alta_usuario(nombre, distrito, actividad, password)
                print("" if resultado else "", mensaje)
                
            elif opcion == '2':
                print("\n BAJA DE USUARIO ")
                if usuario_actual is None:
                    user_id_input = input("ID de usuario a eliminar: ").strip()
                    try:
                        user_id = int(user_id_input)
                    except ValueError:
                        print(" El ID debe ser un número")
                        continue
                else:
                    user_id = usuario_actual
                    print(f"Eliminando usuario actual (ID: {user_id})")
                
                confirmar = input("¿Estás seguro? (s/n): ").lower()
                if confirmar == 's':
                    resultado, mensaje = baja_usuario(user_id)
                    print("" if resultado else "", mensaje)
                    if resultado and user_id == usuario_actual:
                        usuario_actual = None
                else:
                    print("Operación cancelada")
                    
            elif opcion == '3':
                listar_almacen_central()
                
            elif opcion == '4':
                acceder_distrito()
                
            elif opcion == '5':
                if usuario_actual is None:
                    print(" Debes autenticarte primero (opción 11)")
                else:
                    acceder_actividad()
                    
            elif opcion == '6':
                if usuario_actual is None:
                    print(" Debes autenticarte primero (opción 11)")
                else:
                    listar_materiales_ubicacion()
                
            elif opcion == '7':
                buscar_material()
                
            elif opcion == '8':
                if usuario_actual is None:
                    print(" Debes autenticarte primero (opción 11)")
                    continue

                print("\n RESERVAR MATERIAL ")
                print("Distritos disponibles:", ", ".join(distritos.keys()))
                distrito = input("Distrito donde buscar: ").strip()
                
                if distrito not in distritos:
                    print(" Distrito no válido")
                    continue
                
                # Mostrar materiales disponibles en ese distrito
                print(f"\nMateriales disponibles en {distrito}:")
                disponibles = []
                for material_id, material in distritos[distrito]["inventario"].items():
                    if material["estado"] == "disponible":
                        print(f"  - ID: {material_id} | {material['nombre']} ({material['actividad']})")
                        disponibles.append(material_id)
                
                if not disponibles:
                    print(" No hay materiales disponibles en este distrito")
                    continue
                
                material_id = input("\nID del material a reservar: ").strip()
                if material_id not in disponibles:
                    print(" ID no válido o material no disponible")
                    continue
                
                resultado, mensaje = reservar_material(usuario_actual, material_id, distrito)
                print("" if resultado else "", mensaje)
                
            elif opcion == '9':
                if usuario_actual is None:
                    print(" Debes autenticarte primero (opción 11)")
                else:
                    print(consultar_reservas_usuario(usuario_actual))
                    
            elif opcion == '10':
                if usuario_actual is None:
                    print(" Debes autenticarte primero (opción 11)")
                    continue
                    
                print("\n DEVOLVER MATERIAL ")
                reservas_usuario = reservas.get(usuario_actual, [])
                
                if not reservas_usuario:
                    print(" No tienes materiales reservados")
                    continue
                
                print("Tus reservas activas:")
                for i, reserva in enumerate(reservas_usuario, 1):
                    print(f"{i}. {reserva['material_nombre']} (ID: {reserva['material_id']})")
                
                try:
                    num_reserva = int(input("\nNúmero de reserva a devolver: ")) - 1
                    if 0 <= num_reserva < len(reservas_usuario):
                        material_id = reservas_usuario[num_reserva]["material_id"]
                        resultado, mensaje = devolver_material(usuario_actual, material_id)
                        print("" if resultado else "", mensaje)
                    else:
                        print(" Número de reserva no válido")
                except ValueError:
                    print(" Debes introducir un número")
                    
            elif opcion == '11':
                print("\n AUTENTICACIÓN DE USUARIO")
                try:
                    user_id_input = input("ID de usuario: ").strip()
                    user_id = int(user_id_input)
                    password = input("Contraseña: ").strip()
                    resultado, mensaje = autenticar_usuario(user_id, password)
                    print("" if resultado else "", mensaje)
                except ValueError:
                    print(" El ID debe ser un número")
                    
            elif opcion == '12':
                print("Saliendo del programa... ¡Hasta luego!")
                break
                
            else:
                print(" Opción no válida. Por favor, selecciona 1-12")
                
        except Exception as e:
            print(f" Error inesperado: {e}")

# Inicializar el sistema
inicializar_inventarios()

# Ejecutar el menú principal
if __name__ == "__main__":
    menu_principal()