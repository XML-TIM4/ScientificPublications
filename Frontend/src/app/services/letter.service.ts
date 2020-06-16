import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {map, take} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class LetterService {

  constructor(private http: HttpClient ) { }

  findOne(letterId: string, type: string) {
    return this.http.get('http://localhost:8080/api/cover-letters/' + letterId, {headers: new HttpHeaders({'Content-Type': type, 'Accept': type, 'Response-Type': 'text'}), responseType: 'text'});

  }

  findByPaper(paperId: string) {
    return this.http.get('http://localhost:8080/api/cover-letters/paper/' + paperId, {headers: new HttpHeaders({'Content-Type': 'text/plain', 'Accept': 'text/plain', 'Response-Type': 'text/plain'}), responseType: 'text'});
  }

}