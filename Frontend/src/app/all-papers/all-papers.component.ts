import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Paper} from '../model/paper.model';

@Component({
  selector: 'app-all-papers',
  templateUrl: './all-papers.component.html',
  styleUrls: ['./all-papers.component.scss']
})
export class AllPapersComponent implements OnInit {
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
