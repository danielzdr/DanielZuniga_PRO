import { Component, Input } from '@angular/core';
import { Pizza } from '../../../modelo/pizza';
import { Router } from 'express';

@Component({
  selector: 'app-carta',
  standalone: false,
  templateUrl: './carta.html',
  styleUrl: './carta.css',
})
export class Carta {
@Input() item?: Pizza;
  verDetalle(arg0: any) {
throw new Error('Method not implemented.');
}


constructor(private gestorRutas: Router) { 

}
}
