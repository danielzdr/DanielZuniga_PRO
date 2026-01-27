import { Component } from '@angular/core';
import { Tarea } from '../../modelos/Tarea';
@Component({
  selector: 'app-mostrar',
  standalone: false,
  templateUrl: './mostrar.html',
  styleUrl: './mostrar.css',
})
export class Mostrar {
  listaTareas: Tarea[] = [];
  //Mostrar el las tareas mediante un conjunto de cards que se agregaron en el componente agregar-->
 

}
