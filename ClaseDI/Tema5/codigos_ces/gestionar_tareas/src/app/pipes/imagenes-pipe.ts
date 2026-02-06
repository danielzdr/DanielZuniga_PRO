import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'imagenes',
  standalone: false,
})
export class ImagenesPipe implements PipeTransform {

  transform(value: number, ...args: unknown[]): string {
    switch (value) {
      case 1:
        return "https://st3.depositphotos.com/1431107/15344/v/450/depositphotos_153444464-stock-illustration-high-priority-ink-vector-stamp.jpg";
      case 2:
        return "https://static.wixstatic.com/media/69c7de_5ee318607c284c4e803b228cee335e29~mv2.jpg/v1/fill/w_480,h_218,al_c,q_80,usm_0.66_1.00_0.01,enc_avif,quality_auto/69c7de_5ee318607c284c4e803b228cee335e29~mv2.jpg";
      case 3:
        return "https://thumbs.dreamstime.com/b/prioridad-baja-88003619.jpg";
      default:
        return '';
    }
  }

}
