let botonAgregar = document.querySelector("#btnAgregar");
botonAgregar.addEventListener("click", (ev) => {
  console.log("Boton pulsado");
  console.log(ev);
  //quiero a mis variables del formulario
  let nombre = document.querySelector("#inputNombre").value;
  let apellido = document.querySelector("#inputApellido").value;
  let edad = document.querySelector("#inputEdad").value;
  let fecha= document.querySelector("#inputFecha").value;
  let listaAgregados=document.querySelector("#divAgregados ul");
    console.log(`Nombre: ${nombre} Apellido: ${apellido} Edad: ${edad}`);
    if (nombre === "" || apellido === "" || edad === "") {
      alert("Por favor, complete todos los campos.");
    } else {
      lanzarDialogo("Usuario agregado", `Nombre: ${nombre} Apellido: ${apellido} Edad: ${edad} agregado correctamente` , "success");
    }
});

let botonVaciar=document.querySelector("#btnVaciar");
botonVaciar.addEventListener("click",(ev)=>{
  listaAgregados.innerHTML="";
  lanzarDialogo("Lista vaciada","Se han eliminado todos los usuarios de la lista","info");

})

function lanzarDialogo(title, text, icon) {
  Swal.fire({
  title: title,
  text: text,
  icon: icon
});
}

function agregarLi(nombre,apellido,fecha) {
  lanzarDialogo(
    "Usuario agregado",
    `Nombre: ${nombre} Apellido: ${apellido} Fecha de nacimiento: ${fecha} agregado correctamente`,
    "success"
  )
  let nodoLi=document.createElement("li");
  //nodoLi.classList.add("animate__animated","d-flex","animate__fadeInRight","justify-content-between","align-items-center","border","border-2","border-primary","rounded","m-2","p-2");
  nodoLi.innerText=`Nombre: ${nombre} Apellido: ${apellido} Fecha de nacimiento: ${fecha}`;
  let nodoBoton=document.createElement("button");
  nodoBoton.classList.add("btn","btn-danger","btn-sm","m-2");
  nodoBoton.innerText="Eliminar";
  nodoBoton.addEventListener("click", (ev)=>{
    nodoLi.remove();});
  nodoLi.appendChild(nodoBoton);
  let listaAgregados=document.querySelector("#divAgregados ul");
  listaAgregados.appendChild(nodoLi);
  
}

//funcion para eliminar mi Li de forma animada
function eliminarLi(nombre,apellido,fecha) {

}
