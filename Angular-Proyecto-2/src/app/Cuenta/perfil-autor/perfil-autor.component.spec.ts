import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerfilAutorComponent } from './perfil-autor.component';

describe('PerfilAutorComponent', () => {
  let component: PerfilAutorComponent;
  let fixture: ComponentFixture<PerfilAutorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PerfilAutorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PerfilAutorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
