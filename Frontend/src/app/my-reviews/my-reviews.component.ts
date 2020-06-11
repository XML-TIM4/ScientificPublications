import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Paper} from '../model/paper.model';

@Component({
  selector: 'app-my-reviews',
  templateUrl: './my-reviews.component.html',
  styleUrls: ['./my-reviews.component.scss']
})
export class MyReviewsComponent implements OnInit {


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
  }

  onSubmit() {

  }
}
