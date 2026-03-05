import { TestBed } from '@angular/core/testing';

import { ServicioLibros } from './servicio-libros';

describe('ServicioLibros', () => {
  let service: ServicioLibros;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicioLibros);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
