import { Pipe, PipeTransform } from '@angular/core';
import { Libro } from '../modelo/libro';

@Pipe({
  name: 'imagenes',
  standalone: false,
})
export class ImagenesPipe implements PipeTransform {

  transform(value: Libro, ...args: unknown[]): unknown {
    if (value.Pages < 150){
      return 'https://cdn-icons-png.flaticon.com/512/1945/1945940.png';
    }else if (value.Pages <250){
      return '<250: https://cdn-icons-png.flaticon.com/512/8832/8832880.png'
    }else if (value.Pages <400){
      return 'https://cdn-icons-png.flaticon.com/512/3145/3145765.png'
  }
  return 'https://cdn-icons-png.flaticon.com/512/2232/2232688.png'

}
}
