import { Component } from '@angular/core';
import { Examen } from '../../model/examen';
import { Servicio } from '../../services/servicio';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-detalle',
  standalone: false,
  templateUrl: './detalle.html',
  styleUrl: './detalle.css',
})
export class Detalle {
  examen?: Examen;

  constructor(
    private servicioExamen: Servicio,
    private gestorRutasActivas: ActivatedRoute
  ){
    let dato:string= this.gestorRutasActivas.snapshot.params['id'];
    this.examen=this.servicioExamen.obtenerExamen(Number(dato));
  }
}
