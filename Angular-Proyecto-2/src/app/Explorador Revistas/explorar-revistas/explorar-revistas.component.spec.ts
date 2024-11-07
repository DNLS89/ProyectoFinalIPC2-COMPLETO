import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExplorarRevistasComponent } from './explorar-revistas.component';

describe('ExplorarRevistasComponent', () => {
  let component: ExplorarRevistasComponent;
  let fixture: ComponentFixture<ExplorarRevistasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExplorarRevistasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExplorarRevistasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
