import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteGanaciasPorAnuncianteComponent } from './reporte-ganacias-por-anunciante.component';

describe('ReporteGanaciasPorAnuncianteComponent', () => {
  let component: ReporteGanaciasPorAnuncianteComponent;
  let fixture: ComponentFixture<ReporteGanaciasPorAnuncianteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteGanaciasPorAnuncianteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReporteGanaciasPorAnuncianteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
