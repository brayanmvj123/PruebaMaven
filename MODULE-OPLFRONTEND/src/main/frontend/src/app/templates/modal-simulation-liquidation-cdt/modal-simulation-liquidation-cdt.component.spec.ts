import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalSimulationLiquidationCdtComponent } from './modal-simulation-liquidation-cdt.component';

describe('ModalSimulationLiquidationCdtComponent', () => {
  let component: ModalSimulationLiquidationCdtComponent;
  let fixture: ComponentFixture<ModalSimulationLiquidationCdtComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalSimulationLiquidationCdtComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalSimulationLiquidationCdtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
