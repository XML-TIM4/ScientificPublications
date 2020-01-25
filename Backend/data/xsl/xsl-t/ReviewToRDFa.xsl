<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xpath-default-namespace="https://github.com/XML-TIM4/ScientificPublications"
                xmlns="https://github.com/XML-TIM4/ScientificPublications"
                xmlns:sh="https://schema.org/">

    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:variable name="review-id">
        <xsl:value-of select="review/@id"/>
    </xsl:variable>

    <xsl:template match="review">
        <review
                vocab="https://schema.org/Review"
                about="https://github.com/XML-TIM4/ScientificPublications/reviews/{$review-id}"
                typeof="sh:Review"
                property="sh:inLanguage" datatype="sh:Text" content="{@language}">
            <xsl:apply-templates select="node()|@*"/>
        </review>
    </xsl:template>

    <xsl:template match="//scientific-paper-id">
        <scientific-paper-id property="sh:itemReviewed" datatype="sh:ScholarlyArticle" content="{.}">
            <xsl:apply-templates select="node()|@*"/>
        </scientific-paper-id>
    </xsl:template>

    <xsl:template match="//date">
        <date property="sh:dateCreated" datatype="sh:Date" content="{.}">
            <xsl:apply-templates select="node()|@*"/>
        </date>
    </xsl:template>

    <xsl:template match="//recommendation">
        <recommendation property="sh:reviewRating" datatype="sh:Text" content="{.}">
            <xsl:apply-templates select="node()|@*"/>
        </recommendation>
    </xsl:template>

    <xsl:template match="//reviewer">
        <reviewer vocab="https://schema.org/Review"
                  about="https://github.com/XML-TIM4/ScientificPublications/users/{@id}"
                  typeof="sh:Person"
                  rel="sh:author"
                  href="https://github.com/XML-TIM4/ScientificPublications/reviews/{$review-id}">
            <xsl:apply-templates select="node()|@*"/>
        </reviewer>
    </xsl:template>

</xsl:stylesheet>
