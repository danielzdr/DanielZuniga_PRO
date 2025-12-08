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
  
}
