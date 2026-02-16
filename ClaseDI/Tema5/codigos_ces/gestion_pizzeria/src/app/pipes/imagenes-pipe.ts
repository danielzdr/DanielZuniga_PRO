import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'imagenes',
  standalone: false,
})
export class ImagenesPipe implements PipeTransform {

  transform(value: number, ...args: unknown[]): unknown {
    switch (value) {
      case 1:
        return 'https://st2.depositphotos.com/1431107/6747/v/950/depositphotos_67475433-stock-illustration-high-priority-icon.jpg';
      case 2:
        return 'https://img.freepik.com/psd-gratis/3d-renderizar-icono-prioridad-media_372682-3288.jpg';
      case 3:
        return 'https://th.bing.com/th/id/OIP.eQINkq6a-WQEVr5XpFMTtwHaHa?w=200&h=195&c=7&r=0&o=7&dpr=1.6&pid=1.7&rm=3';
      default:
        return 'https://tse1.mm.bing.net/th/id/OIP.hyH-kot_TfAzkf05MZxrZgHaFa?rs=1&pid=ImgDetMain&o=7&rm=3';
  }

}
}
