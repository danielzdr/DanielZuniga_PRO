import random
import string
#Ejercicio11
def generar_contraseña():
    # Definir las tuplas
    digitos = tuple(string.digits)  # 0-9
    letras = tuple(string.ascii_letters)  # a-z A-Z (sin ñ)
    caracteres_especiales = ('!', '@', '#', '$', '%', '&', '*', '(', ')', 
                            '-', '_', '=', '+', '[', ']', '{', '}', ';', 
                            ':', '<', '>', '?', '/')
    
    while True:
        try:
            longitud = int(input("Introduce la longitud de la contraseña (8-64): "))
            if 8 <= longitud <= 64:
                break
            else:
                print("La longitud debe estar entre 8 y 64 caracteres")
        except ValueError:
            print("Por favor, introduce un número válido")
    
    # Contraseña fuerte
    num_digitos_fuerte = max(1, int(longitud * 0.2))
    num_letras_fuerte = max(1, int(longitud * 0.4))
    num_especiales_fuerte = longitud - num_digitos_fuerte - num_letras_fuerte
    
    # Ajustar si la suma no coincide con la longitud
    while num_digitos_fuerte + num_letras_fuerte + num_especiales_fuerte < longitud:
        num_especiales_fuerte += 1
    while num_digitos_fuerte + num_letras_fuerte + num_especiales_fuerte > longitud:
        num_especiales_fuerte -= 1
    
    # Generar contraseña fuerte
    contraseña_fuerte = []
    contraseña_fuerte.extend(random.choices(digitos, k=num_digitos_fuerte))
    contraseña_fuerte.extend(random.choices(letras, k=num_letras_fuerte))
    contraseña_fuerte.extend(random.choices(caracteres_especiales, k=num_especiales_fuerte))
    random.shuffle(contraseña_fuerte)
    contraseña_fuerte = tuple(contraseña_fuerte)
    
    # Contraseña débil
    num_digitos_debil = max(1, int(longitud * 0.8))
    num_letras_debil = max(1, int(longitud * 0.15))
    num_especiales_debil = longitud - num_digitos_debil - num_letras_debil
    
    # Ajustar si la suma no coincide con la longitud
    while num_digitos_debil + num_letras_debil + num_especiales_debil < longitud:
        num_especiales_debil += 1
    while num_digitos_debil + num_letras_debil + num_especiales_debil > longitud:
        num_especiales_debil -= 1
    
    # Generar contraseña débil
    contraseña_debil = []
    contraseña_debil.extend(random.choices(digitos, k=num_digitos_debil))
    contraseña_debil.extend(random.choices(letras, k=num_letras_debil))
    contraseña_debil.extend(random.choices(caracteres_especiales, k=num_especiales_debil))
    random.shuffle(contraseña_debil)
    contraseña_debil = tuple(contraseña_debil)
    
    # Convertir a string para mostrar
    contraseña_fuerte_str = ''.join(contraseña_fuerte)
    contraseña_debil_str = ''.join(contraseña_debil)
    
    print(f"\nContraseña FUERTE ({longitud} caracteres):")
    print(f"Tupla: {contraseña_fuerte}")
    print(f"String: {contraseña_fuerte_str}")
    print(f"Composición: {num_digitos_fuerte} dígitos, {num_letras_fuerte} letras, {num_especiales_fuerte} especiales")
    
    print(f"\nContraseña DÉBIL ({longitud} caracteres):")
    print(f"Tupla: {contraseña_debil}")
    print(f"String: {contraseña_debil_str}")
    print(f"Composición: {num_digitos_debil} dígitos, {num_letras_debil} letras, {num_especiales_debil} especiales")
    
    return contraseña_fuerte, contraseña_debil

# Generar contraseñas
contraseña_fuerte, contraseña_debil = generar_contraseña()