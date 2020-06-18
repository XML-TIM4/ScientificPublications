import { Component, OnInit } from '@angular/core';
import {Paper} from '../model/paper.model';
import {PaperService} from "../services/paper.service";
import {ReviewService} from "../services/review.service";

@Component({
  selector: 'app-all-reviews',
  templateUrl: './all-reviews.component.html',
  styleUrls: ['./all-reviews.component.scss']
})
export class AllReviewsComponent implements OnInit {
  theHtmlString: any;
  papers: Paper[] = [];

  constructor(private paperService: PaperService, private reviewService: ReviewService) { }

  ngOnInit() {
    this.reviewService.searchPapersFinishedReviews().subscribe((resData => {

      this.papers = [];
      if (!resData.resultIds.length) {
        for (let i = 0; i < resData.resultIds.length; i++) {
          this.paperService.findOne(resData[i], 'application/xml').subscribe((resPaper => {
            const parser = new DOMParser();
            const xmlDoc = parser.parseFromString(resPaper, 'application/xml');

            this.papers.push({
              id: resData[i],
              title: xmlDoc.getElementsByTagName('title')[0].childNodes[0].nodeValue,
              category: xmlDoc.getElementsByTagName('category')[0].childNodes[0].nodeValue,
              date: xmlDoc.getElementsByTagName('received')[0].childNodes[0].nodeValue,
              author: xmlDoc.getElementsByTagName('first-name')[0].childNodes[0].nodeValue + ' ' + xmlDoc.getElementsByTagName('last-name')[0].childNodes[0].nodeValue});
          }));
        }
      }
    }));

  }

  review(id: any) {
    this.reviewService.findByPaper(id).subscribe((letterId => {
      this.reviewService.findOne(letterId, 'text/html').subscribe((resData => {
        this.theHtmlString = resData;
      }));
    }));
  }
}
