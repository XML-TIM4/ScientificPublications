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

    <xsl:template match="scientific-paper">
        <scientific-paper
                vocab="https://schema.org/ScholarlyArticle"
                about="https://github.com/XML-TIM4/ScientificPublications/scientific-papers/{@id}"
                typeof="sh:ScholarlyArticle"
                property="sh:inLanguage" datatype="sh:Text" content="{@language}">
            <xsl:apply-templates select="node()|@*"/>
        </scientific-paper>
    </xsl:template>

    <xsl:template match="scientific-paper/title">
        <title property="sh:headline" datatype="sh:Text" content="{.}">
            <xsl:apply-templates select="node()|@*"/>
        </title>
    </xsl:template>

    <xsl:variable name="abstract">
        <xsl:for-each select="//abstract/abstract-item">
            <xsl:value-of select="@title"/>
            <xsl:text> - </xsl:text>
            <xsl:value-of select="."/>
            <xsl:if test="position() != last()">
                <xsl:text>;</xsl:text>
            </xsl:if>
        </xsl:for-each>
    </xsl:variable>

    <xsl:template match="//abstract">
        <abstract property="sh:abstract" datatype="sh:Text" content="{$abstract}">
            <xsl:apply-templates select="node()|@*"/>
        </abstract>
    </xsl:template>

    <xsl:template match="//received">
        <received property="sh:dateCreated" datatype="sh:Date" content="{.}">
            <xsl:apply-templates select="node()|@*"/>
        </received>
    </xsl:template>

    <xsl:template match="//revised">
        <revised property="sh:dateModified" datatype="sh:Date" content="{.}">
            <xsl:apply-templates select="node()|@*"/>
        </revised>
    </xsl:template>

    <xsl:template match="//accepted">
        <accepted property="sh:datePublished" datatype="sh:Date" content="{.}">
            <xsl:apply-templates select="node()|@*"/>
        </accepted>
    </xsl:template>

    <xsl:template match="//version">
        <version property="sh:version" datatype="sh:Text" content="{.}">
            <xsl:apply-templates select="node()|@*"/>
        </version>
    </xsl:template>

    <xsl:template match="//status">
        <status property="sh:creativeWorkStatus" datatype="sh:Text" content="{.}">
            <xsl:apply-templates select="node()|@*"/>
        </status>
    </xsl:template>

    <xsl:template match="//category">
        <category property="sh:genre" datatype="sh:Text" content="{.}">
            <xsl:apply-templates select="node()|@*"/>
        </category>
    </xsl:template>

    <xsl:variable name="keywords">
        <xsl:for-each select="//keywords/keyword">
            <xsl:if test="position() != 1">,</xsl:if>
            <xsl:value-of select="."/>
        </xsl:for-each>
    </xsl:variable>

    <xsl:template match="//keywords">
        <keywords property="sh:keywords" datatype="sh:Text" content="{$keywords}">
            <xsl:apply-templates select="node()|@*"/>
        </keywords>
    </xsl:template>


</xsl:stylesheet>
