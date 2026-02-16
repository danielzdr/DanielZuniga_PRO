import { Injectable } from '@angular/core';
import { Examen } from '../model/examen';

@Injectable({
  providedIn: 'root',
})
export class Servicio {
  examenes: Examen[] = [];

  getExamenes(): Examen[] {
    return this.examenes;
  }

  agregarExamen(examen: Examen): Examen[] {
    this.examenes.push(examen);
    return this.examenes;
  }

  obtenerExamen(id: number): Examen | undefined{
    return this.examenes.find(examen => examen.id === id);
  }

  buscarExamenesPorNota(nota: string): Examen[] {
    return this.examenes.filter(examen => examen.nota === nota);
  }

}
