import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCoverLetterComponent } from './edit-cover-letter.component';

describe('EditCoverLetterComponent', () => {
  let component: EditCoverLetterComponent;
  let fixture: ComponentFixture<EditCoverLetterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditCoverLetterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCoverLetterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
