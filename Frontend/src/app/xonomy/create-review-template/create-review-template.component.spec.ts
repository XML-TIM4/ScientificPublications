import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateReviewTemplateComponent } from './create-review-template.component';

describe('CreateReviewTemplateComponent', () => {
  let component: CreateReviewTemplateComponent;
  let fixture: ComponentFixture<CreateReviewTemplateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateReviewTemplateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateReviewTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
