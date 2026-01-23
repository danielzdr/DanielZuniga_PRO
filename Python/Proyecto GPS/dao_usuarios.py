# database/dao_usuarios.py
import hashlib

def verificar_usuario(conn, username, password):
    """Verificar credenciales de usuario"""
    query = "SELECT id, username, pass_haseada FROM usuarios WHERE username = ?"
    usuario = obtener_uno(conn, query, (username,))
    
    if not usuario:
        return None
    
    # Hashear la contraseña proporcionada para comparar
    hash_provided = hashlib.sha256(password.encode()).hexdigest()
    
    if usuario['pass_haseada'] == hash_provided:
        return {
            'id': usuario['id'],
            'username': usuario['username']
        }
    
    return None

def registrar_usuario(conn, username, password):
    """Registrar un nuevo usuario"""
    # Verificar si el usuario ya existe
    query_check = "SELECT id FROM usuarios WHERE username = ?"
    existente = obtener_uno(conn, query_check, (username,))
    
    if existente:
        return None
    
    # Hashear la contraseña
    pass_hash = hashlib.sha256(password.encode()).hexdigest()
    
    query_insert = "INSERT INTO usuarios (username, pass_haseada) VALUES (?, ?)"
    cursor = ejecutar_consulta(conn, query_insert, (username, pass_hash))
    
    if cursor:
        return cursor.lastrowid
    return None