import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Paper} from '../model/paper.model';
import {IPaperSearch, PaperService} from '../services/paper.service';

@Component({
  selector: 'app-papers-to-review',
  templateUrl: './papers-to-review.component.html',
  styleUrls: ['./papers-to-review.component.scss']
})
export class PapersToReviewComponent implements OnInit {

  searchForm: FormGroup;
  papers: Paper[] = [];
  paperStatus: string[] = ['ACCEPTED', 'REJECTED', 'REVISION', 'WITHDRAWN', 'UPLOADED'];
  paperCategory: string[] = ['RESEARCH_PAPER', 'VIEWPOINT', 'TECHNICAL_PAPER', 'CONCEPTUAL_PAPER', 'CASE_STUDY', 'LITERATURE_REVIEW', 'GENERAL_REVIEW'];


  constructor(private paperService: PaperService) { }

  ngOnInit() {
    this.searchForm = new FormGroup({
      text: new FormControl(''),
      keywords: new FormControl(''),
      version: new FormControl(''),
      category: new FormControl(''),
      status: new FormControl(''),
      revised: new FormControl(null),
      received: new FormControl(null),
      accepted: new FormControl(null),
    });
    this.papers[0] = {
      id: '142',
      title: 'Test Person 2',
      category: 'Section 2',
      date: '87654321',
      author: 'Covek covek'
    };
  }

  onSubmit() {

    const keywordz = this.searchForm.get('keywords').value.toString().split(',');
    console.log(keywordz, '  OVO SU KEYWORDZZ');
    const searchParams: IPaperSearch = {
      basic: false,
      text: this.searchForm.get('text').value,
      revised: this.searchForm.get('revised').value !== null ? this.searchForm.get('revised').value.getTime() : null,
      received: this.searchForm.get('received').value !== null ? this.searchForm.get('received').value.getTime() : null,
      accepted: this.searchForm.get('accepted').value !== null ? this.searchForm.get('accepted').value.getTime() : null,
      version: this.searchForm.get('version').value,
      status: this.searchForm.get('status').value !== '' ? this.searchForm.get('status').value : 'ACCEPTED',
      category: this.searchForm.get('category').value !== '' ? this.searchForm.get('category').value : 'RESEARCH_PAPER',
      keywords: keywordz,
    };

    this.paperService.search(searchParams).subscribe((resData =>{
      console.log(resData);
    }));

  }

}
