import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import axios from "axios";

@Injectable({
  providedIn: 'root'
})
export class MetadataService {

  constructor(private http: HttpClient) { }

  extractMetadata(type: string, value: string) {
    //
    // axios({
    //   method: 'post',
    //   url: 'http://rdf-translator.appspot.com/convert/rdfa/' + type + '/content',
    //   data: {
    //     content: value
    //   },
    //   responseType: 'text',
    //   headers: {
    //     'Content-Type': 'multipart/form-data',
    //     'Response-Type': 'text'
    //   }
    // }).then(function (response) {
    //   console.log(response.data);
    // });

    return this.http.post('http://rdf-translator.appspot.com/convert/rdfa/' + type + '/content', {'content': value} , {headers: new HttpHeaders({'Content-Type': 'multipart/form-data', 'skip': 'true', 'Response-Type': 'text'}), responseType: 'text'});
  }


}
