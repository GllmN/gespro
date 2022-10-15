import {ComponentFixture, TestBed} from '@angular/core/testing';

import {GpEmployeesComponent} from './gp-employees.component';

describe('GpEmployeesComponent', () => {
  let component: GpEmployeesComponent;
  let fixture: ComponentFixture<GpEmployeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GpEmployeesComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GpEmployeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
