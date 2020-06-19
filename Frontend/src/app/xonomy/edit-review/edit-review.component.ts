import { Component, OnInit } from '@angular/core';
import { EmptyDocuments } from '../../../shared/empty-documents';
import { ActivatedRoute } from '@angular/router';
import { ReviewService } from 'src/app/services/review.service';

declare var Xonomy: any;

@Component({
  selector: 'app-edit-review',
  templateUrl: './edit-review.component.html',
  styleUrls: ['./edit-review.component.scss']
})
export class EditReviewComponent implements OnInit {

  xonomyEditor;

  documentSpecification = {
    elements: {
      'scientific-paper-id': {
        oneliner: true,
        asker(defaultString) {return '<b>Cannot change value</b>'; }
      },
      'question-text': {
        oneliner: true,
        asker(defaultString) {return '<b>Cannot change value</b>'; }
      },
      'review-metadata': {
        menu: [{
          caption: 'Add <reviewer> info',
          action: Xonomy.newElementChild,
          actionParameter: '<reviewer><name><first-name></first-name><last-name></last-name></name><email></email>' +
          '<affiliation><university></university><city></city><state></state><country></country></affiliation></reviewer>',
          hideIf(jsElement) {
            return jsElement.hasChildElement('reviewer');
          }
        }]
      },
      reviewer: {
        mustBeBefore: ['date', 'recommendation', 'scientific-paper-id']
      },
      name: {
        menu: [{
          caption: 'Add a <middle-name>',
          action: Xonomy.newElementChild,
          actionParameter: '<middle-name></middle-name>'
        }]
      },
      'first-name': {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString
      },
      'last-name': {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString
      },
      'middle-name': {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString,
        mustBeBefore: ['last-name'],
        mustBeAfter: ['first-name'],
        canDropTo: ['name'],
        menu: [{
          caption: 'Remove <middle-name>',
          action: Xonomy.deleteElement
        }]
      },
      email: {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString
      },
      university: {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString
      },
      city: {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString
      },
      state: {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString
      },
      country: {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString
      },
      date: {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString
      },
      recommendation: {
        asker: Xonomy.askPicklist,
        oneliner: true,
        askerParameter: [
          'accept', 'reject', 'revise'
        ]
      },
      answer: {
        oneliner: true,
        asker(defaultString) {return '<b>Cannot change value</b>'; },
        menu: [{
          caption: 'Choose answer',
          action: this.chooseAnswerAction
        }]
      },
      'reviewer-comments': {
        menu: [{
          caption: 'Add a <reviewer-comment>',
          action: Xonomy.newElementChild,
          actionParameter: '<reviewer-comment><review-reference></review-reference><comment-text></comment-text></reviewer-comment>'
        }]
      },
      'reviewer-comment': {
        menu: [{
          caption: 'Remove <reviewer-comment>',
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
  editorComments: string;

  constructor(private route: ActivatedRoute, private reviewService: ReviewService) { }

  ngOnInit() {
    this.xmlString = EmptyDocuments.emptyReviewTemplate('');
    this.paperId = '';
    this.xonomyEditor = document.getElementById('xonomy-editor');
    this.route.params.subscribe(params => {
      this.paperId = params.id;
      this.reviewService.findByPaper(this.paperId).subscribe(resData => {
        if (resData !== '') {
          this.xmlString = resData;
          this.renderXonomy();
        }
      });
    });
    this.renderXonomy();
  }

  chooseAnswerAction(htmlID, param) {
    let div = document.getElementById(htmlID);
    let jsElement = Xonomy.harvestElement(div);
    for (const element of jsElement.parent().getChildElements('answer')) {
      Xonomy.deleteAttribute(element.getAttribute('selected').htmlID);
      Xonomy.newAttribute(element.htmlID, {name: 'selected', value: 'false'});
    }
    div = document.getElementById(htmlID);
    jsElement = Xonomy.harvestElement(div);
    Xonomy.deleteAttribute(jsElement.getAttribute('selected').htmlID);
    Xonomy.newAttribute(jsElement.htmlID, {name: 'selected', value: 'true'});
  }

  replaceEditorComments(xmlString: string) {
    const criteria = /<editor\-comments>([^]*)<\/editor\-comments>/g;
    const matches = xmlString.match(criteria);
    console.log(matches);
    if (!Array.isArray(matches)) {
      return xmlString;
    }
    const clean = matches[0];
    this.editorComments = clean;
    xmlString = xmlString.replace(RegExp(clean.replace(/[.*+?^${}()|[\]\\]/g, '\\$&'), 'g'), '<editor-comments></editor-comments>');
    return xmlString;
  }

  replaceEditorCommentsBack(xmlString: string) {
    const clean = '<editor-comments/>';
    xmlString = xmlString.replace(RegExp(clean.replace(/[.*+?^${}()|[\]\\]/g, '\\$&'), 'g'), this.editorComments);
    return xmlString;
  }

  renderXonomy() {
    this.xmlString = this.replaceEditorComments(this.xmlString);
    Xonomy.render(this.xmlString, this.xonomyEditor, this.documentSpecification);
    Xonomy.changed();
  }

  submitReview() {
    this.xmlString = Xonomy.harvest();
    this.xmlString = this.replaceEditorCommentsBack(this.xmlString);
    // console.log(this.xmlString);
    // console.log(this.paperId);
    this.reviewService.createTemplate(this.xmlString).subscribe(data => {
      console.log(data);
    });
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
    this.xmlString = this.replaceEditorCommentsBack(this.xmlString);
    const blob = new Blob([this.xmlString]);
    const url  = window.URL || window.webkitURL;
    const link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
    link.href = url.createObjectURL(blob);
    link.download = 'review_save.xml';

    const event = document.createEvent('MouseEvents');
    event.initEvent('click', true, false);
    link.dispatchEvent(event);
  }

  // testWaters() {
  // this.xmlString = '<?xml version="1.0" encoding="UTF-8"?><review xmlns="https://github.com/XML-TIM4/ScientificPublications" ' +
  // 'xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="https://github.com/XML-TIM4/ScientificPublications' +
  // ' file:/data/schemas/Review.xsd"><review-metadata><date>2006-05-04</date><recommendation>accept</recommendation><scientific-' +
  // 'paper-id>asdfqwer</scientific-paper-id></review-metadata><questionnaire><question><question-text>question-text0</question-text>' +
  // '<answer selected="false">q1a1</answer><answer selected="false">q1a2</answer></question><question><question-text>question-text1' +
  // '</question-text><answer selected="false">q1a1</answer><answer selected="false">q1a2</answer><answer selected="false">q1a3</answer>' +
  // '</question></questionnaire><reviewer-comments></reviewer-comments><editor-comments><editor-comment><review-reference>review-' +
  // 'reference2</review-reference><comment-text>comment-text2</comment-text></editor-comment><editor-comment><review-reference>review' +
  // '-reference3</review-reference><comment-text>comment-text3</comment-text></editor-comment></editor-comments></review>';
  //   this.paperId = '';
  //   this.xonomyEditor = document.getElementById('xonomy-editor');
  //   this.renderXonomy();
  // }

}
