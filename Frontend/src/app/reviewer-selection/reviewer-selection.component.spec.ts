import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewerSelectionComponent } from './reviewer-selection.component';

describe('ReviewerSelectionComponent', () => {
  let component: ReviewerSelectionComponent;
  let fixture: ComponentFixture<ReviewerSelectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewerSelectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewerSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
