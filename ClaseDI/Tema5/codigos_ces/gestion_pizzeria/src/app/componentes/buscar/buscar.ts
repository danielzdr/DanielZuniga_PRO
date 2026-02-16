import { Component } from '@angular/core';
import { Pizzas } from '../../services/pizzas';
import { ActivatedRoute } from '@angular/router';
import { Pizza } from '../../../modelo/pizza';

@Component({
  selector: 'app-buscar',
  standalone: false,
  templateUrl: './buscar.html',
  styleUrl: './buscar.css',
})
export class Buscar {
  prioridad?: string ;
  pizzas:Pizza[] = [];
  filtradas:Pizza[] = [];
  responsable:string = '';
  busqueda:boolean = false;

constructor(private servicesPizzas:Pizzas, private gestorRutas:ActivatedRoute){
  this.gestorRutas.paramMap.subscribe((item) => {
      this.busqueda = false;
      this.prioridad = item.get('id') ?? undefined;
      if (Number(this.prioridad) == 4) {
        this.pizzas = this.servicesPizzas.getPizza();
      } else if (this.prioridad) {
        this.pizzas = this.servicesPizzas.getPizzasPorPrioridad(this.prioridad);
      }
      this.filtradas = this.pizzas;
    });
}

//logica de búsqueda
realizarBusqueda() {
    this.filtradas = this.pizzas.filter((item) =>item.responsable==this.responsable);
    this.busqueda=true;
}
}
