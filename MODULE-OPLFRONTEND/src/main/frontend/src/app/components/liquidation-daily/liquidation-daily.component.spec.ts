import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LiquidationDailyComponent } from './liquidation-daily.component';

describe('LiquidationDailyComponent', () => {
  let component: LiquidationDailyComponent;
  let fixture: ComponentFixture<LiquidationDailyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LiquidationDailyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LiquidationDailyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
