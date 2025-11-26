//console.log("Pablo es el mejor y se folla a alvaro")
/*if(true){
    const DNI= "123345A"//varibale que no puede cambiar su valor
let nombre="Pablo soriano";//variable que guarda el ambito del nombre
console.log(nombre);
console.log(DNI);
}*/
/* let nombre="Pablo soriano"
console.log(`Mi nombre es ${nombre}`)


/* alert("Te llamas pablo Soriano")
confirm(`¿Estas seguro que quieres darle ${nombre}?`) 
prompt("Por favor introduce tu nombre ")*/
let nombre=""
nombre=prompt("Por favor introduce tu nombre")
let volver=true
while(volver){
let operando1=parseInt(prompt("Introduce el primer numero"))
let operando2=parseInt(prompt("Introduce el segundo numero"))
if(isNaN(operando1)|| isNaN(operando2)){
alert("Los valores introducidos no son numeros ")
}else{
let suma=operando1+operando2
confirm(`${nombre}la suma de ${operando1} y ${operando2} es: ${suma}`)
let resta=operando1-operando2
confirm(`${nombre} la resta de ${operando1} y ${operando2} es: ${resta}`)
let multiplicacion=operando1*operando2
confirm(`${nombre} la multiplicacion de ${operando1} y ${operando2} es: ${multiplicacion}`)
let division=operando1/operando2
confirm(`${nombre} la division de ${operando1} y ${operando2} es: ${division}`)
}
volver=confirm("¿Quieres volver a realizar las operaciones?")
}
