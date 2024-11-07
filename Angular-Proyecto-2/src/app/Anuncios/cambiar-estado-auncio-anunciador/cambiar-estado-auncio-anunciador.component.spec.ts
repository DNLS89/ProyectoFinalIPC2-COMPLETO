import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CambiarEstadoAuncioAnunciadorComponent } from './cambiar-estado-auncio-anunciador.component';

describe('CambiarEstadoAuncioAnunciadorComponent', () => {
  let component: CambiarEstadoAuncioAnunciadorComponent;
  let fixture: ComponentFixture<CambiarEstadoAuncioAnunciadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CambiarEstadoAuncioAnunciadorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CambiarEstadoAuncioAnunciadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
