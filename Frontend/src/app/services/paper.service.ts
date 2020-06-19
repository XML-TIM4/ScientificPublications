import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaderResponse, HttpHeaders, HttpParams, HttpRequest} from '@angular/common/http';

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


  searchEditor(paper: IPaperSearch) {
    return this.http.post<IPaperResults>('http://localhost:8080/api/scientific-papers/search-editor',
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
    return this.http.get('http://localhost:8080/api/scientific-papers/' + paperId, {headers: new HttpHeaders({'Content-Type': type, Accept: type, 'Response-Type': 'text'}), responseType: 'text'});
  }

  findOneBlob(paperId: string, type: string) {
    return this.http.get('http://localhost:8080/api/scientific-papers/' + paperId, {headers: new HttpHeaders({'Content-Type': type, Accept: type, 'Response-Type': 'blob'}), responseType: 'blob'});
  }

  withdraw(paperId: string) {
    return this.http.delete('http://localhost:8080/api/scientific-papers/' + paperId);
  }

  revise(paperId: string, xmlString: string) {
    return this.http.put('http://localhost:8080/api/scientific-papers/' + paperId, xmlString, {headers: new HttpHeaders({'Response-Type': 'text'}), responseType: 'text'});
  }

  create(xmlString: string) {
    return this.http.post('http://localhost:8080/api/scientific-papers', xmlString, {headers: new HttpHeaders({'Content-Type': 'text/xml', Accept: 'text/xml', 'Response-Type': 'text'}), responseType: 'text'});
  }

  decideForPaper(id: string, decision: string) {
    const fd = new FormData();
    fd.append('decision', decision);
    return this.http.post('http://localhost:8080/api/scientific-papers/' + id, fd);
  }

}
