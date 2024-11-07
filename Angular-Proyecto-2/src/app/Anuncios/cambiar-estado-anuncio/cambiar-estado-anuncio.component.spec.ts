import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CambiarEstadoAnuncioComponent } from './cambiar-estado-anuncio.component';

describe('CambiarEstadoAnuncioComponent', () => {
  let component: CambiarEstadoAnuncioComponent;
  let fixture: ComponentFixture<CambiarEstadoAnuncioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CambiarEstadoAnuncioComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CambiarEstadoAnuncioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
