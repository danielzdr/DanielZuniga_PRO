import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Servicio } from '../../services/servicio';
import { Examen } from '../../model/examen';

@Component({
  selector: 'app-listar',
  standalone: false,
  templateUrl: './listar.html',
  styleUrl: './listar.css',
})
export class Listar {
examenes: Examen[] = [];
  constructor( private servicioExamen:Servicio){
    this.examenes=servicioExamen.getExamenes()
  }
}
