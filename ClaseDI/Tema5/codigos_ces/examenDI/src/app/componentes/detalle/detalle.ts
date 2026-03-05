import { Component } from '@angular/core';
import { Libros } from '../libros/libros';
import { ActivatedRoute } from '@angular/router';
import { ServicioLibros } from '../../services/servicio-libros';
import { Libro } from '../../modelo/libro';

@Component({
  selector: 'app-detalle',
  standalone: false,
  templateUrl: './detalle.html',
  styleUrl: './detalle.css',
})
export class Detalle {
  libro?: Libro;

  constructor(
    private servicioLibros: ServicioLibros,
    private gestorRutasActivas: ActivatedRoute
  ){
    let dato:string= this.gestorRutasActivas.snapshot.params['id'];
    this.libro=this.servicioLibros.getLibroById(Number(dato));
  }


}
