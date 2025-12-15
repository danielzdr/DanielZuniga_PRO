listaPalabras=["Daniel", "Pablo", "Ruben", "Alvaro"]
palabra=str(input("Introduce una palabra:"))
if palabra in listaPalabras:
    print(f"La palabra {palabra} esta en la lista")
else:
    print(f"La palabra {palabra} no esta en la lista")