export class EmptyDocuments {
    static emptyScientificPaper = '<scientific-paper xmlns="https://github.com/XML-TIM4/ScientificPublications" ' +
    'xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" ' +
    'xsi:schemaLocation="https://github.com/XML-TIM4/ScientificPublications file:/data/schemas/ScientificPaper.xsd" language="en" id="">' +
        '<metadata>' +
            '<received></received><revised></revised><accepted></accepted><version>1.0</version><status></status>' +
            '<category>research-paper</category>' +
            '<keywords>' +
            '</keywords>' +
        '</metadata>' +
        '<title>New Title</title>' +
        '<authors>' +
        '</authors>' +
        '<abstract>' +
        '</abstract>' +
        '<content>' +
        '</content>' +
        '<references>' +
        '</references>' +
    '</scientific-paper>';

    static emptyCoverLetter(scientificPaperReference: string) {
        let ret = '<cover-letter xmlns="https://github.com/XML-TIM4/ScientificPublications" ' +
        'xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="https://github.com/XML-TIM4/ScientificPublications ' +
        'file:/data/schemas/CoverLetter.xsd"><cover-letter-metadata><author><name><first-name></first-name><last-name></last-name></name>' +
        '<email></email><affiliation><university></university><city></city><state></state><country></country></affiliation></author>' +
        '<date></date><scientific-paper-reference>';
        ret += scientificPaperReference;
        ret += '</scientific-paper-reference></cover-letter-metadata><content></content></cover-letter>';
        return ret;
    }
}