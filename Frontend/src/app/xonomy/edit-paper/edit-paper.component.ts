import { Component, OnInit } from '@angular/core';
import { EmptyDocuments } from '../../../shared/empty-documents';
import { ActivatedRoute } from '@angular/router';
import { PaperService } from 'src/app/services/paper.service';
import * as uuid from 'uuid';

declare var Xonomy: any;

@Component({
  selector: 'app-edit-paper',
  templateUrl: './edit-paper.component.html',
  styleUrls: ['./edit-paper.component.scss']
})
export class EditPaperComponent implements OnInit {

  xonomyEditor;

  documentSpecification = {
    elements: {
      received: {
        oneliner: true,
        asker(defaultString) {return '<b>Cannot change value</b>'; }
      },
      revised: {
        oneliner: true,
        asker(defaultString) {return '<b>Cannot change value</b>'; }
      },
      accepted: {
        oneliner: true,
        asker(defaultString) {return '<b>Cannot change value</b>'; }
      },
      version: {
        oneliner: true,
        asker(defaultString) {return '<b>Cannot change value</b>'; }
      },
      status: {
        oneliner: true,
        asker(defaultString) {return '<b>Cannot change value</b>'; }
      },
      category: {
        oneliner: true,
        asker: Xonomy.askPicklist,
        askerParameter: [
          'research-paper', 'viewpoint', 'technical-paper', 'conceptual-paper',
          'case-study', 'literature-review', 'general-review'
        ]
      },
      keywords: {
        menu: [{
          caption: 'Add a <keyword>',
          action: Xonomy.newElementChild,
          actionParameter: '<keyword>word</keyword>'
        }]
      },
      keyword: {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Remove keyword',
          action: Xonomy.deleteElement
        }]
      },
      title: {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString
      },
      authors: {
        menu: [{
          caption: 'Add an <author>',
          action: Xonomy.newElementChild,
          actionParameter: '<author><name><first-name></first-name><last-name></last-name></name><email></email>' +
          '<affiliation><university></university><city></city><state></state><country></country></affiliation></author>',
          hideIf(jsElement) {
            return jsElement.parent().name === 'reference';
          }
        },
        {
          caption: 'Add a <name>',
          action: Xonomy.newElementChild,
          actionParameter: '<name><first-name></first-name><last-name></last-name></name>',
          hideIf(jsElement) {
            return jsElement.parent().name !== 'reference';
          }
        }]
      },
      author: {
        menu: [{
          caption: 'Remove <author>',
          action: Xonomy.deleteElement
        }]
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
      abstract: {
        menu: [{
          caption: 'Add Purpose',
          action: Xonomy.newElementChild,
          actionParameter: '<abstract-item title="Purpose"></abstract-item>',
          hideIf(jsElement) {
            let ret = false;
            jsElement.getChildElements('abstract-item').forEach(element => {
              if (element.getAttributeValue('title', '') === 'Purpose') {
                ret = true;
              }
            });
            return ret;
          }
        },
        {
          caption: 'Add Methodology',
          action: Xonomy.newElementChild,
          actionParameter: '<abstract-item title="Methodology"></abstract-item>',
          hideIf(jsElement) {
            let ret = false;
            jsElement.getChildElements('abstract-item').forEach(element => {
              if (element.getAttributeValue('title', '') === 'Methodology') {
                ret = true;
              }
            });
            return ret;
          }
        },
        {
          caption: 'Add Findings',
          action: Xonomy.newElementChild,
          actionParameter: '<abstract-item title="Findings"></abstract-item>',
          hideIf(jsElement) {
            let ret = false;
            jsElement.getChildElements('abstract-item').forEach(element => {
              if (element.getAttributeValue('title', '') === 'Findings') {
                ret = true;
              }
            });
            return ret;
          }
        },
        {
          caption: 'Add Implications',
          action: Xonomy.newElementChild,
          actionParameter: '<abstract-item title="Implications"></abstract-item>',
          hideIf(jsElement) {
            let ret = false;
            jsElement.getChildElements('abstract-item').forEach(element => {
              if (element.getAttributeValue('title', '') === 'Implications') {
                ret = true;
              }
            });
            return ret;
          }
        },
        {
          caption: 'Add Practical Implications',
          action: Xonomy.newElementChild,
          actionParameter: '<abstract-item title="Practical Implications"></abstract-item>',
          hideIf(jsElement) {
            let ret = false;
            jsElement.getChildElements('abstract-item').forEach(element => {
              if (element.getAttributeValue('title', '') === 'Practical Implications') {
                ret = true;
              }
            });
            return ret;
          }
        },
        {
          caption: 'Add Social Implications',
          action: Xonomy.newElementChild,
          actionParameter: '<abstract-item title="Social Implications"></abstract-item>',
          hideIf(jsElement) {
            let ret = false;
            jsElement.getChildElements('abstract-item').forEach(element => {
              if (element.getAttributeValue('title', '') === 'Social Implications') {
                ret = true;
              }
            });
            return ret;
          }
        },
        {
          caption: 'Add Originality',
          action: Xonomy.newElementChild,
          actionParameter: '<abstract-item title="Originality"></abstract-item>',
          hideIf(jsElement) {
            let ret = false;
            jsElement.getChildElements('abstract-item').forEach(element => {
              if (element.getAttributeValue('title', '') === 'Originality') {
                ret = true;
              }
            });
            return ret;
          }
        }]
      },
      'abstract-item': {
        hasText: true,
        menu: [{
          caption: 'Remove <abstract-item>',
          action: Xonomy.deleteElement
        }],
        canDropTo: ['abstract']
      },
      content: {
        menu: [{
          caption: 'Add a <section>',
          action: Xonomy.newElementChild,
          actionParameter: '<section><title></title></section>'
        }]
      },
      section: {
        canDropTo: ['content'],
        menu: [{
          caption: 'Add a <paragraph>',
          action: Xonomy.newElementChild,
          actionParameter: '<paragraph></paragraph>'
        },
        {
          caption: 'Remove <section>',
          action: Xonomy.deleteElement
        }]
      },
      paragraph: {
        hasText: true,
        mustBeAfter: ['title'],
        canDropTo: ['section'],
        menu: [{
          caption: 'Add a <figure>',
          action: Xonomy.newElementChild,
          actionParameter: '<figure type="figure"></figure>'
        },
        {
          caption: 'Add a <table>',
          action: Xonomy.newElementChild,
          actionParameter: '<table title=""><header></header><body></body></table>'
        },
        {
          caption: 'Add a <list>',
          action: Xonomy.newElementChild,
          actionParameter: '<list ordered="false"></list>'
        },
        {
          caption: 'Add a <quote>',
          action: Xonomy.newElementChild,
          actionParameter: '<quote><quote-text></quote-text><publication>' +
          '<title></title><publisher></publisher><place></place><url></url></publication></quote>'
        },
        {
          caption: 'Remove <paragraph>',
          action: Xonomy.deleteElement
        }],
        inlineMenu: [{
          caption: 'Wrap with <decorator>',
          action: Xonomy.wrap,
          actionParameter: {template: '<decorator>$</decorator>', placeholder: '$'}
        }]
      },
      figure: {
        hasText: true,
        menu: [{
          caption: 'Add an <image>',
          action: Xonomy.newElementChild,
          actionParameter: '<image></image>'
        },
        {
          caption: 'Remove <figure>',
          action: Xonomy.deleteElement
        }],
        attributes: {
          type: {
            asker: Xonomy.askPicklist,
            askerParameter: ['figure', 'box', 'equation', 'chart']
          }
        }
      },
      image: {
        hasText: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Remove <image>',
          action: Xonomy.deleteElement
        }]
      },
      table: {
        hasText: false,
        attributes: {
          title: {
            asker: Xonomy.askString
          }
        },
        menu: [{
          caption: 'Remove <table>',
          action: Xonomy.deleteElement
        }]
      },
      header: {
        menu: [{
          caption: 'Add a <row>',
          action: Xonomy.newElementChild,
          actionParameter: '<row></row>'
        }]
      },
      body: {
        menu: [{
          caption: 'Add a <row>',
          action: Xonomy.newElementChild,
          actionParameter: '<row></row>'
        }]
      },
      row: {
        menu: [{
          caption: 'Add a <column>',
          action: Xonomy.newElementChild,
          actionParameter: '<column></column>'
        },
        {
          caption: 'Remove <row>',
          action: Xonomy.deleteElement
        }]
      },
      column: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Remove <column>',
          action: Xonomy.deleteElement
        }]
      },
      list: {
        attributes: {
          ordered: {
            asker: Xonomy.askPicklist,
            askerParameter: ['false', 'true']
          }
        },
        menu: [{
          caption: 'Add a <list-item>',
          action: Xonomy.newElementChild,
          actionParameter: '<list-item></list-item>'
        },
        {
          caption: 'Remove <list>',
          action: Xonomy.deleteElement
        }]
      },
      'list-item': {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Remove <list-item>',
          action: Xonomy.deleteElement
        }]
      },
      decorator: {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Unwrap <decorator>',
          action: this.recursiveUnwrapStart,
          actionParameter: this
        }],
        inlineMenu: [{
          caption: 'Wrap with <bold>',
          action: Xonomy.wrap,
          actionParameter: {template: '<bold>$</bold>', placeholder: '$'}
        },
        {
          caption: 'Wrap with <italic>',
          action: Xonomy.wrap,
          actionParameter: {template: '<italic>$</italic>', placeholder: '$'}
        },
        {
          caption: 'Wrap with <underline>',
          action: Xonomy.wrap,
          actionParameter: {template: '<underline>$</underline>', placeholder: '$'}
        }]
      },
      bold: {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Unwrap <bold>',
          action: Xonomy.unwrap
        }],
        inlineMenu: [{
          caption: 'Wrap with <bold>',
          action: Xonomy.wrap,
          actionParameter: {template: '<bold>$</bold>', placeholder: '$'}
        },
        {
          caption: 'Wrap with <italic>',
          action: Xonomy.wrap,
          actionParameter: {template: '<italic>$</italic>', placeholder: '$'}
        },
        {
          caption: 'Wrap with <underline>',
          action: Xonomy.wrap,
          actionParameter: {template: '<underline>$</underline>', placeholder: '$'}
        }]
      },
      italic: {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Unwrap <italic>',
          action: Xonomy.unwrap
        }],
        inlineMenu: [{
          caption: 'Wrap with <bold>',
          action: Xonomy.wrap,
          actionParameter: {template: '<bold>$</bold>', placeholder: '$'}
        },
        {
          caption: 'Wrap with <italic>',
          action: Xonomy.wrap,
          actionParameter: {template: '<italic>$</italic>', placeholder: '$'}
        },
        {
          caption: 'Wrap with <underline>',
          action: Xonomy.wrap,
          actionParameter: {template: '<underline>$</underline>', placeholder: '$'}
        }]
      },
      underline: {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString,
        menu: [{
          caption: 'Unwrap <underline>',
          action: Xonomy.unwrap
        }],
        inlineMenu: [{
          caption: 'Wrap with <bold>',
          action: Xonomy.wrap,
          actionParameter: {template: '<bold>$</bold>', placeholder: '$'}
        },
        {
          caption: 'Wrap with <italic>',
          action: Xonomy.wrap,
          actionParameter: {template: '<italic>$</italic>', placeholder: '$'}
        },
        {
          caption: 'Wrap with <underline>',
          action: Xonomy.wrap,
          actionParameter: {template: '<underline>$</underline>', placeholder: '$'}
        }]
      },
      quote: {
        menu: [{
          caption: 'Remove <quote>',
          action: Xonomy.deleteElement
        }]
      },
      'quote-text': {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },
      publisher: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },
      place: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },
      url: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },
      references: {
        menu: [{
          caption: 'Add a <reference>',
          action: Xonomy.newElementChild,
          actionParameter: '<reference><authors></authors><publication>' +
          '<title></title><year>1970</year><publisher></publisher><place></place><url></url></publication></reference>'
        }]
      },
      reference: {
        menu: [{
          caption: 'Remove <reference>',
          action: Xonomy.deleteElement
        }]
      },
      year: {
        hasText: true,
        oneliner: true,
        asker(defaultString) {
          let html = '';
          html += '<form onsubmit=\'Xonomy.answer(this.val.value); return false\'>';
          html += '<input name=\'val\' class=\'focusme\' value=\'1970\' ' +
          'type="number" min="1800" max="2019"/>';
          html += ' <input type=\'submit\' value=\'OK\'>';
          html += '</form>';
          return html;
          }
      }
    }
  };
  xmlString: string;
  imageMapper = {};

  constructor(private route: ActivatedRoute, private paperService: PaperService) { }

  ngOnInit() {
    // Xonomy.referenceToThis = this;
    this.xmlString = EmptyDocuments.emptyScientificPaper;
    this.xonomyEditor = document.getElementById('xonomy-editor');
    this.route.params.subscribe(params => {
      this.paperService.findOne(params.id, 'application/xml').subscribe((resPaper => {
        this.xmlString = resPaper;
        this.renderXonomy();
      }));
    });
    this.renderXonomy();
  }

  getKeyByValue(object, value) {
    for (const prop in object) {
      if (object.hasOwnProperty(prop)) {
        if (object[prop] === value) {
          return prop;
        }
      }
    }
    return null;
  }

  onlyUnique(value, index, self) {
    return self.indexOf(value) === index;
  }

  replaceImageText(xmlString: string) {
    const criteria = /<image>([^<]*)<\/image>/g;
    const matches = xmlString.match(criteria);
    if (!Array.isArray(matches)) {
      return xmlString;
    }
    for (const match of matches.filter(this.onlyUnique)) {
      const clean = match.substring(7, match.length - 8);
      const imageId = uuid.v4();
      this.imageMapper[imageId] = clean;
      xmlString = xmlString.replace(RegExp(clean.replace(/[.*+?^${}()|[\]\\]/g, '\\$&'), 'g'), imageId);
    }
    return xmlString;
  }

  replaceImageTextBack(xmlString: string) {
    const criteria = /<image>([^<]*)<\/image>/g;
    const matches = xmlString.match(criteria);
    if (!Array.isArray(matches)) {
      return xmlString;
    }
    for (const match of matches.filter(this.onlyUnique)) {
      const imageId = match.substring(7, match.length - 8);
      const clean = this.imageMapper[imageId];
      if (clean === undefined) {
        continue;
      }
      xmlString = xmlString.replace(RegExp(imageId.replace(/[.*+?^${}()|[\]\\]/g, '\\$&'), 'g'), clean);
    }
    return xmlString;
  }

  renderXonomy() {
    this.xmlString = this.replaceImageText(this.xmlString);
    Xonomy.render(this.xmlString, this.xonomyEditor, this.documentSpecification);
    Xonomy.changed();
  }

  recursiveUnwrap(jsElement) {
    jsElement.getChildElements('bold').forEach(element => {
      this.recursiveUnwrap(element);
    });
    jsElement.getChildElements('italic').forEach(element => {
      this.recursiveUnwrap(element);
    });
    jsElement.getChildElements('underline').forEach(element => {
      this.recursiveUnwrap(element);
    });
    Xonomy.unwrap(jsElement.htmlID, null);
  }

  recursiveUnwrapStart(htmlID, that) {
    const div = document.getElementById(htmlID);
    const jsElement = Xonomy.harvestElement(div);
    that.recursiveUnwrap(jsElement);
    Xonomy.changed();
  }

}
