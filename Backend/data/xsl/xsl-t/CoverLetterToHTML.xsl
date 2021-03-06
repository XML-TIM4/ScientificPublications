<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:sc="https://github.com/XML-TIM4/ScientificPublications" version="2.0">
    <xsl:import href="Templates.xsl"/>
    <xsl:template match="/">
    <html>

        <head>
            <title>Cover letter</title>
        </head>

        <body style="padding-left:10%; padding-right:10%">
           <div align="left">
               <xsl:value-of select="sc:cover-letter/sc:cover-letter-metadata/sc:date"></xsl:value-of>
               <xsl:call-template name="TAuthor">
                    <xsl:with-param name="author" select="sc:cover-letter/sc:cover-letter-metadata/sc:author"/>
                </xsl:call-template>
            </div>

            <div align="center" style="margin-top: 10px; margin-bottom:20px">
                Cover Letter for
                <a><xsl:attribute name="href">
                    http://localhost:8080/scientific-papers/<xsl:value-of select="sc:cover-letter/sc:cover-letter-metadata/sc:scientific-paper-reference"/>
                </xsl:attribute>
                    Scientific Paper
                </a>
            </div>
            <div align="center">
            <xsl:for-each select="sc:cover-letter/sc:content/sc:paragraph">
                <xsl:call-template name="TParagraph"/>
            </xsl:for-each>

            <img>
                <xsl:attribute name="src">
                    data:image/*;base64,<xsl:value-of select="sc:cover-letter/sc:content/sc:signature"/>
                </xsl:attribute>
            </img>
                
            </div>

        </body>






    </html>
    </xsl:template>

</xsl:stylesheet>