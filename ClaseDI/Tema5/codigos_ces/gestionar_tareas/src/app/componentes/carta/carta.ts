import { Component, Input, input } from '@angular/core';
import { Tarea } from '../../modelos/Tarea';
import { Router } from '@angular/router';

@Component({
  selector: 'app-carta',
  standalone: false,
  templateUrl: './carta.html',
  styleUrl: './carta.css',
})
export class Carta {
  @Input() item?: Tarea;

  constructor(private gestorRutas:Router) {

  }

  verDetalle(id:number) {
    this.gestorRutas.navigate(['detalle',id]);
  }
}
