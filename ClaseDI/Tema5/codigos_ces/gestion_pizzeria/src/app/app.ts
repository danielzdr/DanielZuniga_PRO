import { Component, signal } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('gestion_pizzeria');
  opcion: string= '1';

  constructor(private gestorRutas: Router) {
     }
  navegar(parm:String) {
    this.gestorRutas.navigate(['buscar', parm]);
  }
}
