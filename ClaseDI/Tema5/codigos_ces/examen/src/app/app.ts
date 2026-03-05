import { Component, signal } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App {

  protected readonly title = signal('examen');
  constructor(private router:Router) {
    
  }
  navegar(parm: String) {
    this.router.navigate(['buscar',parm]);
}
}
