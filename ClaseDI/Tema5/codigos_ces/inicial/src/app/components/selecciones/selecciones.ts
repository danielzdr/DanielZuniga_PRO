import { Component } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-selecciones',
  standalone: false,
  templateUrl: './selecciones.html',
  styleUrl: './selecciones.css',
})
export class Selecciones {
  nombre='';
  direccionEnvio='';
  direccionFacturacion='';
  coste=0;
  tipoPago='';
  numeroTarjeta=0;
  caducidad='';
  cvv=0;
  numeroTelefono=0;
  iban='';
  analizada=false;
  resolucion=false;


  validarCompra(){
    if(this.nombre && this.direccionEnvio && this.direccionFacturacion && this.coste && this.tipoPago){
      if(this.tipoPago){
         Swal.fire({
      title: "Pago corretamente realizado",
      icon: "success",
      draggable: true
    
        });
      }else{
        this.resolucion=false
      }
      this.analizada=true;
        setTimeout(() => {
          this.resetearDatos();
        }, 5000);
   
    }else{
    Swal.fire({
    icon: "error",
    title: "Error",
    text: "pago no realizado, faltan datos",
    draggable: true
});
    }
  }

  resetearDatos(){
    this.nombre=''
    this.direccionEnvio=''
    this.direccionFacturacion=''
    this.coste=0
    this.tipoPago=''
    this.numeroTarjeta=0;
    this.caducidad='';
    this.cvv=0;
    this.numeroTelefono=0;
    this.iban='';
    this.analizada=false;


  }
}
