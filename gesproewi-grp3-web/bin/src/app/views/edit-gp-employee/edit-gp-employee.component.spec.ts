import {ComponentFixture, TestBed} from '@angular/core/testing';

import {EditGpEmployeeComponent} from './edit-gp-employee.component';

describe('EditGpEmployeeComponent', () => {
  let component: EditGpEmployeeComponent;
  let fixture: ComponentFixture<EditGpEmployeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditGpEmployeeComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditGpEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
