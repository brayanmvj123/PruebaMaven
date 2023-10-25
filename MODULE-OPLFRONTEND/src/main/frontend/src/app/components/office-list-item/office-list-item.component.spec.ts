import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OfficeListItemComponent } from './office-list-item.component';

describe('OfficeListItemComponent', () => {
  let component: OfficeListItemComponent;
  let fixture: ComponentFixture<OfficeListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OfficeListItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OfficeListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
