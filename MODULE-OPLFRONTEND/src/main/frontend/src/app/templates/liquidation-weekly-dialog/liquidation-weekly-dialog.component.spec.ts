import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LiquidationWeeklyDialogComponent } from './liquidation-weekly-dialog.component';

describe('LiquidationWeeklyDialogComponent', () => {
  let component: LiquidationWeeklyDialogComponent;
  let fixture: ComponentFixture<LiquidationWeeklyDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LiquidationWeeklyDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LiquidationWeeklyDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
