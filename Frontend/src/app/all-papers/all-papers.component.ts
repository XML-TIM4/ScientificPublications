import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Paper} from '../model/paper.model';
import {IPaperSearch, PaperService} from '../services/paper.service';
import * as FileSaver from "file-saver";

@Component({
  selector: 'app-all-papers',
  templateUrl: './all-papers.component.html',
  styleUrls: ['./all-papers.component.scss']
})
export class AllPapersComponent implements OnInit {
  searchForm: FormGroup;
  papers: Paper[] = [];
  paperStatus: string[] = ['ACCEPTED', 'REJECTED', 'REVISION', 'WITHDRAWN', 'UPLOADED'];
  paperCategory: string[] = ['RESEARCH_PAPER', 'VIEWPOINT', 'TECHNICAL_PAPER', 'CONCEPTUAL_PAPER', 'CASE_STUDY', 'LITERATURE_REVIEW', 'GENERAL_REVIEW'];
  isBasic: boolean;
  ownPapers: string[] = [];
  otherPapers: string[] = [];

  constructor(private paperService: PaperService) { }

  ngOnInit() {


    this.searchForm = new FormGroup({
      basic: new FormControl(''),
      text: new FormControl(''),
      keywords: new FormControl(''),
      version: new FormControl(''),
      category: new FormControl(''),
      status: new FormControl(''),
      revised: new FormControl(null),
      received: new FormControl(null),
      accepted: new FormControl(null),
    });

  }

  onSubmit() {

    const keywordz = this.searchForm.get('keywords').value.toString().split(',');
    console.log(this.searchForm.get('basic').value, ' BAZIK');
    const searchParams: IPaperSearch = {
      basic: this.searchForm.get('basic').value,
      text: this.searchForm.get('text').value,
      revised: this.searchForm.get('revised').value !== null ? this.searchForm.get('revised').value.getTime() : null,
      received: this.searchForm.get('received').value !== null ? this.searchForm.get('received').value.getTime() : null,
      accepted: this.searchForm.get('accepted').value !== null ? this.searchForm.get('accepted').value.getTime() : null,
      version: this.searchForm.get('version').value,
      status: this.searchForm.get('status').value !== '' ? this.searchForm.get('status').value : 'ACCEPTED',
      category: this.searchForm.get('category').value !== '' ? this.searchForm.get('category').value : 'RESEARCH_PAPER',
      keywords: keywordz,
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
             author: xmlDoc.getElementsByTagName('first-name')[0].childNodes[0].nodeValue + ' ' + xmlDoc.getElementsByTagName('last-name')[0].childNodes[0].nodeValue});
         }));
      }

      for (let i = 0; i < resData.otherPaperIds.length; i++) {
        this.paperService.findOne(resData.otherPaperIds[i], 'application/xml').subscribe((resPaper => {
          console.log(resPaper);
          const parser = new DOMParser();
          const xmlDoc = parser.parseFromString(resPaper, 'application/xml');

          this.papers.push({
            id: resData.otherPaperIds[i],
            title: xmlDoc.getElementsByTagName('title')[0].childNodes[0].nodeValue,
            category: xmlDoc.getElementsByTagName('category')[0].childNodes[0].nodeValue,
            date: xmlDoc.getElementsByTagName('received')[0].childNodes[0].nodeValue,
            author: xmlDoc.getElementsByTagName('first-name')[0].childNodes[0].nodeValue + ' ' + xmlDoc.getElementsByTagName('last-name')[0].childNodes[0].nodeValue});
        }));
      }

    //
    //   for (let i = 0; i < resData.ownPaperIds.length; i++) {
    //     this.paperService.findOnePdf(resData.ownPaperIds[i]).subscribe((resPaper => {
    //
    //       const filename = 'filename.pdf';
    //       FileSaver.saveAs(resPaper, filename);
    //     }));
    //   }

    }));

  }
}
