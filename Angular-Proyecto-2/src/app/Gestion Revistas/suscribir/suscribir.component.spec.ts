import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuscribirComponent } from './suscribir.component';

describe('SuscribirComponent', () => {
  let component: SuscribirComponent;
  let fixture: ComponentFixture<SuscribirComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SuscribirComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SuscribirComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
