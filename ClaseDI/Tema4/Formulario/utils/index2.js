let botonAgregar= document.querySelector("#btnAgregar");
let inputNombre= document.querySelector("#inputNombre");
let inputFecha= document.querySelector("#inputFecha");
let inputPrioridad= document.querySelector("#selectPrioridad");
let parrafo= document.querySelector("#contenido");

//llenar el desplegable
let prioridades=["Alta","Media","Baja"];
prioridades.forEach(prioridad=>{
    let opcion=document.createElement("option");
    opcion.value=prioridad;
    opcion.textContent=prioridad;
    inputPrioridad.appendChild(opcion);
});
botonAgregar.addEventListener("click", ()=>{
    // aviso cuando le doy al boton agregar
    alert("Has pulsado el boton agregar");
    //si se han completado los campos del formulario agregar la tarea, si no, 
    //avisar que faltan campos por completar
    if(inputNombre.value === "" || inputFecha.value === "" || inputPrioridad.value === ""){
        alert("Faltan campos por completar");
        return;
    }else{
    //recoger los datos del formulario
    let nombre=document.querySelector("#inputNombre").value;
    let fecha=document.querySelector("#inputFecha").value;
    let prioridad=document.querySelector("#selectPrioridad").value;
    //crear la tarea
    let tareaNueva=new tarea(nombre,new Date(fecha),prioridad);
    //mostrar los datos en la lista
    let lista=document.querySelector(".list-group");
    lista.innerHTML+=`<li class="list-group-item">El nombre es: ${tareaNueva.getNombre}, 
    la fecha es: ${tareaNueva.fecha.toLocaleDateString()},  
    y la prioridad es: ${tareaNueva.prioridad}</li>`;
    //limpiar el formulario
    inputNombre.value="";
    inputFecha.value="";
    inputPrioridad.value="";
}
//agregar la tarea al array de tareas
tarea.push(tareaNueva);
//dependiendo de la prioridad dandole al boton buscar y seleccionando una prioridad, mostrar una de las 3 imagenes
let botonBuscar=document.querySelector("#btnBuscar");
botonBuscar.addEventListener("click", ()=>{
    //aviso cuando se encuentran las tareas
    alert("Has pulsado el boton buscar");
    //si no se ha seleccionado ninguna prioridad, avisar que hay que seleccionar una
    alert("Debes seleccionar una prioridad");
    let prioridadBuscar=document.querySelector("#selectPrioridadBuscar").value;
    let imagen=document.querySelector("#imagenPrioridad");
    if(prioridadBuscar==="Alta"){
        imagen.src="./alta.png";
    }else if(prioridadBuscar==="Media"){
        imagen.src="./media.png";
    }else if(prioridadBuscar==="Baja"){
        imagen.src="./baja.png";
    }
});
});
