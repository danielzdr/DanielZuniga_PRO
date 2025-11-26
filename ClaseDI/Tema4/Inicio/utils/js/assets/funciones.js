//funciones nominales->function(parametros){}
//funciones en flecha
function realizarCalculo(op1,op2) {
    console.log(`La suma de los dos parametros es ${op1+op2}`);

}

realizarCalculo(5,5)

function realizarCalculoFantasma(op1) {
    console.log("Calculo fantasma");
    console.log(op1+" es parametro requerido ");
    //arguments->argumentos fantasma que son pasados adicionalmente..[]
    console.log(`el numero de argumentos fantasma es de ${arguments.length}`);
                                      
}

//hagais la suma de todos los argumentos que se pasam por parametros, indicar
    //cuanntos son pasados y cuantos son fantasma
    //hay 4 argumentos, 3 fantasmas
    //la suma de todos los es 10
    let suma=0;
    for(let i=0;i<=arguments.length;i++){
        suma+=arguments[i];
    } 