import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Paper} from '../model/paper.model';
import {IPaperSearch, PaperService} from '../services/paper.service';
import * as FileSaver from 'file-saver';
import {MetadataService} from '../services/metadata.service';
import {UpperCasePipe} from '@angular/common';

@Component({
  selector: 'app-all-papers',
  templateUrl: './all-papers.component.html',
  styleUrls: ['./all-papers.component.scss']
})
export class AllPapersComponent implements OnInit {
  searchForm: FormGroup;
  papers: Paper[] = [];
  paperStatus: string[] = ['ACCEPTED', 'REJECTED', 'REVISION', 'WITHDRAWN', 'UPLOADED'];
  paperCategory: string[] = ['RESEARCH_PAPER', 'VIEWPOINT', 'TECHNICAL_PAPER', 'CONCEPTUAL_PAPER', 'CASE_STUDY', 'LITERATURE_REVIEW', 'GENERAL_REVIEW'];
  downloadForm: FormGroup;
  metaForm: FormGroup;
  paperTypes: string[] = ['html', 'pdf'];
  paperMetas: string[] = ['nt', 'json-ld'];

  constructor(private paperService: PaperService, private metadataService: MetadataService) { }

  ngOnInit() {


    this.searchForm = new FormGroup({
      basic: new FormControl(''),
      text: new FormControl(''),
      keywords: new FormControl(''),
      version: new FormControl(''),
      category: new FormControl(''),
      status: new FormControl(''),
      revised: new FormControl(null),
      received: new FormControl(null),
      accepted: new FormControl(null),
    });

    this.downloadForm = new FormGroup({
      id: new FormControl(''),
      paperType: new FormControl(''),
    });


    this.metaForm = new FormGroup({
      id: new FormControl(''),
      paperMeta: new FormControl(''),
    });

  }

  onSubmit() {

    const keywordz = this.searchForm.get('keywords').value.toString().split(',');
    console.log(this.searchForm.get('basic').value, ' BAZIK');
    const searchParams: IPaperSearch = {
      basic: this.searchForm.get('basic').value !== null ? this.searchForm.get('basic').value : false,
      text: this.searchForm.get('text').value,
      revised: this.searchForm.get('revised').value !== null ? this.searchForm.get('revised').value.getTime() : null,
      received: this.searchForm.get('received').value !== null ? this.searchForm.get('received').value.getTime() : null,
      accepted: this.searchForm.get('accepted').value !== null ? this.searchForm.get('accepted').value.getTime() : null,
      version: this.searchForm.get('version').value !== '' ? this.searchForm.get('version').value : null,
      status: this.searchForm.get('status').value !== '' ? this.searchForm.get('status').value : null,
      category: this.searchForm.get('category').value !== '' ? this.searchForm.get('category').value : null,
      keywords: keywordz[0] !== '' ? keywordz : [],
    };

    this.paperService.search(searchParams).subscribe((resData => {

      console.log(resData);
      this.papers = [];
      for (let i = 0; i < resData.ownPaperIds.length; i++) {
        setTimeout( () => {
          this.paperService.findOne(resData.ownPaperIds[i], 'application/xml').subscribe((resPaper => {
            const parser = new DOMParser();
            const xmlDoc = parser.parseFromString(resPaper, 'application/xml');

            this.papers.push({
              id: resData.ownPaperIds[i],
              title: xmlDoc.getElementsByTagName('title')[0].childNodes[0].nodeValue,
              category: xmlDoc.getElementsByTagName('category')[0].childNodes[0].nodeValue,
              date: xmlDoc.getElementsByTagName('received')[0].childNodes[0].nodeValue,
              author: xmlDoc.getElementsByTagName('first-name')[0].childNodes[0].nodeValue + ' ' + xmlDoc.getElementsByTagName('last-name')[0].childNodes[0].nodeValue});
          })); }, 300 );

      }

      for (let i = 0; i < resData.otherPaperIds.length; i++) {

        setTimeout( () => {
          this.paperService.findOne(resData.otherPaperIds[i], 'application/xml').subscribe((resPaper => {
            console.log(resPaper);
            const parser = new DOMParser();
            const xmlDoc = parser.parseFromString(resPaper, 'application/xml');

            this.papers.push({
              id: resData.otherPaperIds[i],
              title: xmlDoc.getElementsByTagName('title')[0].childNodes[0].nodeValue,
              category: xmlDoc.getElementsByTagName('category')[0].childNodes[0].nodeValue,
              date: xmlDoc.getElementsByTagName('received')[0].childNodes[0].nodeValue,
              author: xmlDoc.getElementsByTagName('first-name')[0].childNodes[0].nodeValue + ' ' + xmlDoc.getElementsByTagName('last-name')[0].childNodes[0].nodeValue});
          }));
        }, 300 );

      }

    }));

    function delay(ms: number) {
      return new Promise( resolve => setTimeout(resolve, ms) );
    }
  }



  onSubmitDownload() {
    if (this.downloadForm.get('paperType').value === 'html') {
        this.paperService.findOneBlob(this.downloadForm.get('id').value, 'text/html').subscribe((resBlob => {
          const filename = 'filename.html';
          FileSaver.saveAs(resBlob, filename);
        }));
    } else {
      this.paperService.findOneBlob(this.downloadForm.get('id').value, 'application/pdf').subscribe((resBlob => {
        const filename = 'filename.pdf';
        FileSaver.saveAs(resBlob, filename);
      }));
  }
}

  onSubmitMeta() {
      this.metadataService.extractMetadata(this.metaForm.get('id').value, this.metaForm.get('paperMeta').value).subscribe((metaData => {
        console.log('META ', metaData);
        let metaname = '';
        if (this.metaForm.get('paperMeta').value === 'nt') {
          metaname = 'metanme.ttl';
        } else {
          metaname = 'metanme.jsonld';
        }
        const blob = new Blob([metaData], { type: 'text/plain' });
        FileSaver.saveAs(blob, metaname);
      }));
  }

}
