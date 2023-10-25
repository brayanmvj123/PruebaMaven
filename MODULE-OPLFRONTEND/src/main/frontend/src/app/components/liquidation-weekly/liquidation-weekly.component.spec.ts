import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LiquidationWeeklyComponent } from './liquidation-weekly.component';

describe('LiquidationWeeklyComponent', () => {
  let component: LiquidationWeeklyComponent;
  let fixture: ComponentFixture<LiquidationWeeklyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LiquidationWeeklyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LiquidationWeeklyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
