import { Component } from '@angular/core';
import Swal from 'sweetalert2';
import { Tarea } from '../../modelos/Tarea';
import { Tareas } from '../../services/tareas';

@Component({
  selector: 'app-agregar',
  standalone: false,
  templateUrl: './agregar.html',
  styleUrl: './agregar.css',
})
export class Agregar {
 items: string[] = [];
  nombre?: string;
  fecha?: string;
  responsable?: string;
  prioridad?: string;
  completada?: boolean;
  descripcion?: string;
  item?: string;

  constructor( private tareaService: Tareas ) {
      //logica para el tareaService

  }
  
  agregarTarea() {
    if (!this.nombre || !this.fecha || !this.responsable || !this.items || this.completada === undefined) {
      Swal.fire({
        title: "Error, Rellena los campos",
        text: "Por favor complete todos los campos antes de agregar la tarea.",
        icon: "error",
      });
      return;
    }else{
      let Tareas: Tarea = {
      nombre: this.nombre,
      responsable: this.responsable,
      items: this.items,
      fecha: this.fecha,
      prioridad: Number(this.prioridad),
      descipcion: this.descripcion,
      completada: this.completada,
    };
      this.tareaService.agregarTareas(Tareas);
      this.limpiarCampos();
      Swal.fire({
        title: "Tarea agregada",
        text: "La tarea ha sido agregada exitosamente.",
        icon: "success",
      });
    }
        
    }


  limpiarCampos() {
    this.nombre = '';
    this.fecha = '';
    this.responsable = '';
    this.items = [];
    this.prioridad = '';
    this.descripcion = '';
  }

  
}

