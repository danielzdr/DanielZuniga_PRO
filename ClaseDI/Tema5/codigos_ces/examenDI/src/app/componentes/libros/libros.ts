import { Component } from '@angular/core';
import { Libro } from '../../modelo/libro';
import { ServicioLibros } from '../../services/servicio-libros';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-libros',
  standalone: false,
  templateUrl: './libros.html',
  styleUrl: './libros.css',
})
export class Libros {
  libros: Libro[] = [];

  constructor(private servicioLibros: ServicioLibros, private gestorRutas:ActivatedRoute){
    this.libros=this.servicioLibros.getLibros();
  }
}
