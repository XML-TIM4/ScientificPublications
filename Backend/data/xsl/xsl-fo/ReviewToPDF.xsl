<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:sc="https://github.com/XML-TIM4/ScientificPublications" version="2.0">
    <xsl:import href="Templates.xsl"/>
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="reviewPage">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="reviewPage">
                <fo:flow flow-name="xsl-region-body">
                <fo:block font-size="10px">
                    <xsl:value-of select="sc:review/sc:review-metadata/sc:date"></xsl:value-of>
                </fo:block>
                <fo:block font-size="10px" text-align="left" space-after="30px">
                    <xsl:call-template name="TAuthor">
                        <xsl:with-param name="author" select="sc:review/sc:review-metadata/sc:reviewer"/>
                    </xsl:call-template>
                </fo:block>

                <fo:block text-align="center" font-size="20px" space-after="25px">
                    <xsl:value-of select="sc:review/sc:review-metadata/sc:recommendation"/>
                </fo:block>

                <fo:block text-align="center" space-before="10px" space-after="45px">
                    <xsl:for-each select="sc:review/sc:questionnaire/sc:question">
                        <xsl:call-template name="TQuestion"/>
                    </xsl:for-each>
                </fo:block>
                <fo:block text-align-last="center" space-after="20px">
                    <fo:inline margin-bottom="10px">Author comments</fo:inline>
                    <xsl:for-each select="sc:review/sc:author-comments">
                        <xsl:call-template name="TComment">
                            <xsl:with-param name="comment" select="sc:author-comment"/>
                        </xsl:call-template>
                    </xsl:for-each>
                </fo:block>
                <fo:block text-align-last="center">
                    <fo:inline margin-bottom="10px">Editor comments</fo:inline>
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