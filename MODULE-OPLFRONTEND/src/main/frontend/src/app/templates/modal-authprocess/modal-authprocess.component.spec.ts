import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalAuthprocessComponent } from './modal-authprocess.component';

describe('ModalAuthprocessComponent', () => {
  let component: ModalAuthprocessComponent;
  let fixture: ComponentFixture<ModalAuthprocessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalAuthprocessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalAuthprocessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
