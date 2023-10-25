import { TestBed } from '@angular/core/testing';

import { OfficeListService } from './office-list.service';

describe('OfficeListService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OfficeListService = TestBed.get(OfficeListService);
    expect(service).toBeTruthy();
  });
});
