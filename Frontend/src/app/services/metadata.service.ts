import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MetadataService {

  constructor(private http: HttpClient) { }

  extractMetadata(id: string, type: string) {
    return this.http.get('http://localhost:8080/api/scientific-papers/' + id + '/' + type, {headers: new HttpHeaders({'Content-Type': 'text/plain', Accept: 'text/plain', 'Response-Type': 'text/plain'}), responseType: 'text'});
  }


}
