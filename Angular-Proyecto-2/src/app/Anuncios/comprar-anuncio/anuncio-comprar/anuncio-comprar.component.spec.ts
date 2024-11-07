import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnuncioComprarComponent } from './anuncio-comprar.component';

describe('AnuncioComprarComponent', () => {
  let component: AnuncioComprarComponent;
  let fixture: ComponentFixture<AnuncioComprarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnuncioComprarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnuncioComprarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
