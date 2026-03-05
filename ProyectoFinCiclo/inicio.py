import pygame
import random
import personaje
import variables
import meteoritos
pygame.init()
variables.font = pygame.font.SysFont(None, 36)


def escalar_cuadrado(image, side):
    # Recorta al mayor cuadrado centrado para evitar deformacion por estirado.
    width, height = image.get_size()
    base = min(width, height)
    x = (width - base) // 2
    y = (height - base) // 2
    square = image.subsurface((x, y, base, base)).copy()
    return pygame.transform.smoothscale(square, (side, side))


# Creacion de la ventana
ventana= pygame.display.set_mode((variables.ANCHO, variables.ALTO)) 

#cargar imagenes
player_image = pygame.image.load("assets/milenario.png").convert_alpha()
meteor_image = pygame.image.load("assets/meteorito.png").convert_alpha()
background_image = pygame.image.load("assets/fondo.png").convert()

#Escalar imagenes
player_image = escalar_cuadrado(player_image, personaje.Personaje.player_width)
meteor_image = escalar_cuadrado(meteor_image, meteoritos.meteor_width)
#nombre de la ventana
pygame.display.set_caption("Juego Star Wars de Daniel y Ruben")
#Reloj para controlar los FPS
clock = pygame.time.Clock()
#para abrir la ventana permanete y cerrar al hacer un evento de cierre
run = True
while run== True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            run = False
    
    keys = pygame.key.get_pressed()
    speed = personaje.Personaje.player_speed
    if (keys[pygame.K_LEFT] or keys[pygame.K_a]) and personaje.Personaje.player.left > 0:
        personaje.Personaje.player.x -= speed

    if (keys[pygame.K_RIGHT] or keys[pygame.K_d]) and personaje.Personaje.player.right < variables.ANCHO:
        personaje.Personaje.player.x += speed
    
    if (keys[pygame.K_UP] or keys[pygame.K_w]) and personaje.Personaje.player.top > 0:
        personaje.Personaje.player.y -= speed
    if (keys[pygame.K_DOWN] or keys[pygame.K_s]) and personaje.Personaje.player.bottom < variables.ALTO:
        personaje.Personaje.player.y += speed

    #Generar meteoritos
    if len(meteoritos.meteors) < 5:
        meteor_x = random.randint(0, variables.ANCHO - meteoritos.meteor_width)
        meteor_y = random.randint(-100, -40)
        meteor_rect = pygame.Rect(meteor_x, meteor_y, meteoritos.meteor_width, meteoritos.meteor_height)
        meteoritos.meteors.append(meteor_rect)
    ventana.fill(variables.BLACK)
    ventana.blit(background_image, (0, 0))
    ventana.blit(player_image, (personaje.Personaje.player.x, personaje.Personaje.player.y))
    #Mover y dibujar meteoritos
    for meteor in meteoritos.meteors:
        ventana.blit(meteor_image, (meteor.x, meteor.y))
        meteor.y += 5
        if meteor.top > variables.ALTO:
            meteoritos.meteors.remove(meteor)
            variables.puntuacion += 1

    #Detectar coliniones
    for meteor in meteoritos.meteors:
        if personaje.Personaje.player.colliderect(meteor):
            run = False
    #Mostrar puntuacion
    puntuacion_text = variables.font.render(f"Puntuacion: {variables.puntuacion}", True, variables.WHITE)
    ventana.blit(puntuacion_text, (10, 10))

    #Actualizar la ventana
    pygame.display.flip()
    clock.tick(60)


pygame.quit()