import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

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

}
