<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:sc="https://github.com/XML-TIM4/ScientificPublications" version="2.0">
    <xsl:import href="Templates.xsl"/>
    <xsl:template match="/">
        <html>

            <head>
                <title>Review</title>
            </head>

            <body style="padding-left:10%; padding-right:10%">
                <div align="left">
                    <xsl:value-of select="sc:review/sc:review-metadata/sc:date"></xsl:value-of>
                    <xsl:call-template name="TAuthor">
                        <xsl:with-param name="author" select="sc:review/sc:review-metadata/sc:reviewer"/>
                    </xsl:call-template>
                </div>

                <div align="center" style="margin-top: 10px; margin-bottom: 20px">
                    <h1><xsl:value-of select="sc:review/sc:review-metadata/sc:recommendation"/></h1>
                </div><br/>
                <div align="center">

                    <div>
                    <xsl:for-each select="sc:review/sc:questionnaire/sc:question">
                        <xsl:call-template name="TQuestion"/>
                    </xsl:for-each>
                    </div>

                    <div>
                        <h3 style="margin-top: 15px">Author comments</h3>
                        <xsl:call-template name="TComment">
                            <xsl:with-param name="comments" select="sc:review/sc:author-comments"/>
                            <xsl:with-param name="scientificId" select="sc:review/sc:review-metadata/sc:scientific-paper-id"/>
                        </xsl:call-template>
                    </div>

                <div>
                    <h3 style="margin-bottom: 10px; margin-top: 15px">Editor comments</h3>

                        <xsl:call-template name="TComment">
                            <xsl:with-param name="comments" select="sc:review/sc:editor-comments"/>
                            <xsl:with-param name="scientificId" select="sc:review/sc:review-metadata/sc:scientific-paper-id"/>
                        </xsl:call-template>

                </div>
                </div>

            </body>






        </html>
    </xsl:template>

</xsl:stylesheet>