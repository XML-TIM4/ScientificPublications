<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:sc="https://github.com/XML-TIM4/ScientificPublications" version="2.0">
    <xsl:import href="Templates.xsl"/>
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="reviewPage">
                    <fo:region-body margin="0.75in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="reviewPage">
                <fo:flow flow-name="xsl-region-body">
                <fo:block>
                    <xsl:value-of select="sc:review/sc:review-metadata/sc:date"></xsl:value-of>
                </fo:block>
                <fo:block text-align="left" margin-top="10px" margin-bottom="10px">
                    <xsl:call-template name="TAuthor">
                        <xsl:with-param name="author" select="sc:review/sc:review-metadata/sc:reviewer"/>
                    </xsl:call-template>
                </fo:block>

                <fo:block text-align="left" margin-top="10px" margin-bottom="10px">
                    <fo:inline><xsl:value-of select="sc:review/sc:review-metadata/sc:recommendation"/></fo:inline>
                </fo:block>

                <fo:block text-align="center" margin-top="10px" margin-bottom="10px">

                    <xsl:for-each select="sc:review/sc:questionnaire/sc:question">
                        <xsl:call-template name="TQuestion"/>
                    </xsl:for-each>
                </fo:block>
                <fo:block text-align-last="center">
                    <fo:inline>Author comments</fo:inline>
                    <xsl:for-each select="sc:review/sc:author-comments">
                        <xsl:call-template name="TComment">
                            <xsl:with-param name="comment" select="sc:author-comment"/>
                        </xsl:call-template>
                    </xsl:for-each>
                </fo:block>
                <fo:block text-align-last="center">
                    <fo:inline>Editor comments</fo:inline>

                    <xsl:for-each select="sc:review/sc:editor-comments">
                        <xsl:call-template name="TComment">
                            <xsl:with-param name="comment" select="sc:editor-comment"/>
                        </xsl:call-template>
                    </xsl:for-each>

                </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

</xsl:stylesheet>