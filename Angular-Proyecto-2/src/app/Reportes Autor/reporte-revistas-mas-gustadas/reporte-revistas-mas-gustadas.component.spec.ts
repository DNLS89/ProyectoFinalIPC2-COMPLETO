import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteRevistasMasGustadasComponent } from './reporte-revistas-mas-gustadas.component';

describe('ReporteRevistasMasGustadasComponent', () => {
  let component: ReporteRevistasMasGustadasComponent;
  let fixture: ComponentFixture<ReporteRevistasMasGustadasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteRevistasMasGustadasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteRevistasMasGustadasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
