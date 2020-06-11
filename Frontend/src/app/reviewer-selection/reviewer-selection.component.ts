import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Paper} from '../model/paper.model';

@Component({
  selector: 'app-reviewer-selection',
  templateUrl: './reviewer-selection.component.html',
  styleUrls: ['./reviewer-selection.component.scss']
})
export class ReviewerSelectionComponent implements OnInit {

  searchForm: FormGroup;
  reviewers: Paper[] = [];

  constructor() { }

  ngOnInit() {
    this.searchForm = new FormGroup({
      title: new FormControl(''),
      category: new FormControl('')
    });
  }

  onSubmit() {

  }

}
