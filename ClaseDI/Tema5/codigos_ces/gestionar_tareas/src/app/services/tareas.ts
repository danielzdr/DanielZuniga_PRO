import { Injectable } from '@angular/core';
import { Tarea } from '../modelos/Tarea';

@Injectable({
  providedIn: 'root',
})
export class Tareas {
ordenarTareasPorPrioridad() {
    this.Tarea.sort((a, b) => a.prioridad - b.prioridad);
    
}

obtenerTareas(): Tarea[] {
    return this.Tarea;
  }
  
  Tarea:Tarea[] = [];
  prioridad: number = 0;

  agregarTareas(Tarea:Tarea) {
    this.Tarea.push(Tarea);
  }

  getTareas(): Tarea[] {
    return this.Tarea;
  }

  //obtener tareas por prioridad
  obtenerTareasPorPrioridad(prioridad: string): Tarea[] {
    return this.Tarea.filter(tarea => tarea.prioridad === Number(prioridad));
  }

}
