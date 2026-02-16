import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Agregar } from './componentes/agregar/agregar';
import { Buscar } from './componentes/buscar/buscar';
import { Listar } from './componentes/listar/listar';


const routes: Routes = [{path:"agregar",component:Agregar,},
  {path:"buscar",component:Buscar,},
  {path:"listar",component:Listar,},
{path:"**",pathMatch:"full",redirectTo:"agregar"}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
