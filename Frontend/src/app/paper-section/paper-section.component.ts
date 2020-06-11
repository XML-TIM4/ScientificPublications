import { Component, OnInit, Input } from '@angular/core';
import {Paper} from '../model/paper.model';

@Component({
  selector: 'app-paper-section',
  templateUrl: './paper-section.component.html',
  styleUrls: ['./paper-section.component.scss']
})
export class PaperSectionComponent implements OnInit {
  @Input()
  paper: Paper;
  constructor() { }

  ngOnInit() {
  }

}
