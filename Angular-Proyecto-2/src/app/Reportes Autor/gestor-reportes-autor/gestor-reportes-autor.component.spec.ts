import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestorReportesAutorComponent } from './gestor-reportes-autor.component';

describe('GestorReportesAutorComponent', () => {
  let component: GestorReportesAutorComponent;
  let fixture: ComponentFixture<GestorReportesAutorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GestorReportesAutorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestorReportesAutorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
