import { Component, OnInit } from '@angular/core';
import {Paper} from '../model/paper.model';
import {PaperService} from '../services/paper.service';
import {ReviewService} from '../services/review.service';
import * as FileSaver from 'file-saver';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-all-reviews',
  templateUrl: './all-reviews.component.html',
  styleUrls: ['./all-reviews.component.scss']
})
export class AllReviewsComponent implements OnInit {
  theHtmlString: any;
  papers: Paper[] = [];
  downloadForm: FormGroup;
  paperTypes: string[] = ['html', 'pdf'];
  paperToreview: Map<string, string>;
  decideForm: FormGroup;
  answerTypes: string[] = ['ACCEPTED', 'REJECTED', 'REVISION', 'WITHDRAWN', 'UPLOADED'];


  constructor(private paperService: PaperService, private reviewService: ReviewService) { }

  ngOnInit() {

    this.downloadForm = new FormGroup({
      id: new FormControl(''),
      paperType: new FormControl(''),
    });

    this.decideForm = new FormGroup({
      id: new FormControl(''),
      answerType: new FormControl(''),
    });

    this.paperToreview = new Map<string, string>();

    this.reviewService.searchPapersFinishedReviews().subscribe((resData => {

      this.papers = [];
      if (!resData.resultIds.length) {
      } else {
        for (let i = 0; i < resData.resultIds.length; i++) {
          this.paperService.findOne(resData.resultIds[i], 'application/xml').subscribe((resPaper => {
            const parser = new DOMParser();
            const xmlDoc = parser.parseFromString(resPaper, 'application/xml');

            this.papers.push({
              id: resData.resultIds[i],
              title: xmlDoc.getElementsByTagName('title')[0].childNodes[0].nodeValue,
              category: xmlDoc.getElementsByTagName('category')[0].childNodes[0].nodeValue,
              date: xmlDoc.getElementsByTagName('received')[0].childNodes[0].nodeValue,
              author: xmlDoc.getElementsByTagName('first-name')[0].childNodes[0].nodeValue + ' ' + xmlDoc.getElementsByTagName('last-name')[0].childNodes[0].nodeValue});
            this.reviewService.findByPaper(resData.resultIds[i]).subscribe((reviewId => {
              this.paperToreview.set(resData.resultIds[i], reviewId);
            }));
          }));
        }
      }
    }));

  }

  review(id: string) {
    this.reviewService.findByPaper(id).subscribe((reveiwId => {
      this.reviewService.findOne(reveiwId, 'text/html').subscribe((resData => {
        console.log(resData);
        this.theHtmlString = resData;
      }));
    }));
  }


  onSubmitDownload() {
    if (this.downloadForm.get('paperType').value === 'html') {
      this.reviewService.findOneBlob(this.paperToreview.get(this.downloadForm.get('id').value), 'text/html').subscribe((resBlob => {
        const filename = 'filename.html';
        FileSaver.saveAs(resBlob, filename);
      }));
    } else {
      this.reviewService.findOneBlob(this.paperToreview.get(this.downloadForm.get('id').value), 'application/pdf').subscribe((resBlob => {
        const filename = 'filename.pdf';
        FileSaver.saveAs(resBlob, filename);
      }));
    }
  }


  onSubmitDecide() {
      this.paperService.decideForPaper(this.decideForm.get('id').value, this.decideForm.get('answerType').value).subscribe((resData => {
        const id = this.decideForm.get('id').value;
        const temp = this.papers;
        this.papers = [];
        for (let i = 0; i < temp.length; i++) {
          if (temp[i].id !== id) {
            this.papers.push(temp[i]);
          }
        }
        console.log(resData);
      }));
  }
}
