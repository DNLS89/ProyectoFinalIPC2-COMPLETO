import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestorAnunciosComponent } from './gestor-anuncios.component';

describe('GestorAnunciosComponent', () => {
  let component: GestorAnunciosComponent;
  let fixture: ComponentFixture<GestorAnunciosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GestorAnunciosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestorAnunciosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
