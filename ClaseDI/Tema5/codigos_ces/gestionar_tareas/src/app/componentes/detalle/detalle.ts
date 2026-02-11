import { Component } from '@angular/core';
import { Tarea } from '../../modelos/Tarea';
import { ActivatedRoute } from '@angular/router';
import { Tareas } from '../../services/tareas';

@Component({
  selector: 'app-detalle',
  standalone: false,
  templateUrl: './detalle.html',
  styleUrl: './detalle.css',
})
export class Detalle {
  tarea?: Tarea;

  constructor(
    private servicioTareas: Tareas,
    private gestorRutasActivas: ActivatedRoute
  ){
    let dato:string= this.gestorRutasActivas.snapshot.params['id'];
    this.tarea=this.servicioTareas.getTareaById(Number(dato));
  }
}
