import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Pablos } from './pablos';

describe('Pablos', () => {
  let component: Pablos;
  let fixture: ComponentFixture<Pablos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Pablos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Pablos);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
