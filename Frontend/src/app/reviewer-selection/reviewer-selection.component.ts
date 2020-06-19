import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Paper} from '../model/paper.model';

@Component({
  selector: 'app-reviewer-selection',
  templateUrl: './reviewer-selection.component.html',
  styleUrls: ['./reviewer-selection.component.scss']
})
export class ReviewerSelectionComponent implements OnInit {

  reviewers: Paper[] = [];

  constructor() { }

  ngOnInit() {

  }

  onSubmit() {

  }

}
