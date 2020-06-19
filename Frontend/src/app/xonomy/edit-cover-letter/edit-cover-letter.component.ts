import { LetterService } from 'src/app/services/letter.service';
import { Component, OnInit } from '@angular/core';
import { EmptyDocuments } from '../../../shared/empty-documents';
import { ActivatedRoute } from '@angular/router';

declare var Xonomy: any;

@Component({
  selector: 'app-edit-cover-letter',
  templateUrl: './edit-cover-letter.component.html',
  styleUrls: ['./edit-cover-letter.component.scss']
})
export class EditCoverLetterComponent implements OnInit {

  xonomyEditor;

  documentSpecification = {
    elements: {
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
      'scientific-paper-reference': {
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString
      },
      content: {
        menu: [{
          caption: 'Add a <paragraph>',
          action: Xonomy.newElementChild,
          actionParameter: '<paragraph></paragraph>'
        }]
      },
      signature: {
        mustBeAfter: ['paragraph'],
        oneliner: true,
        hasText: true,
        asker: Xonomy.askString
      },
      paragraph: {
        hasText: true,
        mustBeBefore: ['signature'],
        canDropTo: ['content'],
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
      }
    }
  };

  xmlString: string;
  paperId: string;

  constructor(private route: ActivatedRoute, private letterService: LetterService) { }

  ngOnInit() {
    this.xmlString = EmptyDocuments.emptyCoverLetter('');
    this.paperId = '';
    this.xonomyEditor = document.getElementById('xonomy-editor');
    this.route.params.subscribe(params => {
      this.xmlString = EmptyDocuments.emptyCoverLetter(params.id);
      this.paperId = params.id;
      this.renderXonomy();
    });
    this.renderXonomy();
  }

  renderXonomy() {
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
    link.download = 'letter_save.xml';

    const event = document.createEvent('MouseEvents');
    event.initEvent('click', true, false);
    link.dispatchEvent(event);
  }

  submitLetter() {
    this.xmlString = Xonomy.harvest();
    // console.log(this.xmlString);
    // console.log(this.paperId);
    this.letterService.create(this.xmlString).subscribe(data => {
      console.log(data);
    });
  }

}
