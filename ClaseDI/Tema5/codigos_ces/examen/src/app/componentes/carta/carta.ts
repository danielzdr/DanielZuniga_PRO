import { Component, Input } from '@angular/core';
import { Examen } from '../../model/examen';
import { Router } from '@angular/router';

@Component({
  selector: 'app-carta',
  standalone: false,
  templateUrl: './carta.html',
  styleUrl: './carta.css',
})
export class Carta {

  @Input() item?: Examen;

  constructor(private router:Router) {

  }


}
