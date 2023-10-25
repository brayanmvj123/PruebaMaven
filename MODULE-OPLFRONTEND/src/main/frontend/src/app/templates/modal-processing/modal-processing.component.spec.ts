import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalProcessingComponent } from './modal-processing.component';

describe('ModalProcessingComponent', () => {
  let component: ModalProcessingComponent;
  let fixture: ComponentFixture<ModalProcessingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalProcessingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalProcessingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
