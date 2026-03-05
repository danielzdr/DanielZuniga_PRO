import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Libros } from './componentes/libros/libros';
import { Carrito } from './componentes/carrito/carrito';

const routes: Routes = [
  {path:"libros",component:Libros,},
  {path:"carrito",component:Carrito,},
{path:"**",pathMatch:"full",redirectTo:"libros"}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
