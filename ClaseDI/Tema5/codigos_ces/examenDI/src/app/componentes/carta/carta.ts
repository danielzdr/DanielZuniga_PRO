import { Component, Input } from '@angular/core';
import { Libro } from '../../modelo/libro';
import { Router } from '@angular/router';

@Component({
  selector: 'app-carta',
  standalone: false,
  templateUrl: './carta.html',
  styleUrl: './carta.css',
})
export class Carta {
  @Input() item?:Libro;

  constructor( private router: Router){
    
  }

  agregarAlCarrito(id: number) {
    this.router.navigate(['/carrito', id]);
  }
  verDetalle(id: number) {
    this.router.navigate(['/detalle', id]);
  }
}
