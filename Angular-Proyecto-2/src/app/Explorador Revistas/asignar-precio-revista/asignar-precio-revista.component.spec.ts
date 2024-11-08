import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsignarPrecioRevistaComponent } from './asignar-precio-revista.component';

describe('AsignarPrecioRevistaComponent', () => {
  let component: AsignarPrecioRevistaComponent;
  let fixture: ComponentFixture<AsignarPrecioRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AsignarPrecioRevistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AsignarPrecioRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
