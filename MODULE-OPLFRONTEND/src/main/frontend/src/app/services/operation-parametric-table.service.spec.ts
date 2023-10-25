import { TestBed } from '@angular/core/testing';
import { OperationParametricTable } from './operation-parametric-table.service';

describe('OperationParametricTable', () => {
    beforeEach(() => TestBed.configureTestingModule({}));

    it('should be created', () => {
        const service: OperationParametricTable = TestBed.get(OperationParametricTable);
        expect(service).toBeTruthy();
    });
});