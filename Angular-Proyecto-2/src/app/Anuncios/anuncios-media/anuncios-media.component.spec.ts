import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnunciosMediaComponent } from './anuncios-media.component';

describe('AnunciosMediaComponent', () => {
  let component: AnunciosMediaComponent;
  let fixture: ComponentFixture<AnunciosMediaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnunciosMediaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnunciosMediaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
