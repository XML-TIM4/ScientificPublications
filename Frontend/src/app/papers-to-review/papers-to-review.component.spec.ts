import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PapersToReviewComponent } from './papers-to-review.component';

describe('PapersToReviewComponent', () => {
  let component: PapersToReviewComponent;
  let fixture: ComponentFixture<PapersToReviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PapersToReviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PapersToReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
