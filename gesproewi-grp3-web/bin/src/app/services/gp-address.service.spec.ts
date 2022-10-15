import {TestBed} from '@angular/core/testing';

import {GpAddressService} from './gp-address.service';

describe('GpAddressService', () => {
  let service: GpAddressService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GpAddressService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
