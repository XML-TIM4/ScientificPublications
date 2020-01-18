<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:sc="https://github.com/XML-TIM4/ScientificPublications" version="2.0">
    <xsl:import href="Templates.xsl"/>
    <xsl:template match="/">
        <html>

            <head>
                <title>Review</title>
            </head>

            <body>
                <div alight="right">
                    <xsl:value-of select="sc:review/sc:review-metadata/sc:date"></xsl:value-of>
                </div>
                <div align="left">
                    <xsl:call-template name="TAuthor">
                        <xsl:with-param name="author" select="sc:review/sc:review-metadata/sc:reviewer"/>
                    </xsl:call-template>
                </div>

                <div align="center">

                    <xsl:for-each select="sc:review/sc:questionnaire/sc:question">
                        <xsl:call-template name="TQuestion"/>
                    </xsl:for-each>

                    Author comments
                    <xsl:for-each select="sc:review/sc:author-comments">
                        <xsl:call-template name="TComment">
                            <xsl:with-param name="comment" select="sc:author-comment"/>
                        </xsl:call-template>
                    </xsl:for-each>

                    Editor comments

                    <xsl:for-each select="sc:review/sc:editor-comments">
                        <xsl:call-template name="TComment">
                            <xsl:with-param name="comment" select="sc:editor-comment"/>
                        </xsl:call-template>
                    </xsl:for-each>


                </div>

            </body>






        </html>
    </xsl:template>

</xsl:stylesheet>