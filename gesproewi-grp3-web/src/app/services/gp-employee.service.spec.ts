import {TestBed} from '@angular/core/testing';

import {GpEmployeeService} from './gp-employee.service';

describe('GpEmployeeService', () => {
  let service: GpEmployeeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GpEmployeeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
