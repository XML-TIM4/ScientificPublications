<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:sc="https://github.com/XML-TIM4/ScientificPublications" version="2.0">
    <xsl:import href="Templates.xsl"/>
    <xsl:template match="/">
    <html>

        <head>
            <title>Cover letter</title>
        </head>

        <body>
            <div alight="right">
                <xsl:value-of select="sc:CoverLetter/sc:cover-letter-metadata/sc:date"></xsl:value-of>
            </div>
            <div align="left">
                <xsl:call-template name="TAuthor">
                    <xsl:with-param name="author" select="sc:CoverLetter/sc:cover-letter-metadata/sc:author"/>
                </xsl:call-template>
            </div>

            <div align="center">
                <a><xsl:attribute name="href">
                    http://localhost:8080/scientific-papers/<xsl:value-of select="sc:CoverLetter/sc:cover-letter-metadata/sc:scientific-paper-reference"/>
                </xsl:attribute>
                    AAAAAA
                </a>
            </div>

            <div align="center">
            <xsl:for-each select="sc:CoverLetter/sc:content/sc:paragraph">
                <xsl:call-template name="TParagraph"/>
            </xsl:for-each>

            <p>
                Signature: <i><xsl:value-of select="sc:CoverLetter/sc:content/sc:signature"/></i>
            </p>
            </div>

            <div align="left">
                <xsl:call-template name="TAuthor">
                    <xsl:with-param name="author" select="sc:CoverLetter/sc:cover-letter-metadata/sc:editor"/>
                </xsl:call-template>
            </div>

        </body>






    </html>
    </xsl:template>

</xsl:stylesheet>