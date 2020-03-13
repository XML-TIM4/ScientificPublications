import { Component, OnInit } from '@angular/core';

declare const Xonomy: any;

@Component({
  selector: 'app-edit-paper',
  templateUrl: './edit-paper.component.html',
  styleUrls: ['./edit-paper.component.scss']
})
export class EditPaperComponent implements OnInit {

  xonomyEditor;

  constructor() { }

  ngOnInit() {
    this.xonomyEditor = document.getElementById("xonomy-editor");
    const temp:String = "<foo><bar jo='3'></bar></foo>";
    Xonomy.render(temp, this.xonomyEditor, null);
  }

}
