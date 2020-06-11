import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewerSectionComponent } from './reviewer-section.component';

describe('ReviewerSectionComponent', () => {
  let component: ReviewerSectionComponent;
  let fixture: ComponentFixture<ReviewerSectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewerSectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewerSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
