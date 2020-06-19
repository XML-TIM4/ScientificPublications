import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

export interface IRequestPapers {
  resultIds: [];
}


@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) { }


  findOne(reviewId: string, type: string) {
    return this.http.get('http://localhost:8080/api/reviews/' + reviewId, {headers: new HttpHeaders({'Content-Type': type, Accept: type, 'Response-Type': 'text'}), responseType: 'text'});
  }

  findOneBlob(reviewId: string, type: string) {
    return this.http.get('http://localhost:8080/api/reviews/' + reviewId, {headers: new HttpHeaders({'Content-Type': type, Accept: type, 'Response-Type': 'blob'}), responseType: 'blob'});
  }
  findByPaper(paperId: string) {
    return this.http.get('http://localhost:8080/api/reviews/paper/' + paperId, {headers: new HttpHeaders({'Content-Type': 'text/plain', 'Accept': 'text/plain', 'Response-Type': 'text/plain'}), responseType: 'text'});
  }

  searchPapersWReviews() {
    return this.http.get<IRequestPapers>('http://localhost:8080/api/business-process/own');
  }

  searchPapersFinishedReviews() {
    return this.http.get<IRequestPapers>('http://localhost:8080/api/business-process/finished');
  }

}
