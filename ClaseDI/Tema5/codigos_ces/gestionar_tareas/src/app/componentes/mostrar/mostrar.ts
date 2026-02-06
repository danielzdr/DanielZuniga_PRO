import { Component } from '@angular/core';
import { Tarea } from '../../modelos/Tarea';
import { Tareas } from '../../services/tareas';
@Component({
  selector: 'app-mostrar',
  standalone: false,
  templateUrl: './mostrar.html',
  styleUrl: './mostrar.css',
})
export class Mostrar {
  tareas: Tarea[] = [];
  //Mostrar el las tareas mediante un conjunto de cards que se agregaron en el componente agregar-->
 
  constructor(private tareaService: Tareas) {
    //logica para mostrar las tareas
    this.tareas = this.tareaService.getTareas();

  }

 


}
