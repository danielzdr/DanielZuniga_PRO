#Ejercicio1
num1 = int (input("Ingresa el primer numero:"))
num2= int (input("Ingresa el segundo numero:"))
suma = num1+num2
print ("La suma es ",suma)
#Ejercicio2
lado1= int (input("Ingresa el primer lado del rectangulo"))
lado2= int (input("Ingresa el segundo lado del rectangulo"))
area= lado1*lado2
print("El area del rectangulo es ",area)
#Ejercicio3
temperatura= int (input("Ingresa la temperatura en grados Celcius:"))
fahrenheit = (temperatura * 9/5) + 32
print("La temperatura en grados Fahrenheit es ",fahrenheit)
#Ejercicio4
peso= float (input("Ingresa el peso en kg:"))
altura= float (input("Ingresa la altura en metros:"))
imc= peso / (altura ** 2)
print("El índice de masa corporal (IMC) es ",imc)
#Ejercicio5
cadena1=input("Ingresa la primera cadena de texto:")
cadena2=input("Ingresa la segunda cadena de texto:")
concatenacion=cadena1+" "+cadena2
print("La cadena resultante es:",concatenacion)
#Ejercicio6
def determinar_tipo_dato(valor):
    try:
        int_val = int(valor)
        print(f"'{valor}' es de tipo: int")
    except ValueError:
        try:
            float_val = float(valor)
            print(f"'{valor}' es de tipo: float")
        except ValueError:
            if valor.lower() == "true" or valor.lower() == "false":
                print(f"'{valor}' es de tipo: bool")
            else:
                print(f"'{valor}' es de tipo: str")
entrada = input("Ingresa un valor: ")
determinar_tipo_dato(entrada)
