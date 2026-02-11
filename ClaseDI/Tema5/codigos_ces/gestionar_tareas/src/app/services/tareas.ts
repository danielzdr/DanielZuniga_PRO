import { Injectable } from '@angular/core';
import { Tarea } from '../modelos/Tarea';

@Injectable({
  providedIn: 'root',
})
export class Tareas {

  contador=6;

  tareas: Tarea[] = [
    {
      id: 1,
      nombre: 'Tarea 1',
      responsable: 'R1',
      items: [],
      fecha: '2024-06-01',
      prioridad: '1',
      descipcion: 'Descripción de la tarea 1'

    },
    {
      id: 2,
      nombre: 'Tarea 2',
      responsable: 'R2',
      items: [],
      fecha: '2024-06-02',
      prioridad: '2',
      descipcion: 'Descripción de la tarea 2'
    },
    {
      id: 3,
      nombre: 'Tarea 3',
      responsable: 'R3',
      items: [],
      fecha: '2024-06-03',
      prioridad: '3',
      descipcion: 'Descripción de la tarea 3'
    },
    {
      id: 4,
      nombre: 'Tarea 4',
      responsable: 'R4',
      items: [],
      fecha: '2024-06-04',
      prioridad: '4',
      descipcion: 'Descripción de la tarea 4'
    },
    {
      id: 5,
      nombre: 'Tarea 5',
      responsable: 'R5',
      items: [],
      fecha: '2024-06-05',
      prioridad: '5',
      descipcion: 'Descripción de la tarea 5'
    }
  ];


obtenerTareas(): Tarea[] {
    return this.Tarea;
  }
  
  Tarea:Tarea[] = [];
  prioridad: number = 0;

  agregarTareas(Tarea:Tarea) {
    Tarea.id = this.contador;
    this.Tarea.push(Tarea);
    this.contador++;
  }

  getTareas(): Tarea[] {
    return this.Tarea;
  }

  //metodo para obtener las tareas por prioridad, lo utilizaremos para mostrar las tareas filtradas por prioridad en el componente buscar
  getTareasPorPrioridad(numero: string): Tarea[] {
    return this.Tarea.filter(tarea => tarea.prioridad === numero);
  }

  //metodo para obtener una tarea por su id, lo utilizaremos para mostrar los detalles de la tarea en el componente detalle
  getTareaById(id: number): Tarea | undefined {
    return this.Tarea.find(t => t.id === id);
  }
  
  

}
