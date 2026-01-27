import { Component } from '@angular/core';
import Swal from 'sweetalert2';
import { Tarea } from '../../modelos/Tarea';

@Component({
  selector: 'app-agregar',
  standalone: false,
  templateUrl: './agregar.html',
  styleUrl: './agregar.css',
})
export class Agregar {
  nombre: string = '';
  fecha: string = '';
  nombreResponsable: string = '';
  items: string = '';
  completada: boolean = false;
  
  // Lista para almacenar las tareas creadas
  listaTareas: Tarea[] = [];

  agregarTarea() {
    if (!this.nombre || !this.fecha || !this.nombreResponsable || !this.items) {
      Swal.fire({
        title: "Error, Datos incompletos",
        text: "Por favor complete todos los campos antes de agregar la tarea.",
        icon: "error",
      });
      return;
    }

    const itemsArray = this.items.split(',').map(item => item.trim()).filter(item => item !== '');
    
    if (itemsArray.length === 0) {
      Swal.fire({
        title: "Error",
        text: "Debe ingresar al menos un item separado por comas",
        icon: "error",
      });
      return;
    }
    const fechaDate = new Date(this.fecha);
    const nuevaTarea = new Tarea(
      this.nombre,
      fechaDate,
      this.nombreResponsable,
      itemsArray,
      this.completada
    );
    const tareaExistente = this.listaTareas.find(
      tarea => tarea.getNombre().toLowerCase() === nuevaTarea.getNombre().toLowerCase()
    );

    if (tareaExistente) {
      Swal.fire({
        title: "Error",
        text: "Ya existe una tarea con ese nombre",
        icon: "error",
      });
      return;
    }
    this.listaTareas.push(nuevaTarea);
    Swal.fire({
      title: "Tarea agregada",
      text: `La tarea "${nuevaTarea.getNombre()}" ha sido agregada exitosamente.`,
      icon: "success",
    });

    console.log('Tarea creada:', nuevaTarea);
    console.log('Lista de tareas:', this.listaTareas);

    this.limpiarCampos();
  }

  limpiarCampos() {
    this.nombre = '';
    this.fecha = '';
    this.nombreResponsable = '';
    this.items = '';
    this.completada = false;
  }

  
}
