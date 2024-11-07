import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevistasPublicadasComponent } from './revistas-publicadas.component';

describe('RevistasPublicadasComponent', () => {
  let component: RevistasPublicadasComponent;
  let fixture: ComponentFixture<RevistasPublicadasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RevistasPublicadasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RevistasPublicadasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
