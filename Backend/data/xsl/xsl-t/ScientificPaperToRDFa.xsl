<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="http://www.w3.org/ns/rdfa#"
                xmlns:sc="https://github.com/XML-TIM4/ScientificPublications"
                xmlns:sh="https://schema.org">

    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="sc:scientific-paper">
        <sc:scientific-paper
                about="https://github.com/XML-TIM4/ScientificPublications/scientific-papers/{@id}"
                typeof="sh:ScholarlyArticle">
            <xsl:apply-templates select="node()|@*"/>
        </sc:scientific-paper>
    </xsl:template>

    <xsl:template match="//sc:version">
        <sc:version property="sh:version" datatype="xs:string" content="{.}">
            <xsl:apply-templates select="node()|@*"/>
        </sc:version>
    </xsl:template>
</xsl:stylesheet>
