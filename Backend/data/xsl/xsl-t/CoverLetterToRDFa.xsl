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

    <xsl:variable name="cover-letter-id">
        <xsl:value-of select="cover-letter/@id"/>
    </xsl:variable>

    <xsl:template match="cover-letter">
        <cover-letter
                vocab="https://schema.org/CreativeWork"
                about="https://github.com/XML-TIM4/ScientificPublications/cover-letters/{$cover-letter-id}"
                typeof="sh:CreativeWork"
                property="sh:inLanguage" datatype="sh:Text" content="{@language}">
            <xsl:apply-templates select="node()|@*"/>
        </cover-letter>
    </xsl:template>

    <xsl:template match="//scientific-paper-id">
        <scientific-paper-id property="sh:about" datatype="sh:ScholarlyArticle" content="{.}">
            <xsl:apply-templates select="node()|@*"/>
        </scientific-paper-id>
    </xsl:template>

    <xsl:template match="//date">
        <date property="sh:dateCreated" datatype="sh:Date" content="{.}">
            <xsl:apply-templates select="node()|@*"/>
        </date>
    </xsl:template>

    <xsl:template match="//reviewer">
        <author vocab="https://schema.org/CreativeWork"
                about="https://github.com/XML-TIM4/ScientificPublications/users/{@id}"
                typeof="sh:Person"
                rel="sh:author"
                href="https://github.com/XML-TIM4/ScientificPublications/cover-letters/{$cover-letter-id}">
            <xsl:apply-templates select="node()|@*"/>
        </author>
    </xsl:template>


</xsl:stylesheet>
