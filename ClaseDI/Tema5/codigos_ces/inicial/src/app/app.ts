import { Component, input, signal } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('inicial');
  nombre:string = 'pablo';
  apellido:string= 'soriano';
  curso:number=2;
  nombreCurso:string= 'DAM';
  nota:number|string=0;

  pulsarComenzar(notaInput:string){ 
    /*if(isNaN(Number(notaInput))){
      this.nota='Valor incorrecto';
      return;
    }else{
      this.nota=Number(notaInput);
    }*/
    Swal.fire({
    title: 'Error!',
    text: 'Do you want to continue',
    icon: 'error',
    confirmButtonText: 'Cool'
  })
  }   
}