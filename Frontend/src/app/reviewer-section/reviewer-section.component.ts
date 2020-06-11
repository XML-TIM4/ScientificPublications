import {Component, Input, OnInit} from '@angular/core';
import {Paper} from '../model/paper.model';

@Component({
  selector: 'app-reviewer-section',
  templateUrl: './reviewer-section.component.html',
  styleUrls: ['./reviewer-section.component.scss']
})
export class ReviewerSectionComponent implements OnInit {
  @Input()
  reviewer: Paper;
  constructor() { }

  ngOnInit() {
  }

}
