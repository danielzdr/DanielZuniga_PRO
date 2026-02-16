import { Component, signal } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App {
navegar(arg0: string) {
  this.router.navigate([arg0]);
}
  protected readonly title = signal('examen');
  constructor(private router:Router) {
    
  }
}
