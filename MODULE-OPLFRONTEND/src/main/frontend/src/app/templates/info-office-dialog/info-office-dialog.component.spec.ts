import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoOfficeDialogComponent } from './info-office-dialog.component';

describe('InfoOfficeDialogComponent', () => {
  let component: InfoOfficeDialogComponent;
  let fixture: ComponentFixture<InfoOfficeDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfoOfficeDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoOfficeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
