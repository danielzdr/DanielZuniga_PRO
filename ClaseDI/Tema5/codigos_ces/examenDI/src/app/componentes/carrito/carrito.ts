import { Component } from '@angular/core';
import { ServicioLibros } from '../../services/servicio-libros';
import { ActivatedRoute } from '@angular/router';
import { Libro } from '../../modelo/libro';

@Component({
  selector: 'app-carrito',
  standalone: false,
  templateUrl: './carrito.html',
  styleUrl: './carrito.css',
})
export class Carrito {
  libros: Libro[] = [];

  constructor(private servicioLibros: ServicioLibros, private gestorRutas: ActivatedRoute){ {
    this.libros = this.servicioLibros.getLibros();
  }
}
}
