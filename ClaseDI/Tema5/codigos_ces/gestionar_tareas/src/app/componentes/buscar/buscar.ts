import { Component } from '@angular/core';
import { Tareas } from '../../services/tareas';
import Swal from 'sweetalert2';
import { Tarea } from '../../modelos/Tarea';

@Component({
  selector: 'app-buscar',
  standalone: false,
  templateUrl: './buscar.html',
  styleUrl: './buscar.css',
})
export class Buscar {
prioridad: string = '1';
tareasFiltradas: Tarea[] = [];

constructor( private tareaService: Tareas ) {
      //logica para el tareaService

  }

  buscarPorPrioridad(): void {
    if (!this.prioridad) {
      Swal.fire({
        title: "Error, Selecciona una prioridad",
        text: "Por favor selecciona una prioridad para buscar las tareas.",
        icon: "error",
      });
      return;
    }else{
      let p = (this.prioridad); 
    this.tareasFiltradas = this.tareaService.obtenerTareasPorPrioridad(p);
    }

  }


}
