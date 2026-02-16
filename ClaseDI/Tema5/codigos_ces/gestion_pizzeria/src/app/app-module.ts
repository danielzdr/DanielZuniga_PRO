import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Agregar } from './componentes/agregar/agregar';
import { Listar } from './componentes/listar/listar';
import { Buscar } from './componentes/buscar/buscar';
import { FormsModule } from '@angular/forms';
import { Carta } from './componentes/carta/carta';
import { ImagenesPipe } from './pipes/imagenes-pipe';

@NgModule({
  declarations: [
    App,
    Agregar,
    Listar,
    Buscar,
    Carta,
    ImagenesPipe
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
