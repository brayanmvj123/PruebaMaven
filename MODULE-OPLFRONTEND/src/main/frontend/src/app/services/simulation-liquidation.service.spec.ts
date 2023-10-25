import { TestBed } from '@angular/core/testing';

import { SimulationLiquidationService } from './simulation-liquidation.service';

describe('SimulationLiquidationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SimulationLiquidationService = TestBed.get(SimulationLiquidationService);
    expect(service).toBeTruthy();
  });
});
