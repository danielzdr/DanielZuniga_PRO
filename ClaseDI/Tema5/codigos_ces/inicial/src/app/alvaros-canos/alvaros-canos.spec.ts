import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlvarosCanos } from './alvaros-canos';

describe('AlvarosCanos', () => {
  let component: AlvarosCanos;
  let fixture: ComponentFixture<AlvarosCanos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AlvarosCanos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlvarosCanos);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
