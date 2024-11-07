import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloquearGestionesComponent } from './bloquear-gestiones.component';

describe('BloquearGestionesComponent', () => {
  let component: BloquearGestionesComponent;
  let fixture: ComponentFixture<BloquearGestionesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BloquearGestionesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloquearGestionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
