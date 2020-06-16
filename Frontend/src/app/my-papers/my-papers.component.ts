import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Paper} from '../model/paper.model';
import {IPaperSearch, PaperService} from '../services/paper.service';
import {EditPaperComponent} from '../xonomy/edit-paper/edit-paper.component';

@Component({
  selector: 'app-my-papers',
  templateUrl: './my-papers.component.html',
  styleUrls: ['./my-papers.component.scss']
})
export class MyPapersComponent implements OnInit {

  searchForm: FormGroup;
  papers: Paper[] = [];
  myPapersComponent = '';

  constructor(private paperService: PaperService) { }

  ngOnInit() {
    const searchParams: IPaperSearch = {
      basic: true,
      text: '',
      revised: null,
      received: null,
      accepted: null,
      version: '',
      status: 'ACCEPTED',
      category: 'RESEARCH_PAPER',
      keywords: [],
    };

    this.paperService.search(searchParams).subscribe((resData => {

      this.papers = [];
      for (let i = 0; i < resData.ownPaperIds.length; i++) {
        this.paperService.findOne(resData.ownPaperIds[i], 'application/xml').subscribe((resPaper => {
          const parser = new DOMParser();
          const xmlDoc = parser.parseFromString(resPaper, 'application/xml');

          this.papers.push({
            id: resData.ownPaperIds[i],
            title: xmlDoc.getElementsByTagName('title')[0].childNodes[0].nodeValue,
            category: xmlDoc.getElementsByTagName('category')[0].childNodes[0].nodeValue,
            date: xmlDoc.getElementsByTagName('received')[0].childNodes[0].nodeValue,
            author: xmlDoc.getElementsByTagName('first-name')[0].childNodes[0].nodeValue + ' ' + xmlDoc.getElementsByTagName('last-name')[0].childNodes[0].nodeValue
          });
        }));
      }


    }));

  }

  call(id: string) {
    this.paperService.findOne(id, 'application/xml').subscribe((resPaper => {
      this.myPapersComponent = resPaper;
    }));
  }

  cancel(id: string) {
    const temp = this.papers;
    this.papers = [];
    for(let i = 0; i < temp.length; i++) {
      if (temp[i].id !== id) {
        this.papers.push(temp[i]);
      }
    }
    this.paperService.withdraw(id);

  }
}
