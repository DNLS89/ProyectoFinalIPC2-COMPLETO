import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteRevistasMasComentadasComponent } from './reporte-revistas-mas-comentadas.component';

describe('ReporteRevistasMasComentadasComponent', () => {
  let component: ReporteRevistasMasComentadasComponent;
  let fixture: ComponentFixture<ReporteRevistasMasComentadasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteRevistasMasComentadasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteRevistasMasComentadasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
