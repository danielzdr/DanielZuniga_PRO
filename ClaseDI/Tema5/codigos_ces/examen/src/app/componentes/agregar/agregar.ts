import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Servicio } from '../../services/servicio';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-agregar',
  standalone: false,
  templateUrl: './agregar.html',
  styleUrl: './agregar.css',
})
export class Agregar {
  id: number = 0;
nombre: string = '';
asignatura: string = '';
nota: string = '';
descripcion: string = '';
fecha: Date | null = null;
agregarExamen() {
if(!this.nombre || !this.asignatura || !this.nota || !this.descripcion || !this.fecha) {
Swal.fire({
icon: 'error',
title: 'Error',
text: 'Por favor, complete todos los campos.',
});
return;
}else{

this.servicioExamen.agregarExamen({
id: this.id,
nombre: this.nombre,
asignatura: this.asignatura,
nota: this.nota,
descripcion: this.descripcion,
fecha: this.fecha
});
Swal.fire({
icon: 'success',
title: 'Examen agregado',
text: 'El examen se ha agregado correctamente.',
});

}
setTimeout(() => {
      this.resetearDatos();
    }, 5000);
}
resetearDatos() {
this.id = 0;
this.nombre = '';
this.asignatura = '';
this.nota = '';
this.descripcion = '';
this.fecha = null;
}

constructor(private router: Router, private servicioExamen:Servicio) {

}



}
