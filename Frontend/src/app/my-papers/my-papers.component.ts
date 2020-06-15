import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Paper} from '../model/paper.model';
import {IPaperSearch, PaperService} from "../services/paper.service";

@Component({
  selector: 'app-my-papers',
  templateUrl: './my-papers.component.html',
  styleUrls: ['./my-papers.component.scss']
})
export class MyPapersComponent implements OnInit {

  searchForm: FormGroup;
  papers: Paper[] = [];

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
          const text = '' + resPaper + '';
          console.log(text);
          const parser = new DOMParser();
          const xmlDoc = parser.parseFromString(text, 'application/xml');

          this.papers.push({
            title: xmlDoc.getElementsByTagName('title')[0].childNodes[0].nodeValue,
            category: xmlDoc.getElementsByTagName('category')[0].childNodes[0].nodeValue,
            date: xmlDoc.getElementsByTagName('received')[0].childNodes[0].nodeValue,
            author: xmlDoc.getElementsByTagName('first-name')[0].childNodes[0].nodeValue + ' ' + xmlDoc.getElementsByTagName('last-name')[0].childNodes[0].nodeValue
          });
        }));
      }


    }));

  }

}
