import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OfficeListRegistrationComponent } from './office-list-registration.component';

describe('OfficeListRegistrationComponent', () => {
  let component: OfficeListRegistrationComponent;
  let fixture: ComponentFixture<OfficeListRegistrationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OfficeListRegistrationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OfficeListRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
