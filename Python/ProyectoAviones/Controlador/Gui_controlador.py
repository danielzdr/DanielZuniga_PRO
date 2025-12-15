import tkinter as tk
import FlujoFinal as sim  # Asegúrate de que se llama FlujoFinal.py y está en la misma carpeta


# -------------------------
# Inicialización del sistema usando tu lógica
# -------------------------

def inicializar_sistema():
    """Reproduce la parte de inicialización de main(), pero sin el menú."""
    # Reiniciar estructuras globales
    sim.vuelos = []
    sim.flujo_aterrizaje = []
    sim.flujo_despegue = []
    sim.vuelos_completados = []
    sim.eventos_log = []
    sim.reloj = 0

    # Cargar pistas y vuelos igual que en main()
    sim.cargar_pistas_desde_archivo()
    sim.cargar_vuelos_desde_txt("vuelos.txt", {})

    # Registrar inicio
    sim.registrar_evento("SISTEMA_INICIADO_DESDE_GUI")


def avanzar_un_minuto():
    """Avanza un minuto de simulación SIN usar time.sleep."""
    sim.reloj += 1
    sim.actualizar_sistema()


def obtener_estado_para_gui():
    """
    Devuelve una lista de diccionarios con el estado de cada vuelo:
    id, tipo, estado, pista, prioridad, combustible, progreso (0–1 si ocupa pista).
    """
    estado_vuelos = []

    # Mapa pista -> tiempo uso
    tiempos_uso_pista = {}
    for pista in sim.pistas:
        tiempos_uso_pista[pista["id"]] = pista.get("tiempo_uso", 3)

    for vuelo in sim.vuelos:
        vid = vuelo["id"]
        tipo = vuelo.get("tipo", "ATERRIZAJE")
        estado = vuelo.get("estado", "EN_COLA")
        prioridad = vuelo.get("prioridad", 0)
        combustible = vuelo.get("combustible", None)

        pista_asignada = None
        fin_ocupacion = None
        tiempo_uso = None

        for pista_id, info in sim.pistas_estado.items():
            if info.get("vuelo") == vid:
                pista_asignada = pista_id
                fin_ocupacion = info.get("fin_ocupacion")
                tiempo_uso = tiempos_uso_pista.get(pista_id, 3)
                break

        progreso = 0.0
        if pista_asignada and fin_ocupacion is not None and tiempo_uso:
            tiempo_restante = max(fin_ocupacion - sim.reloj, 0)
            progreso = 1.0 - (tiempo_restante / tiempo_uso)
            progreso = max(0.0, min(1.0, progreso))

        estado_vuelos.append({
            "id": vid,
            "tipo": tipo,
            "estado": estado,
            "pista": pista_asignada,
            "prioridad": prioridad,
            "combustible": combustible,
            "progreso": progreso
        })

    return estado_vuelos


# -------------------------
# GUI con animación suave
# -------------------------

class GUIControlador:
    def __init__(self):
        inicializar_sistema()

        self.root = tk.Tk()
        self.root.title("Simulación de Controlador Aéreo (GUI)")

        self.canvas_width = 1000
        self.canvas_height = 500

        self.canvas = tk.Canvas(
            self.root,
            width=self.canvas_width,
            height=self.canvas_height,
            bg="#84c5f4"  # azul cielo suave
        )
        self.canvas.pack()

        # Barra inferior: botones y estado
        toolbar = tk.Frame(self.root)
        toolbar.pack(fill=tk.X)

        tk.Button(toolbar, text="Avanzar 1 minuto", command=self.tick_manual).pack(side=tk.LEFT, padx=5, pady=5)
        tk.Button(toolbar, text="Animación ON", command=self.iniciar_animacion).pack(side=tk.LEFT, padx=5)
        tk.Button(toolbar, text="Animación OFF", command=self.detener_animacion).pack(side=tk.LEFT, padx=5)

        self.lbl_estado = tk.Label(toolbar, text="Minuto 0")
        self.lbl_estado.pack(side=tk.RIGHT, padx=10)

        # Layout de pistas: pista_id -> (x_inicio, x_fin, y_centro)
        self.layout_pistas = {}
        self._dibujar_aeropuerto()

        # Aviones
        # id_vuelo -> id_figure (triángulo)
        self.objetos_vuelo = {}
        # id_vuelo -> (tx, ty) posición objetivo para animación
        self.targets = {}

        # Config animación
        self.animacion_activa = True
        self.intervalo_sim_ms = 900   # cada cuánto avanza 1 "minuto" de simulación
        self.intervalo_frame_ms = 30  # cada cuánto se repinta (≈33 FPS)

        # Guardamos último estado para poder consultarlo desde los frames
        self.estado_vuelos_actual = []

        # Arrancar bucles
        self.root.after(self.intervalo_sim_ms, self.actualizar_simulacion)
        self.root.after(self.intervalo_frame_ms, self.actualizar_animacion)

    # ---------- Dibujo estático (aeropuerto) ----------

    def _dibujar_aeropuerto(self):
        """
        Dibuja pistas horizontales, con aspecto más “real”.
        """
        # “suelo” inferior simulando tierra/pista
        self.canvas.create_rectangle(
            0, 180, self.canvas_width, self.canvas_height,
            fill="#6aa84f", outline=""
        )

        base_y = 220
        separacion = 80
        x_inicio = 320
        x_fin = 780

        for idx, pista in enumerate(sim.pistas):
            y_centro = base_y + idx * separacion
            y0 = y_centro - 18
            y1 = y_centro + 18

            habilitada = pista.get("habilitada", True)
            color_pista = "#4d4d4d" if habilitada else "#2b2b2b"

            # Rectángulo principal
            self.canvas.create_rectangle(
                x_inicio, y0, x_fin, y1,
                fill=color_pista, outline="black"
            )

            # Línea discontinua central (la “pista” real)
            num_tramos = 10
            tramo = (x_fin - x_inicio) / num_tramos
            for i in range(num_tramos):
                if i % 2 == 0:
                    x0 = x_inicio + i * tramo
                    x1 = x0 + tramo * 0.6
                    self.canvas.create_rectangle(
                        x0, y_centro - 2, x1, y_centro + 2,
                        fill="white", outline=""
                    )

            # Etiqueta pista
            self.canvas.create_text(
                x_inicio - 40, y_centro,
                text=pista["id"],
                font=("Segoe UI", 11, "bold"),
                anchor="e"
            )

            self.layout_pistas[pista["id"]] = (x_inicio, x_fin, y_centro)

        # Títulos para las colas
        self.canvas.create_text(
            80, 40,
            text="Cola Aterrizajes",
            font=("Segoe UI", 11, "bold"),
            fill="black"
        )
        self.canvas.create_text(
            80, 260,
            text="Cola Despegues",
            font=("Segoe UI", 11, "bold"),
            fill="black"
        )

    # ---------- Utilidades de dibujo de aviones ----------

    def _crear_avion(self, x, y, info_vuelo):
        """
        Crea un triángulo que simula un avión, apuntando hacia la derecha.
        """
        size = 14
        # Triángulo apuntando a la derecha
        puntos = [
            x - size, y - size / 2,
            x - size, y + size / 2,
            x + size, y
        ]
        color = self.color_por_estado(info_vuelo)
        avion = self.canvas.create_polygon(
            puntos,
            fill=color,
            outline="black",
            width=1
        )
        # id encima del avión
        texto = self.canvas.create_text(
            x, y - 14,
            text=info_vuelo["id"],
            font=("Segoe UI", 8),
            fill="black"
        )
        return avion, texto

    def color_por_estado(self, vuelo_info):
        estado = vuelo_info["estado"]
        tipo = vuelo_info["tipo"]
        prioridad = vuelo_info["prioridad"]

        if estado == "EN_COLA":
            return "#f6b26b" if tipo == "ATERRIZAJE" else "#6fa8dc"
        if estado == "ASIGNADO":
            return "#e06666" if tipo == "ATERRIZAJE" else "#93c47d"
        if estado == "COMPLETADO":
            return "gray"
        if estado == "CANCELADO":
            return "black"
        if prioridad >= 2:
            return "#8e7cc3"
        return "white"

    def posicion_objetivo(self, vuelo_info, indice_cola_aterr, indice_cola_despegue):
        """
        Calcula la posición objetivo (x, y) donde debería ir el avión según su estado.
        """
        estado = vuelo_info["estado"]
        tipo = vuelo_info["tipo"]
        pista_id = vuelo_info["pista"]
        progreso = vuelo_info["progreso"]

        # Zona de colas
        if estado == "EN_COLA":
            if tipo == "ATERRIZAJE":
                x = 80
                y = 70 + indice_cola_aterr * 26
            else:
                x = 80
                y = 290 + indice_cola_despegue * 26
            return x, y

        # Aviones en pista / aproximación / salida
        if estado == "ASIGNADO" and pista_id and pista_id in self.layout_pistas:
            x0, x1, y_pista = self.layout_pistas[pista_id]

            if tipo == "ATERRIZAJE":
                # Desde la izquierda hacia el inicio de pista
                x_inicial = 40
                x_final = x0
            else:
                # Desde el centro de pista hacia la derecha
                x_inicial = (x0 + x1) / 2
                x_final = 960

            x = x_inicial + (x_final - x_inicial) * progreso
            y = y_pista - 10  # un pelín por encima de la pista
            return x, y

        # Completados -> fuera de pantalla
        return self.canvas_width + 200, self.canvas_height + 200

    # ---------- Bucles de actualización ----------

    def actualizar_simulacion(self):
        """
        Avanza la simulación (cada X ms) y actualiza los targets
        hacia donde deberán moverse los aviones.
        """
        if self.animacion_activa:
            avanzar_un_minuto()

        self.lbl_estado.config(text=f"Minuto {sim.reloj}")

        estado_vuelos = obtener_estado_para_gui()
        self.estado_vuelos_actual = estado_vuelos

        # Calcular colas para ordenarlos visualmente
        cola_aterr = [
            v for v in estado_vuelos
            if v["estado"] == "EN_COLA" and v["tipo"] == "ATERRIZAJE"
        ]
        cola_despegue = [
            v for v in estado_vuelos
            if v["estado"] == "EN_COLA" and v["tipo"] == "DESPEGUE"
        ]
        indice_cola_aterr = {v["id"]: i for i, v in enumerate(cola_aterr)}
        indice_cola_despegue = {v["id"]: i for i, v in enumerate(cola_despegue)}

        activos = set()

        for v in estado_vuelos:
            vid = v["id"]
            activos.add(vid)

            # Si no existe, creamos avión + texto
            if vid not in self.objetos_vuelo:
                # inicial en (0,0), luego lo movemos
                avion, texto = self._crear_avion(0, 0, v)
                self.objetos_vuelo[vid] = {"avion": avion, "texto": texto}

            # Calcular posición objetivo
            idx_a = indice_cola_aterr.get(vid, 0)
            idx_d = indice_cola_despegue.get(vid, 0)
            tx, ty = self.posicion_objetivo(v, idx_a, idx_d)
            self.targets[vid] = (tx, ty)

            # Actualizar color según estado
            color = self.color_por_estado(v)
            self.canvas.itemconfig(self.objetos_vuelo[vid]["avion"], fill=color)

        # Eliminar aviones que ya no están en la simulación
        for vid in list(self.objetos_vuelo.keys()):
            if vid not in activos:
                self.canvas.delete(self.objetos_vuelo[vid]["avion"])
                self.canvas.delete(self.objetos_vuelo[vid]["texto"])
                del self.objetos_vuelo[vid]
                if vid in self.targets:
                    del self.targets[vid]

        # Programar siguiente actualización de simulación
        self.root.after(self.intervalo_sim_ms, self.actualizar_simulacion)

    def actualizar_animacion(self):
        """
        Mueve los aviones suavemente hacia su posición objetivo.
        """
        factor = 0.2  # cuanto más alto, más rápido se acercan al target

        for vid, data in self.objetos_vuelo.items():
            avion = data["avion"]
            texto = data["texto"]

            if vid not in self.targets:
                continue

            tx, ty = self.targets[vid]

            # Centro actual del avión
            coords = self.canvas.coords(avion)  # [x1,y1, x2,y2, x3,y3]
            xs = coords[0::2]
            ys = coords[1::2]
            cx = sum(xs) / len(xs)
            cy = sum(ys) / len(ys)

            dx = tx - cx
            dy = ty - cy

            # Si ya está muy cerca, no hacemos casi nada
            if abs(dx) < 0.5 and abs(dy) < 0.5:
                continue

            mx = dx * factor
            my = dy * factor

            self.canvas.move(avion, mx, my)
            self.canvas.move(texto, mx, my)

        # Programar siguiente frame
        self.root.after(self.intervalo_frame_ms, self.actualizar_animacion)

    # ---------- Controles de botones ----------

    def tick_manual(self):
        """Botón: avanzar 1 minuto aunque la animación esté pausada."""
        avanzar_un_minuto()
        self.lbl_estado.config(text=f"Minuto {sim.reloj}")
        # Forzar actualización de targets inmediatamente
        self.actualizar_simulacion()

    def iniciar_animacion(self):
        self.animacion_activa = True

    def detener_animacion(self):
        self.animacion_activa = False

    def run(self):
        self.root.mainloop()


if __name__ == "__main__":
    gui = GUIControlador()
    gui.run()
