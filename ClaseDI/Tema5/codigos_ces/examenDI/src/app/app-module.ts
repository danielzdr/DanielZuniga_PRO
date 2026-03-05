import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule, provideClientHydration, withEventReplay } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Libros } from './componentes/libros/libros';
import { Carrito } from './componentes/carrito/carrito';
import { Carta } from './componentes/carta/carta';
import { Detalle } from './componentes/detalle/detalle';
import { ImagenesPipe } from './pipes/imagenes-pipe';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    App,
    Libros,
    Carrito,
    Carta,
    Detalle,
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
