import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefinirCostoRevistaComponent } from './definir-costo-revista.component';

describe('DefinirCostoRevistaComponent', () => {
  let component: DefinirCostoRevistaComponent;
  let fixture: ComponentFixture<DefinirCostoRevistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DefinirCostoRevistaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DefinirCostoRevistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
