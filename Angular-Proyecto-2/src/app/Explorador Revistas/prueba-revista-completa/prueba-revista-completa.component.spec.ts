import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PruebaRevistaCompletaComponent } from './prueba-revista-completa.component';

describe('PruebaRevistaCompletaComponent', () => {
  let component: PruebaRevistaCompletaComponent;
  let fixture: ComponentFixture<PruebaRevistaCompletaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PruebaRevistaCompletaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PruebaRevistaCompletaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
