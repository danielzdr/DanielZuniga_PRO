import { Component } from '@angular/core';
import Swal from 'sweetalert2';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-agregar',
  standalone: false,
  templateUrl: './agregar.html',
  styleUrl: './agregar.css',
})
export class Agregar {
  nombre: string = '';
  telefono: number = 0;
  precio: number = 0;
  ingredientes: string[] = [];
  responsable: string = '';
  prioridad: string = '';
  ing: string = '';
  ingredientesList: string[] = [];
mensaje: any;

  agregarIngrediente(ingrediente: string) {
    if (ingrediente) {
      this.ingredientes.push(ingrediente);
      this.ingredientesList.push(ingrediente);
      this.ing = '';
    }
  }
  agregarPizza() {
    if (!this.nombre || !this.telefono || !this.precio || !this.prioridad || !this.responsable || this.ingredientes.length === 0) {
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Por favor, completa todos los campos y agrega al menos un ingrediente.',
      });
      return;
    }else{
      Swal.fire({
        icon: 'success',
        title: 'Pizza Agregada',
        text: `La pizza "${this.nombre}" ha sido agregada exitosamente.`,
      });
      const nuevaPizza = {
        nombre: this.nombre,
        telefono: this.telefono,
        precio: this.precio,
        ingredientes: this.ingredientes,
        prioridad: this.prioridad,
        responsable: this.responsable
    }
    // Aquí puedes agregar la lógica para guardar la pizza en tu sistema
    
      // Reiniciar los campos después de agregar la pizza
      this.nombre = '';
      this.telefono = 0;
      this.precio = 0;
      this.ingredientes = [];
      this.prioridad = '';
      this.ingredientesList = [];
      this.responsable = '';
  }
  }
}
