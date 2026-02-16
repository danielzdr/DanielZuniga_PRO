import { Injectable } from '@angular/core';
import { Pizza } from '../../modelo/pizza';

@Injectable({
  providedIn: 'root',
})
export class Pizzas {
  pizza:Pizza[] = [];

  constructor() {

   }

   //agregar una pizza al array de pizzas
   agregarPizza(pizza: Pizza) {
     this.pizza.push(pizza);
   }

   //metodo para obtener todas las pizzas, lo utilizaremos para mostrar todas las pizzas en el componente listar
   obtenerPizzas(): Pizza[] {
     return this.pizza;
   }

    //metodo para obtener todas las pizzas, lo utilizaremos para mostrar todas las pizzas en el componente listar
   getPizza(): Pizza[] {
     return this.pizza;
   }

   //metodo para obtener las pizzas por prioridad, lo utilizaremos para mostrar las pizzas filtradas por prioridad en el componente buscar
  getPizzasPorPrioridad(prioridad: string): Pizza[] {
    const prioridadNumero = Number(prioridad);
    return this.pizza.filter(pizza => pizza.prioridad === prioridadNumero);
  }

  //metodo para obtener una pizza por su id, lo utilizaremos para mostrar los detalles de la pizza en el componente detalle
  getPizzaById(id: number): Pizza | undefined {
    return this.pizza.find(p => p.id === id);
  }

}
