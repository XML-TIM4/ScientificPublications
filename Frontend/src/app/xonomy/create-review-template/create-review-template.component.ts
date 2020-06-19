import { ReviewService } from './../../services/review.service';
import { Component, OnInit } from '@angular/core';
import { EmptyDocuments } from '../../../shared/empty-documents';
import { ActivatedRoute } from '@angular/router';

declare var Xonomy: any;

@Component({
  selector: 'app-create-review-template',
  templateUrl: './create-review-template.component.html',
  styleUrls: ['./create-review-template.component.scss']
})
export class CreateReviewTemplateComponent implements OnInit {

  xonomyEditor;

  documentSpecification = {
    elements: {
      'scientific-paper-id': {
        oneliner: true,
        asker(defaultString) {return '<b>Cannot change value</b>'; }
      },
      questionnaire: {
        menu: [{
          caption: 'Add a <question>',
          action: Xonomy.newElementChild,
          actionParameter: '<question><question-text></question-text></question>'
        }]
      },
      'question-text': {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString
      },
      question: {
        menu: [{
          caption: 'Add an <answer>',
          action: Xonomy.newElementChild,
          actionParameter: '<answer selected="false"></answer>'
        },
        {
          caption: 'Remove <question>',
          action: Xonomy.deleteElement
        }],
        canDropTo: ['questionnaire']
      },
      answer: {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Remove <answer>',
          action: Xonomy.deleteElement
        }]
      },
      'editor-comments': {
        menu: [{
          caption: 'Add an <editor-comment>',
          action: Xonomy.newElementChild,
          actionParameter: '<editor-comment><review-reference></review-reference><comment-text></comment-text></editor-comment>'
        }]
      },
      'editor-comment': {
        menu: [{
          caption: 'Remove <editor-comment>',
          action: Xonomy.deleteElement
        }]
      },
      'review-reference': {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString
      },
      'comment-text': {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString
      }
    }
  };
  xmlString: string;
  paperId: string;

  constructor(private route: ActivatedRoute, private reviewService: ReviewService) { }

  ngOnInit() {
    this.xmlString = EmptyDocuments.emptyReviewTemplate('');
    this.paperId = '';
    this.xonomyEditor = document.getElementById('xonomy-editor');
    this.route.params.subscribe(params => {
      this.paperId = params.id;
      this.xmlString = EmptyDocuments.emptyReviewTemplate(this.paperId);
      this.renderXonomy();
    });
    this.renderXonomy();
  }

  renderXonomy() {
    Xonomy.render(this.xmlString, this.xonomyEditor, this.documentSpecification);
    Xonomy.changed();
  }

  fileLoad(event) {
    const file = event.target.files[0];
    const fileReader = new FileReader();
    fileReader.onload = (e) => {
      this.xmlString = fileReader.result.toString();
      this.renderXonomy();
    };
    if (file !== undefined) {
      fileReader.readAsText(file);
    }
  }

  saveFile() {
    this.xmlString = Xonomy.harvest();
    const blob = new Blob([this.xmlString]);
    const url  = window.URL || window.webkitURL;
    const link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
    link.href = url.createObjectURL(blob);
    link.download = 'review_save.xml';

    const event = document.createEvent('MouseEvents');
    event.initEvent('click', true, false);
    link.dispatchEvent(event);
  }

  submitReview() {
    this.xmlString = Xonomy.harvest();
    // console.log(this.xmlString);
    // console.log(this.paperId);
    this.reviewService.createTemplate(this.xmlString).subscribe(data => {
      console.log(data);
    });
  }

}
