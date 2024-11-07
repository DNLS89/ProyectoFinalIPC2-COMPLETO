import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnuncioAnunciadorComponent } from './anuncio-anunciador.component';

describe('AnuncioAnunciadorComponent', () => {
  let component: AnuncioAnunciadorComponent;
  let fixture: ComponentFixture<AnuncioAnunciadorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnuncioAnunciadorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnuncioAnunciadorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
