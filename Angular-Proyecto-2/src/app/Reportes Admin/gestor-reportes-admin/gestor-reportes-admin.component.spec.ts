import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestorReportesAdminComponent } from './gestor-reportes-admin.component';

describe('GestorReportesAdminComponent', () => {
  let component: GestorReportesAdminComponent;
  let fixture: ComponentFixture<GestorReportesAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GestorReportesAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestorReportesAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
