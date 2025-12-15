#Ejercicio10
def buscar_temperatura_media(ciudades_temperaturas, ciudad_buscada):
    for ciudad, temperaturas in ciudades_temperaturas:
        if ciudad.lower() == ciudad_buscada.lower():
            media = sum(temperaturas) / len(temperaturas)
            return media
    return None

# Datos de ejemplo
ciudades_temperaturas = (
    ("Madrid", (30, 32, 31)),
    ("Barcelona", (20, 26, 21)),
    ("Valencia", (28, 29, 27))
)

ciudad = input("Introduce el nombre de una ciudad: ")
media = buscar_temperatura_media(ciudades_temperaturas, ciudad)

if media is not None:
    print(f"La temperatura media en {ciudad} es: {media:.1f}°C")
else:
    print(f"No se encontraron datos para la ciudad {ciudad}")