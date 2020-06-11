import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaperSectionComponent } from './paper-section.component';

describe('PaperSectionComponent', () => {
  let component: PaperSectionComponent;
  let fixture: ComponentFixture<PaperSectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaperSectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaperSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
