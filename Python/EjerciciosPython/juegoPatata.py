import time
import random

tiempoLimite= random.randint(8,15)
numeroSecreto= random.randint(1,100)
intentos=3
print("Bienvendos al juegos de la patata caliente")
print(f"Tienes {tiempoLimite} segundos para adivinar el nuemro entre 1 y 100 ")
print("Si fallas 3 veces.... ¡Te quemas!")
inicio=time.time()

while intentos>0:
    #Funcion para comprobar el tiempo y si es mayor al tiempo limite(random) pierdes.
    tiempo_transcurrido=time.time()-inicio
    if tiempo_transcurrido>tiempoLimite:
        print("Se te ha acabado el tiempo, te has quemado")
        break
    print(f"\nTe quedan {intentos} intentos")
    intento = int(input("Adivina el numero: "))
    if intento == numeroSecreto:
        print("Felicidades has adivinado el numero y te has librado de la patata caliente")
        break
    
    elif intento < numeroSecreto:
        print("El numero es mayor. ")
        intentos -= 1
    else:
        print("El numero es menor. ")
        intentos-=1
if intentos==0:
    print(f" Lo siento te has quemado, el numero era {numeroSecreto}")
