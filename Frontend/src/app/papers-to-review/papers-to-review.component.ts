import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Paper} from '../model/paper.model';

@Component({
  selector: 'app-papers-to-review',
  templateUrl: './papers-to-review.component.html',
  styleUrls: ['./papers-to-review.component.scss']
})
export class PapersToReviewComponent implements OnInit {

  searchForm: FormGroup;
  papers: Paper[] = [];

  constructor() { }

  ngOnInit() {
    this.searchForm = new FormGroup({
      title: new FormControl(''),
      category: new FormControl(''),
      startDate: new FormControl(null),
      endDate: new FormControl(null),
    });
    this.papers[0] = {
      title: 'Test Person 2',
      category: 'Section 2',
      date: '87654321'
    };
  }

  onSubmit() {

  }
}
