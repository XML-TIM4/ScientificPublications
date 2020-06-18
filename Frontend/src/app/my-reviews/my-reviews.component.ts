import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Paper} from '../model/paper.model';
import {PaperService} from "../services/paper.service";
import {ReviewService} from "../services/review.service";

@Component({
  selector: 'app-my-reviews',
  templateUrl: './my-reviews.component.html',
  styleUrls: ['./my-reviews.component.scss']
})
export class MyReviewsComponent implements OnInit {

  papers: Paper[] = [];
  theHtmlString: any;

  constructor(private paperService: PaperService, private reviewService: ReviewService) { }

  ngOnInit() {
    this.reviewService.searchPapersWReviews().subscribe((resData => {

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
        }));
      }
      }
    }));
  }

  view(id: string) {
    this.paperService.findOne(id, 'text/html').subscribe((resData => {
      this.theHtmlString = resData;
      setTimeout(function() { document.getElementsByTagName('table')[0].remove(); }, 1000);
    }));
  }
}
