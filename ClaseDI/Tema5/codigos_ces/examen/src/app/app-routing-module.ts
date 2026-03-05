import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Listar } from './componentes/listar/listar';
import { Buscar } from './componentes/buscar/buscar';
import { Agregar } from './componentes/agregar/agregar';
import { Detalle } from './componentes/detalle/detalle';

const routes: Routes = [{path:"agregar",component:Agregar,},
  {path:"buscar",component:Buscar,},
  {path:"listar",component:Listar,},
  {path:"detalle/:id",component:Detalle,},
{path:"**",pathMatch:"full",redirectTo:"agregar"}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
