const lista= ["Hola", "Mundo", "JavaScript", "es", "genial"];

//agrega al final de la lista
lista.push("Palabra nueva", "Palabra nueva2")
console.log(lista);
//agrega al inicio de la lista
lista.unshift("Inicio1", "Inicio2");
console.log(lista);
//eliminar elemento del final y lo retonrna el elemento eliminado
lista.pop();
console.log(lista);
//eliminar elemento del inicio y lo retorna el elemento eliminado
lista.shift();
console.log(lista);
// Función para concatenar los elementos de la lista en una sola cadena
function concatenarLista(lista) {
    return lista.join(" ");
}

//devuelve la lista completa
lista.filter((item)=>{return item != "Inicio1"})
console.log(lista);

