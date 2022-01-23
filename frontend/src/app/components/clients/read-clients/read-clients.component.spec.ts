import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReadClientsComponent } from './read-clients.component';

describe('ReadClientsComponent', () => {
  let component: ReadClientsComponent;
  let fixture: ComponentFixture<ReadClientsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReadClientsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReadClientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
