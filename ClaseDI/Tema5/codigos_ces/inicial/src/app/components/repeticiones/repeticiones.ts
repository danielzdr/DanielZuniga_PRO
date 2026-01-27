import { Component } from '@angular/core';
import Swal from 'sweetalert2';
import { usuario } from '../../modelos/usuario';

@Component({
  selector: 'app-repeticiones',
  standalone: false,
  templateUrl: './repeticiones.html',
  styleUrl: './repeticiones.css',
})
export class Repeticiones {
  usuarios:usuario[] = [];
  nombre='';
  apellido='';
  edad=0;
  hobbie:string[]=[];

  pulsar(){
    if(this.nombre){
        if(!this.usuarios.includes(this.usuarios.find(p=>p.getNombre()===this.nombre)!)){
          this.usuarios.push(new usuario(this.nombre,this.apellido,this.edad));
          this.nombre='';
          Swal.fire({
              title: "Usuario añadido correctamente",
              icon: "success",
              draggable: true
          });
        } else{
          Swal.fire({
              icon: "error",
              title: "Error",
              text: "El usuario ya existe",
              draggable: true
          });
        }
    }else{
      Swal.fire({
          icon: "error",
          title: "Error",
          text: "Faltan datos",
          draggable: true
      });
    }
  }

 agregarHobbie(){
    this.hobbie.push('');
    
 }
}
