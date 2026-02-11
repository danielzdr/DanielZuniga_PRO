import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Agregar } from './componentes/agregar/agregar';
import { Mostrar } from './componentes/mostrar/mostrar';
import { Buscar } from './componentes/buscar/buscar';
import { FormsModule } from '@angular/forms';
import { ImagenesPipe } from './pipes/imagenes-pipe';
import { Carta } from './componentes/carta/carta';
import { Detalle } from './componentes/detalle/detalle';

@NgModule({
  declarations: [
    App,
    Agregar,
    Mostrar,
    Buscar,
    ImagenesPipe,
    Carta,
    Detalle
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideClientHydration(withEventReplay()),
  ],
  bootstrap: [App]
})
export class AppModule { }
