def registrar_evento(mensaje):
    """Registra un evento en el log"""
    evento = f"[t={reloj_simulado}] {mensaje}"
    eventos_log.append(evento)
    print(evento)  # También mostrar en consola
    
    # Guardar inmediatamente en el archivo
    with open('eventos.log', 'a', encoding='utf-8') as f:
        f.write(evento + '\n')

def guardar_eventos_log():
    """Guarda todos los eventos en el archivo log"""
    with open('eventos.log', 'w', encoding='utf-8') as f:
        for evento in eventos_log:
            f.write(evento + '\n')