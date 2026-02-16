import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'imagenes',
  standalone: false,
})
export class ImagenesPipe implements PipeTransform {

  transform(value: string, ...args: unknown[]): unknown {
    switch (value) {
      case '1':
        return 'https://www.magisnet.com/wp-content/uploads/2016/08/pq_pg.10.jpg';
      case '2':
        return 'https://pbs.twimg.com/media/EqWg7ihXIAcvUuz.jpg';
      case '3':
        return 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5AgQpmBB5HEihnisA1S3sCba_MthU3o5gLQ&s';
      case '4':
        return 'https://www.enclase.es/wp-content/uploads/2016/04/aprobar-examen.jpg';
      case '5':
        return 'https://img.desmotivaciones.es/201102/lolazo_5.jpg';
    }
    return null;
  }

}

