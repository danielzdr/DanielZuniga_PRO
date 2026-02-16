import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { FormsModule } from '@angular/forms';
import { Agregar } from './componentes/agregar/agregar';
import { Buscar } from './componentes/buscar/buscar';
import { Listar } from './componentes/listar/listar';
import { ImagenesPipe } from './pipes/imagenes-pipe';
import { Carta } from './componentes/carta/carta';

@NgModule({
  declarations: [
    App,
    Agregar,
    Buscar,
    Listar,
    ImagenesPipe,
    Carta
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
