import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevistaIndividualCompletaComponent } from './revista-individual-completa.component';

describe('RevistaIndividualCompletaComponent', () => {
  let component: RevistaIndividualCompletaComponent;
  let fixture: ComponentFixture<RevistaIndividualCompletaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RevistaIndividualCompletaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RevistaIndividualCompletaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
