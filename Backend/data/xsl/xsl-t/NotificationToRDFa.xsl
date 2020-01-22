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

    <xsl:variable name="notification-id">
        <xsl:value-of select="notification/@id"/>
    </xsl:variable>

    <xsl:template match="notification">
        <notification
                vocab="https://schema.org/EmailMessage"
                about="https://github.com/XML-TIM4/ScientificPublications/notifications/{$notification-id}"
                typeof="sh:EmailMessage"
                property="sh:inLanguage" datatype="sh:Text" content="{@language}">
            <xsl:apply-templates select="node()|@*"/>
        </notification>
    </xsl:template>

    <xsl:template match="//date">
        <date property="sh:dateSent" datatype="sh:DateTime" content="{.}">
            <xsl:apply-templates select="node()|@*"/>
        </date>
    </xsl:template>

    <xsl:template match="//scientific-paper-id">
        <scientific-paper-id property="sh:about" datatype="sh:ScholarlyArticle" content="{.}">
            <xsl:apply-templates select="node()|@*"/>
        </scientific-paper-id>
    </xsl:template>

    <xsl:template match="//author">
        <reviewer vocab="https://schema.org/EmailMessage"
                  about="https://github.com/XML-TIM4/ScientificPublications/users/{@id}"
                  typeof="sh:Person"
                  rel="sh:recipient"
                  href="https://github.com/XML-TIM4/ScientificPublications/notifications/{$notification-id}">
            <xsl:apply-templates select="node()|@*"/>
        </reviewer>
    </xsl:template>


</xsl:stylesheet>
