import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaderResponse, HttpHeaders, HttpRequest} from "@angular/common/http";

export interface IPaperSearch {
  basic: boolean;
  text: string;
  revised: Date;
  received: Date;
  accepted: Date;
  version: string;
  status: string;
  category: string;
  keywords: string[];
}

export interface IPaperResults {
  ownPaperIds: string[];
  otherPaperIds: string[];
}


@Injectable({
  providedIn: 'root'
})
export class PaperService {
  httpResponse: HttpHeaderResponse;

  constructor(private http: HttpClient ) {}

  search(paper: IPaperSearch) {
    return this.http.post<IPaperResults>('http://localhost:8080/api/scientific-papers/search',
      {
        basic: paper.basic,
        text: paper.text,
        revised: paper.revised,
        received: paper.received,
        accepted: paper.accepted,
        version: paper.version,
        status: paper.status,
        category: paper.category,
        keywords: paper.keywords
      });
  }

  findOne(paperId: string, type: string) {
    // const httpOptions = {
    //   headers: new HttpHeaders({
    //     'Content-Type':  type,
    //     'Accept':  type,
    //     'Response-Type': 'text'
    //   }),
    //   responseType: 'text'
    // };

    return this.http.get('http://localhost:8080/api/scientific-papers/' + paperId, {headers: new HttpHeaders({'Content-Type': type, 'Accept': type, 'Response-Type': 'text'}), responseType: 'text'});

  }

}
