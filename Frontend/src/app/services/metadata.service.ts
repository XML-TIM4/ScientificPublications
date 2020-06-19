import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MetadataService {

  constructor(private http: HttpClient) { }

  extractMetadata(type: string, value: string) {
    return this.http.post('http://rdf-translator.appspot.com/convert/rdfa/' + type + '/content', {'content': value} , {headers: new HttpHeaders({'Content-Type': 'multipart/form-data', 'skip': 'true', 'Response-Type': 'text'}), responseType: 'text'});
  }


}
