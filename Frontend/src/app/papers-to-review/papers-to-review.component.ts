import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Paper} from '../model/paper.model';
import {IPaperSearch, PaperService} from '../services/paper.service';
import {LetterService} from '../services/letter.service';
import {ReviewService} from '../services/review.service';
import * as FileSaver from 'file-saver';

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
  theHtmlString: any;
  downloadForm: FormGroup;
  paperTypes: string[] = ['html', 'pdf'];
  paperToletter: Map<string, string>;

  constructor(private paperService: PaperService, private letterService: LetterService, private reviewService: ReviewService) { }

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

    this.downloadForm = new FormGroup({
      id: new FormControl(''),
      paperType: new FormControl(''),
    });

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

    this.paperToletter = new Map<string, string>();

    this.reviewService.searchPapersFinishedReviews().subscribe((rewData => {


      this.paperService.searchEditor(searchParams).subscribe((resData => {

        this.papers = [];
        for (let i = 0; i < resData.otherPaperIds.length; i++) {

          let pass = true;
          this.letterService.findByPaper(resData.otherPaperIds[i]).subscribe(( letterId => {

            if (letterId !== '') {
              if (!rewData.resultIds.length) {

              } else {
                for (let l = 0; l < rewData.resultIds.length; l++) {
                  if (rewData.resultIds[l] === resData.otherPaperIds[i]) {
                    pass = false;
                  }
                }
              }

              if (pass) {
                this.paperToletter.set(resData.otherPaperIds[i].toString(), letterId);
                this.paperService.findOne(resData.otherPaperIds[i].toString(), 'application/xml').subscribe((resPaper => {
                  const parser = new DOMParser();
                  const xmlDoc = parser.parseFromString(resPaper, 'application/xml');

                  this.papers.push({
                    id: resData.otherPaperIds[i],
                    title: xmlDoc.getElementsByTagName('title')[0].childNodes[0].nodeValue,
                    category: xmlDoc.getElementsByTagName('category')[0].childNodes[0].nodeValue,
                    date: xmlDoc.getElementsByTagName('received')[0].childNodes[0].nodeValue,
                    author: xmlDoc.getElementsByTagName('first-name')[0].childNodes[0].nodeValue + ' ' + xmlDoc.getElementsByTagName('last-name')[0].childNodes[0].nodeValue
                  });
                }));
              }
            }
          }));

        }

      }));

    }));


  }

  onSubmit() {

    const keywordz = this.searchForm.get('keywords').value.toString().split(',');
    console.log(this.searchForm.get('basic').value, ' BAZIK');
    const searchParams: IPaperSearch = {
      basic: this.searchForm.get('basic').value !== null ? this.searchForm.get('basic').value : false,
      text: this.searchForm.get('text').value,
      revised: this.searchForm.get('revised').value !== null ? this.searchForm.get('revised').value.getTime() : null,
      received: this.searchForm.get('received').value !== null ? this.searchForm.get('received').value.getTime() : null,
      accepted: this.searchForm.get('accepted').value !== null ? this.searchForm.get('accepted').value.getTime() : null,
      version: this.searchForm.get('version').value !== '' ? this.searchForm.get('version').value : null,
      status: this.searchForm.get('status').value !== '' ? this.searchForm.get('status').value : null,
      category: this.searchForm.get('category').value !== '' ? this.searchForm.get('category').value : null,
      keywords: keywordz[0] !== '' ? keywordz : [],
    };

    this.reviewService.searchPapersFinishedReviews().subscribe((rewData => {


      this.paperService.searchEditor(searchParams).subscribe((resData => {

      this.papers = [];
      for (let i = 0; i < resData.otherPaperIds.length; i++) {

        let pass = true;
        this.letterService.findByPaper(resData.otherPaperIds[i]).subscribe(( letterId => {

          if (letterId !== '') {
            if (!rewData.resultIds.length) {

            } else {
              for (let l = 0; l < rewData.resultIds.length; l++) {
                if (rewData.resultIds[l] === resData.otherPaperIds[i]) {
                  pass = false;
                }
              }
            }

            if (pass) {
            this.paperService.findOne(resData.otherPaperIds[i].toString(), 'application/xml').subscribe((resPaper => {
              const parser = new DOMParser();
              const xmlDoc = parser.parseFromString(resPaper, 'application/xml');

              this.papers.push({
                id: resData.otherPaperIds[i],
                title: xmlDoc.getElementsByTagName('title')[0].childNodes[0].nodeValue,
                category: xmlDoc.getElementsByTagName('category')[0].childNodes[0].nodeValue,
                date: xmlDoc.getElementsByTagName('received')[0].childNodes[0].nodeValue,
                author: xmlDoc.getElementsByTagName('first-name')[0].childNodes[0].nodeValue + ' ' + xmlDoc.getElementsByTagName('last-name')[0].childNodes[0].nodeValue
              });
            }));
              }
          }
        }));

      }


    }));

    }));

  }

  view(id: string) {
      this.paperService.findOne(id, 'text/html').subscribe((resData => {
        this.theHtmlString = resData;
      }));
  }

  viewLetter(id: string) {
      this.letterService.findByPaper(id).subscribe((letterId => {
        this.letterService.findOne(letterId, 'text/html').subscribe((resData => {
          this.theHtmlString = resData;
        }));
      }));
  }

  onSubmitDownload() {
    if (this.downloadForm.get('paperType').value === 'html') {
      this.letterService.findOneBlob(this.paperToletter.get(this.downloadForm.get('id').value), 'text/html').subscribe((resBlob => {
        const filename = 'filename.html';
        FileSaver.saveAs(resBlob, filename);
      }));
    } else {
      this.letterService.findOneBlob(this.paperToletter.get(this.downloadForm.get('id').value), 'application/pdf').subscribe((resBlob => {
        const filename = 'filename.pdf';
        FileSaver.saveAs(resBlob, filename);
      }));
    }
  }


}
