import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserListByOfficeComponent } from './user-list-by-office.component';

describe('UserListByOfficeComponent', () => {
  let component: UserListByOfficeComponent;
  let fixture: ComponentFixture<UserListByOfficeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserListByOfficeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserListByOfficeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
