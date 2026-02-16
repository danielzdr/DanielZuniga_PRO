import { Component } from '@angular/core';
import { Pizzas } from '../../services/pizzas';
import { ActivatedRoute } from '@angular/router';
import { Pizza } from '../../../modelo/pizza';

@Component({
  selector: 'app-listar',
  standalone: false,
  templateUrl: './listar.html',
  styleUrl: './listar.css',
})
export class Listar {
  pizzas: Pizza[] = [];

  constructor(private pizzasService: Pizzas, private route: ActivatedRoute) {
    this.pizzas = this.pizzasService.getPizza();
  }
}
