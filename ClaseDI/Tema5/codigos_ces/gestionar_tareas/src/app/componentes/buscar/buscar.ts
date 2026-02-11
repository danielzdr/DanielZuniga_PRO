import { Component } from '@angular/core';
import { Tareas } from '../../services/tareas';
import Swal from 'sweetalert2';
import { Tarea } from '../../modelos/Tarea';
import { ActivatedRoute } from '@angular/router';
import { it } from 'node:test';

@Component({
  selector: 'app-buscar',
  standalone: false,
  templateUrl: './buscar.html',
  styleUrl: './buscar.css',
})
export class Buscar {
  prioridad?: string;
  tareas: Tarea[] = [];
  filtradas:Tarea[]=[];
  busqueda:boolean=false;
  responsable: string = '';

  constructor(
    private gestorRutasActivas: ActivatedRoute,
    private servicioTareas: Tareas,
  ) {
    // this.prioridad = gestorRutasActivas.snapshot.params['id'];
    this.gestorRutasActivas.paramMap.subscribe((item) => {
      this.busqueda = false;
      this.prioridad = item.get('id') ?? undefined;
      if (Number(this.prioridad) == 4) {
        this.tareas = this.servicioTareas.getTareas();
      } else if (this.prioridad) {
        this.tareas = this.servicioTareas.getTareasPorPrioridad(this.prioridad);
      }
      this.filtradas = this.tareas;
    });
  }

  realizarBusqueda() {
    this.filtradas = this.tareas.filter((item) =>item.responsable==this.responsable);
    this.busqueda=true;
}
}






