import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Servicio } from '../../services/servicio';
import { Examen } from '../../model/examen';

@Component({
  selector: 'app-buscar',
  standalone: false,
  templateUrl: './buscar.html',
  styleUrl: './buscar.css',
})
export class Buscar {
  nota: string = '';
  examenes: Examen[] = [];
busqueda: boolean = false;
filtradas: Examen[] = [];
realizarBusqueda() {
  this.busqueda = true;
}

constructor(private gestorRutas: ActivatedRoute,private servicioExamen:Servicio) {
  this.gestorRutas.paramMap.subscribe((item)=>{
    this.busqueda = false;
    this.nota = item.get('nota') || '';
    if(Number(this.nota)===5){
      this.examenes= this.servicioExamen.getExamenes();
    }else if(this.nota){
      this.examenes=this.servicioExamen.obtenerExamen(Number(this.nota)) ? [this.servicioExamen.obtenerExamen(Number(this.nota)) as Examen] : [];
    }
    this.filtradas = this.examenes;
  })
}
}
