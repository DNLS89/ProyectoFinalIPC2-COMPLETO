import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevistaIndividualComponent } from './revista-individual.component';

describe('RevistaIndividualComponent', () => {
  let component: RevistaIndividualComponent;
  let fixture: ComponentFixture<RevistaIndividualComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RevistaIndividualComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RevistaIndividualComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
