import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltradorRevistasComponent } from './filtrador-revistas.component';

describe('FiltradorRevistasComponent', () => {
  let component: FiltradorRevistasComponent;
  let fixture: ComponentFixture<FiltradorRevistasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FiltradorRevistasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FiltradorRevistasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
