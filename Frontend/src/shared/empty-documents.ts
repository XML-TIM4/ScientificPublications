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
        ret += '</scientific-paper-reference></cover-letter-metadata><content><signature></signature></content></cover-letter>';
        return ret;
    }

    static emptyReviewTemplate(scientificPaperReference: string) {
        let ret = '<review xmlns="https://github.com/XML-TIM4/ScientificPublications" xmlns:xsi="http://www.w3.org/2001/XMLSchema-insta' +
        'nce" xsi:schemaLocation="https://github.com/XML-TIM4/ScientificPublications file:/data/schemas/Review.xsd"><review-metadata>' +
        '<reviewer><name><first-name>John</first-name><last-name>Smith</last-name></name><email>john.smith@example.com</email>' +
        '<affiliation><university>Smith University</university><city>Washington</city><state>MD</state><country>USA</country>' +
        '</affiliation></reviewer><date></date><recommendation></recommendation><scientific-paper-id>';
        ret += scientificPaperReference;
        ret += '</scientific-paper-id></review-metadata><questionnaire></questionnaire><reviewer-comments></reviewer-comments>' +
        '<editor-comments></editor-comments></review>';
        return ret;
    }
}
