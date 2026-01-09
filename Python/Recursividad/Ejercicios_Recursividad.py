#1.Calcula fatorial de un numero
def factorial(n):
    if n == 0 or n == 1:
        return 1
    else:
        return n * factorial(n - 1)
#2.Calcula la multiplicacion de dos numeros mayores a 0
def multiplicacion(a, b):
    if b == 0:
        return 0
    else:
        return a + multiplicacion(a, b - 1)

#3.Calcula el exponente de dos numeros (n elevado a m)
def exponente(n, m):
    if m == 0:
        return 1
    else:
        return n * exponente(n, m - 1)

#4.Calcula la resta de dos numeros
def resta(a,b):
    if b==0:
        return a
    else:
        return resta(a-1,b-1)