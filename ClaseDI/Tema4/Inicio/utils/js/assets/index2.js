let boton= document.querySelector("#btnAgregar");
let parrafo= document.querySelector("#contenido");
boton.addEventListener("click", ()=>{
    let tarea= new Tarea("Estudiar JS", new Date(), "Media");
    tarea.mostrarDatos();
    
});