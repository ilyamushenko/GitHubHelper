import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscribeRepComponent } from './subscribe-rep.component';

describe('SubscribeRepComponent', () => {
  let component: SubscribeRepComponent;
  let fixture: ComponentFixture<SubscribeRepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubscribeRepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubscribeRepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
